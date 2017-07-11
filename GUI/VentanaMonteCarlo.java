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
 * @author Jeannette
 */
public class VentanaMonteCarlo extends JDialog implements ActionListener {

    private JButton boton;
    private JLabel etiqueta;
    private JTextField campoTexto;
    private VentanaPrincipal principal;

    public VentanaMonteCarlo(JFrame parent, boolean modal, VentanaPrincipal graphPanel) {
        super(parent, modal);
        this.setTitle("MonteCarlo");
        this.setSize(300, 300);
        this.setLayout(null);
        this.setResizable(false);
        init();
        this.principal = graphPanel;
    }//constructor
/*iicializa las  componentes graficos*/
    public void init() {

        this.etiqueta = new JLabel("Iniciar del nodo:");
        this.etiqueta.setBounds(20, 40, 150, 20);

        this.campoTexto = new JTextField();
        this.campoTexto.setBounds(160, 40, 100, 20);

        this.boton = new JButton("Iniciar");
        this.boton.setBounds(100, 120, 100, 30);
        this.boton.addActionListener(this);

        this.add(this.etiqueta);
        this.add(this.campoTexto);
        this.add(this.boton);

    }//init

    @Override
    public void actionPerformed(ActionEvent e) {
        if (e.getSource() == this.boton) {
            if (!this.campoTexto.getText().isEmpty()) {
                this.principal.setTreeVertexs(this.principal.getGraph().monteCarlo(this.campoTexto.getText()));
                this.dispose();
            }
        }
    }

}//class
