/*
 * To change this license header, choose License Headers in Project Properties.
 * To change this template file, choose Tools | Templates
 * and open the template in the editor.
 */
package horsentp.ld35.commands;

import horsentp.ld35.ButtonListener;
import horsentp.ld35.Command;
import horsentp.ld35.CommandType;
import horsentp.ld35.Game;
import horsentp.ld35.GuiButton;
import horsentp.ld35.GuiCoordinateSystem;
import horsentp.ld35.ImageBank;
import horsentp.ld35.Monster;

/**
 *
 * @author Jonathon
 */
public class Move implements Command {

    protected float x;
    protected float y;
    protected float velX;
    protected float velY;
    
    public Move(Monster monster, float x, float y) {
        this.x = x;
        this.y = y;
        velX = x-monster.getLocationX();
        velY = y-monster.getLocationY();
        float mag = (float)Math.sqrt((velX*velX) + (velY*velY));
        velX = velX*3/mag;
        velY = velY*3/mag;
    }


    @Override
    public void update(Monster monster, Game game) {
        monster.move(velX, velY);
        monster.getDirectionalImage().update(velX);
    }
    
    private float abs(float x) {
        if (x < 0) {
            return -x;
        } else {
            return x;
        }
    }

    @Override
    public boolean isComplete(Monster monster, Game game) {
        float dx = abs(monster.getLocationX()-x);
        float dy = abs(monster.getLocationY()-y);
        return dx < 3 && dy < 3;
    }
}
