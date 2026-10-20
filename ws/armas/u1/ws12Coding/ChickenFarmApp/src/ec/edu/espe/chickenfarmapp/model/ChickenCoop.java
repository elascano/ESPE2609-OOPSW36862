package ec.edu.espe.chickenfarmapp.model;

import java.util.Date;

/**
 *
 * @author Demian Armas, Logical Lords, @ESPE
 */
public class ChickenCoop {
    private int chickenCoopId;
    private Chicken chickens[]= new Chicken[10];
    private int currentIndex;
    
    public void add (Chicken chicken){
        if(chickens.length<10);
        
    }
    
    public void remove(int chickenId){
        
    }
    
    public void resetIteration (){
        currentIndex = 0;
    }
    
    public Chicken next(){
        
        return new Chicken(3, "Lucy", "white",new Date(2020,3,3));
    }

    public ChickenCoop(int chickenCoopId, Chicken[] chicken) {
        this.chickenCoopId = chickenCoopId;
        this.chickens = chicken;
    }

    @Override
    public String toString() {
        return "ChickenCoop{" + "chickenCoopId=" + chickenCoopId + ", chicken=" + chicken + '}';
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
     * @return the chicken
     */
    public Chicken[] getChicken() {
        return chicken;
    }

    /**
     * @param chicken the chicken to set
     */
    public void setChicken(Chicken[] chicken) {
        this.chicken = chicken;
    }
    
    
    
}
