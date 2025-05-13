package org.example;
import java.awt.image.BufferedImage;

public class Revealer {
    public static BufferedImage revealImage(BufferedImage stego) {
        int width = stego.getWidth();
        int height = stego.getHeight();
        BufferedImage result = new BufferedImage(width, height, BufferedImage.TYPE_INT_RGB);

        for (int y = 0; y < height; y++) {
            for (int x = 0; x < width; x++) {
                int pixel = stego.getRGB(x, y);

                int rBit = (pixel >> 16) & 1;
                int gBit = (pixel >> 8) & 1;
                int bBit = pixel & 1;

                int r = rBit << 7;
                int g = gBit << 7;
                int b = bBit << 7;

                int extractedPixel = (r << 16) | (g << 8) | b;
                result.setRGB(x, y, extractedPixel);
            }
        }
        return result;
    }
}

