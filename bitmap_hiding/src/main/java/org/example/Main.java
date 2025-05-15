package org.example;
import java.awt.image.BufferedImage;
import java.io.File;
import javax.imageio.ImageIO;

public class Main {
    public static void main(String[] args) throws Exception {
        // cover und hidden images aus "ressources" laden
        // cover ist Trägerbild, hidden das zu verbergende Bild
        BufferedImage coverImage = ImageIO.read(new File("bitmap_hiding/src/main/resources/cover.png"));
        BufferedImage hiddenImage = ImageIO.read(new File("bitmap_hiding/src/main/resources/hidden.png"));

        // hider auf cover und hidden anwenden
        BufferedImage stegoImage = Hider.hideImage(coverImage, hiddenImage);
        // gemergedes Bild "stego" erzeugen
        ImageIO.write(stegoImage, "png", new File("stego.png"));
        System.out.println("Bild erfolgreich versteckt und gespeichert als stego.png");

        // revealer auf stego image anwenden
        BufferedImage extractedImage = Revealer.revealImage(stegoImage);
        // extrahiertes Bild als extracted speichern
        ImageIO.write(extractedImage, "png", new File("extracted.png"));
        System.out.println("Verstecktes Bild extrahiert und gespeichert als extracted.png");
    }
}
