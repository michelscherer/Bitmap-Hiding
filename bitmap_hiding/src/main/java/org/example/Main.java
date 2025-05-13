package org.example;
import java.awt.image.BufferedImage;
import java.io.File;
import javax.imageio.ImageIO;

public class Main {
    public static void main(String[] args) throws Exception {
        BufferedImage coverImage = ImageIO.read(new File("cover.png"));
        BufferedImage hiddenImage = ImageIO.read(new File("hidden.png"));

        BufferedImage stegoImage = Hider.hideImage(coverImage, hiddenImage);
        ImageIO.write(stegoImage, "png", new File("stego.png"));
    }
}