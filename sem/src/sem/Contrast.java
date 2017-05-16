/*
 * To change this license header, choose License Headers in Project Properties.
 * To change this template file, choose Tools | Templates
 * and open the template in the editor.
 */
package sem;

import java.awt.image.BufferedImage;
import java.awt.image.Raster;

/**
 *
 * @author PSharp
 */
public class Contrast {
    
    public BufferedImage changeContrast(BufferedImage image, float contrast_amt) {
        
        return null;
    }
    
    public BufferedImage autoAdjust(BufferedImage image) {
        Raster raster = image.getRaster();
        //byte[] bufferbyte = ((DataBufferByte)image.getRaster().getDataBuffer()).getData();
        /* int[] histogram = new int[65536]
        Raster raster = image.getRaster();
        final int w = image.getWidth();
        final int h = image.getHeight();
        double[] r = new double[w * h];
        r = raster.getSamples(0, 0, w, h, 0, r);
        */
        return null;
    }
}
