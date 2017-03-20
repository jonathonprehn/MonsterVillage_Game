/*
 * To change this license header, choose License Headers in Project Properties.
 * To change this template file, choose Tools | Templates
 * and open the template in the editor.
 */
package horsentp.ld35;

import java.awt.event.KeyEvent;
import java.awt.event.MouseEvent;
import java.awt.image.BufferedImage;
import java.util.ArrayList;

/**
 * Root class
 *
 * @author Jonathon
 */
public class Game {

    /*
     Standard depths
     */
    public static final int SCREEN_GUI_DEPTH = -99999;
    //One higher so that the context GUI and its buttons can have
    //the non-standard depth value of 1.
    //the code for that is inside the context gui.
    public static final int WORLD_GUI_DEPTH = -99999+2;
    public static final int WORLD_DEPTH_START = -99999+3;

    private boolean[] cameraMoving;

    private MonsterFactory factory = new MonsterFactory();
    
    private int points;
    private int gems;
    private int metal;

    private float camX;
    private float camY;
    
    private float storedMouseX;
    private float storedMouseY;

    private ArrayList<Entity> entities;
    private ArrayList<Gui> gui;
    private BufferedImage selectHighlight;
    
    //Context Gui is its own GUI because its placement depends
    //on the "following" entity and the player's mouse click,
    //compared to other GUI objects that are just placed
    //wherever
    private ContextGui context;
    
    private SelectedGui selectGui;
    
    //Variable for storing the selected entity
    private Entity selected;
    //Variable for storing who the context menu is pointing at
    //if it is pointing at no one, then it stays fixed
    //at the position it was placed at
    //This is like the second layer of "selected"
    private Entity following;
    
    private GuiText pointsCounter;

    private boolean began = false;
    
    public Game() {
        entities = new ArrayList<>();
        gui = new ArrayList<>();
        points = 1000;
        cameraMoving = new boolean[4];

        context = new ContextGui(0, 0);
        selectGui = new SelectedGui();
        selectHighlight = ImageBank.getImage("images/selectHighlight.png");
        
        entities.add(factory.createMiningMonster(300, 100));
        entities.add(factory.createNurseMonster(100, 300));
        Entity incubator = Entity.createIncubator();
        incubator.setX(400);
        incubator.setY(200);
        entities.add(incubator);
        
        pointsCounter = new GuiText(0, 0, ImageBank.getImage("images/pointsBackground.png"), GuiCoordinateSystem.SCREEN);
        pointsCounter.setCentered(false);
        pointsCounter.setText("Points: " + this.points);
        gui.add(pointsCounter);
    }

    public void render(Renderer renderer) {
        if (!began) {
            renderer.drawImage(ImageBank.getImage("images/title.png"), 0, 0, 0);
        } else {
            for (int i = 0; i < entities.size(); i++) {
                if (entities.get(i) == selected) {
                    renderer.drawImage(selectHighlight, (int)(entities.get(i).getLocationX()-60-camX), (int)(entities.get(i).getLocationY()-25-camY), -entities.get(i).getSpriteBottomBoundary());
                }
                entities.get(i).render(this, renderer, camX, camY);
            }
            for (int i = 0; i < gui.size(); i++) {
                gui.get(i).render(this, renderer, camX, camY);
            }
            if (selected != null) {
                selectGui.render(this, renderer, camX, camY);
            }
            if (context.isShowing()) {
                context.render(this, renderer, camX, camY);
            }
        }
    }

    public void update(int millis) {
        if (!began)
            return;
        
        
        for (int i = 0; i < entities.size(); i++) {
            entities.get(i).update(this, millis, camX, camY);
        }
        for (int i = 0; i < gui.size(); i++) {
            gui.get(i).update(this, millis, camX, camY);
        }
        context.update(this, millis, camX, camY);
        selectGui.update(this, millis, camX, camY);
        if (following != null) {
            context.setX(following.x);
            context.setY(following.y-following.getDirectionalImage().getCurrentSpriteHeight()-context.getBackground().getHeight());
        }
        if (cameraMoving[0]) {
            camY -= (millis / 1.5f);
        }
        if (cameraMoving[1]) {
            camY += (millis / 1.5f);
        }
        if (cameraMoving[2]) {
            camX -= (millis / 1.5f);
        }
        if (cameraMoving[3]) {
            camX += (millis / 1.5f);
        }
    }

    public void mineOutput(int gems, int metal, Entity egg) {
        gems += gems;
        addPoints(gems*50);
        metal += metal;
        addPoints(metal*20);
        if (egg != null) {
            addToWorld(egg);
            addPoints(100);
        }
    }
    
    public void addPoints(int amount) {
        points += amount;
        pointsCounter.setText("Points: " + this.points + " (+" + amount + ")");
    }
    
    public void keyInput(int key, boolean pressed) {
        if (!began)
            return;
        if (key == KeyEvent.VK_W || key == KeyEvent.VK_UP) {
            cameraMoving[0] = pressed;
        } else if (key == KeyEvent.VK_S || key == KeyEvent.VK_DOWN) {
            cameraMoving[1] = pressed;
        } else if (key == KeyEvent.VK_A || key == KeyEvent.VK_LEFT) {
            cameraMoving[2] = pressed;
        } else if (key == KeyEvent.VK_D || key == KeyEvent.VK_RIGHT) {
            cameraMoving[3] = pressed;
        }
        
        if (!pressed && key == KeyEvent.VK_DELETE) {
            System.exit(0);
        } 
    }

    public void mouseInput(Game game, int mouseX, int mouseY, int button, boolean pressed) {
        if (pressed) {
            if (!began) {
                began = true;
            } else {
            if (button == 1) {
                for (Gui g : gui) {
                    if (g.clickedOn(game, mouseX, mouseY, camX, camY)) {
                        g.onClick(game, mouseX, mouseY, camX, camY);
                    }
                }
                if (context.isShowing()) {
                    context.clickedOn(game, mouseX, mouseY, camX, camY);
                    context.setShowing(false);
                }
            }
            
            
            //Check to see if you have clicked on an entity
            /*
             If you have no selection, then it is safe to 
             say that you can select something. If you are 
             selecting something already, then a click
             is assuming that you are going to do some action.
             */
            
            Entity closest = getClosest(game, mouseX, mouseY);
                        
            if (button != 3) { //Button 1 is the select button
                if (closest != null) {
                    //Try to see if you can select something
                    selected = closest;
                    selectGui.updateSelected(this);
                    following = null;
                } else {
                    selected = null;
                    following = null;
                }
                context.setShowing(false);
            } else if (button != 1) { //Button 3 is the command button
                if (selected != null) {
                    if (closest != null) {
                        context.setShowing(true);
                        following = closest;
                        context.update(selected, closest);
                    } else {
                        following = null;
                        context.setShowing(true);
                        context.update(selected, closest);
                        context.setX(mouseX+camX);
                        context.setY(mouseY+camY);
                        storedMouseX = mouseX+camX;
                        storedMouseY = mouseY+camY;
                    }
                }
            }
            }
        }
    }
    
    public float getStoredMouseX() {
        if (following != null) {
            return following.getLocationX();
        } else {
            return storedMouseX;
        }
    }
    
    public float getStoredMouseY() {
        if (following != null) {
            return following.getLocationY();
        } else {
            return storedMouseY;
        }
    }

    public Entity getRandomIncubator() {
        ArrayList<Entity> incubators = new ArrayList<>();
        for (int i=0; i<entities.size(); i++) {
            if (entities.get(i).getName().equals("Incubator")) {
                incubators.add(entities.get(i));
            }
        }
        int index = (int) (Math.random()*incubators.size());
        return incubators.get(index);
    }
    
    public boolean isNearTown(Entity farm) {
        ArrayList<Entity> towns = new ArrayList<>();
        for (int i=0; i<entities.size(); i++) {
            if (entities.get(i).getName().equals("Town")) {
                towns.add(entities.get(i));
            }
        }
        for (int i=0; i<towns.size(); i++) {
            float dx = farm.getLocationX()-towns.get(i).getLocationX();
            float dy = farm.getLocationY()-towns.get(i).getLocationY();
            float distance = (float)Math.sqrt(dx*dx + dy*dy);
            if (distance < 200) {
                return true;
            }
        }
        return false;
    }
    
    public ArrayList<Entity> shootRay(Game game, int mouseX, int mouseY) {
        ArrayList<Entity> clickedOn = new ArrayList<>();
       // System.out.println("Shooting ray...");
        for (int i = 0; i < entities.size(); i++) {
//            System.out.println("Checking with entity " + i + "...");
//            System.out.println("Mouse " + (mouseX+camX) + ", " +  (mouseY+camY));
//            System.out.println("Top " + entities.get(i).getSpriteTopBoundary());
//            System.out.println("Bottom " + entities.get(i).getSpriteBottomBoundary());
//            System.out.println("Left " + entities.get(i).getSpriteLeftBoundary());
//            System.out.println("Right " + entities.get(i).getSpriteRightBoundary());
            if (entities.get(i).clickedOn(game, mouseX, mouseY, camX, camY)) {
                clickedOn.add(entities.get(i));
                //System.out.println("Hit!");
            }
        }
        //System.out.println("Ray collided with " + clickedOn.size() + " entities");
        
        return clickedOn;
    }

    public Entity getClosest(ArrayList<Entity> entities) {
        if (entities.isEmpty()) {
            return null;
        }
        Entity closest = entities.get(0);
        for (int i = 1; i < entities.size(); i++) {
            if (entities.get(i).getDepthValue() < closest.getDepthValue()) {
                closest = entities.get(i);
            }
        }
        return closest;
    }
    
    public void addToWorld(Entity e) {
        entities.add(e);
    }

    public void removeFromWorld(Entity e) {
        entities.remove(e);
    }
    
    public Entity getClosest(Game game, int mouseX, int mouseY) {
        return getClosest(shootRay(game, mouseX, mouseY));
    }
   

    public float getCamY() {
        return camY;
    }

    public float getCamX() {
        return camX;
    }

    public Entity getSelected() {
        return selected;
    }

    public Entity getFollowing() {
        return following;
    }

    public MonsterFactory getFactory() {
        return factory;
    }

    
}
