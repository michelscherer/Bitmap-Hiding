package org.example;
import java.awt.image.BufferedImage;
import java.io.File;
import javax.imageio.ImageIO;

public class Main {
    public static void main(String[] args) throws Exception {
        // Bilder laden
        BufferedImage coverImage = ImageIO.read(new File("bitmap_hiding/src/main/resources/cover.png"));
        BufferedImage hiddenImage = ImageIO.read(new File("bitmap_hiding/src/main/resources/hidden.png"));

        // Hiden
        BufferedImage stegoImage = Hider.hideImage(coverImage, hiddenImage);
        ImageIO.write(stegoImage, "png", new File("stego.png"));
        System.out.println("Bild erfolgreich versteckt und gespeichert als stego.png");

        // Revealen
        BufferedImage extractedImage = Revealer.revealImage(stegoImage);
        ImageIO.write(extractedImage, "png", new File("extracted.png"));
        System.out.println("Verstecktes Bild extrahiert und gespeichert als extracted.png");
    }
}
