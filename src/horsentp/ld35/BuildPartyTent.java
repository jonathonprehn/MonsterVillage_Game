/*
 * To change this license header, choose License Headers in Project Properties.
 * To change this template file, choose Tools | Templates
 * and open the template in the editor.
 */
package horsentp.ld35;

import horsentp.ld35.commands.Move;

/**
 *
 * @author Jonathon
 */
public class BuildPartyTent extends Move {

    public BuildPartyTent(Monster monster, float x, float y) {
        super(monster, x, y);
    }
    
    @Override
    public boolean isComplete(Monster monster, Game game) {
        boolean complete = super.isComplete(monster, game); //To change body of generated methods, choose Tools | Templates.
        if (complete) {
            Entity e = Entity.createPartyTent();
            e.setX(x+50);
            e.setY(y-10);
            game.addToWorld(e);
            if (monster.hasGoal(GoalType.BUILD_PARTY_TENTS)) {
                monster.getGoal(GoalType.BUILD_PARTY_TENTS).add(1);
            }
            game.addPoints(55);
        }
        return complete;
    }
}
