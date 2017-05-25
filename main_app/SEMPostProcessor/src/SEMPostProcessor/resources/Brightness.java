
package SEMPostProcessor.resources;

import java.awt.image.BufferedImage;
import java.awt.image.RescaleOp;
import java.io.File;
import java.io.IOException;
import javax.imageio.ImageIO;

public class Brightness {
    
    // Pre:  image must be greyscale, throws IllegalArgumentException otherwise
    // Post: returns image with all pixels Brightness values incremented by bright_amt
public static BufferedImage changeBrightness(BufferedImage image, int bright_amt) throws IllegalArgumentException {
//        if (image.getType() !=  BufferedImage.TYPE_BYTE_GRAY) { // checks if image is grayscale
//            throw new IllegalArgumentException();
//        }
        RescaleOp bright_ro = new RescaleOp(1f, (float) bright_amt, null);
        return bright_ro.filter(image,null);
    }
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
