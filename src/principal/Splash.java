/*
 * To change this template, choose Tools | Templates
 * and open the template in the editor.
 */

package principal;

import java.awt.BorderLayout;
import javax.swing.ImageIcon;
import javax.swing.JLabel;
import javax.swing.JPanel;
import javax.swing.JWindow;
import recursos.Resource;

/**
 *
 * @author 
 */
public class Splash extends JWindow{
    int duracion=0;

    public Splash(int duracion) {
        this.duracion = duracion;
        
        JPanel panel=(JPanel)getContentPane();
        ImageIcon img=new ImageIcon(Resource.URL_SPLASH);
        
        panel.add(new JLabel(img),BorderLayout.CENTER);
        setSize(img.getIconWidth() , img.getIconHeight());
        setLocationRelativeTo(null);
        setVisible(true);
        
        try {
            Thread.sleep(duracion);
        } catch (Exception e) {
            e.printStackTrace();
        }
        setVisible(false);
        
    }
    
}
