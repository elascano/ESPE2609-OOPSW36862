package ec.edu.espe.chickenfarmapp.model;

import java.util.Date;
/**
 *
 * @author Mateo Unda, CodeLex, @ESPE
 */
public class ChickenCoop {
    private static final int CAPACITY = 10;   

    private int chickenCoopId;
    private Chicken chickens[];
    private int count;    
    private int index;    

    // ===================== CONSTRUCTORS =====================

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
                // move the chickens on the right one position to the left
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
        return null;   // no more chickens
    }

    // ===================== HELPERS =====================

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

    // ===================== toString (V2) =====================

    @Override
    public String toString() {
        String localChickens;
        localChickens = "";

        for (Chicken chicken : chickens) {
            if (chicken != null) {          // the empty nests are skipped
                localChickens += chicken;
                localChickens += "\t";
            }
        }

        return "ChickenCoop{" + "chickenCoopId=" + chickenCoopId + ", chickens=\n" + localChickens + '}';
    }

    // ===================== GETTERS AND SETTERS =====================

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
     * @return how many chickens are in the coop
     */
    public int getCount() {
        return count;
    }

    /**
     * @return how many chickens fit in the coop
     */
    public int getCapacity() {
        return chickens.length;
    }
}
