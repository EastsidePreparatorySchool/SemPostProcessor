
package sem;

import java.awt.image.BufferedImage;
import java.awt.image.RescaleOp;

public class Brightness {
    
    // Pre:  image must be greyscale, throws IllegalArgumentException otherwise
    // Post: returns image with all pixels Brightness values incremented by bright_amt
    public BufferedImage changeBrightness(BufferedImage image, int bright_amt) throws IllegalArgumentException {
        if (image.getType() !=  BufferedImage.TYPE_BYTE_GRAY) { // checks if image is grayscale
            throw new IllegalArgumentException();
        }
        RescaleOp bright_ro = new RescaleOp(1f, (float) bright_amt, null);
        return bright_ro.filter(image,null);
    }
}
