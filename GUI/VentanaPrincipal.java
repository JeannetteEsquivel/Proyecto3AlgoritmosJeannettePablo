/*
 * To change this license header, choose License Headers in Project Properties.
 * To change this template file, choose Tools | Templates
 * and open the template in the editor.
 */
package GUI;

import Domain.Logica;
import Domain.Nodo;
import java.awt.Color;
import java.awt.Dimension;
import java.awt.Graphics;
import java.awt.Graphics2D;
import java.awt.event.ActionEvent;
import java.awt.event.ActionListener;
import java.awt.event.MouseEvent;
import java.awt.event.MouseListener;
import java.awt.image.BufferedImage;
import java.util.ArrayList;
import java.util.logging.Level;
import java.util.logging.Logger;
import javax.swing.JButton;
import javax.swing.JFrame;
import javax.swing.JPanel;

/**
 *
 * @author Jeannette
 */
public class VentanaPrincipal extends JPanel implements Runnable, MouseListener, ActionListener {

    //dimensiones
    public static final int WIDTH = 800;
    public static final int HEIGHT = 714;

    //doble buffer
    BufferedImage imagen;
    Graphics2D graphics2D;
    private Color color;
    //hilo de juego
    private Thread hilo;
    private boolean estado;
    private int FPS = 60;
    private long tiempo = 1000 / FPS;

    private ArrayList<PintarNodo> arrayNodo;
    private ArrayList<PintarAristaYPeso> arrayArista;
    private ArrayList<Nodo> arrayNodosTree;
    private Fondo fondo;

    private JFrame principal;

    private Logica grafo;

    private double posx;
    private double posy;

    private JButton boton;
    private JButton botonArista;
    private JButton btnMetodoPrhin;
    private JButton btnMetodoMonte;

    private JPanel panelBotones;

    public VentanaPrincipal(JFrame frame) {
        super();
        this.setLayout(null);
        this.posx = (int) (Math.random() * (700 - 0) + 0);
        this.posy = (int) (Math.random() * (500 - 0) + 0);
        iniciar();
        Dimension dim = super.getToolkit().getScreenSize();
        this.setPreferredSize(dim);
        this.setFocusable(true);
        this.requestFocus();
        this.arrayNodo = new ArrayList<>();
        this.arrayArista = new ArrayList<>();
        this.arrayNodosTree = new ArrayList<>();
        this.fondo = new Fondo();
        this.principal = frame;
        this.grafo = new Logica();
        this.addMouseListener(this);

    }//constructor

    @Override
    public void addNotify() {
        super.addNotify();
        if (this.hilo == null) {
            this.hilo = new Thread(this);
            this.hilo.start();
        }//if
    }//addnotify
/*inicializa los componentes graficos de la ventana*/
    public void iniciar() {

        panelBotones = new JPanel();
        panelBotones.setLayout(null);
        this.add(this.panelBotones);
        this.panelBotones.setBounds(800, 0, 600, 716);
        panelBotones.setBackground(Color.CYAN);
        boton = new JButton("Insertar letra en el nodo");
        panelBotones.add(this.boton);
        this.boton.setBounds(150, 50, 250, 30);
        this.boton.addActionListener(this);

        this.botonArista = new JButton("Insertar Arista");
        panelBotones.add(this.botonArista);
        this.botonArista.setBounds(150, 100, 250, 30);
        this.botonArista.addActionListener(this);

        this.btnMetodoPrhin = new JButton("Método Prim");
        panelBotones.add(this.btnMetodoPrhin);
        this.btnMetodoPrhin.setBounds(150, 150, 250, 30);
        this.btnMetodoPrhin.addActionListener(this);

        this.btnMetodoMonte = new JButton("Método Monte Carlo");
        panelBotones.add(this.btnMetodoMonte);
        this.btnMetodoMonte.setBounds(150, 200, 250, 30);
        this.btnMetodoMonte.addActionListener(this);
    }

    public void init() {
        this.imagen = new BufferedImage(WIDTH, HEIGHT, BufferedImage.TYPE_INT_RGB);
        this.graphics2D = (Graphics2D) this.imagen.getGraphics();
        this.estado = true;
    }//init

    private void draw() {
        this.fondo.draw(graphics2D);
        for (int i = 0; i < this.arrayArista.size(); i++) {
            this.arrayArista.get(i).draw(graphics2D);
        }//dibujar aristas
        for (int i = 0; i < this.arrayNodo.size(); i++) {
            this.arrayNodo.get(i).draw(graphics2D);
        }//dibujar vertices
    }//draw

    private void update() {
        this.fondo.update();
    }//update

    @Override
    public void run() {
        init();

        long start;
        long elapsed;
        long wait;
        while (estado) {
            start = System.nanoTime();
            update();
            draw();
            drawToScreen();

            elapsed = System.nanoTime() - start;
            wait = tiempo - elapsed / 1000000;

            if (wait < 0) {
                wait = 5;
            }//if

            try {
                Thread.sleep(wait);
            } catch (InterruptedException ex) {
                Logger.getLogger(VentanaPrincipal.class.getName()).log(Level.SEVERE, null, ex);
            }//try-catch//try-catch

        }//while
    }//run

    public void drawToScreen() {
        Graphics g = getGraphics();
        g.drawImage(imagen, 0, 0, null);
        g.dispose();
    }//drawToScreen

    public void pintarPrim() {
        for (int j = 0; j < this.arrayNodosTree.size(); j++) {
            try {
                Thread.sleep(1000);
            } catch (InterruptedException ex) {
                Logger.getLogger(VentanaPrincipal.class.getName()).log(Level.SEVERE, null, ex);
            }//try-catch//try-catch
            for (int i = 0; i < this.arrayNodo.size(); i++) {
                if (this.arrayNodosTree.get(j).getId().equals(this.arrayNodo.get(i).getLetter())) {
                    this.arrayNodo.get(i).setColor(this.color);
                }
            }
        }
    }
    public void pintarMonteCarlo() {
        for (int j = 0; j < this.arrayNodosTree.size(); j++) {
            try {
                Thread.sleep(1000);
            } catch (InterruptedException ex) {
                Logger.getLogger(VentanaPrincipal.class.getName()).log(Level.SEVERE, null, ex);
            }//try-catch//try-catch
            for (int i = 0; i < this.arrayNodo.size(); i++) {
                if (this.arrayNodosTree.get(j).getId().equals(this.arrayNodo.get(i).getLetter())) {
                    this.arrayNodo.get(i).setColor(Color.pink);
                }
            }
        }
    }
    
    public void rePintar() {
        for (int j = 0; j < this.arrayNodosTree.size(); j++) {
            try {
                Thread.sleep(1000);
            } catch (InterruptedException ex) {
                Logger.getLogger(VentanaPrincipal.class.getName()).log(Level.SEVERE, null, ex);
            }//try-catch//try-catch
            for (int i = 0; i < this.arrayNodo.size(); i++) {
                if (this.arrayNodosTree.get(j).getId().equals(this.arrayNodo.get(i).getLetter())) {
                    this.arrayNodo.get(i).setColor(new Color(80, 200, 100));
                }
            }
        }
    }

    public ArrayList<PintarNodo> getVertexs() {
        return arrayNodo;
    }//getThreads

    public ArrayList<PintarAristaYPeso> getEdges() {
        return arrayArista;
    }//getEdges

    public Logica getGraph() {
        return grafo;
    }//getGraph

    @Override
    public void mouseClicked(MouseEvent e) {
    }

    @Override
    public void mousePressed(MouseEvent e) {
        this.posx = e.getX();
        this.posy = e.getY();
        this.setFocusable(true);
    }//mousePressed

    @Override
    public void mouseReleased(MouseEvent e) {
    }

    @Override
    public void mouseEntered(MouseEvent e) {
    }

    @Override
    public void mouseExited(MouseEvent e) {
    }

    public double getPosx() {
        return posx;
    }

    public void setX(double x) {
        this.posx = x;
    }

    public double getPosy() {
        return posy;
    }

    public void setY(double y) {
        this.posy = y;
    }

    public ArrayList<Nodo> getTreeVertexs() {
        return arrayNodosTree;
    }

    public void setTreeVertexs(ArrayList<Nodo> treeVertexs) {
        this.arrayNodosTree = treeVertexs;
    }

    @Override
    public void actionPerformed(ActionEvent e) {
        if (e.getSource() == boton) {
            VentanaInsertarNodo insertVertex = new VentanaInsertarNodo(this.principal, true, this);
            insertVertex.setLocation(950, 350);
            insertVertex.setVisible(true);

        } else if (e.getSource() == botonArista) {
            VentanaInsertarArista insertNeighbor = new VentanaInsertarArista(this.principal, true, this);
            insertNeighbor.setLocation(950, 350);
            insertNeighbor.setVisible(true);

        } else if (e.getSource() == btnMetodoPrhin) {
            this.color = new Color(200, 200, 100);
            VentanaPrim prim = new VentanaPrim(this.principal, true, this);
            prim.setLocation(950, 350);
            prim.setVisible(true);
            pintarPrim();

        } else if (e.getSource() == btnMetodoMonte) {
            this.color = new Color(80, 0, 100);
            VentanaMonteCarlo monteCarlo = new VentanaMonteCarlo(this.principal, true, this);
            monteCarlo.setLocation(950, 350);
            monteCarlo.setVisible(true);
            pintarPrim();

        }
    }

}//clase
