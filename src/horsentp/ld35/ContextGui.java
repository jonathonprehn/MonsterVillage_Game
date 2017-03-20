/*
 * To change this license header, choose License Headers in Project Properties.
 * To change this template file, choose Tools | Templates
 * and open the template in the editor.
 */
package horsentp.ld35;

import java.util.ArrayList;

/**
 * Positions the buttons for you for the context menu.
 * @author Jonathon
 */
public class ContextGui extends Gui {

    //Controls its own show state since it is more specific.
    //Behavior that sets when it is showing is inside the Game class
    //because that is where the top-level logic code is
    private boolean showing;
    //Holds the buttons that this context gui is displaying
    private ArrayList<GuiButton> buttonStack = new ArrayList<>();
    private int totalHeight;
    
    public ContextGui(float x, float y) {
        super(x, y, ImageBank.getImage("images/contextStackBottom.png"), GuiCoordinateSystem.WORLD);
        totalHeight = getBackground().getHeight();
    }

    public ArrayList<GuiButton> getButtonStack() {
        return buttonStack;
    }

    public void pushButton(GuiButton button) {
        buttonStack.add(button);
        totalHeight += button.getBackground().getHeight();
        button.setX(x);
        button.setY(y-totalHeight);
    }
    
    public GuiButton popButton() {
        if (buttonStack.isEmpty())
            return null;
        GuiButton b = buttonStack.get(buttonStack.size()-1);
        totalHeight -= b.getBackground().getHeight();
        buttonStack.remove(0);
        return b;
    }
    
    @Override
    public void renderWorldCoordinates(Game game, Renderer renderer, float camX, float camY) {
        renderer.drawImage(background, (int)(x-camX), (int)(y-camY), Game.WORLD_GUI_DEPTH-1);
        GuiButton b;
        for (int i=0; i<buttonStack.size(); i++) {
            b = buttonStack.get(i);
            b.setDepthValue(Game.WORLD_GUI_DEPTH-1);
            b.render(game, renderer, camX, camY);
            b.setDepthValue(Game.WORLD_GUI_DEPTH);
        }
    }

    @Override
    public void renderScreenCoordinates(Game game, Renderer renderer, float camX, float camY) {
        throw new UnsupportedOperationException("Not supported yet.");
    }
    
    @Override
    public void onClick(Game game, float mouseX, float mouseY, float camX, float camY) {
        
    }

    @Override
    public void update(Game game, int millis, float mouseX, float mouseY) {
        for (int i=0; i<buttonStack.size(); i++) {
            buttonStack.get(i).update(game, millis, mouseX, mouseY);
        }
    }

    public boolean isShowing() {
        return showing;
    }

    public void setShowing(boolean showing) {
        this.showing = showing;
    }

    @Override
    public void setX(float x) {
        this.x = x-(background.getWidth()/2);
        for (int i=0; i<buttonStack.size(); i++) {
            buttonStack.get(i).setX(this.x);
        }
    }
    
    @Override
    public void setY(float y) {
        this.y = y-background.getHeight();
        float prog = this.y;
        for (int i=0; i<buttonStack.size(); i++) {
            prog -= buttonStack.get(i).getBackground().getHeight();
            buttonStack.get(i).setY(prog);
        }
    }

    @Override
    public boolean clickedOn(Game game, float x, float y, float camX, float camY) {
        for (int i=0; i<buttonStack.size(); i++) {
            if (buttonStack.get(i).clickedOn(game, x, y, camX, camY)) {
                buttonStack.get(i).onClick(game, x, y, camX, camY);
            }
        }
        return false;
    }
    
    public void update(Entity selecting, Entity attachingTo) {
        if (!(selecting instanceof Monster)) {
            setShowing(false);
            return; //Can't have these kids do anything
        }
        buttonStack.clear();
        
        Monster selected = (Monster)selecting;
        if (attachingTo == null) {
            buttonStack.add(createButton(CommandType.MOVE, "Move here", null));
        }
        switch(selected.getType()) {
            case MINING:
                if (attachingTo == null) {
                    buttonStack.add(createButton(CommandType.BREAK_EARTH, "Break earth here", null));
                } else {
                    if (attachingTo.getName().equals("Mine")) {
                        buttonStack.add(createButton(CommandType.DIG, "Dig in this Mine", attachingTo));
                    }
                }
                break;
            case BUILDING:
                if (attachingTo == null) {
                    buttonStack.add(createButton(CommandType.BUILD_TOWN, "Build town here", null));
                    buttonStack.add(createButton(CommandType.BUILD_NEST, "Build incubator here", null));
                    buttonStack.add(createButton(CommandType.BUILD_PARTY_TENT, "Build party tent hre", null));
                }
                break;
            case NURSE:
                if (attachingTo != null) {
                    if (attachingTo.getName().equals("Egg")) {
                        buttonStack.add(createButton(CommandType.TAKE_TO_INCUBATOR, "Take egg to incubator", attachingTo));
                    }
                }
                break;
            case GROWER:
                if (attachingTo == null) {
                    buttonStack.add(createButton(CommandType.GROW_FOREST, "Grow forest here", null));
                    buttonStack.add(createButton(CommandType.GROW_FARM, "Grow farm here", null));
                }
                break;
            case RAGE:
                if (attachingTo != null) {
                    if (attachingTo.getName().equals("Town")) {
                        buttonStack.add(createButton(CommandType.INCINERATE_TOWN, "Incinerte town", attachingTo));
                    } else if (attachingTo.getName().equals("Forst")) {
                        buttonStack.add(createButton(CommandType.INCINERATE_FOREST, "Incinerate forest", attachingTo));
                    }
                }
                break;
            case EVIL:
                if (attachingTo != null) {
                    if (attachingTo instanceof Monster) {
                        buttonStack.add(createButton(CommandType.KILL, "Kill monster", attachingTo));
                    }
                }
                break;
            case SOCIAL:
                
                break;
            case CAPITALIST:
                
                break;
            default:
                throw new AssertionError(selected.getType().name());
        }
    }
    
    private GuiButton createButton(final CommandType commandType, String text, final Entity performingOn) {
        GuiButton button = new GuiButton(0, 0, 
                ImageBank.getImage("images/commandUp.png"), 
                ImageBank.getImage("images/commandDown.png"),
                GuiCoordinateSystem.WORLD);
        button.setText(text);
        button.setButtonListener(new ButtonListener() {
            @Override
            public void pressed(Game game, float mouseX, float mouseY, float camX, float camY) {
                ((Monster)game.getSelected()).setCurrentCommand(game, commandType, performingOn, mouseX, mouseY, camX, camY);
            }
        });
        return button;
    }
}
