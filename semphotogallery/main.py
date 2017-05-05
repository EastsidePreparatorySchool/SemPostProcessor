import webapp2
import base64
import copy
import json
import jinja2
import os
import string
import logging
import datetime
import random
from google.appengine.ext import ndb
from google.appengine.api import images
import cgi
import urllib
from google.appengine.ext import blobstore
from google.appengine.ext.webapp import blobstore_handlers

JINJA_ENVIRONMENT = jinja2.Environment(
    loader=jinja2.FileSystemLoader(os.path.dirname(__file__)),
    extensions=['jinja2.ext.autoescape'],
    autoescape=True)

# The NDB model used for storing photos
class Photo(ndb.Model):
    date_taken = ndb.DateTimeProperty(auto_now_add=True)
    last_updated = ndb.DateTimeProperty(auto_now_add=True)
    magnification = ndb.IntegerProperty(required=False)
    voltage = ndb.IntegerProperty(required=False)
    notes = ndb.StringProperty()
    specimen = ndb.StringProperty(required=False)
    scale = ndb.FloatProperty()
    photokeys = ndb.BlobKeyProperty(required=False, repeated=True) # Stored chronologically, first entry is oldest
    icon = ndb.BlobProperty()

class BaseHandler(webapp2.RequestHandler):
    def getDBObj(self, str_id, cache=False):
        db_obj = Photo.get_by_id(int(str_id), use_cache=cache, use_memcache=cache)
        if not db_obj:
            self.error(404)
        return db_obj

class MainHandler(webapp2.RequestHandler):
    def get(self):
        template = JINJA_ENVIRONMENT.get_template('html/index.html')
        template_values = {}
        self.response.out.write(template.render(template_values))

class LatestHandler(webapp2.RequestHandler):
    def get(self):
        query = Photo.query().order(-Photo.date)
        url = "/image/"
        for obj in query:
            url += str(obj.key.id())
            break

        template = JINJA_ENVIRONMENT.get_template('html/latest.html')
        template_values = { \
        'photourl': url, \
        }
        self.response.out.write(template.render(template_values))

class PhotoUploader(blobstore_handlers.BlobstoreUploadHandler, BaseHandler):

    def get(self):
        template = JINJA_ENVIRONMENT.get_template('html/upload.html')
        template_values = { \
        'uploadurl': blobstore.create_upload_url('/upload'), \
        }
        self.response.out.write(template.render(template_values))

    def post(self):
        logging.info("Recieved an upload")
        upload = self.get_uploads()[0]

        if not self.request.get('modified_from'):
            logging.info("Created a new entry in DB")
            photo_obj = Photo(photokeys=[upload.key()])
            photo_obj.notes = self.request.get('notes')
        else:
            logging.info("Updated entry in DB")
            photo_obj = self.getDBObj(self.request.get('modified_from'))

            if self.request.get('copy'):
                photo_obj = self.clone_entity(self.getDBObj(self.request.get('modified_from')))

            photo_obj.photokeys.append(upload.key())
            photo_obj.last_updated = datetime.datetime.now()

        if bool(self.request.get('update_metadata')):
            logging.info("Metadata updated")
            photo_obj.populate(
                magnification=int(self.request.get('magnification')),
                voltage=int(self.request.get('voltage')),
                scale=int(self.request.get('scale')),
                specimen=self.request.get('specimen'))

        logging.info("Photo data recieved: " + str(upload.key()))
        photo_obj.put()
        self.response.write(str(upload.key()))

    def clone_entity(self, e):
        # Code from http://bit.ly/1o64d4a
        klass = e.__class__
        props = dict((v._code_name, v.__get__(e, klass)) for v in klass._properties.itervalues() if type(v) is not ndb.ComputedProperty)
        return klass(**props)

class RetrieveMetadataHandler(BaseHandler):
    def get(self, str_id):
        obj = self.getDBObj(str_id)

        dump = {}

        props = ['magnification', 'voltage', 'notes', 'specimen', 'scale']
        for prop in props:
            dump[prop] = getattr(obj, prop)

        dts = ['date_taken', 'last_updated']
        for d in dts:
            try:
                dump[prop] = getattr(obj, prop).isoformat()
            except AttributeError:
                dump[prop] = None

        self.response.write(json.dumps(dump))

class APIURLRetrieveHandler(webapp2.RequestHandler):
    def get(self):
        self.response.write(blobstore.create_upload_url('/upload'))

class ImageHandler(blobstore_handlers.BlobstoreDownloadHandler):
    def get(self, str_id):
        logging.info("String ID is " + str_id)
        obj = self.getDBObj(str_id)

        if not obj:
            return

        index = -1

        if self.request.get("revision_num"):
            index = int(self.request.get("revision_num"))
            logging.info("Revision num specified, it was " + str(index))
            logging.info("The length of photokeys is " + str(len(obj.photokeys)))
            if index > len(obj.photokeys):
                logging.info("Opps! Your index was too long")
                self.error(400)
                return

        if not blobstore.get(obj.photokeys[index]):
            self.error(404)
        else:
            self.send_blob(obj.photokeys[index])

    def getDBObj(self, str_id, cache=False):
        db_obj = Photo.get_by_id(int(str_id), use_cache=cache, use_memcache=cache)
        if not db_obj:
            self.error(404)
        return db_obj

class AdjustableComponentHandler(BaseHandler):
    def get(self, file):    
        if file == "animated-grid.html":
            items_to_preload = 10
            arr = []

            query = Photo.query().order(-Photo.date)

            for obj in query:
                if len(arr) >= items_to_preload:
                    break
                arr.append({"specimen": obj.specimen, "magnification": obj.magnification, "photourl": "/image/" + str(obj.key.id()), "color": "red"})

            logging.info(json.dumps(arr))
            template = JINJA_ENVIRONMENT.get_template('html/animated-grid.html')
            template_values = { \
            'latestdata': json.dumps(arr), \
            }
            self.response.out.write(template.render(template_values))


app = webapp2.WSGIApplication([
    ('/', MainHandler),
    ('/latest', LatestHandler),
    ('/upload', PhotoUploader),
    ('/image/([\w\-=]+)', ImageHandler),
    ('/component/([\w\-.=]+)', AdjustableComponentHandler),
    ('/geturl', APIURLRetrieveHandler),
    ('/metadata/([\w\-.=]+)', RetrieveMetadataHandler)
], debug=True)
