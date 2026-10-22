package ec.edu.espe.chickenfarmapp.model;

import java.util.Random;

/**
 *
 * @author Leslye Cuichan
 */
public class ChickenCoop {

    private int chickenCoopId;
    private Chicken chickens[] = new Chicken[10];
    private int currentIndex;

    public ChickenCoop(int chickenCoopId, Chicken[] chickens) {
        this.chickenCoopId = chickenCoopId;
        this.chickens = chickens;
    }

    public ChickenCoop(int chickenCoopId) {
        this.chickenCoopId = chickenCoopId;
    }

    public int generateUniqueId() {
        Random random = new Random();
        int generatedId;
        boolean isDuplicate;

        do {
            generatedId = random.nextInt(100) + 1;
            isDuplicate = false;

            for (Chicken c : chickens) {
                if (c != null && c.getId() == generatedId) {
                    isDuplicate = true;
                    break;
                }
            }
        } while (isDuplicate);

        return generatedId;
    }

    public boolean add(Chicken chicken) {
        if (chicken == null) {
            return false;
        }
        for (int i = 0; i < chickens.length; i++) {
            if (chickens[i] == null) {
                chickens[i] = chicken;
                return true;
            }
        }
        return false;
    }

    public boolean remove(int chickenId) {
        for (int i = 0; i < chickens.length; i++) {
            if (chickens[i] != null && chickens[i].getId() == chickenId) {
                chickens[i] = null;
                return true;
            }
        }
        return false;
    }

    public void resetIteration() {
        currentIndex = 0;
    }

    public Chicken next() {
        while (currentIndex < chickens.length) {
            Chicken current = chickens[currentIndex];
            currentIndex++;
            if (current != null) {
                return current;
            }
        }
        return null;
    }

    public int getCurrentPosition() {
        return currentIndex;
    }

    public int getCount() {
        int count = 0;
        for (Chicken c : chickens) {
            if (c != null) {
                count++;
            }
        }
        return count;
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

    @Override
    public String toString() {
        return "ChickenCoop{" + "chickenCoopId=" + chickenCoopId + ", chickens=" + java.util.Arrays.toString(chickens) + '}';
    }
}