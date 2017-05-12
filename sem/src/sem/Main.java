/*
 * To change this license header, choose License Headers in Project Properties.
 * To change this template file, choose Tools | Templates
 * and open the template in the editor.
 */
package sem;

import java.awt.image.BufferedImage;
import java.io.File;
import java.io.IOException;
import javax.imageio.ImageIO;

/**
 *
 * @author PSharp
 */

public class Main {
    
    public static void main(String[] args) {
        Main main = new Main();
        Brightness bright = new Brightness();
        BufferedImage img = main.loadImage("C:\\Users\\psharp\\Documents\\test_image.png");
        BufferedImage img2 = bright.changeBrightness(img, 40);
        try {
            ImageIO.write(img2, "png", new File("C:\\Users\\psharp\\Documents\\test_image_2.png"));
        } catch (IOException e) {
            System.out.println(e);
            System.out.println("file failed to save");
        }
    }
    
    // this is also test code.
    private BufferedImage loadImage(String filename) {
        BufferedImage image;
        try {
            image = ImageIO.read(new File(filename));
            return image;
        } catch (IOException e) {
            System.out.println(e);
            System.out.println("failed to load image.");
        }
        return null;
    }
}
