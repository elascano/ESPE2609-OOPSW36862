package ec.edu.espe.chickenfarmapp.model;
 
import java.util.Arrays;
 

/**
 *
 * @author Paucar Matias, ByteWorks, @ESPE
 */
public class ChickenCoop {
    private int chickenCoopId;
    private Chicken chickens[]= new Chicken[10];
    private int currentIndex;
    private int numberOfChickens;
    
    
    //we need to know the maximum, in this case is 10
    //we need to know the index
    
    
    public void add(Chicken chicken){
        //algorithm
        
        //if we have less than 10 checkens, we can add one more
        //else we can not add any more
        
        if(getNumberOfChickens() < getChickens().length){
            getChickens()[getNumberOfChickens()] = chicken;
            setNumberOfChickens(getNumberOfChickens() + 1) ;
            System.out.println("The chicken" + chicken.getName()+ " was added to the coop");
        }else{
            System.out.println("The coop is full, we can not add any more chickens");
        }
       
        
    }
    public void remove(int ChickenId){
        int position = -1;
        
        for (int i = 0; i<getNumberOfChickens(); i++){
            if(getChickens()[i].getId() == ChickenId){
                position = i;
                break;
            }
        }
        if (position == -1){
            System.out.println("There is no chicken with id" + ChickenId);
            return;    
        }
        
        String removedName = getChickens()[position].getName();
        for(int i = position; i< getNumberOfChickens() -1; i++ ){
            getChickens()[i]= getChickens()[i+1];
        }
        
        getChickens()[getNumberOfChickens() -1]=null;
        setNumberOfChickens(getNumberOfChickens() - 1) ;
        System.out.println("The chicken "+ removedName + "was removed from the coop");
         
    }
    
    public void resetIteration(){
        setCurrentIndex(0);

    }
    
    public boolean hasNext(){
        
        return getCurrentIndex() < getNumberOfChickens();
    }
    public Chicken next(){
        
        if (hasNext()){
            Chicken chicken = getChickens()[getCurrentIndex()];
            setCurrentIndex(getCurrentIndex() + 1);
            return chicken;
        }
        return null;
   
    }
    
    public ChickenCoop(int chickenCoopId) {
        this.chickenCoopId = chickenCoopId;
        this.chickens = new Chicken[10];
        this.numberOfChickens = 0;
        this.currentIndex = 0;
    }
    

    public ChickenCoop(int chickenCoopId, Chicken[] chickens) {
        this.chickenCoopId = chickenCoopId;
        this.chickens = chickens;
    }

    @Override
    public String toString() {
        return "ChickenCoop{" + "chickenCoopId=" + getChickenCoopId() + ", chickens=" + getChickens() + '}';
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

    /**
     * @return the numberOfChickens
     */
    public int getNumberOfChickens() {
        return numberOfChickens;
    }

    /**
     * @param numberOfChickens the numberOfChickens to set
     */
    public void setNumberOfChickens(int numberOfChickens) {
        this.numberOfChickens = numberOfChickens;
    }
    
    
    
}
