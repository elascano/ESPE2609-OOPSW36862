package ec.edu.espe.chickenfarmapp.model;

import java.util.Date;

/**
 *
 * @author Marcos Mamarandi, Binary Bosses,@ESPE
 */
public class ChickenCoop {
    private int chickenCoopId;
    private Chicken chickens[]= new Chicken[10]; 
    private int currentIndex; 
    // we need to know the maximum, in this case is 10
    // we need to know de Index
    // 
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

    
    public void resetIteration() {
        currentIndex = 0;
    }

    // Get the next chicken
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
    
    
    
    

    @Override
    public String toString() {
        return "ChickenCoop{" + "chickenCoopId=" + chickenCoopId + ", chickens=" + chickens + '}';
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
