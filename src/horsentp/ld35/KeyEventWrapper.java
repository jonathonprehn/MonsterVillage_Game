/*
 * To change this license header, choose License Headers in Project Properties.
 * To change this template file, choose Tools | Templates
 * and open the template in the editor.
 */
package horsentp.ld35;

import java.awt.event.KeyEvent;

   public class KeyEventWrapper {
        private KeyEvent event;
        private boolean pressed;

        public KeyEventWrapper(KeyEvent event, boolean pressed) {
            this.event = event;
            this.pressed = pressed;
        }

        public KeyEvent getEvent() {
            return event;
        }

        public boolean isPressed() {
            return pressed;
        }
        
        
    }
