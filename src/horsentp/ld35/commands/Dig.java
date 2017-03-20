/*
 * To change this license header, choose License Headers in Project Properties.
 * To change this template file, choose Tools | Templates
 * and open the template in the editor.
 */
package horsentp.ld35.commands;

import horsentp.ld35.Command;
import horsentp.ld35.Entity;
import horsentp.ld35.Game;
import horsentp.ld35.GoalType;
import horsentp.ld35.Monster;

/**
 *
 * @author Jonathon
 */
public class Dig extends Move {

    private int there;
    private int waitTime = 5000;
    private Entity mine;
    
    public Dig(Monster monster, Entity mine) {
        super(monster, mine.getLocationX(), mine.getLocationY());
        this.mine = mine;
    }

    @Override
    public void update(Monster monster, Game game) {
        super.update(monster, game); //To change body of generated methods, choose Tools | Templates.
        if (monster.isPoof()) {
            there += 30;
        }
    }
    
    @Override
    public boolean isComplete(Monster monster, Game game) {
        boolean complete = super.isComplete(monster, game); //To change body of generated methods, choose Tools | Templates.
        if (complete) {
            monster.setPoof(true);
        }
        if (monster.isPoof() && there > waitTime) {
            monster.setPoof(false);
            monster.setX(mine.getLocationX()-50);
            monster.setY(mine.getLocationY()+10);
            //Random output
            
            int ran = (int)(Math.random()*6);
            if (ran == 0 || ran == 1) {
                int amount = (int)(Math.random()*10) + 5;
                game.mineOutput(0, amount, null);
                if (monster.hasGoal(GoalType.MINE_METAL)) {
                    monster.getGoal(GoalType.MINE_METAL).add(amount);
                }
            } else if (ran == 2 || ran == 3) {
                int amount = (int)(Math.random()*5) + 2;
                game.mineOutput(amount, 0, null);
                if (monster.hasGoal(GoalType.MINE_GEMS)) {
                    monster.getGoal(GoalType.MINE_GEMS).add(amount);
                }
            } else if (ran == 4 || ran == 5) {
                Entity egg = Entity.createEgg();
                egg.setX(mine.getLocationX() + (float)(Math.random()*150)-75 );
                egg.setY(mine.getLocationY() + (float)(Math.random()*50) + 20);
                game.mineOutput(0, 0, egg);
                if (monster.hasGoal(GoalType.FIND_EGGS)) {
                    monster.getGoal(GoalType.FIND_EGGS).add(1);
                }
            }
            return true;
        }
        return false;
    }

   
}
