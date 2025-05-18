package org.example;
import java.awt.image.BufferedImage;

public class Revealer {
    public static BufferedImage revealImage(BufferedImage stego) {
        // result image ist hier das extracted image
        int width = stego.getWidth();
        int height = stego.getHeight();
        BufferedImage result = new BufferedImage(width, height, BufferedImage.TYPE_INT_RGB);

        // pixel für pixel von stego.png durchgehen
        for (int y = 0; y < height; y++) {
            for (int x = 0; x < width; x++) {
                int pixel = stego.getRGB(x, y);

                // holt das versteckte MSB vom hidden image (in stego.png ist das natürlich das LSB)
                int rBit = (pixel >> 16) & 1;
                int gBit = (pixel >> 8) & 1;
                int bBit = pixel & 1;

                // shiftet das extrahierte Bit wieder hoch sodass es wieder das MSB wird
                int r = rBit << 7;
                int g = gBit << 7;
                int b = bBit << 7;

                // fügt das Pixel vom hidden image wieder zusammen, Qualität geht verloren, weil Bits beim Extrahieren verloren gehen
                int extractedPixel = (r << 16) | (g << 8) | b;
                result.setRGB(x, y, extractedPixel);
            }
        }
        return result;
    }
}

