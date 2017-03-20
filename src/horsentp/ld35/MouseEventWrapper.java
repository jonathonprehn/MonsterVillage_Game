/*
 * To change this license header, choose License Headers in Project Properties.
 * To change this template file, choose Tools | Templates
 * and open the template in the editor.
 */
package horsentp.ld35;

import java.awt.event.MouseEvent;

public class MouseEventWrapper {
    private MouseEvent event;
    private boolean pressed;

    public MouseEventWrapper(MouseEvent event, boolean pressed) {
        this.event = event;
        this.pressed = pressed;
    }

    public MouseEvent getEvent() {
        return event;
    }

    public boolean isPressed() {
        return pressed;
    }


}