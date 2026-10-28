package ec.edu.espe.chickenfarmapp.model;

import java.util.Date;

/**
 *
 * @author Daniel Sangoquiza, Saborcode, @ESPE
 */
public class ChickenCoop {
    private static final int CAPACITY = 10;   

    private int chickenCoopId;
    private Chicken chickens[];
    private int count;    
    private int index;    

    public ChickenCoop(int chickenCoopId) {
        this.chickenCoopId = chickenCoopId;
        this.chickens = new Chicken[CAPACITY];
        this.count = 0;
        this.index = 0;
    }

    public ChickenCoop(int chickenCoopId, Chicken[] chickens) {
        this(chickenCoopId);
        for (Chicken chicken : chickens) {
            if (chicken != null) {
                add(chicken);
            }
        }
    }

    public ChickenCoop() {
        this(1);
        Chicken chicken = new Chicken(1, "Lucy", "black", new Date(), 0, false);
        Chicken chicken2 = new Chicken(2, "Alejandra", "white", new Date(), 0, false);

        add(chicken);
        add(chicken2);
    }

    public void add(Chicken chicken) {
        if (count < chickens.length) {
            chickens[count] = chicken;
            count++;
        }
    }

    public void remove(int chickenId) {
        for (int i = 0; i < count; i++) {
            if (chickens[i].getId() == chickenId) {
                for (int j = i; j < count - 1; j++) {
                    chickens[j] = chickens[j + 1];
                }
                chickens[count - 1] = null;
                count--;
                return;
            }
        }
    }

    public void resetIteration() {
        index = 0;
    }

    public Chicken next() {
        if (index < count) {
            Chicken chicken = chickens[index];
            index++;
            return chicken;
        }
        return null;
    }

    public boolean exists(int chickenId) {
        for (int i = 0; i < count; i++) {
            if (chickens[i].getId() == chickenId) {
                return true;
            }
        }
        return false;
    }

    public boolean isFull() {
        return count == chickens.length;
    }

    public boolean isEmpty() {
        return count == 0;
    }

    @Override
    public String toString() {
        String localChickens;
        localChickens = "";

        for (Chicken chicken : chickens) {
            if (chicken != null) {
                localChickens += chicken;
                localChickens += "\t";
            }
        }

        return "ChickenCoop{" + "chickenCoopId=" + chickenCoopId + ", chickens=\n" + localChickens + '}';
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

    public int getCapacity() {
        return chickens.length;
    }
}