/*
 * To change this license header, choose License Headers in Project Properties.
 * To change this template file, choose Tools | Templates
 * and open the template in the editor.
 */
package horsentp.ld35.commands;

import horsentp.ld35.Entity;
import horsentp.ld35.Game;
import horsentp.ld35.Monster;

/**
 *
 * @author Jonathon
 */
public class KillMonster extends Move {

    private Entity target;
    
    public KillMonster(Monster monster, Entity target) {
        super(monster, target.getLocationX(), target.getLocationY());
        this.target = target;
    }

    @Override
    public void update(Monster monster, Game game) {
        x = target.getLocationX();
        y = target.getLocationY();
        velX = x-monster.getLocationX();
        velY = y-monster.getLocationY();
        float mag = (float)Math.sqrt((velX*velX) + (velY*velY));
        velX = velX*3/mag;
        velY = velY*3/mag;
        super.update(monster, game);
    }

    @Override
    public boolean isComplete(Monster monster, Game game) {
        boolean complete =  super.isComplete(monster, game); //To change body of generated methods, choose Tools | Templates.
    
        if (complete) {
            game.removeFromWorld(target);
            game.addPoints(1000);
        }
    
        return complete;
    }
    
    
    
}
