/*
 * To change this license header, choose License Headers in Project Properties.
 * To change this template file, choose Tools | Templates
 * and open the template in the editor.
 */
package Domain;

/**
 *
 * @author Jeannette
 */
public class Vecino {

    public String id;
    public int cost;
    public Vecino nextNeighbor;

    public Vecino(String id, int cost) {
        this.id = id;
        this.cost = cost;
        this.nextNeighbor = null;
    }//Constructor

    public String getId() {
        return id;
    }

    public void setId(String id) {
        this.id = id;
    }

    public int getCost() {
        return cost;
    }

    public void setCost(int cost) {
        this.cost = cost;
    }

    public Vecino getNextNeighbor() {
        return nextNeighbor;
    }

    public void setNextNeighbor(Vecino nextNeighbor) {
        this.nextNeighbor = nextNeighbor;
    }

    
    
}//Class
