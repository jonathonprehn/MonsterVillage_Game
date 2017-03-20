/*
 * To change this license header, choose License Headers in Project Properties.
 * To change this template file, choose Tools | Templates
 * and open the template in the editor.
 */
package horsentp.ld35;

/**
 *
 * @author Jonathon
 */
public interface Clickable {
    public boolean clickedOn(Game game, float x, float y, float camX, float camY);
    public void onClick(Game game, float mouseX, float mouseY, float camX, float camY);
}
