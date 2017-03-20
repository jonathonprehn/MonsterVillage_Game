/*
 * To change this license header, choose License Headers in Project Properties.
 * To change this template file, choose Tools | Templates
 * and open the template in the editor.
 */
package horsentp.ld35;

import java.awt.event.ActionEvent;
import java.awt.image.BufferedImage;

/**
 *
 * @author Jonathon
 */
public class GuiButton extends GuiText {

    private BufferedImage down;
    private BufferedImage up;
    private ButtonListener buttonListener;
    private boolean hovering = false;

    public GuiButton(float x, float y, BufferedImage up, BufferedImage down, GuiCoordinateSystem system) {
        super(x, y, up, system);
        this.up = up;
        this.down = down;
    }

    public void setButtonListener(ButtonListener buttonListener) {
        this.buttonListener = buttonListener;
    }

    @Override
    public void update(Game game, int millis, float mouseX, float mouseY) {
        if (clickedOn(game, mouseX, mouseY, game.getCamX(), game.getCamY())) {
            setBackground(down);
            if (!hovering) {
                drawToBuffer();
            }
            hovering = true;
        } else {
            setBackground(up);
            if (hovering) {
                drawToBuffer();
            }
            hovering = false;
        }
    }

    @Override
    public void onClick(Game game, float mouseX, float mouseY, float camX, float camY) {
        //Play click sound and do action
        if (buttonListener != null) {
            buttonListener.pressed(game, mouseX, mouseY, camX, camY);
        }
    }

    public void setUp(BufferedImage up) {
        this.up = up;
    }

    public void setDown(BufferedImage down) {
        this.down = down;
    }

}
