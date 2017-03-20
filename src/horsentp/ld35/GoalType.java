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
public enum GoalType {
    MINE_METAL, MINE_GEMS, FIND_EGGS,
    START_MINES, BUILD_TOWNS, BUILD_NESTS,
    BUILD_PARTY_TENTS, BUILD_BUSINESSES,
    INCUBATE_EGGS,
    PLANT_FARMS, PLANT_FOREST,
    INCINERATE_FOREST, INCINERATE_TOWNS,
    KILL_MONSTERS, JOKE_WITH_MONSTERS,
    THROW_A_PARTY_WITH_MONSTERS,
    CREATE_A_BUSINESS, HIRE_WORKERS,
    CREATE_WEALTH;
    
    public int getRandomAmount() {
        switch(this) {
            case MINE_METAL:
                return Utils.randomValue(100, 200);
            case MINE_GEMS:
                return Utils.randomValue(20, 30);
            case FIND_EGGS:
                return Utils.randomValue(3, 5);
            case START_MINES:
                return Utils.randomValue(1, 3);
            case BUILD_TOWNS:
                return Utils.randomValue(2, 4);
            case BUILD_NESTS:
                return Utils.randomValue(1, 3);
            case BUILD_PARTY_TENTS:
                return Utils.randomValue(1, 3);
            case BUILD_BUSINESSES:
                return Utils.randomValue(1, 3);
            case INCUBATE_EGGS:
                return Utils.randomValue(10, 20);
            case PLANT_FARMS:
                return Utils.randomValue(8, 10);
            case PLANT_FOREST:
                return Utils.randomValue(20, 30);
            case INCINERATE_FOREST:
                return Utils.randomValue(30, 40);
            case INCINERATE_TOWNS:
                return Utils.randomValue(5, 10);
            case KILL_MONSTERS:
                return Utils.randomValue(2, 4);
            case JOKE_WITH_MONSTERS:
                return Utils.randomValue(10, 20);
            case THROW_A_PARTY_WITH_MONSTERS:
                return Utils.randomValue(1, 3);
            case CREATE_A_BUSINESS:
                return Utils.randomValue(1, 3);
            case HIRE_WORKERS:
                return Utils.randomValue(100, 150);
            case CREATE_WEALTH:
                return Utils.randomValue(1000, 2000);
        }        
        return 0;
    }
}
