package serpiente;


import java.awt.Color;
import java.awt.Graphics2D;
import java.awt.event.KeyEvent;
import java.util.ArrayList;
import recursos.Resource;

/*
 * To change this template, choose Tools | Templates
 * and open the template in the editor.
 */

/**
 *
 * @author 
 */
public class Serpiente{
    private int x=240;
    private int y=240;
    private String direccion="izq"; // 1=arriba - 2 =abajo 3=izq 4=der 
    private int largo=5;
    private int velocidad=200;
    private static int AVANCE=12;
    private Color borde;
    private Color interno;

//    private boolean isDead=false;
//    private int speed=;
    private CabezaSerpiente  cabeza=new CabezaSerpiente(240,240);
    private ArrayList<SegmentoSerpiente> cuerpo=new ArrayList<SegmentoSerpiente>();

    public Serpiente() {
        for (int i = 0; i < largo; i++) {
            cuerpo.add(new SegmentoSerpiente());
        }
    }
    
    public void move(){
//        Resource.MOVE.play();
        if (getDireccion().equals("arriba")) {
            setX(getCabeza().getX());
            setY(getCabeza().getY());
           
            getCabeza().setXY(getX(), getY()-getAVANCE());

            for (int i=0;i<getCuerpo().size();i++) {
                int xAnt=getCuerpo().get(i).getX();
                int yAnt=getCuerpo().get(i).getY();
                String dirAnt=getCuerpo().get(i).getDireccion();
                getCuerpo().get(i).setXY(getX(), getY());
                setX(xAnt);
                setY(yAnt);
             }
        }
        
        
        if (getDireccion().equals("abajo")) {
            setX(getCabeza().getX());
            setY(getCabeza().getY());
            
            getCabeza().setXY(getX(), getY()+getAVANCE());
            for (int i=0;i<getCuerpo().size();i++) {
                int xAnt=getCuerpo().get(i).getX();
                int yAnt=getCuerpo().get(i).getY();
                getCuerpo().get(i).setXY(getX(), getY());
                setX(xAnt);
                setY(yAnt);
            }
        }
        
        
        if (getDireccion().equals("izq")) {
            setX(getCabeza().getX());
            setY(getCabeza().getY());
            
            getCabeza().setXY(getX()-getAVANCE(), getY());
            
            for (int i=0;i<getCuerpo().size();i++) {
                int xAnt=getCuerpo().get(i).getX();
                int yAnt=getCuerpo().get(i).getY();
                getCuerpo().get(i).setXY(getX(), getY());
                setX(xAnt);
                setY(yAnt);
            }
        }
        
        
        if (getDireccion().equals("der")) {
            setX(getCabeza().getX());
            setY(getCabeza().getY());
            
            getCabeza().setXY(getX()+getAVANCE(), getY());
            for (int i=0;i<getCuerpo().size();i++) {
                int xAnt=getCuerpo().get(i).getX();
                int yAnt=getCuerpo().get(i).getY();
                getCuerpo().get(i).setXY(getX(), getY());
                setX(xAnt);
                setY(yAnt);
            }
        }    
    }
    
    
    public boolean isdead(){
        boolean dead=false;
        int cabezaX=getCabeza().getX();
        int cabezaY=getCabeza().getY();
        if (cabezaY>479-12 || cabezaY<0 || cabezaX>479-12 || cabezaX<0) {
            dead=true;
        }
        for (int i = 0; i < getCuerpo().size() && !dead; i++) {
            if (getCabeza().getBound().intersects(getCuerpo().get(i).getBound())) {
                dead=true;
            }
        }
        return dead;
    }
    
    
    public void crecer(int n){
            for (int i = 0; i < n; i++) {
                getCuerpo().add(new SegmentoSerpiente());
            }
        
    }
    
    
    public void encoger(int n){
        if(getCuerpo().size()>n){
            int cont=n;
            for (int i = getCuerpo().size()-1;cont>0 ; i--) {
                getCuerpo().remove(getCuerpo().get(i));
                cont--;
            }
        }
    }
    
    public void getColor(){
        interno=Color.gray;
        borde=Color.darkGray;
        if(getVelocidad()<40){
            interno=Color.RED;
            borde=new Color(200,0,30);
        }else {
            if(getVelocidad()<70){
                interno=new Color(255,210 , 0);
                borde=new Color(255,160 , 0);
            }else {
                if(getVelocidad()<120){
                    borde=Resource.COLOR_VERDE;
                    interno=Color.GREEN;
                }else {
                    if(getVelocidad()<160){
                        borde=Color.BLUE;
                        interno=new Color(150, 150, 255);
                    }
                }
            }
        }
    }
    public void paint(Graphics2D g){
//        g.setColor(getColor());
        getColor();
        getCabeza().paint(g);
        for (SegmentoSerpiente seg : getCuerpo()) {
//        g.setColor(getColor());
            seg.paint(g,borde,interno);
        }
        
    }

    public void keyPressed(KeyEvent e) {
            //Tecla mover derecha
        if (e.getKeyCode()==KeyEvent.VK_LEFT && !(getDireccion().equals("der"))) {
            setDireccion("izq");
        }
        //tecla mover arriba
        if (e.getKeyCode()==KeyEvent.VK_DOWN && !(getDireccion().equals("arriba"))) {
            setDireccion("abajo");
        }
        
        //mover abajo
        if (e.getKeyCode()==KeyEvent.VK_UP && !(getDireccion().equals("abajo"))) {
            setDireccion("arriba");
        }
        
        //mover izq
        if (e.getKeyCode()==KeyEvent.VK_RIGHT && !(getDireccion().equals("izq"))) {
            setDireccion("der");
        }
        
        // acelerar
        if (e.getKeyCode()==KeyEvent.VK_SPACE) {
            setVelocidad(getVelocidad()-5);
        }
        if (e.getKeyCode()==KeyEvent.VK_G) {
            crecer(1);
        }
        if (e.getKeyCode()==KeyEvent.VK_M) {
            encoger(3);
        }
    }
    
    
    public String getDireccion() {
        return direccion;
    }

    /**
     * @param direccion the direccion to set
     */
    public void setDireccion(String direccion) {
        this.direccion = direccion;
    }

    /**
     * @return the largo
     */
    public int getLargo() {
        return largo;
    }

    /**
     * @param largo the largo to set
     */
    public void setLargo(int largo) {
        this.largo = largo;
    }

    /**
     * @return the velocidad
     */
    public int getVelocidad() {
        return velocidad;
    }

    /**
     * @param velocidad the velocidad to set
     */
    public void setVelocidad(int velocidad) {
        this.velocidad = velocidad;
    }

    /**
     * @return the cabeza
     */
    public CabezaSerpiente getCabeza() {
        return cabeza;
    }

    /**
     * @param cabeza the cabeza to set
     */
    public void setCabeza(CabezaSerpiente cabeza) {
        this.cabeza = cabeza;
    }

    /**
     * @return the cuerpo
     */
    public ArrayList<SegmentoSerpiente> getCuerpo() {
        return cuerpo;
    }

    /**
     * @param cuerpo the cuerpo to set
     */
    public void setCuerpo(ArrayList<SegmentoSerpiente> cuerpo) {
        this.setCuerpo(cuerpo);
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
     * @return the AVANCE
     */
    public static int getAVANCE() {
        return AVANCE;
    }

    /**
     * @param aAVANCE the AVANCE to set
     */
    public static void setAVANCE(int aAVANCE) {
        AVANCE = aAVANCE;
    }

    /**
     * @return the borde
     */
    public Color getBorde() {
        return borde;
    }

    /**
     * @param borde the borde to set
     */
    public void setBorde(Color borde) {
        this.borde = borde;
    }

    /**
     * @return the interno
     */
    public Color getInterno() {
        return interno;
    }

    /**
     * @param interno the interno to set
     */
    public void setInterno(Color interno) {
        this.interno = interno;
    }

    

}
