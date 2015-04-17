/*

* To change this template, choose Tools | Templates
 * and open the template in the editor.
 */

package game;

import powerup.PowerUp;
import java.awt.Color;
import java.awt.Graphics;
import java.awt.Graphics2D;
import java.awt.Menu;
import java.awt.MenuItem;
import java.awt.event.KeyEvent;
import java.awt.event.KeyListener;
import java.math.BigDecimal;
import javax.swing.JFrame;
import javax.swing.JLabel;
import javax.swing.JMenu;
import javax.swing.JMenuBar;
import javax.swing.JOptionPane;
import javax.swing.JPanel;
import serpiente.Serpiente;
import recursos.Resource;

/**
 *
 * @author 
 */
public class Game extends JPanel{
    private Serpiente serpiente;
    private PowerUp comida;
    private boolean pause;
    private BigDecimal score;
    private boolean gameOver;
    JLabel countScore;
    JLabel labelSpeed;
    JLabel posSer;
//    Mapa mapa;
    
    public Game() throws InterruptedException {
        KeyListener keyListener=new KeyListener() {

            @Override
            public void keyTyped(KeyEvent e) {
            }

            @Override
            public void keyPressed(KeyEvent e) {
                    // TECLAS PARA MOVER SERPIENTE
                    if (e.getKeyCode()==KeyEvent.VK_LEFT || e.getKeyCode()==KeyEvent.VK_DOWN || e.getKeyCode()==KeyEvent.VK_UP || e.getKeyCode()==KeyEvent.VK_RIGHT) {
                    serpiente.keyPressed(e);
                    }
                    if (e.getKeyCode()==KeyEvent.VK_ENTER) {setPause(!pause);}
                    if (e.getKeyCode()==KeyEvent.VK_SPACE) {
                        serpiente.keyPressed(e);
                    }
                    if (e.getKeyCode()==KeyEvent.VK_G) {
                        serpiente.keyPressed(e);
                    }
                    if (e.getKeyCode()==KeyEvent.VK_M) {
                        serpiente.keyPressed(e);
                    }
            }

            @Override
            public void keyReleased(KeyEvent e) {
            }
        };
        JFrame frmGame=new JFrame("Viborita");
            setBackground(Color.white);
            frmGame.setSize(480, 533);
            frmGame.setLocationRelativeTo(null);
            frmGame.add(this);
            frmGame.setVisible(true);
            frmGame.setDefaultCloseOperation(JFrame.EXIT_ON_CLOSE);
            frmGame.addKeyListener(keyListener);
            frmGame.setFocusable(true);
            JMenuBar barraMenu=new JMenuBar();
            JMenu mnuNivel1=new JMenu("Nivel 1");
            barraMenu.add(mnuNivel1);
            frmGame.add(barraMenu);
        do {
            initVariables();
            Thread.sleep(200);
            Resource.MUSIC_BACK.loop();
            addLabels();
            
            while (!gameOver) {    
                while (pause) {/*loop pausa*/}
                if (serpiente.isdead()) {
                    gameOver=true;
                    Resource.MUSIC_BACK.stop();
                }else{
                    actualizarObj();
                    actualizarLabels();
                    repaint();
                    Thread.sleep(serpiente.getVelocidad());
                }
            }
            Resource.GAME_OVER.play();
            
            
            gameOver=true;
            removeLabels();
            JOptionPane.showMessageDialog(this, "Game Over"+ "Score= "+score);
            deleteVar();
        } while (true);
//        Thread.sleep(5000);
    }

    public void initVariables(){
        setSerpiente(new Serpiente());
        setComida(new PowerUp());
        setScore(BigDecimal.valueOf(1));
        setPause(false);
        setGameOver(false);
        countScore=new JLabel();
        labelSpeed=new JLabel();
        posSer=new JLabel();
    }
    
    public void addLabels(){
        this.add(countScore);
//        this.add(labelSpeed);
//        this.add(comida.getImg());
        this.add(posSer);
        
    }
    public void removeLabels(){
        this.remove(countScore);
//        this.remove(labelSpeed);
//        this.remove(comida.getImg());
        this.remove(posSer);
    }
    
    public void actualizarLabels(){
        countScore.setText(String.valueOf(score.doubleValue()));
        posSer.setText("x= "+serpiente.getX()+" y= "+serpiente.getY());
//        labelSpeed.setText(String.valueOf(serpiente.getVelocidad()));
    }
    public void actualizarObj() throws InterruptedException{
        serpiente.move();
        Thread.sleep(4);
        comida.aplicarBonus(this);
    }
    
    @Override
    public void paint(Graphics g) {
        super.paint(g);
        g.drawImage(Resource.IMG_FONDO, 0, 0, null);
        Graphics2D g2=(Graphics2D)g;
        getSerpiente().paint(g2);
        getComida().paint(g2);
        countScore.paint(g2);
    }

    public void deleteVar(){
        setSerpiente(null);
        setComida(null);
        countScore=null;
        labelSpeed=null;
    }
    /**
     * @return the serpiente
     */
    public Serpiente getSerpiente() {
        return serpiente;
    }

    /**
     * @param serpiente the serpiente to set
     */
    public void setSerpiente(Serpiente serpiente) {
        this.serpiente = serpiente;
    }

    /**
     * @return the comida
     */
    public PowerUp getComida() {
        return comida;
    }

    /**
     * @param comida the comida to set
     */
    public void setComida(PowerUp comida) {
        this.comida = comida;
    }

    /**
     * @return the score
     */
    public BigDecimal getScore() {
        return score;
    }

    /**
     * @param score the score to set
     */
    public void setScore(BigDecimal score) {
        this.score=score;
    }

    /**
     * @return the pause
     */
    public boolean isPause() {
        return pause;
    }

    /**
     * @param pause the pause to set
     */
    public void setPause(boolean pause) {
        this.pause = pause;
    }

    /**
     * @return the gameOver
     */
    public boolean isGameOver() {
        return gameOver;
    }

    /**
     * @param gameOver the gameOver to set
     */
    public void setGameOver(boolean gameOver) {
        this.gameOver = gameOver;
    }

    

    
    
}
