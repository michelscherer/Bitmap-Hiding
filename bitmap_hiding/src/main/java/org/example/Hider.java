package org.example;
import java.awt.image.BufferedImage;

public class Hider {
    public static BufferedImage hideImage(BufferedImage cover, BufferedImage hidden) {
        // Neues Bild von gleicher Auflösung generieren
        int width = cover.getWidth();
        int height = cover.getHeight();
        BufferedImage result = new BufferedImage(width, height, BufferedImage.TYPE_INT_RGB);

        // Zeile für Zeile in Bild wird durchlaufen
        for (int y = 0; y < height; y++) {
            for (int x = 0; x < width; x++) {
                // RGB Werte des aktuellen Pixels
                int coverPixel = cover.getRGB(x, y);
                if (x == 0 && y == 0) {
                    System.out.println("coverPixel: " + Integer.toBinaryString(coverPixel));
                }
                int hiddenPixel = hidden.getRGB(x, y);
                if (x == 0 && y == 0) {
                    System.out.println("hiddenPixel: " + Integer.toBinaryString(hiddenPixel));
                }

                // [ 31 - 24 ] [ 23 - 16 ] [ 15 - 8 ] [ 7 - 0 ]
                // |    A     |     R     |     G    |    B    |
                int rCover = (coverPixel >> 16) & 0xFF;
                int gCover = (coverPixel >> 8) & 0xFF;
                int bCover = coverPixel & 0xFF;

                // [ 31 - 24 ] [ 23 - 16 ] [ 15 - 8 ] [ 7 - 0 ]
                // |    A     |     R     |     G    |    B    |
                // MSB wird extrahiert, entweder 0 oder 1
                int rHiddenBit = ((hiddenPixel >> 16) & 0xFF) >> 7;
                int gHiddenBit = ((hiddenPixel >> 8) & 0xFF) >> 7;
                int bHiddenBit = (hiddenPixel & 0xFF) >> 7;

                // Mit 0xFE behalten wir nur die ersten 7 Bits
                // Anschliessend wird das MSB angehängt
                rCover = (rCover & 0xFE) | rHiddenBit;
                gCover = (gCover & 0xFE) | gHiddenBit;
                bCover = (bCover & 0xFE) | bHiddenBit;

                // Pixel wird zusammengesetzt
                int mergedPixel = (rCover << 16) | (gCover << 8) | bCover;
                result.setRGB(x, y, mergedPixel);
                if (x == 0 && y == 0) {
                    System.out.println("mergedPixel: " + Integer.toBinaryString(mergedPixel));
                }
            }
        }
        return result;
    }
}

