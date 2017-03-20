/*
 * To change this license header, choose License Headers in Project Properties.
 * To change this template file, choose Tools | Templates
 * and open the template in the editor.
 */
package horsentp.ld35;

import java.awt.Graphics;

/**
 *
 * @author Jonathon
 */
public interface Renderable {
    public void render(Game game, Renderer renderer, float camX, float camY);
}
