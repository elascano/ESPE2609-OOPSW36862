/*
 * Click nbfs://nbhost/SystemFileSystem/Templates/Licenses/license-default.txt to change this license
 * Click nbfs://nbhost/SystemFileSystem/Templates/Classes/Class.java to edit this template
 */
package ec.edu.espe.chickenfarmapp.model;

/**
 *
 * @author Renata Olalla, Binary Bosses, @ESPE
 */
public class ChickenCoop {
private int chickenCoopId;
private Chicken chickens[]= new Chicken[10];


public void add(Chicken chicken){
    //if we have less than 10 chickens we can add one more, else we cant't add anymore
    

}
public ChickenCoop(int chickenCoopId, Chicken[] chickens) {
this.chickenCoopId = chickenCoopId;
this.chickens = chickens;
}

    /**
     * @return the chickenCoopId
     */
    public int getChickenCoopId() {
        return chickenCoopId;
    }

    /**
     * @param chickenCoopId the chickenCoopId to set
     */
    public void setChickenCoopId(int chickenCoopId) {
        this.chickenCoopId = chickenCoopId;
    }

    /**
     * @return the chickens
     */
    public Chicken[] getChickens() {
        return chickens;
    }

    /**
     * @param chickens the chickens to set
     */
    public void setChickens(Chicken[] chickens) {
        this.chickens = chickens;
    }
}
