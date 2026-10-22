package ec.edu.espe.chickenfarmapp.model;

import java.util.Arrays;
import java.util.Date;

/**
 *
 * @author Enrique Ayala, LogicLords,@ESPE
 */
public class ChickenCoop {
    private int chickenCoopId;
    private Chicken[] chickens = new Chicken[10];
    private int count;
    private int currentIndex;

    public ChickenCoop(int chickenCoopId) {
        this.chickenCoopId = chickenCoopId;
    }

    public boolean add(Chicken chicken) {
        if (findIndexById(chicken.getId()) != -1) {
            System.out.println("There is already a chicken with id " + chicken.getId());
            return false;
        }
        if (count >= chickens.length) {
            System.out.println("The coop is full (maximum " + chickens.length + ").");
            return false;
        }
        chickens[count] = chicken;
        count++;
        return true;
    }

    public boolean remove(int chickenId) {
        int index = findIndexById(chickenId);
        if (index == -1) {
            System.out.println("There is no chicken with id " + chickenId);
            return false;
        }
        for (int i = index; i < count - 1; i++) {
            chickens[i] = chickens[i + 1];
        }
        chickens[count - 1] = null;
        count--;
        return true;
    }

    public boolean update(int chickenId, String name, String color,
                          Date bornOnDate, boolean molting) {
        int index = findIndexById(chickenId);
        if (index == -1) {
            System.out.println("There is no chicken with id " + chickenId);
            return false;
        }
        Chicken chicken = chickens[index];
        chicken.setName(name);
        chicken.setColor(color);
        chicken.setBornOnDate(bornOnDate);
        chicken.setMolting(molting);
        chicken.setAgeInYears();
        return true;
    }

    private int findIndexById(int chickenId) {
        for (int i = 0; i < count; i++) {
            if (chickens[i].getId() == chickenId) {
                return i;
            }
        }
        return -1;
    }

    public Chicken[] toArray() {
        return Arrays.copyOf(chickens, count); // only the real chickens, no nulls
    }

    public void resetIteration() {
        currentIndex = 0;
    }

    public Chicken next() {
        if (currentIndex >= count) {
            return null;
        }
        Chicken chicken = chickens[currentIndex];
        currentIndex++;
        return chicken;
    }

    @Override
    public String toString() {
        String text = "ChickenCoop{id=" + chickenCoopId + ", chickens=" + count;
        for (int i = 0; i < count; i++) {
            text = text + "\n  " + chickens[i];
        }
        return text + "}";
    }

    public int getChickenCoopId() {
        return chickenCoopId;
    }

    public void setChickenCoopId(int chickenCoopId) {
        this.chickenCoopId = chickenCoopId;
    }

    public int getCount() {
        return count;
    }
}