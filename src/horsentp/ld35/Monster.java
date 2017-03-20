/*
 * To change this license header, choose License Headers in Project Properties.
 * To change this template file, choose Tools | Templates
 * and open the template in the editor.
 */
package horsentp.ld35;

import horsentp.ld35.commands.BreakEarth;
import horsentp.ld35.commands.BuildNest;
import horsentp.ld35.commands.BuildTown;
import horsentp.ld35.commands.Dig;
import horsentp.ld35.commands.IncinerateForest;
import horsentp.ld35.commands.IncinerateTown;
import horsentp.ld35.commands.KillMonster;
import horsentp.ld35.commands.Move;
import horsentp.ld35.commands.PlantFarm;
import horsentp.ld35.commands.PlantForest;
import horsentp.ld35.commands.TakeEggToIncubator;
import java.util.ArrayList;

/**
 *
 * @author Jonathon
 */
public class Monster extends Entity {

    private Goal[] goals;
    private final MonsterType type;
    private ArrayList<DirectionalImage> formsStack;

    private Command current;

    public Monster(MonsterType type) {
        this.type = type;
    }
    
    
    @Override
    protected void specializedUpdate(Game game, int millis) {
        for (int i=0; i<goals.length; i++) {
            if (goals[i] != null) {
                if (!goals[i].isComplete() && goals[i].checkGoalComplete(this, game)) {
                    goals[i].complete();
                    //Change forms to be cooler
                    if (!formsStack.isEmpty()) {
                        setDirectionalImage(formsStack.get(0));
                        formsStack.remove(0);
                    }
                }
            }
        }
        if (current != null) {
            current.update(this, game);
            if (current.isComplete(this, game)) {
                current = null;
            }
        }
    }

    @Override
    public void onClick(Game game, float mouseX, float mouseY, float camX, float camY) {
        
    }

    public void setFormsStack(ArrayList<DirectionalImage> formsStack) {
        this.formsStack = formsStack;
    }

    public MonsterType getType() {
        return type;
    }
    
    public void setCurrentCommand(Game game, CommandType type, Entity performingOn, float mouseX, float mouseY, float camX, float camY) {
        if (current != null) {
            current = null;
        }
        switch(type) {
            case MOVE:
                current = new Move(this, game.getStoredMouseX(), game.getStoredMouseY());
                break;
            case GROW_FARM:
                current = new PlantFarm(this, game.getStoredMouseX(), game.getStoredMouseY());
                break;
            case BREAK_EARTH:
                current = new BreakEarth(this, game.getStoredMouseX(), game.getStoredMouseY());
                break;
            case DIG:
                current = new Dig(this, performingOn);
                break;
            case BUILD_TOWN:
                current = new BuildTown(this, game.getStoredMouseX(), game.getStoredMouseY());
                break;
            case BUILD_NEST:
                current = new BuildNest(this, game.getStoredMouseX(), game.getStoredMouseY());
                break;
            case BUILD_PARTY_TENT:
                current = new BuildPartyTent(this, game.getStoredMouseX(), game.getStoredMouseY());
                break;
            case TAKE_TO_INCUBATOR:
                current = new TakeEggToIncubator(this, performingOn);
                break;
            case GROW_FOREST:
                current = new PlantForest(this, game.getStoredMouseX(), game.getStoredMouseY());
                break;
            case INCINERATE_TOWN:
                current = new IncinerateTown(this, performingOn);
                break;
            case INCINERATE_FOREST:
                current = new IncinerateForest(this, performingOn);
                break;
            case KILL:
                current = new KillMonster(this, performingOn);
                break;
            default:
                throw new AssertionError(type.name());
        }
    }

    public void setGoals(Goal[] goals) {
        this.goals = goals;
    }

    public Goal[] getGoals() {
        return goals;
    }
    
    
    public boolean hasGoal(GoalType type) {
        for (int i=0; i<goals.length; i++) {
            if (goals[i] != null) {
                if (goals[i].getType() == type) {
                    return true;
                }
            }
        }
        return false;
    }
    
    public Goal getGoal(GoalType goal) {
        for (int i=0; i<goals.length; i++) {
            if (goals[i] != null) {
                if (goals[i].getType() == goal) {
                    return goals[i];
                }
            }
        }
        return null;
    }
}
