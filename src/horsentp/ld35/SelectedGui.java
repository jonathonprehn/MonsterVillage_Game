/*
 * To change this license header, choose License Headers in Project Properties.
 * To change this template file, choose Tools | Templates
 * and open the template in the editor.
 */
package horsentp.ld35;

import java.awt.image.BufferedImage;

/**
 *
 * @author Jonathon
 */
public class SelectedGui extends Gui {

    private GuiText[] goals;
    boolean showGoals = false;
    private GuiText description;
    
    public SelectedGui() {
        super(0, 0, ImageBank.getImage("images/selectedSide.png"), GuiCoordinateSystem.WORLD);
        goals = new GuiText[3];
        goals[0] = new GuiText(0, 0, ImageBank.getImage("images/goalBackground.png"), GuiCoordinateSystem.WORLD);
        goals[1] = new GuiText(0, 0, ImageBank.getImage("images/goalBackground.png"), GuiCoordinateSystem.WORLD);
        goals[2] = new GuiText(0, 0, ImageBank.getImage("images/goalBackground.png"), GuiCoordinateSystem.WORLD);
        description = new GuiText(0, 0, ImageBank.getImage("images/descriptionBackground.png"), GuiCoordinateSystem.WORLD);
    }
    
    @Override
    public void renderWorldCoordinates(Game game, Renderer renderer, float camX, float camY) {
        if (game.getSelected() != null) {
            renderer.drawImage(background, (int)(x-camX), (int)(y-camY), Game.WORLD_GUI_DEPTH);
            if (showGoals) {
                for (int i=0; i<goals.length; i++) {
                    goals[i].renderWorldCoordinates(game, renderer, camX, camY);
                }
                description.renderWorldCoordinates(game, renderer, camX, camY);
            } else {
                description.renderWorldCoordinates(game, renderer, camX, camY);
            }
        }
    }

    public void updateSelected(Game game) {
        if (game.getSelected() != null) {
            if (game.getSelected() instanceof Monster) {
                Monster sel = (Monster)game.getSelected();
                for (int i=0; i<goals.length; i++) {
                    if (sel.getGoals()[i] != null) {
                        goals[i].setText(sel.getGoals()[i].getText());
                    } else {
                        goals[i].setText("");
                    }
                }
                showGoals = true;
            } else {
                showGoals = false;
            } 
            description.setText(game.getSelected().getName() + "\n" + game.getSelected().getDescription());
            setX(game.getSelected().getSpriteRightBoundary());
            setY(game.getSelected().getSpriteTopBoundary()-180);
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
        if (game.getSelected() != null) {
            setX(game.getSelected().getSpriteRightBoundary());
            setY(game.getSelected().getSpriteTopBoundary()-180);
        }
    }
    
    @Override
    public void setX(float x) {
        if (showGoals) {
            for (int i=0; i<goals.length; i++) {
                goals[i].setX(background.getWidth()+x);
            }
            description.setX(background.getWidth() + x + goals[0].getBackground().getWidth());
        } else {
            description.setX(background.getWidth() + x);
        }
        this.x = x;
    }
    
    @Override
    public void setY(float y) {
        for (int i=0; i<goals.length; i++) {
            goals[i].setY(y + i*goals[i].getBackground().getHeight());
        }
        description.setY(y);
        this.y = y;
    }

}
