/*
 * Click nbfs://nbhost/SystemFileSystem/Templates/Licenses/license-default.txt to change this license
 * Click nbfs://nbhost/SystemFileSystem/Templates/Classes/Class.java to edit this template
 */
package ec.edu.espe.chickenfarmapp.model;

import java.util.Date;


/**
 *
 * @author Nacimba Mateo, Group5, @ESPE
 */
public class ChickenCoop {
    
    private int chickenCoopId;
    private Chicken chickens[] = new Chicken[10];
    private int currentIndex;
    
    public ChickenCoop(int chickenCoopId, Chicken[] chickens) {
        this.chickenCoopId = chickenCoopId;
        this.chickens = chickens;
        this.currentIndex = 0;
    }

    public void add(Chicken chicken) {
        if (currentIndex < chickens.length) {
            chickens[currentIndex] = chicken;
            currentIndex++;
            System.out.println("Chicken added successfully.");
        } else {
            System.out.println("The chicken coop is full. Cannot add more chickens.");
        }
    }
    
    public void remove(int chickenId) {
        boolean found = false;
        for (int i = 0; i < chickens.length; i++) {
            if (chickens[i] != null && chickens[i].getId() == chickenId) { // Asumiendo que Chicken tiene getId()
                chickens[i] = null;
                found = true;
                System.out.println("Chicken with ID " + chickenId + " removed.");
                break;
            }
        }
        if (!found) {
            System.out.println("Chicken with ID " + chickenId + " not found.");
        }
    }
    
    public void resetIteration() {
        currentIndex = 0;
    }
    
    public Chicken next() {
        if (currentIndex < chickens.length && chickens[currentIndex] != null) {
            Chicken chicken = chickens[currentIndex];
            currentIndex++;
            return chicken;
        }
        return null; // Retorna null si ya no hay más pollos o hay espacios vacíos
    }

    @Override
    public String toString() {
        return "ChickenCoop{" + "chickenCoopId=" + chickenCoopId + ", chickens=" + java.util.Arrays.toString(chickens) + '}';
    }

    public int getChickenCoopId() {
        return chickenCoopId;
    }

    public void setChickenCoopId(int chickenCoopId) {
        this.chickenCoopId = chickenCoopId;
    }

    public Chicken[] getChickens() {
        return chickens;
    }

    public void setChickens(Chicken[] chickens) {
        this.chickens = chickens;
    }
}