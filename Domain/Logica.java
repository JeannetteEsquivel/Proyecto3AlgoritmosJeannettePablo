
/*
 * To change this license header, choose License Headers in Project Properties.
 * To change this template file, choose Tools | Templates
 * and open the template in the editor.
 */
package Domain;

import java.util.ArrayList;
import java.util.Collections;

/**
 *
 * @author Jeannette
 */
public class Logica {

    public Nodo startNode;
    public Logica grafo;
    public int cont = 0;

    public Logica() {
        this.startNode = null;
    }//Constructor

    public ArrayList<Nodo> monteCarlo(String id) {
        Nodo NodoId = getNode(id);
        
        ArrayList<Nodo> tree = new ArrayList<>();
        
        while (NodoId != null) {
            
            NodoId.visited = 1;
            tree.add(NodoId);
            Vecino neighbor = NodoId.startNeighbor;
            ArrayList<Vecino> neighbors = new ArrayList<>();
            while (neighbor != null) {
                if (getNode(neighbor.id).visited != 1) {
                    neighbors.add(neighbor);
                }
                neighbor = neighbor.nextNeighbor;
            }//obtener el peso de sus vecinos
            int index = (int) (Math.random() * neighbors.size());
            String neighborSearch = "";
            if (neighbors.size() > 0) {
                neighborSearch = neighbors.get(index).id;
            }//validar los vecinos disponibles
            if (!neighborSearch.equals("")) {
                NodoId = getNode(neighborSearch);
                if (NodoId.visited != 0) {
                    NodoId = null;
                }//saber si el nodo que se llego ya fue visitado
            } else {
                NodoId = null;
            }//if-else si puede avanzar a otro nodo o no
        }//while para recorrer los nodos del grafo
        return tree;
    }//monteCarlo

    public ArrayList<Nodo> prim(String id) {
        Nodo start = getNode(id);
        ArrayList<Nodo> tree = new ArrayList<>();
        while (start != null) {
            start.visited = 1;
            tree.add(start);
            ArrayList<Integer> costs = new ArrayList<>();
            Vecino neighbor = start.startNeighbor;
            while (neighbor != null) {
                costs.add(neighbor.cost);
                neighbor = neighbor.nextNeighbor;
            }//validar los pesos de sus vecinos
            Collections.sort(costs);
            String neighborSearch = compareCosts(costs, start);
            if (!neighborSearch.equals("")) {
                start = getNode(neighborSearch);
                if (start.visited != 0) {
                    start = null;
                }//saber si el nodo que se llego ya fue visitado
            } else {
                start = null;
            }//if-else si puede avanzar a otro nodo o no
        }//while para recorrer los nodos del grafo
        return tree;
    }//prim
    
    
    public void insertGraphNode(String id) {
        //cantidadNodos++;
        Nodo newNode = new Nodo(id, 0);

        Nodo temp = startNode;
        newNode.nextNode = temp;
        startNode = newNode;
    }//insertGraphNode

    public void insertNeighbor(String idNode, int costNeighbor, String idNeighbor) {//insertaarista
        Nodo node = getNode(idNode);
        if (node != null) {
            //cantidadAristas++;
            Vecino newNeighbor = new Vecino(idNeighbor, costNeighbor);

            Vecino ntemp = node.startNeighbor;
            newNeighbor.nextNeighbor = ntemp;
            node.startNeighbor = newNeighbor;
        }//validar el nodo que se obtiene
    }//insertNeighbor
    
    public Nodo getNode(String id) {
        Nodo currentNode = startNode;
        while (currentNode != null && !currentNode.id.equals(id)) {
            currentNode = currentNode.nextNode;
        }//buscar el nodo
        return currentNode;
    }//getNode

    public String compareCosts(ArrayList<Integer> costs, Nodo start) {
        Vecino neighbor = null;
        String neighborSearch = "";
        int countNeighbor = 0;
        while (countNeighbor < costs.size()) {
            neighbor = start.startNeighbor;
            while (neighbor != null) {
                if (neighbor.cost == costs.get(countNeighbor)) {
                    neighborSearch = neighbor.id;
                    if (getNode(neighborSearch).visited == 0) {
                        countNeighbor = costs.size();
                        neighbor = null;
                    }//validar si ya fue visitado
                }//validar si el pesos es el minimo
                if (neighbor != null) {
                    neighbor = neighbor.nextNeighbor;
                }//validar si el vecino es nulo para que siga
            }//recorrer los vecinos
            countNeighbor++;
        }//while para recorrer los costos
        return neighborSearch;
    }//compareCosts

    public boolean areNeighbor(String u, String t) {//preguntan si estan a la par
        Nodo temp = startNode;

        while (temp != null) {
            if (temp.id.equals(u)) {
                Vecino tempNeighbor = temp.startNeighbor;
                while (tempNeighbor != null) {
                    if (tempNeighbor.id.equals(t)) {
                        return true;
                    } else {
                        tempNeighbor = tempNeighbor.nextNeighbor;
                    }
                }
            }
            temp = temp.nextNode;
        }//while
        return false;
    }//areNeighbor

    public int calculateWeight(String w, String s) {
        Nodo temp = startNode;
        while (temp != null) {
            if (temp.id.equals(w)) {
                Vecino neighbor = temp.startNeighbor;
                while (neighbor != null) {
                    if (s.equals(neighbor.id)) {
                        return neighbor.cost;
                    }
                    neighbor = neighbor.nextNeighbor;
                }
            }
            temp = temp.nextNode;
        }//while
        return 0;
    }//calculateWeight
    
    public Nodo getMinimunVertex() {
        Nodo temp = this.startNode;

        Nodo minimun = temp;
        while (temp != null) {

            if (minimun.distance > temp.distance && !temp.viewed) {
                minimun = temp;
            }//preguntamos si el minimo es mayor que el siguiente para asi darle a minimo su nuevo valor

            temp = temp.nextNode;

            if (minimun.viewed) {
                temp = startNode;
                minimun = temp.nextNode;
            }//por si el primer fue menor a todos y esta viewed que vuelva a retomar el while
        }//while
        return minimun;
    }//getMinimunVertex

}//Clase
