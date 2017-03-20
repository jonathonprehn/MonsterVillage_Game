/*
 * To change this license header, choose License Headers in Project Properties.
 * To change this template file, choose Tools | Templates
 * and open the template in the editor.
 */
package horsentp.ld35;

import java.awt.Graphics;

/**
 *
 * @author Jonathon
 */
public class Entity implements GamePiece {
    
    protected float x;
    protected float y;
    protected float radius;
    protected DirectionalImage directionalImage;
    protected int depthValue;
    protected boolean poof = false;
        
    /*
    The name variable is to enable the game to distinguish between
    different types of entities when seeing if a button should be
    added to the context gui once the player has selected
    a "following" value.
    */
    protected String name = "NO_NAME";
    /*
    Description is displayed by a description GuiText object 
    if they have selected an entity
    */
    protected String description = "NO_DESCRIPTION";
    
    @Override
    public void update(Game game, int millis, float mouseX, float mouseY) {
        specializedUpdate(game, millis);
    }
    
    protected void specializedUpdate(Game game, int millis) {} 
    
    @Override
    public void render(Game game, Renderer renderer, float camX, float camY) {
        if (poof)
            return;
        if (directionalImage.getCurrentSprite() == null) {
            System.out.println("NULL IAMGE");
        }
        renderer.drawImage(directionalImage.getCurrentSprite(), 
                (int)(getSpriteLeftBoundary()-camX), 
                (int)(getSpriteTopBoundary()-camY), -getSpriteBottomBoundary());
    }

    public void setX(float x) {
        this.x = x;
    }

    public void setY(float y) {
        this.y = y;
    }
    
    
    
    /**
     * Sees if this entity is in the radius of the other entity.
     * @param entity the entity to check proximity to
     * @return if this entity is in the radius of the other entity.
     */
    public boolean inRangeOf(Entity entity) {
        float dx = (x-entity.x);
        float dy = (y-entity.y);
        return (float)Math.sqrt( dx*dx + dy*dy) < entity.radius; 
    }
    
    /**
     * Sees if this entity was clicked on.
     * @param game
     * @param x the screen position of the click
     * @param y the screen position of the click
     * @param camX the x position of the camera
     * @param camY the y position of the camera
     * @return if the click was on this entity
     */
    @Override
    public boolean clickedOn(Game game, float x, float y, float camX, float camY) {
        if (poof)
            return false;
        return (x+camX) > getSpriteLeftBoundary() && (x+camX) < getSpriteRightBoundary() &&
                (y+camY) > getSpriteTopBoundary() && (y+camY) < getSpriteBottomBoundary();
    }

    @Override
    public void onClick(Game game, float mouseX, float mouseY, float camX, float camY) {}

    public void setPoof(boolean poof) {
        this.poof = poof;
    }

    public boolean isPoof() {
        return poof;
    }
    
   
    
    public float getSpriteRightBoundary() {
        return x + (directionalImage.getCurrentSpriteWidth()/2);
    }
    
    public float getSpriteLeftBoundary() {
        return x - (directionalImage.getCurrentSpriteWidth()/2);
    }
    
    public float getSpriteTopBoundary() {
        return y - directionalImage.getCurrentSpriteHeight();
    }
    
    public float getSpriteBottomBoundary() {
        return y;
    }
    
    public float getLocationX() {
        return x;
    }
    
    public float getLocationY() {
        return y;
    }
    
    public void move(float x, float y) {
        this.x += x;
        this.y += y;
    }
    
    public float getRadius() {
        return radius;
    }

    public String getDescription() {
        return description;
    }

    public void setDescription(String description) {
        this.description = description;
    }

    public void setDirectionalImage(DirectionalImage animation) {
        this.directionalImage = animation;
    }

    public DirectionalImage getDirectionalImage() {
        return directionalImage;
    }

    public String getName() {
        return name;
    }

    public void setName(String name) {
        this.name = name;
    }
    
    public void setDepthValue(int depthValue) {
        this.depthValue = depthValue;
    }

    public int getDepthValue() {
        return depthValue;
    }
    
    
    
    public static final Entity createForest() {
        Entity e = new Entity();
        e.setName("Forest");
        e.setDescription("Just a bunch of trees");
        e.setDirectionalImage(new DirectionalImage(ImageBank.getImage("images/forest.png"), ImageBank.getImage("images/forest.png")));
        return e;
    }
    
    public static final Entity createFarm() {
        Entity e = new Entity();
        e.setName("Farm");
        e.setDescription("Grows food for the local town");
        e.setDirectionalImage(new DirectionalImage(ImageBank.getImage("images/farm.png"), ImageBank.getImage("images/farm.png")));
        return e;
    }
    
    public static final Entity createTown() {
        Entity e = new Entity();
        e.setName("Town");
        e.setDescription("Houses the humans. Building farms near the town will generate points.");
        e.setDirectionalImage(new DirectionalImage(ImageBank.getImage("images/towns.png"), ImageBank.getImage("images/towns.png")));
        return e;
    }
    
    public static final Entity createMine() {
        Entity e = new Entity();
        e.setName("Mine");
        e.setDescription("An entrance into the ground. Send miners here to get resources and eggs.");
        e.setDirectionalImage(new DirectionalImage(ImageBank.getImage("images/mine.png"), ImageBank.getImage("images/mine.png")));
        return e;
    }
    
    public static final Entity createEgg() {
        Entity e = new Entity();
        e.setName("Egg");
        e.setDescription("Hatches into a new monster. Have a nurse come and incubate it.");
        e.setDirectionalImage(new DirectionalImage(ImageBank.getImage("images/egg.png"), ImageBank.getImage("images/egg.png")));
        return e;
    }
    
    public static final Entity createIncubator() {
        Entity e = new Entity();
        e.setName("Incubator");
        e.setDescription("Incubates monsters. When a nurse grabs an egg, it will hatch at one of these.");
        e.setDirectionalImage(new DirectionalImage(ImageBank.getImage("images/incubator.png"), ImageBank.getImage("images/incubator.png")));
        return e;
    }
    
    public static final Entity createPartyTent() {
        Entity e = new Entity();
        e.setName("Party Tent");
        e.setDescription("Brings joy and laughter to the world.");
        e.setDirectionalImage(new DirectionalImage(ImageBank.getImage("images/partyTent.png"), ImageBank.getImage("images/partyTent.png")));
        return e;
    }
}
