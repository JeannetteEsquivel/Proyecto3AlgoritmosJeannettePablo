/*
 * To change this license header, choose License Headers in Project Properties.
 * To change this template file, choose Tools | Templates
 * and open the template in the editor.
 */
package GUI;

import java.awt.Color;
import java.awt.Graphics2D;
import java.util.logging.Level;
import java.util.logging.Logger;

/**
 *
 * @author Pablo
 */
public class PintarAristaYPeso extends Thread {

    private Graphics2D graphics2D;
    private double x;
    private double y;
    private double tempX;
    private double tempY;

    private double movimientoVelocidad;
    private int velocidad;
    private boolean verifica;

    private VentanaPrincipal mp;

    private String peso = "";

    public PintarAristaYPeso(double originX, double originY, double destinyX, double destinyY, VentanaPrincipal mp, String weight) {
        this.mp = mp;
        this.x = originX;
        this.y = originY;
        this.tempX = destinyX;
        this.tempY = destinyY;
        this.velocidad = 7;
        this.movimientoVelocidad = 0.3;
        this.verifica = true;
        this.peso = weight;
    }//constructor

    @Override
    public void run() {
        while (this.verifica) {
            try {
                Thread.sleep(this.velocidad);
            } catch (InterruptedException ex) {
                Logger.getLogger(PintarAristaYPeso.class.getName()).log(Level.SEVERE, null, ex);
            }//try-catch//try-catch
        }//while
    }//run

    public void draw(Graphics2D g2) {
        if (this.verifica) {
            g2.setColor(Color.black);
            g2.drawLine((int) this.x + 15, (int) this.y + 15, (int) this.tempX + 15, (int) this.tempY + 15);
            g2.setColor(Color.black);
            g2.drawString(this.peso, (int) (this.x + this.tempX) / 2+10, (int) (this.y + this.tempY) / 2+10);
        }//if
    }//draw

    public String getLetter() {
        return peso;
    }//getLetter

    public void setLetter(String letter) {
        this.peso = letter;
    }//setLetter

}//class
