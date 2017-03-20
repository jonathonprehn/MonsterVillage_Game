/*
 * To change this license header, choose License Headers in Project Properties.
 * To change this template file, choose Tools | Templates
 * and open the template in the editor.
 */
package horsentp.ld35;

import java.util.ArrayList;

/**
 *
 * @author Jonathon
 */
public class MonsterFactory {
    
    public Monster createMiningMonster(float x, float y) {
        Monster ahh = new Monster(MonsterType.MINING);
        ahh.setName("Miner");
        ahh.setDescription("Mines metal and gems from the earth, finds eggs"
         + ", and digs new mining locations.");
        ahh.setDirectionalImage(new DirectionalImage(
                ImageBank.getImage("images/monsters/miningMonsterLeft.png"),
                ImageBank.getImage("images/monsters/miningMonsterRight.png")
        ));
        DirectionalImage form1 = new DirectionalImage(
                ImageBank.getImage("images/monsters/miningMonsterLeft.png"),
                ImageBank.getImage("images/monsters/miningMonsterRight.png")
        );
        DirectionalImage form2 = new DirectionalImage(
                ImageBank.getImage("images/monsters/miningMonsterLeft.png"),
                ImageBank.getImage("images/monsters/miningMonsterRight.png")
        );
        DirectionalImage form3 = new DirectionalImage(
                ImageBank.getImage("images/monsters/miningMonsterLeft.png"),
                ImageBank.getImage("images/monsters/miningMonsterRight.png")
        );
        ArrayList<DirectionalImage> formStack = new ArrayList<>();
        formStack.add(form1);
        formStack.add(form2);
        formStack.add(form3);
        ahh.setFormsStack(formStack);
        Goal[] goals = new Goal[3];
        int dist = (int)(Math.random()*4);
        if (dist == 0) {
            goals[0] = Goal.create(GoalType.MINE_METAL);
            goals[1] = Goal.create(GoalType.MINE_GEMS);
            goals[2] = Goal.create(GoalType.FIND_EGGS);
        } else if (dist == 1) {
            goals[0] = Goal.create(GoalType.MINE_METAL);
            goals[1] = Goal.create(GoalType.MINE_GEMS);
            goals[2] = Goal.create(GoalType.START_MINES);
        } else if (dist == 2) {
            goals[0] = Goal.create(GoalType.MINE_METAL);
            goals[1] = Goal.create(GoalType.FIND_EGGS);
            goals[2] = Goal.create(GoalType.START_MINES);
        } else {
            goals[0] = Goal.create(GoalType.MINE_GEMS);
            goals[1] = Goal.create(GoalType.FIND_EGGS);
            goals[2] = Goal.create(GoalType.START_MINES);
        }
        ahh.setGoals(goals);
        ahh.setX(x);
        ahh.setY(y);
        return ahh;
    }
    
    public Monster createBuildingMonster(float x, float y) {
        Monster ahh = new Monster(MonsterType.BUILDING);
        ahh.setName("Builder");
        ahh.setDescription("Builds towns, party tents, businesses, and " + 
                "nests.");
        ahh.setDirectionalImage(new DirectionalImage(
                ImageBank.getImage("images/monsters/buildingMonsterLeft.png"),
                ImageBank.getImage("images/monsters/buildingMonsterRight.png")
        ));
        DirectionalImage form1 = new DirectionalImage(
                ImageBank.getImage("images/monsters/buildingMonsterLeft.png"),
                ImageBank.getImage("images/monsters/buildingMonsterRight.png")
        );
        DirectionalImage form2 = new DirectionalImage(
                ImageBank.getImage("images/monsters/buildingMonsterLeft.png"),
                ImageBank.getImage("images/monsters/buildingMonsterRight.png")
        );
        DirectionalImage form3 = new DirectionalImage(
                ImageBank.getImage("images/monsters/buildingMonsterLeft.png"),
                ImageBank.getImage("images/monsters/buildingMonsterRight.png")
        );
        ArrayList<DirectionalImage> formStack = new ArrayList<>();
        formStack.add(form1);
        formStack.add(form2);
        formStack.add(form3);
        ahh.setFormsStack(formStack);
        Goal[] goals = new Goal[3];
        goals[0] = Goal.create(GoalType.BUILD_TOWNS);
        goals[1] = Goal.create(GoalType.BUILD_NESTS);
        goals[2] = Goal.create(GoalType.BUILD_PARTY_TENTS);
        ahh.setGoals(goals);
        ahh.setX(x);
        ahh.setY(y);
        return ahh;
    }
    
    public Monster createNurseMonster(float x, float y) {
        Monster ahh = new Monster(MonsterType.NURSE);
        ahh.setName("Nurse");
        ahh.setDescription("Takes care of child monsters, and helps them " +
                "become adults.");
        ahh.setDirectionalImage(new DirectionalImage(
                ImageBank.getImage("images/monsters/nurseMonsterLeft.png"),
                ImageBank.getImage("images/monsters/nurseMonsterRight.png")
        ));
        DirectionalImage form1 = new DirectionalImage(
                ImageBank.getImage("images/monsters/nurseMonsterLeft.png"),
                ImageBank.getImage("images/monsters/nurseMonsterRight.png")
        );
        DirectionalImage form2 = new DirectionalImage(
                ImageBank.getImage("images/monsters/nurseMonsterLeft.png"),
                ImageBank.getImage("images/monsters/nurseMonsterRight.png")
        );
        DirectionalImage form3 = new DirectionalImage(
                ImageBank.getImage("images/monsters/nurseMonsterLeft.png"),
                ImageBank.getImage("images/monsters/nurseMonsterRight.png")
        );
        ArrayList<DirectionalImage> formStack = new ArrayList<>();
        formStack.add(form1);
        formStack.add(form2);
        formStack.add(form3);
        ahh.setFormsStack(formStack);
        Goal[] goals = new Goal[3];
        goals[0] = Goal.create(GoalType.INCUBATE_EGGS);
        ahh.setGoals(goals);
        ahh.setX(x);
        ahh.setY(y);
        return ahh;
    }
    
    public Monster createGrowerMonster(float x, float y) {
        Monster ahh = new Monster(MonsterType.GROWER);
        ahh.setName("Grower");
        ahh.setDescription("Grows farms to feed humans. Can also grow forests.");
        ahh.setDirectionalImage(new DirectionalImage(
                ImageBank.getImage("images/monsters/growerMonsterLeft.png"),
                ImageBank.getImage("images/monsters/growerMonsterRight.png")
        ));
        DirectionalImage form1 = new DirectionalImage(
                ImageBank.getImage("images/monsters/growerMonsterLeft.png"),
                ImageBank.getImage("images/monsters/growerMonsterRight.png")
        );
        DirectionalImage form2 = new DirectionalImage(
                ImageBank.getImage("images/monsters/growerMonsterLeft.png"),
                ImageBank.getImage("images/monsters/growerMonsterRight.png")
        );
        DirectionalImage form3 = new DirectionalImage(
                ImageBank.getImage("images/monsters/growerMonsterLeft.png"),
                ImageBank.getImage("images/monsters/growerMonsterRight.png")
        );
        ArrayList<DirectionalImage> formStack = new ArrayList<>();
        formStack.add(form1);
        formStack.add(form2);
        formStack.add(form3);
        ahh.setFormsStack(formStack);
        Goal[] goals = new Goal[3];
        goals[0] = Goal.create(GoalType.PLANT_FARMS);
        goals[1] = Goal.create(GoalType.PLANT_FOREST);
        ahh.setGoals(goals);
        ahh.setX(x);
        ahh.setY(y);
        return ahh;
    }
    
    public Monster createRageMonster(float x, float y) {
        Monster ahh = new Monster(MonsterType.RAGE);
        ahh.setName("Rager");
        ahh.setDescription("Gets angry and destroys towns and forests.");
        ahh.setDirectionalImage(new DirectionalImage(
                ImageBank.getImage("images/monsters/rageMonsterLeft.png"),
                ImageBank.getImage("images/monsters/rageMonsterRight.png")
        ));
        DirectionalImage form1 = new DirectionalImage(
                ImageBank.getImage("images/monsters/rageMonsterLeft.png"),
                ImageBank.getImage("images/monsters/rageMonsterRight.png")
        );
        DirectionalImage form2 = new DirectionalImage(
                ImageBank.getImage("images/monsters/rageMonsterLeft.png"),
                ImageBank.getImage("images/monsters/rageMonsterRight.png")
        );
        DirectionalImage form3 = new DirectionalImage(
                ImageBank.getImage("images/monsters/rageMonsterLeft.png"),
                ImageBank.getImage("images/monsters/rageMonsterRight.png")
        );
        ArrayList<DirectionalImage> formStack = new ArrayList<>();
        formStack.add(form1);
        formStack.add(form2);
        formStack.add(form3);
        ahh.setFormsStack(formStack);
        Goal[] goals = new Goal[3];
        goals[0] = Goal.create(GoalType.INCINERATE_FOREST);
        goals[1] = Goal.create(GoalType.INCINERATE_TOWNS);
        ahh.setGoals(goals);
        ahh.setX(x);
        ahh.setY(y);
        return ahh;
    }
    
    public Monster createEvilMonster(float x, float y) {
        Monster ahh = new Monster(MonsterType.EVIL);
        ahh.setName("Predator");
        ahh.setDescription("Hunts and kills other monsters for its own benefit.");
        ahh.setDirectionalImage(new DirectionalImage(
                ImageBank.getImage("images/monsters/evilMonsterLeft.png"),
                ImageBank.getImage("images/monsters/evilMonsterRight.png")
        ));
        DirectionalImage form1 = new DirectionalImage(
                ImageBank.getImage("images/monsters/evilMonsterLeft.png"),
                ImageBank.getImage("images/monsters/evilMonsterRight.png")
        );
        DirectionalImage form2 = new DirectionalImage(
                ImageBank.getImage("images/monsters/evilMonsterLeft.png"),
                ImageBank.getImage("images/monsters/evilMonsterRight.png")
        );
        DirectionalImage form3 = new DirectionalImage(
                ImageBank.getImage("images/monsters/evilMonsterLeft.png"),
                ImageBank.getImage("images/monsters/evilMonsterRight.png")
        );
        ArrayList<DirectionalImage> formStack = new ArrayList<>();
        formStack.add(form1);
        formStack.add(form2);
        formStack.add(form3);
        ahh.setFormsStack(formStack);
        Goal[] goals = new Goal[3];
        goals[0] = Goal.create(GoalType.KILL_MONSTERS);
        ahh.setGoals(goals);
        ahh.setX(x);
        ahh.setY(y);
        return ahh;
    }
    
    public Monster createSocialMonster(float x, float y) {
        Monster ahh = new Monster(MonsterType.SOCIAL);
        ahh.setName("Socializer");
        ahh.setDescription("Wants to have a good time. Can joke around and " + 
                "throw parties with other monsters.");
        
        ahh.setX(x);
        ahh.setY(y);
        return ahh;
    }
    
    public Monster createCapitalistMonster(float x, float y) {
        Monster ahh = new Monster(MonsterType.CAPITALIST);
        ahh.setName("Capitalist");
        ahh.setDescription("Can start a business, hire humans, and create" + 
                " wealth. Will not the plight of the proletariat.");
        
        ahh.setX(x);
        ahh.setY(y);
        return ahh;
    }
}
