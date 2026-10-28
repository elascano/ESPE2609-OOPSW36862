/*
 * Click nbfs://nbhost/SystemFileSystem/Templates/Licenses/license-default.txt to change this license
 * Click nbfs://nbhost/SystemFileSystem/Templates/Classes/Class.java to edit this template
 */
package ec.edu.espe.chickenfarmapp.model;

import java.util.Arrays;

/**
 *
 * @author Daniel Sangoquiza, Saborcode, @ESPE
 */
public class ChickenCoop {
    private int chickenCoopId;
    private Chicken chickens[] = new Chicken[10];
    private int currentIndex;

   public void add(Chicken chicken){
        for (int i = 0; i < chickens.length; i++) {
        if (chickens[i] == null) {
            chickens[i] = chicken;
            return;
        }
    }
    System.out.println("The chicken coop is full.");
    }
    
    public void remove(int chickenId){
         for (int i = 0; i < chickens.length; i++) {
        if (chickens[i] != null &&
            chickens[i].getId() == chickenId) {
            chickens[i] = null;
            return;
        }
    }
    System.out.println("Chicken not found.");
    }
    
    public void resetIteration(){
        currentIndex = 0;
    }
    
    public Chicken next(){
            while (currentIndex < chickens.length) {
        Chicken chicken = chickens[currentIndex];
        currentIndex++;

        if (chicken != null) {
            return chicken;
        }
    }
        return null;
        
    }
    
    @Override
    public String toString() {
        return "ChickenCoop{" + "chickenCoopId=" + chickenCoopId + ", chickens=" + Arrays.toString(chickens) + '}';
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