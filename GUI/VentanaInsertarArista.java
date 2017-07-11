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
import javax.swing.JTextField;

/**
 *
 * @author Jeannette
 */
public class VentanaInsertarArista extends JDialog implements ActionListener {

    private JButton boton;
    private JLabel etiquetaLetra;
    private JTextField campoTexto1;
    private JLabel peso;
    private JTextField jtfPeso;
    private JLabel jlLetraFin;
    private JTextField jtfLetraFin;
    private VentanaPrincipal principal;

    public VentanaInsertarArista(JFrame parent, boolean modal, VentanaPrincipal graphPanel) {
        super(parent, modal);
        this.setLocation(0, 0);
        this.setTitle("Insertar arista");
        this.setSize(300, 300);
        this.setLayout(null);
        this.setResizable(false);
        init();
        this.principal = graphPanel;
    }//constructor
/*metodo inicia componentes */
    public void init() {
        
        this.etiquetaLetra = new JLabel("Letra Inicio:");
        this.etiquetaLetra.setBounds(50, 15, 120, 20);

        this.peso = new JLabel("Peso:");
        this.peso.setBounds(50, 155, 60, 20);

        this.jlLetraFin = new JLabel("Letra Destino:");
        this.jlLetraFin.setBounds(50, 85, 120, 20);

        this.campoTexto1 = new JTextField();
        this.campoTexto1.setBounds(170, 15, 100, 20);

        this.jtfPeso = new JTextField();
        this.jtfPeso.setBounds(170, 155, 100, 20);

        this.jtfLetraFin = new JTextField();
        this.jtfLetraFin.setBounds(170, 85, 100, 20);

        this.boton = new JButton("Insertar");
        this.boton.setBounds(100, 220, 100, 25);
        this.boton.addActionListener(this);

        this.add(this.etiquetaLetra);
        this.add(this.campoTexto1);
        this.add(this.peso);
        this.add(this.jtfPeso);
        this.add(this.jlLetraFin);
        this.add(this.jtfLetraFin);
        this.add(this.boton);

    }//init

    public void create() {
        ArrayList<PintarNodo> Vthreads = this.principal.getVertexs();
        PintarNodo vertexOrigin = null;
        PintarNodo vertexDestiny = null;
        int create = 0;
        for (int i = 0; i < Vthreads.size(); i++) {
            if (Vthreads.get(i).getLetter().equals(this.campoTexto1.getText())) {
                vertexOrigin = Vthreads.get(i);
                create++;
            }//if para buscar el nodo origen
        }//recorrer la lista de nodos
        for (int i = 0; i < Vthreads.size(); i++) {
            if (Vthreads.get(i).getLetter().equals(this.jtfLetraFin.getText())) {
                vertexDestiny = Vthreads.get(i);
                create++;
            }//if para buscar el nodo destino
        }//recorrer la lista de nodos
        if (create == 2) {
            this.principal.getGraph().insertNeighbor(vertexOrigin.getLetter(), Integer.parseInt(this.jtfPeso.getText()), vertexDestiny.getLetter());
            this.principal.getGraph().insertNeighbor(vertexDestiny.getLetter(), Integer.parseInt(this.jtfPeso.getText()), vertexOrigin.getLetter());
            PintarAristaYPeso edge = new PintarAristaYPeso(vertexOrigin.getX(), vertexOrigin.getY(), vertexDestiny.getX(), vertexDestiny.getY(), this.principal, this.jtfPeso.getText());
            this.principal.getEdges().add(edge);
            edge.start();
            this.dispose();
        }//si se puede crear el vecino
        if (vertexOrigin == null) {
            this.campoTexto1.setText("");
        }//if
        if (vertexDestiny == null) {
            this.jtfLetraFin.setText("");
        }//if
    }//create

    @Override
    public void actionPerformed(ActionEvent e) {
        if (e.getSource() == this.boton) {
            if (!this.campoTexto1.getText().isEmpty() && !this.jtfPeso.getText().isEmpty() && !this.jtfLetraFin.getText().isEmpty()) {
                create();
            }//validar campos de texto
        }//boton
    }//actionPerformed

}//class
