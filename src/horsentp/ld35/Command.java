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
public interface Command {
    
    public void update(Monster monster, Game game);
    
    public boolean isComplete(Monster monster, Game game);
}
