package ec.edu.espe.chickenfarmapp.model;

//import java.util.Date;

/**
 *
 * @author Andres Pachacama, Byte Work, @ESPE
 */
public class ChickenCoop {
    private int chickenCoopId;
    private Chicken chickens[]= new Chicken [10];
    private int count;   
    private int index;   

    public ChickenCoop(int chickenCoopId) {
        this.chickenCoopId = chickenCoopId;
        this.chickens = new Chicken[10];
        this.count = 0;
        this.index = 0;
    }

    public void add(Chicken chicken) {
        if (count < chickens.length) {        
            chickens[count] = chicken;
            count++;
        }
        // the messages are shown by the view (ChickenFarmApp)
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

    /**
     * @return how many chickens are in the coop
     */
    public int getCount() {
        return count;
    }

    /**
     * @return how many chickens fit in the coop (10)
     */
    public int getCapacity() {
        return chickens.length;
    }

    /*
    //we need to know the maxium , in this case is 10 
    // we need to know the index
    //
    
    
    public void add(Chicken chicken){
        //algoritmo        
        //if we have less than 10 chickens, we can add one more        
        //else we can nit add any more               
        //if(chickens.length < 10){        
        //}
    
    }
    
    public void remove(int chickenId){
    }

    public void resetIteration(){
    }

    public Chicken next(){
        return new Chicken(3, "Lucy", "white", new Date(2020,3,3), true);
    }
    
    public ChickenCoop(int chickenCoopId, Chicken[] chickens) {
        this.chickenCoopId = chickenCoopId;
        this.chickens = chickens;
    }
*/
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
