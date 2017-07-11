/*
 * To change this license header, choose License Headers in Project Properties.
 * To change this template file, choose Tools | Templates
 * and open the template in the editor.
 */
package proyecto3algoritmosjeannettepablo;

import GUI.VentanaPrincipal;
import javax.swing.JFrame;

/**
 *
 * @author jeannette
 */
public class Proyecto3AlgoritmosJeannettePablo {

    /**
     * @param args the command line arguments
     */
    public static void main(String[] args) {
        JFrame frame = new JFrame();
        frame.setTitle("Familia de algoritmos");
        frame.setDefaultCloseOperation(JFrame.EXIT_ON_CLOSE);
        frame.add(new VentanaPrincipal(frame));
        frame.pack();
        frame.setLocationRelativeTo(null);
        frame.setVisible(true);
    }
    
}
