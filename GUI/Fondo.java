/*
 * To change this license header, choose License Headers in Project Properties.
 * To change this template file, choose Tools | Templates
 * and open the template in the editor.
 */
package GUI;

import java.awt.Graphics2D;
import java.awt.image.BufferedImage;
import java.io.IOException;
import java.util.logging.Level;
import java.util.logging.Logger;
import javax.imageio.ImageIO;

/**
 *
 * @author Jeannette
 */
public class Fondo {

    private BufferedImage imagen;
    private int x;
    private int y;
    private int tempx;
    private int tempy;

    public Fondo() {
        try {
            this.imagen = ImageIO.read(getClass().getResourceAsStream("/assets/bg.jpg"));
        } catch (IOException ex) {
            Logger.getLogger(Fondo.class.getName()).log(Level.SEVERE, null, ex);
        }//try-catch//try-catch
    }//constructor

    public void setPosition(int x, int y) {
        this.x = x;
        this.y = y;
    }

    public void setVector(int dx, int dy) {
        this.tempx += dx;
        this.tempy += dy;
    }

    public void update() {
        this.x += tempx;
        this.y += tempy;
    }// update

    public void draw(Graphics2D g2) {
        g2.drawImage(this.imagen, this.x, this.y, null);
    }//draw

}//class
