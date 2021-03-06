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
public class IncinerateForest extends Move {

    private Entity destroying;
    
    public IncinerateForest(Monster monster, Entity destroying) {
        super(monster, destroying.getLocationX(), destroying.getLocationY());
        this.destroying = destroying;
    }
    
    
    @Override
    public boolean isComplete(Monster monster, Game game) {
        boolean complete = super.isComplete(monster, game); //To change body of generated methods, choose Tools | Templates.
        if (complete) {
            game.removeFromWorld(destroying);
            if (monster.hasGoal(GoalType.INCINERATE_FOREST)) {
                monster.getGoal(GoalType.INCINERATE_FOREST).add(1);
            }
            game.addPoints(100);
        }
        return complete;
    }
}
