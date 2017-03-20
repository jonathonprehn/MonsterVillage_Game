/*
 * To change this license header, choose License Headers in Project Properties.
 * To change this template file, choose Tools | Templates
 * and open the template in the editor.
 */
package horsentp.ld35.commands;

import horsentp.ld35.Entity;
import horsentp.ld35.Game;
import horsentp.ld35.Goal;
import horsentp.ld35.GoalType;
import horsentp.ld35.Monster;

/**
 *
 * @author Jonathon
 */
public class PlantFarm extends Move {

    public PlantFarm(Monster monster, float x, float y) {
        super(monster, x, y);
    }
    
    @Override
    public boolean isComplete(Monster monster, Game game) {
        boolean complete = super.isComplete(monster, game); //To change body of generated methods, choose Tools | Templates.
        if (complete) {
            Entity e = Entity.createFarm();
            e.setX(x+50);
            e.setY(y-10);
            game.addToWorld(e);
            if (monster.hasGoal(GoalType.PLANT_FARMS)) {
                monster.getGoal(GoalType.PLANT_FARMS).add(1);
            }
            if (game.isNearTown(e)) {
                game.addPoints(125);
            }
        }
        return complete;
    }
}
