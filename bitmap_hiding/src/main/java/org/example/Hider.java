package org.example;
import java.awt.image.BufferedImage;

public class Hider {
    public static BufferedImage hideImage(BufferedImage cover, BufferedImage hidden) {
        int width = cover.getWidth();
        int height = cover.getHeight();
        BufferedImage result = new BufferedImage(width, height, BufferedImage.TYPE_INT_RGB);

        for (int y = 0; y < height; y++) {
            for (int x = 0; x < width; x++) {
                int coverPixel = cover.getRGB(x, y);
                int hiddenPixel = hidden.getRGB(x, y);

                int rCover = (coverPixel >> 16) & 0xFF;
                int gCover = (coverPixel >> 8) & 0xFF;
                int bCover = coverPixel & 0xFF;

                int rHiddenBit = ((hiddenPixel >> 16) & 0xFF) >> 7;
                int gHiddenBit = ((hiddenPixel >> 8) & 0xFF) >> 7;
                int bHiddenBit = (hiddenPixel & 0xFF) >> 7;

                rCover = (rCover & 0xFE) | rHiddenBit;
                gCover = (gCover & 0xFE) | gHiddenBit;
                bCover = (bCover & 0xFE) | bHiddenBit;

                int mergedPixel = (rCover << 16) | (gCover << 8) | bCover;
                result.setRGB(x, y, mergedPixel);
            }
        }
        return result;
    }
}

