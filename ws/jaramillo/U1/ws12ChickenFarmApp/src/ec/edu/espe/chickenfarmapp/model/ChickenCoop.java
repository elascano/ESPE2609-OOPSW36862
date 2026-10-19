/*
 * Click nbfs://nbhost/SystemFileSystem/Templates/Licenses/license-default.txt to change this license
 * Click nbfs://nbhost/SystemFileSystem/Templates/Classes/Class.java to edit this template
 */
package ec.edu.espe.chickenfarmapp.model;

import java.util.Date;

/**
 *
 * @author Julian Jaramillo, LogicLords,@ESPE
 */
public class ChickenCoop {
    private int chickenCoopId;
    private Chicken chickens[]= new Chicken[10];
    private int currentIndex;
    
    //we need to know the maxium . in this case is 10
    //we need to know the index
    
    
    public void add(Chicken chicken){
        //algoritmo
        //if we have less than 18 chickens, we can add one more
        //else we can not add any more
        
    }
    
    //public void add(Chicken chicken) {
        //algothm
        
        //if we have less than 10 chickens, we can add one more
        //else we can not add any more
    //}
    
    public void remove(int chickenId) {
        
    }
    
    public void resetIteration() {
        currentIndex = 0;
    }
    
    public Chicken next() {
        return new Chicken(3, "Lucy", "white", new Date(2020,3,3), true); 
    }

    public ChickenCoop(int chickenCoopId, Chicken[] chickens) {
        this.chickenCoopId = chickenCoopId;
        this.chickens = chickens;
    }

    
    @Override
    public String toString() {
        return "ChickenCoop{" + "chickenCoopId=" + chickenCoopId + ", chickens=" + chickens + '}';
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
