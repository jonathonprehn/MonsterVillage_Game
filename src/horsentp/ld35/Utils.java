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
public class Utils {
    
    
    public static int randomValue(int lower, int upper) {
        return lower + (int)(Math.random()*(upper-lower));
    }
}
