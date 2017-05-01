/*
 * To change this license header, choose License Headers in Project Properties.
 * To change this template file, choose Tools | Templates
 * and open the template in the editor.
 */
package fileupload;

import java.io.BufferedReader;
import java.io.File;
import java.io.FileNotFoundException;
import java.io.IOException;
import java.io.InputStreamReader;
import java.net.MalformedURLException;
import java.net.URL;
import org.apache.commons.httpclient.HttpClient;
import org.apache.commons.httpclient.methods.PostMethod;
import org.apache.commons.httpclient.methods.multipart.MultipartRequestEntity;
import org.apache.commons.httpclient.methods.multipart.*;
import org.apache.commons.logging.LogFactory;
import org.apache.commons.codec.DecoderException;


public class FileUpload {
    final static String LOCAL_PATH = "http://localhost:13080/";
    final static String DEPOLYED_PATH = "http://semphotogallery.appspot.com/";
    
    final static boolean IS_LOCAL = true;
    
    public static void uploadFileToServer(Part[] parts) throws FileNotFoundException, IOException, MalformedURLException {
        String stem = DEPOLYED_PATH;
        if (IS_LOCAL) {
            stem = LOCAL_PATH;
        }

        String uploadUrl = getUploadURL(stem + "geturl");
        uploadImage(uploadUrl, parts);
    }
    
    public static void uploadImage(String urlString, Part[] parts) throws FileNotFoundException, IOException {
        PostMethod postMessage = new PostMethod(urlString);
        postMessage.setRequestEntity(new MultipartRequestEntity(parts, postMessage.getParams()));
        HttpClient client = new HttpClient();
        
        int status = client.executeMethod(postMessage);
        System.out.println("Got status message " + status);
    }
    public static String getUploadURL(String reqURL) throws MalformedURLException, IOException {
        
        URL url = new URL(reqURL);
        BufferedReader in = new BufferedReader(
        new InputStreamReader(url.openStream()));

        return in.readLine(); // Will only ever return one line, so we don't need a loop
    }
    public static void main(String args[]) throws FileNotFoundException, IOException {
        
        File f = new File("channel_0.png");
        Part[] parts = {
                new StringPart("notes", "This is not a real photo, only a test"),
                new FilePart("img", f)
        };
        uploadFileToServer(parts);
    }
}