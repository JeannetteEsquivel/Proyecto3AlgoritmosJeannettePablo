/*
 * To change this license header, choose License Headers in Project Properties.
 * To change this template file, choose Tools | Templates
 * and open the template in the editor.
 */
package Domain;

/**
 *
 * @author Pablo
 */
public class Nodo {

    public String id;
    public int visited;
    public Vecino startNeighbor;
    public Nodo nextNode;
    public boolean viewed;
    public int distance;

    public Nodo(String id, int visited) {
        this.id = id;
        this.visited = visited;
        this.startNeighbor = null;
        this.nextNode = null;
        this.distance = 0;
        this.viewed = false;
    }//Constructor

    public String getId() {
        return id;
    }

    public void setId(String id) {
        this.id = id;
    }

}//Clase
