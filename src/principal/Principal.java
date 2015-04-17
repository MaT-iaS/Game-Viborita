/*
 * To change this template, choose Tools | Templates
 * and open the template in the editor.
 */

package principal;
import game.Game;
import javax.swing.JFrame;

//import java.awt.event.KeyListener;
//import javax.swing.JFrame;
/**
 *
 * @author 
 */
public class Principal{
    int duracion=0;
    /**
     * @param args the command line arguments
     */
    public static void main(String[] args) throws InterruptedException {
        new Splash(3000);
        
        new Game();
    }
 
}
