/*
 * To change this template, choose Tools | Templates
 * and open the template in the editor.
 */

package serpiente;

import java.awt.Color;
import java.awt.Graphics2D;
import java.awt.Rectangle;
import recursos.Resource;

/**
 *
 * @author 
 */
public class SegmentoSerpiente {
    private int x;
    private int y;
    private static final int SIZE=12;
    private String direccion;

    public SegmentoSerpiente() {
        setXY(666, 666);
    }
    
    
    
    public void paint(Graphics2D g,Color borde,Color medio){
//        g.setColor(Color.BLACK);
//        g.drawOval(getX(), getY(), 12, 12);
        g.setColor(borde);
        g.fillRect(getX(), getY(), SIZE, SIZE);
        g.setColor(medio);
        g.fillOval(x+2, y+2, SIZE-4, SIZE-4);
//        g.fillRect(getX(), getY(), 12, 12);
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

    /**
     * @return the direccion
     */
    public String getDireccion() {
        return direccion;
    }

    /**
     * @param direccion the direccion to set
     */
    public void setDireccion(String direccion) {
        this.direccion = direccion;
    }

    
}
