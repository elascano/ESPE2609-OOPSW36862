package ec.edu.espe.chickenfarmapp.model;

/**
 *
 * @author Carlos Parra, ByteWorks, @ESPE
 */
public class ChickenCoop {
    private int chickenCoopId;
    private Chicken chickens[]= new Chicken [10];
    private int currentIndex;

    
    public void add(Chicken chicken){
        //algoritmo 
        
        //if we have less than 10 chickens, we can add one more 
        
        //else we can nit add any more
        
        
        
    
    }
    
    public void remove(int ChickenId){
        
    }
    
    public void resetIteration(){
        currentIndex=0; 
    }
    
    
    
    public ChickenCoop(int chickenCoopId, Chicken[] chickens) {
        this.chickenCoopId = chickenCoopId;
        this.chickens = chickens;
    }

    @Override
    public String toString() {
        return "ChickenCoop{" + "chickenCoopId=" + chickenCoopId + ", chickens=" + chickens + '}';
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