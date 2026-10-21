/*
 * Click nbfs://nbhost/SystemFileSystem/Templates/Licenses/license-default.txt to change this license
 * Click nbfs://nbhost/SystemFileSystem/Templates/Classes/Class.java to edit this template
 */
package ec.edu.espe.chickenfarmapp.model;

/**
 *
 * @author Dennis , Binary Bosses, @ESPE
 */
public class ChickenCoop {
private int chickenCoopId;
private Chicken chickens[]= new Chicken[10];
private int currentIndex = 0;

public void add(Chicken chicken){
    //if we have less than 10 chickens we can add one more, else we cant't add anymore
    for (int i = 0; i < chickens.length; i++) {
        if (chickens[i] == null) {
            chickens[i] = chicken;
            break;
        }
    }

}
public void remove(int chickenId) {
    for (int i = 0; i < chickens.length; i++) {
        if (chickens[i] != null && chickens[i].getId() == chickenId) {
            chickens[i] = null;
            break;
        }
    }
}

public void resetIteration() {
    currentIndex = 0;
}
public Chicken next() {
    Chicken chicken = null;
    if (currentIndex < chickens.length) {
        chicken = chickens[currentIndex];
        currentIndex++;
    }
    return chicken;
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
