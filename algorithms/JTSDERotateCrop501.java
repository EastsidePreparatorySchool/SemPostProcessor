/*
 * To change this license header, choose License Headers in Project Properties.
 * To change this template file, choose Tools | Templates
 * and open the template in the editor.
 */
package jtsderotatecrop;


import java.awt.image.BufferedImage;
import java.awt.image.RenderedImage;
import java.io.File;
import java.io.IOException;
import javax.imageio.ImageIO;
import javaxt.io.Image;

/**
 *
 * @author jtseng
 * script which calls function 
 */
public class JTSDERotateCrop {
    /**
     * @param args the command line arguments
     */
    public static void main(String[] args) throws IOException {
        // TODO code application logic here
    System.out.println(new File(".").getAbsolutePath());

test1("image.jpg");
        
    }
    
    public static Image test1  (String filepath) throws IOException {
        javaxt.io.Image image;
        image = new javaxt.io.Image(filepath);
        System.out.println(image.getWidth() + "x" + image.getHeight()); // prints width and height of image
        
        
image.rotate(0.0);        
      image.crop(00, 0, image.getWidth(), image.getHeight());

       image.saveAs("/saved.jpg");
       BufferedImage bufimage = image.getBufferedImage();
        savefile(image.getRenderedImage(), "jpg");
        return null;
    }
    
   // public static void cropandrotate (String ) {
        
    //}
    
    public static void savefile (RenderedImage image, String ext) throws IOException {
       //BufferedImage image = ImageIO.read(new File (filepath));
      
       String savedimage = "savedimage";
       File file = new File(savedimage + "." + ext);
       
       try {
       ImageIO.write(image, ext, file);
       } catch (IOException e) {
           System.out.println("saving file error" + e.getMessage());
       } 
    }
    
    //trim(int r, int g, int b)
}
