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
 * @author Jeannette
 */
public class PintarNodo extends Thread {

    private Graphics2D graphics2D;
    private double x;
    private double y;

    private double movimientoVelocidad;
    private int velocidad;
    private boolean ejecutar;

    private VentanaPrincipal mp;
    private String letra = "";
    private Color color;

    public PintarNodo(double x, double y, VentanaPrincipal mp, String letter) {
        this.mp = mp;
        this.x = x;
        this.y = y;
        this.velocidad = 7;
        this.movimientoVelocidad = 0.3;
        this.ejecutar = true;
        this.letra = letter;
        this.color = new Color(80, 200, 100);
    }//constructor

    @Override
    public void run() {
        while (this.ejecutar) {
            try {
                Thread.sleep(this.velocidad);
            } catch (InterruptedException ex) {
                Logger.getLogger(PintarNodo.class.getName()).log(Level.SEVERE, null, ex);
            }//try-catch//try-catch
        }//while
    }//run

    public void draw(Graphics2D g2) {
        if (this.ejecutar) {
            g2.setColor(this.color);
            g2.fillOval((int) this.x, (int) this.y, 50, 50);
            g2.setColor(Color.white);
            g2.drawString(this.letra, (int) this.x + 20, (int) this.y + 25);
        }//if
    }//draw

    public String getLetter() {
        return letra;
    }

    public void setLetter(String letter) {
        this.letra = letter;
    }

    public double getX() {
        return x;
    }

    public void setX(double x) {
        this.x = x;
    }

    public double getY() {
        return y;
    }

    public void setY(double y) {
        this.y = y;
    }

    public Color getColor() {
        return color;
    }

    public void setColor(Color color) {
        this.color = color;
    }

}//class
