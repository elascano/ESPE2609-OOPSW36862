package ec.edu.espe.chickenfarmapp.model;

import java.util.Arrays;

/**
 *
 * @author Demian Armas, Logical Lords, @ESPE
 */
public class ChickenCoop {

    private int chickenCoopId;
    private Chicken chickens[] = new Chicken[10];
    private int currentIndex;

    public ChickenCoop(int chickenCoopId) {
        this.chickenCoopId = chickenCoopId;
        this.currentIndex = 0;
    }

    public void add(Chicken chicken) {
        for (Chicken existing : chickens) {
            if (existing != null && existing.getId() == chicken.getId()) {
                System.out.println("There is already a chicken with id " + chicken.getId() + ".");
                return;
            }
        }
        for (int i = 0; i < chickens.length; i++) {
            if (chickens[i] == null) {
                chickens[i] = chicken;
                System.out.println("Chicken added: " + chicken.getName());
                return;
            }
        }
        System.out.println("The coop is full (maximum " + chickens.length + ").");
    }

    public void remove(int chickenId) {
        for (int i = 0; i < chickens.length; i++) {
            if (chickens[i] != null && chickens[i].getId() == chickenId) {
                String name = chickens[i].getName();
                for (int j = i; j < chickens.length - 1; j++) {
                    chickens[j] = chickens[j + 1];
                }
                chickens[chickens.length - 1] = null;
                System.out.println("Chicken removed: " + name);
                return;
            }
        }
        System.out.println("No chicken found with id " + chickenId + ".");
    }

    public void resetIteration() {
        currentIndex = 0;
        System.out.println("Iteration reset.");
    }

    public void next() {
        if (currentIndex < chickens.length && chickens[currentIndex] != null) {
            System.out.println(chickens[currentIndex]);
            currentIndex++;
        } else {
            System.out.println("No more chickens. Reset the iteration to start over.");
        }
    }

    @Override
    public String toString() {
        return "ChickenCoop{" + "chickenCoopId=" + chickenCoopId
                + ", chickens=" + Arrays.toString(chickens)
                + ", currentIndex=" + currentIndex + '}';
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
     * @return the currentIndex
     */
    public int getCurrentIndex() {
        return currentIndex;
    }

    /**
     * @param currentIndex the currentIndex to set
     */
    public void setCurrentIndex(int currentIndex) {
        this.currentIndex = currentIndex;
    }

}