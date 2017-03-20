/*
 * To change this license header, choose License Headers in Project Properties.
 * To change this template file, choose Tools | Templates
 * and open the template in the editor.
 */
package horsentp.ld35;

import java.awt.image.BufferedImage;
import java.util.HashMap;
import javax.imageio.ImageIO;

/**
 *
 * @author Jonathon
 */
public class ImageBank {

    private static HashMap<String, BufferedImage> images = new HashMap<String, BufferedImage>();

    public static BufferedImage getImage(String path) {
        BufferedImage image = images.get(path);
        if (image == null) {
            try {
                image = (BufferedImage) ImageIO.read(LD35.class.getResourceAsStream("/" + path));
                images.put(path, image);
            } catch (Exception e) {
                System.out.println("Can't load image on path " + path + ":\n" + e);
                image = null;
            }
        }
        return image;
    }
}
