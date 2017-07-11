/*
 * To change this license header, choose License Headers in Project Properties.
 * To change this template file, choose Tools | Templates
 * and open the template in the editor.
 */
package GUI;

import java.awt.event.ActionEvent;
import java.awt.event.ActionListener;
import java.util.ArrayList;
import javax.swing.JButton;
import javax.swing.JDialog;
import javax.swing.JFrame;
import javax.swing.JLabel;
import javax.swing.JOptionPane;
import javax.swing.JTextField;

/**
 *
 * @author Pablo
 */
public class VentanaInsertarNodo extends JDialog implements ActionListener {

    private JButton jbtnInsert;
    private JLabel jlLetter;
    private JTextField jtfLetter;
    private VentanaPrincipal graphPanel;

    public VentanaInsertarNodo(JFrame parent, boolean modal, VentanaPrincipal graphPanel) {
        super(parent, modal);
        this.setTitle("Insertar una letra en el nodo");
        this.setSize(300, 300);
        this.setLayout(null);
        this.setResizable(false);
        init();
        this.graphPanel = graphPanel;
    }//constructor

    public void init() {

        this.jlLetter = new JLabel("Letra:");
        this.jlLetter.setBounds(50, 50, 50, 20);

        this.jtfLetter = new JTextField();
        this.jtfLetter.setBounds(160, 50, 100, 20);

        this.jbtnInsert = new JButton("Insertar");
        this.jbtnInsert.setBounds(100, 150, 100, 30);
        this.jbtnInsert.addActionListener(this);

        this.add(this.jlLetter);
        this.add(this.jtfLetter);
        this.add(this.jbtnInsert);

    }//init

    public void create() {
        if (jtfLetter.getText().length() < 2) {
            ArrayList<PintarNodo> threads = this.graphPanel.getVertexs();
            boolean create = true;
            for (int i = 0; i < threads.size(); i++) {
                if (threads.get(i).getLetter().equals(this.jtfLetter.getText())) {
                    create = false;
                }//if para validar el valor de vertice
            }//validar el nuevo nodo
            if (create) {
                this.graphPanel.getGraph().insertGraphNode(this.jtfLetter.getText());
                PintarNodo vertex = new PintarNodo(this.graphPanel.getPosx(), this.graphPanel.getPosy(), this.graphPanel, this.jtfLetter.getText());
                this.graphPanel.getVertexs().add(vertex);
                vertex.start();
                this.dispose();
            }//si el nodo se puede crear
            this.jtfLetter.setText("");
        }else JOptionPane.showMessageDialog(null, "Ingrese solo char");
    }//create

    @Override
    public void actionPerformed(ActionEvent e) {
        if (e.getSource() == this.jbtnInsert) {
            if (!this.jtfLetter.getText().isEmpty()) {
                create();
            }//validar el campo de texto
        }//boton
    }//actionPerformed

}//class
