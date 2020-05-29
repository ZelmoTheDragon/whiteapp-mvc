package fr.moselleacademy.whiteapp.view;

import java.awt.geom.AffineTransform;
import java.awt.image.BufferedImage;
import java.io.ByteArrayOutputStream;
import java.io.IOException;
import java.util.Base64;
import javax.imageio.ImageIO;
import javax.servlet.http.Part;

/**
 * Classe utilitaire pour le conversion d'image.
 *
 * @author MOSELLE Maxime
 */
public final class ImageResize {

    /**
     * Constructeur interne.
     */
    private ImageResize() {
        // RAS
    }

    /**
     * 
     * @param file
     * @return 
     */
    public static String convertImageAsBase64(final Part file) {
        String picture;
        try (
                var stream = file.getInputStream();
                var output = new ByteArrayOutputStream()) {

            var image = ImageIO.read(stream);
            var scaledImage = new BufferedImage(128, 128, BufferedImage.TYPE_INT_ARGB);
            var g2d = scaledImage.createGraphics();
            double sx = (double) scaledImage.getWidth() / (double) image.getWidth();
            double sy = (double) scaledImage.getHeight() / (double) image.getHeight();
            var affineTransformation = AffineTransform.getScaleInstance(sx, sy);
            g2d.transform(affineTransformation);
            g2d.drawImage(
                    image,
                    0,
                    0,
                    null
            );

            g2d.dispose();

            ImageIO.write(scaledImage, "png", output);
            picture = Base64.getEncoder().encodeToString(output.toByteArray());

        } catch (IOException ex) {
            // TODO: Utiliser un logger
            ex.printStackTrace();
            picture = null;
        }

        return picture;
    }

}
