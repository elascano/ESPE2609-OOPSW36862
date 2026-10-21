/*
 * Click nbfs://nbhost/SystemFileSystem/Templates/Licenses/license-default.txt to change this license
 * Click nbfs://nbhost/SystemFileSystem/Templates/Classes/Class.java to edit this template
 */
package ec.edu.espe.chickenfarmapp.model;
import java.util.Arrays;
/**
 *
 * @author Isaac Acurio, Liquor Store App Simulation, @ESPE
 */
public class ChickenCoop {
    private int chickenCoopId;
    private Chicken chickens[] = new Chicken[10];
    private int currentIndex;

    public ChickenCoop() {
        this.chickenCoopId = 0;
        this.currentIndex = 0;
    }

    public ChickenCoop(int chickenCoopId, Chicken[] chickens) {
        this.chickenCoopId = chickenCoopId;
        this.chickens = chickens;
        this.currentIndex = 0;
    }

    /**
     * Adds a chicken to the first available slot (null) in the array.
     */
    public void add(Chicken chicken) {
        for (int i = 0; i < chickens.length; i++) {
            if (chickens[i] == null) {
                chickens[i] = chicken;
                return;
            }
        }
        System.out.println("The chicken coop is full. Cannot add more chickens.");
    }

    /**
     * Removes a chicken by matching its ID in the array.
     */
    public void remove(int chickenId) {
        for (int i = 0; i < chickens.length; i++) {
            if (chickens[i] != null && chickens[i].getId() == chickenId) {
                chickens[i] = null;
                return;
            }
        }
        System.out.println("Chicken with ID " + chickenId + " was not found.");
    }

    /**
     * Resets the iteration index back to 0.
     */
    public void resetIteration() {
        currentIndex = 0;
    }

    /**
     * Returns the current chicken and increments the index for the next call.
     */
    public Chicken next() {
        while (currentIndex < chickens.length) {
            Chicken currentChicken = chickens[currentIndex];
            currentIndex++;
            if (currentChicken != null) {
                return currentChicken;
            }
        }
        return null;
    }

    @Override
    public String toString() {
        return "ChickenCoop{" + "chickenCoopId=" + chickenCoopId + ", chickens=" + Arrays.toString(chickens) + '}';
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