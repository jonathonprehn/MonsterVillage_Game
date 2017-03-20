/*
 * To change this license header, choose License Headers in Project Properties.
 * To change this template file, choose Tools | Templates
 * and open the template in the editor.
 */
package horsentp.ld35;

import java.awt.image.BufferedImage;

/**
 *
 * @author Jonathon
 */
public class DirectionalImage {
    
    private BufferedImage left;
    private BufferedImage right;
    private BufferedImage current;
    
    public DirectionalImage(BufferedImage left, BufferedImage right) {
        this.left = left;
        this.right = right;
        current = right;
    }

    public BufferedImage getCurrentSprite() {
        return current;
    }
    
    public int getCurrentSpriteWidth() {
        return current.getWidth();
    }
    
    public int getCurrentSpriteHeight() {
        return current.getHeight();
    }
    
    public void update(float velX) {
        if (velX < 0) {
            current = left;
        } else if (velX > 0) {
            current = right;
        }
    }
}
