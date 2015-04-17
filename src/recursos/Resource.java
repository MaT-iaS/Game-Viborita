/*
 * To change this template, choose Tools | Templates
 * and open the template in the editor.
 */

package recursos;

import java.applet.Applet;
import java.applet.AudioClip;
import java.awt.Color;
import java.awt.Image;
import java.awt.Toolkit;
import java.net.URL;
import javax.swing.ImageIcon;

/**
 *
 * @author 
 */
public class Resource {
    public final static AudioClip MUSIC_BACK=Applet.newAudioClip(Resource.class.getResource("cortina.wav"));
    public final static AudioClip EAT=Applet.newAudioClip(Resource.class.getResource("grown.wav"));
    public final static AudioClip MOVE=Applet.newAudioClip(Resource.class.getResource("wa.wav"));
    public final static AudioClip GAME_OVER=Applet.newAudioClip(Resource.class.getResource("game_over.wav"));
    public final static Color COLOR_VERDE=new Color(0, 200, 80);
    public final static Image IMG_FONDO =new ImageIcon(Resource.class.getResource("fondo.png")).getImage();
    public final static URL URL_SPLASH =Resource.class.getResource("splash.png");
}
