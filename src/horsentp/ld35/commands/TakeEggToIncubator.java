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
public class TakeEggToIncubator extends Move {

    private Entity egg;
    
    public TakeEggToIncubator(Monster monster, Entity egg) {
        super(monster, egg.getLocationX(), egg.getLocationY());
        this.egg = egg;
    }

    @Override
    public boolean isComplete(Monster monster, Game game) {
       boolean complete = super.isComplete(monster, game); //To change body of generated methods, choose Tools | Templates.
    
       if (complete) {
           game.removeFromWorld(egg);
           if (monster.hasGoal(GoalType.INCUBATE_EGGS)) {
               monster.getGoal(GoalType.INCUBATE_EGGS).add(1);
           }
           Entity incubator = game.getRandomIncubator();
           
           //Place a random monster near a random incubator
           int ranMonster = (int)(Math.random()*6);
           Entity adding = null;
           float xLoc = incubator.getLocationX() - 50;
           float yLoc = incubator.getLocationY() + 10;
           switch(ranMonster) {
               case 0: 
                   adding = game.getFactory().createBuildingMonster(xLoc, yLoc);
                   break;
                case 1: 
                   adding = game.getFactory().createMiningMonster(xLoc, yLoc);
                   break;
                case 2: 
                   adding = game.getFactory().createNurseMonster(xLoc, yLoc);
                   break;
                case 3: 
                   adding = game.getFactory().createGrowerMonster(xLoc, yLoc);
                   break;
                case 4: 
                   adding = game.getFactory().createRageMonster(xLoc, yLoc);
                   break;
                case 5: 
                   adding = game.getFactory().createEvilMonster(xLoc, yLoc);
                   break;
           }
           game.addPoints(500);
           game.addToWorld(adding);
       }
       
       return complete;
    }
    
    
}
