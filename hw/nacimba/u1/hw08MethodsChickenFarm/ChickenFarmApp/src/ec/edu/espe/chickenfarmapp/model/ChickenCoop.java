package ec.edu.espe.chickenfarmapp.model;

import java.util.Arrays;

/**
 *
 * @author Nacimba Mateo, Group5, @ESPE
 */
public class ChickenCoop {

    public static final int CAPACITY = 10;

    private int chickenCoopId;
    private Chicken[] chickens;
    private int chickenCount;
    private int iteratorIndex;
    private int nextId;

    public ChickenCoop(int chickenCoopId) {
        this.chickenCoopId = chickenCoopId;
        this.chickens = new Chicken[CAPACITY];
        this.chickenCount = 0;
        this.iteratorIndex = 0;
        this.nextId = 1;
    }

    /**
     * Generates a unique, auto-incremental id for a new chicken.
     */
    public int generateId() {
        return nextId++;
    }

    /**
     * Adds a chicken in the first free slot.
     * @return true if the chicken was added, false if the coop is full or the chicken is null.
     */
    public boolean add(Chicken chicken) {
        if (chicken == null) {
            System.out.println("Cannot add a null chicken.");
            return false;
        }
        if (isFull()) {
            System.out.println("The chicken coop is full. Cannot add more chickens. (Max " + chickens.length + ")");
            return false;
        }

        for (int i = 0; i < chickens.length; i++) {
            if (chickens[i] == null) {
                chickens[i] = chicken;
                chickenCount++;
                System.out.println("Chicken added successfully. Total chickens: " + chickenCount);
                return true;
            }
        }
        return false;
    }

    /**
     * Removes the chicken with the given id.
     * @return true if it was found and removed.
     */
    public boolean remove(int chickenId) {
        for (int i = 0; i < chickens.length; i++) {
            if (chickens[i] != null && chickens[i].getId() == chickenId) {
                chickens[i] = null;
                chickenCount--;
                System.out.println("Chicken with ID " + chickenId + " removed.");
                return true;
            }
        }
        System.out.println("Chicken with ID " + chickenId + " not found.");
        return false;
    }

    public boolean existsId(int chickenId) {
        for (Chicken chicken : chickens) {
            if (chicken != null && chicken.getId() == chickenId) {
                return true;
            }
        }
        return false;
    }

    public void resetIteration() {
        iteratorIndex = 0;
    }

    public Chicken next() {
        while (iteratorIndex < chickens.length) {
            Chicken chicken = chickens[iteratorIndex];
            iteratorIndex++;
            if (chicken != null) {
                return chicken;
            }
        }
        return null;
    }

    public boolean isFull() {
        return chickenCount >= chickens.length;
    }

    public int getChickenCount() {
        return chickenCount;
    }

    @Override
    public String toString() {
        return "ChickenCoop{" + "chickenCoopId=" + chickenCoopId
                + ", chickens=" + Arrays.toString(chickens) + '}';
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
        this.chickenCount = 0;
        for (Chicken chicken : chickens) {
            if (chicken != null) {
                this.chickenCount++;
            }
        }
        this.iteratorIndex = 0;
        for (Chicken chicken : chickens) {
            if (chicken != null && chicken.getId() >= nextId) {
                nextId = chicken.getId() + 1;
            }
        }
    }
}