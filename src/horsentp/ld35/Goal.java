/*
 * To change this license header, choose License Headers in Project Properties.
 * To change this template file, choose Tools | Templates
 * and open the template in the editor.
 */
package horsentp.ld35;

/**
 *
 * @author Jonathon
 */
public class Goal {
    
    private boolean completed = false;
    private final int goalAmount;
    private int runningTotal;
    private final String verb;
    private final String noun;
    private final GoalType type;

    public Goal(GoalType type, int goalAmount, String verb, String noun) {
        this.goalAmount = goalAmount;
        this.type = type;
        this.verb = verb;
        this.noun = noun;
    }

    public int getGoalAmount() {
        return goalAmount;
    }
        
    public int getRunningTotal() { return runningTotal; }
    
    public void add(int amount) {
        runningTotal += amount;
    }
    
    public boolean checkGoalComplete(Monster monster, Game game) {
        return runningTotal >= goalAmount;
    }

    public String getText() {
        return verb + " " + goalAmount + " " + noun + "(" + runningTotal + "/" + goalAmount + ")";
    }

    public String getNoun() {
        return noun;
    }

    public String getVerb() {
        return verb;
    }
    
    public boolean isComplete() {
        return completed;
    }
    
    public void complete() {
        completed = true;
    }

    public GoalType getType() {
        return type;
    }
    
    public static Goal create(GoalType type) {
        switch(type) {
            case MINE_METAL:
                return new Goal(type, type.getRandomAmount(), "Mine", "Metal");
            case MINE_GEMS:
                return new Goal(type, type.getRandomAmount(), "Mine", "Gems");
            case FIND_EGGS:
                return new Goal(type, type.getRandomAmount(), "Find", "Eggs");
            case START_MINES:
                return new Goal(type, type.getRandomAmount(), "Start", "Mines");
            case BUILD_TOWNS:
                return new Goal(type, type.getRandomAmount(), "Build", "Towns");
            case BUILD_NESTS:
                return new Goal(type, type.getRandomAmount(), "Build", "Incubators");
            case BUILD_PARTY_TENTS:
                return new Goal(type, type.getRandomAmount(), "Build", "Party Tents");
            case BUILD_BUSINESSES:
                return new Goal(type, type.getRandomAmount(), "Build", "Businesses");
            case INCUBATE_EGGS:
                return new Goal(type, type.getRandomAmount(), "Incubate", "Eggs");
            case PLANT_FARMS:
                return new Goal(type, type.getRandomAmount(), "Plant", "Farms");
            case PLANT_FOREST:
                return new Goal(type, type.getRandomAmount(), "Plant", "Forests");
            case INCINERATE_FOREST:
                return new Goal(type, type.getRandomAmount(), "Incinerate", "Forests");
            case INCINERATE_TOWNS:
                return new Goal(type, type.getRandomAmount(), "Incinerate", "Towns");
            case KILL_MONSTERS:
                return new Goal(type, type.getRandomAmount(), "Kill", "Monsters");
            case JOKE_WITH_MONSTERS:
                return new Goal(type, type.getRandomAmount(), "Joke with", "Monsters");
            case THROW_A_PARTY_WITH_MONSTERS:
                return new Goal(type, type.getRandomAmount(), "Throw a party with", "Monsters");
            case CREATE_A_BUSINESS:
                return new Goal(type, type.getRandomAmount(), "Create a", "Business");
            case HIRE_WORKERS:
                return new Goal(type, type.getRandomAmount(), "Hire", "Workers");
            case CREATE_WEALTH:
                return new Goal(type, type.getRandomAmount(), "Create", "Wealth");
            default:
                throw new AssertionError(type.name());
            
        }
    }
}
