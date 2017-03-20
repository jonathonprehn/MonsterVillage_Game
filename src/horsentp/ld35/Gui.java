/*
 * To change this license header, choose License Headers in Project Properties.
 * To change this template file, choose Tools | Templates
 * and open the template in the editor.
 */
package horsentp.ld35;

import java.awt.Graphics;
import java.awt.image.BufferedImage;

/**
 *
 * @author Jonathon
 */
public abstract class Gui implements GamePiece {
    
    protected GuiCoordinateSystem coordinateSystem;
    protected float x;
    protected float y;
    //This is so that the depth value can be dynamically selected.
    protected int depthValue;
    protected BufferedImage background;
    
    public Gui(float x, float y, BufferedImage background, GuiCoordinateSystem system) {
        this.x=x;
        this.y=y;
        this.background=background;
        this.coordinateSystem=system;
        if (coordinateSystem == GuiCoordinateSystem.WORLD) {
            depthValue = Game.WORLD_GUI_DEPTH;
        } else if (coordinateSystem == GuiCoordinateSystem.SCREEN) {
            depthValue = Game.SCREEN_GUI_DEPTH;
        }
    }

    @Override
    public void render(Game game, Renderer renderer, float camX, float camY) {
        if (coordinateSystem == GuiCoordinateSystem.WORLD) {
            renderWorldCoordinates(game, renderer, camX, camY);
        } else if (coordinateSystem == GuiCoordinateSystem.SCREEN) {
            renderScreenCoordinates(game, renderer, camX, camY);
        }
    }

    @Override
    public boolean clickedOn(Game game, float x, float y, float camX, float camY) {
        if (coordinateSystem == GuiCoordinateSystem.SCREEN) {
            return this.x < x && this.x+background.getWidth() > x &&
                    this.y < y && this.y+background.getHeight() > y;
        } else if (coordinateSystem == GuiCoordinateSystem.WORLD) {
            return this.x < (x+camX) && this.x+background.getWidth() > (x+camX) &&
                    this.y < (y+camY) && this.y+background.getHeight() > (y+camY);
        }
        return false;
    }
    
    public abstract void renderWorldCoordinates(Game game, Renderer renderer, float camX, float camY);
    
    public abstract void renderScreenCoordinates(Game game, Renderer renderer, float camX, float camY);

    public void setBackground(BufferedImage background) {
        this.background = background;
    }

    public BufferedImage getBackground() {
        return background;
    }

    public void setDepthValue(int depthValue) {
        this.depthValue = depthValue;
    }

    public int getDepthValue() {
        return depthValue;
    }

    public void setX(float x) {
        this.x = x;
    }

    public void setY(float y) {
        this.y = y;
    }

    public float getX() {
        return x;
    }

    public float getY() {
        return y;
    }
    
    
}
