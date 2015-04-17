/*
 * To change this template, choose Tools | Templates
 * and open the template in the editor.
 */

package serpiente;

import java.awt.Color;
import java.awt.Graphics2D;
import java.awt.Rectangle;


/**
 *
 * @author 
 */
public class CabezaSerpiente {
    private int x;
    private int y;
    private static final int SIZE=12;

    public void paint(Graphics2D g ){
//        g.setColor(Color.BLACK);
        g.fillRect(x, y, SIZE, SIZE);
//        g.fillOval(x, y, 12, 12);
        
    }

    public CabezaSerpiente(int x,int y) {
        this.x=x;
        this.y=y;
    }
    
    public Rectangle getBound(){
        return new Rectangle(x, y, SIZE, SIZE);
    }
    
    public void setXY(int x,int y){
        this.setX(x);
        this.setY(y);
    }

    /**
     * @return the x
     */
    public int getX() {
        return x;
    }

    /**
     * @param x the x to set
     */
    public void setX(int x) {
        this.x = x;
    }

    /**
     * @return the y
     */
    public int getY() {
        return y;
    }

    /**
     * @param y the y to set
     */
    public void setY(int y) {
        this.y = y;
    }

}
