/*
 * To change this template, choose Tools | Templates
 * and open the template in the editor.
 */

package powerup;

import game.Game;
import java.awt.Color;
import java.awt.Graphics2D;
import java.awt.Rectangle;
import java.math.BigDecimal;
import java.math.MathContext;
import java.util.Random;
import recursos.Resource;

/**
 *
 * @author 
 */
public class PowerUp {
    private static int SIZE=12;
    private int y;
    private int x;
    private int rango=400;
    private BigDecimal puntaje;
    private int bonus;//BONUS 0=NADA, 1=LONG--, 2=SPEED--, 3=LONG++, 4=SPEED++
    
    private Random rand=new Random();

    public PowerUp() {
//        img =new JLabel(ImgComida.NEW_IMAGE);
        x=rand.nextInt(rango);
        y=rand.nextInt(rango);
        puntaje=new BigDecimal(1.1);
        bonus=0;
    }
    
//    public void paint(Graphics2D g2){
    public void paint(Graphics2D g2){
        switch (bonus) {
            case 1:
                g2.setColor(Resource.COLOR_VERDE);
                g2.fillRect(x, y, SIZE,SIZE);
                break;
            case 2:
                g2.setColor(Color.blue);
                g2.fillOval(x, y, SIZE, SIZE);
                break;
            case 3:
                g2.setColor(Color.orange);
                g2.fillRect(x, y, SIZE,SIZE);
                break;
            case 4:
                g2.setColor(Color.red);
                g2.fillOval(x, y, SIZE, SIZE);
                break;
            default:
                g2.setColor(Color.darkGray);
                g2.fillRect(x, y, SIZE,SIZE);
        }
    }
    
    //METODO CREA UN NUEVO POWER UP CON TIPO PUNTAJE Y UBICACION
    public void createPowerUp(){
        setXY(getRand().nextInt(getRango()), getRand().nextInt(getRango()));
        int probabilidad =getRand().nextInt(100);
        
        
        if (probabilidad>60 && probabilidad<65) {// achica x10 -1-
            bonus=1;
            puntaje=new BigDecimal(1.2);
        }else{
            if (probabilidad>65 && probabilidad<70) { //DISMINUYE VELOCIDAD X10 -2-
                bonus=2;
                puntaje=new BigDecimal(1.2);
            }else{
                if (probabilidad>89 && probabilidad<100) { //AGRANDA X10 -3-
                    bonus=3;
                    puntaje=new BigDecimal(1.5);
                }else{
                    if (probabilidad>49 && probabilidad<51) { // SPEED +15 -4-
                        bonus=4;
                        puntaje=new BigDecimal(1.5);
                    }else{
                        bonus=0;
                        puntaje=new BigDecimal(1.05);
                    }    
                }
            }
        }   
    }
    
// METODO EJECUTA ACCIONES SI EL POWER UP ES TOCADO
    public void aplicarBonus(Game game){
        if (isColisionado(game)) {
            Resource.EAT.play();
            switch (bonus) {
                //encojo 10 unidades
                case 1:
                    if (game.getSerpiente().getCuerpo().size()>10) {
                        game.getSerpiente().encoger(10);
                    }
                    break;
                    
                //disminuyo la velocidad en 10
                case 2:
                    if (game.getSerpiente().getVelocidad()<210) {
                        int velocidadActual=game.getSerpiente().getVelocidad();
                        game.getSerpiente().setVelocidad(velocidadActual+25);
                    }else{
                        game.getSerpiente().setVelocidad(230);
                    }
                    break;
                
                //agrando serpiente en 10
                case 3:
                    game.getSerpiente().crecer(10);
                    break;
                
                //aumento velocidad en 10
                case 4:
                    if (game.getSerpiente().getVelocidad()>40) {
                        int velocidadActual=game.getSerpiente().getVelocidad();
                        game.getSerpiente().setVelocidad(velocidadActual-25);
                    }else{
                        game.getSerpiente().setVelocidad(20);
                    }
                    break;
                default:
                    if (game.getSerpiente().getVelocidad()>20) {
                        game.getSerpiente().setVelocidad(game.getSerpiente().getVelocidad()-2);
                    }
                    game.getSerpiente().crecer(1);
            }
            
            game.setScore(game.getScore().multiply(puntaje, MathContext.DECIMAL32));
            createPowerUp();
        }
    }
    
    //METODO Q DEVUELVE TRUE SI LA SERPIENTE TOCA UN POWER UP
    public boolean isColisionado(Game game){
        boolean retorno=false;
        if (game.getSerpiente().getCabeza().getBound().intersects(getBound())) {
            retorno=true;
        }
        return retorno;
    }
    
    
    public Rectangle getBound(){
        return new Rectangle(getX(), getY(), getSIZE(), getSIZE());
    }
    
    
    public void setXY(int x,int y){
        this.setX(x);
        this.setY(y);
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
     * @return the SIZE
     */
    public static int getSIZE() {
        return SIZE;
    }

    /**
     * @param aSIZE the SIZE to set
     */
    public static void setSIZE(int aSIZE) {
        SIZE = aSIZE;
    }

    /**
     * @return the rango
     */
    public int getRango() {
        return rango;
    }

    /**
     * @param rango the rango to set
     */
    public void setRango(int rango) {
        this.rango = rango;
    }


    /**
     * @return the rand
     */
    public Random getRand() {
        return rand;
    }

    /**
     * @param rand the rand to set
     */
    public void setRand(Random rand) {
        this.rand = rand;
    }

}
