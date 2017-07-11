/*
 * To change this license header, choose License Headers in Project Properties.
 * To change this template file, choose Tools | Templates
 * and open the template in the editor.
 */
package GUI;

import java.awt.Color;
import java.awt.event.ActionEvent;
import java.awt.event.ActionListener;
import javax.swing.JButton;
import javax.swing.JDialog;
import javax.swing.JFrame;
import javax.swing.JLabel;
import javax.swing.JTextField;

/**
 *
 * @author Pablo
 */
public class VentanaPrim extends JDialog implements ActionListener {

    private JButton boton;
    private JLabel etiqueta;
    private JTextField campooTexto;
    private VentanaPrincipal principal;

    public VentanaPrim(JFrame parent, boolean modal, VentanaPrincipal graphPanel) {
        super(parent, modal);
        this.setTitle("Metodo Prim");
        this.setSize(300, 300);
        this.setLayout(null);
        this.setResizable(false);
        init();
        this.principal = graphPanel;
    }//constructor

    public void init() {

        this.etiqueta = new JLabel("Iniciar del nodo:");
        this.etiqueta.setBounds(30, 50, 150, 20);

        this.campooTexto = new JTextField();
        this.campooTexto.setBounds(170, 50, 100, 20);

        this.boton = new JButton("Iniciar");
        this.boton.setBounds(100, 130, 100, 30);
        this.boton.addActionListener(this);

        this.add(this.etiqueta);
        this.add(this.campooTexto);
        this.add(this.boton);

    }//init

    @Override
    public void actionPerformed(ActionEvent e) {
        if (e.getSource() == this.boton) {
            if (!this.campooTexto.getText().isEmpty()) {
                this.principal.setTreeVertexs(this.principal.getGraph().prim(this.campooTexto.getText()));
                this.dispose();
            }
        }
    }

}//class
