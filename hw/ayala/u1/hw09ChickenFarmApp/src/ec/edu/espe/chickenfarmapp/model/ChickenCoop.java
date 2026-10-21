/*
 * Click nbfs://nbhost/SystemFileSystem/Templates/Licenses/license-default.txt to change this license
 * Click nbfs://nbhost/SystemFileSystem/Templates/Classes/Class.java to edit this template
 */
package ec.edu.espe.chickenfarmapp.model;

import java.util.Date;

/**
 *
 * @author Enrique Ayala, LogicLords,@ESPE
 */
public class ChickenCoop {
    private int chickenCoopId;
    private Chicken chickens[]= new Chicken[10];
    private int currentIndex;
    private int count;
    
    //we need to know the maxium . in this case is 10
    //we need to know the index
    
    
    public void add(Chicken chicken){
        if (getCount() < chickens.length){
            chickens[getCount()] = chicken;
            setCount(getCount() + 1);
        } else {
            System.out.println("The coop is full (maxium" + chickens.length + ")");
        }
        
    }
    
    //public void add(Chicken chicken) {
        //algothm
        
        //if we have less than 10 chickens, we can add one more
        //else we can not add any more
    //}
    
    public void remove(int chickenId) {
        for (int i = 0; i < getCount(); i++) {
            if (chickens[i].getId() == chickenId) {
                for (int j = i; j < getCount() - 1; j++) {
                    chickens[j] = chickens[j+1];
                }
                chickens[getCount() - 1]= null;
                setCount(getCount() - 1);
                System.out.println("Chicken removed");
                return;
            }
        }
        System.out.println("There is no chicken whith id"+ chickenId);
    }
    
    public void resetIteration() {
        currentIndex = 0;
    }
    
    public Chicken next() {
       if (currentIndex >= getCount()) {
           return null;
       }
       Chicken chicken = chickens[currentIndex];
       currentIndex++;
       return chicken;
    }
    
    
    public ChickenCoop(int chickenCoopId, Chicken[] chickens) {
        this.chickenCoopId = chickenCoopId;
        this.chickens = chickens;
    }

    
    @Override
   public String toString() {
        String text = "ChickenCoop{id=" + chickenCoopId + ", chickens=" + getCount();
        for (int i = 0; i < getCount(); i++) {
            text = text + "\n  " + chickens[i];
        }
        return text + "}";
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

    /**
     * @return the count
     */
    public int getCount() {
        return count;
    }

    /**
     * @param count the count to set
     */
    public void setCount(int count) {
        this.count = count;
    }

    public ChickenCoop(int count) {
        this.count = count;
    }
    
    
}
