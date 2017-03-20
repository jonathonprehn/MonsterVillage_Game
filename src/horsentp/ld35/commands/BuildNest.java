/*
 * To change this license header, choose License Headers in Project Properties.
 * To change this template file, choose Tools | Templates
 * and open the template in the editor.
 */
package horsentp.ld35.commands;

import horsentp.ld35.Entity;
import horsentp.ld35.Game;
import horsentp.ld35.GoalType;
import horsentp.ld35.Monster;

/**
 *
 * @author Jonathon
 */
public class BuildNest extends Move {

    public BuildNest(Monster monster, float x, float y) {
        super(monster, x, y);
    }
    
    
    @Override
    public boolean isComplete(Monster monster, Game game) {
        boolean complete = super.isComplete(monster, game); //To change body of generated methods, choose Tools | Templates.
        if (complete) {
            Entity e = Entity.createIncubator();
            e.setX(x+50);
            e.setY(y-10);
            game.addToWorld(e);
            if (monster.hasGoal(GoalType.BUILD_NESTS)) {
                monster.getGoal(GoalType.BUILD_NESTS).add(1);
            }
            game.addPoints(50);
        }
        return complete;
    }
}
