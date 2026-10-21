package ec.edu.espe.chickenfarmapp.model;

import java.util.Date;

/**
 *
 * @author Renata Olalla, Binary Bosses, @ESPE
 */
public class ChickenCoop {

    private int chickenCoopId;
    private Chicken chickens[] = new Chicken[10];
    private int currentIndex;

    
    public void add(Chicken chicken) {
        for (int i = 0; i < chickens.length; i++) {
            if (chickens[i] == null) {
                chickens[i] = chicken;
                System.out.println("Chicken added successfully");
                return;
            }
        }

        System.out.println("The chicken coop is full");
    }

    
    public void remove(int chickenId) {
        for (int i = 0; i < chickens.length; i++) {
            if (chickens[i] != null &&
                chickens[i].getId() == chickenId) {

                for (int j = i; j < chickens.length - 1; j++) {
                    chickens[j] = chickens[j + 1];
                }

                chickens[chickens.length - 1] = null;

                System.out.println("Chicken removed successfully");
                return;
            }
        }

        System.out.println("Chicken not found");
    }

    // Reset the iteration
    public void resetIteration() {
        currentIndex = 0;
    }

    
    public Chicken next() {
        while (currentIndex < chickens.length) {
            Chicken chicken = chickens[currentIndex];
            currentIndex++;

            if (chicken != null) {
                return chicken;
            }
        }

        return null;
    }

   
    public ChickenCoop(int chickenCoopId, Chicken[] chickens) {
        this.chickenCoopId = chickenCoopId;
        this.chickens = chickens;
        this.currentIndex = 0;
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
