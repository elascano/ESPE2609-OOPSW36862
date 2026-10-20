package ec.edu.espe.chickenfarmapp.model;

import java.util.Date;
/**
 *
 * @author David Clavón, Infinity Loops, @ESPE
 */
public class ChickenCoop {
    private int chickenCoopId;
    private Chicken chickens[] = new Chicken[10];
    private int currentIndex;
    
    @Override
    public String toString() {
        return "ChickenCoop{" + "chickenCoopId=" + chickenCoopId + ", chickens=" + chickens + '}';
    }

    public ChickenCoop(int chickenCoopId, Chicken[] chickens) {
        this.chickenCoopId = chickenCoopId;
        this.chickens = chickens;
    }

    public void add(Chicken chicken){
        
    }
    
    public void remove(int chickenId){
        
    }
    
    public void resetIteration(){
        currentIndex = 0;
    }
    
    public Chicken next() {
        
        return null; 
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