package ec.edu.espe.model;

import java.util.Date;

/**
 *
 * @author Demian Armas, Logical Lords, @ESPE
 */
public class Chicken extends FarmAnimal {
    
    
    /**
     * feed the chickens with pounds of corn
     * @param food this is corn
     * @param amountOfFood  this is in pounds 
     */
    @Override
    public void feed(Food food, int amountOfFood){
        System.out.println("feeding a chicken with --->"+ amountOfFood + "nound of "+ food );
    }
    
    private boolean isMolting;
    private int numberOfLaidEggsInAMonth;
    private boolean isMeat;
    
    public void layAnEgg(){
        System.out.println("Chicken is laying an egg () ");
    }
    @Override
    public String toString() {
        return "Chicken{" + super.toString() + "isMolting=" + isMolting + ", numberOfLaidEggsInAMonth=" + numberOfLaidEggsInAMonth + ", isMeat=" + isMeat + '}';
    }
    
    
    public Chicken(boolean isMolting, int numberOfLaidEggsInAMonth, boolean isMeat, int id, String breed, Date bornOnDate) {
        super(id, breed, bornOnDate);
        this.isMolting = isMolting;
        this.numberOfLaidEggsInAMonth = numberOfLaidEggsInAMonth;
        this.isMeat = isMeat;
    }
    
    
    
    /**
     * @return the isMolting
     */
    public boolean isIsMolting() {
        return isMolting;
    }

    /**
     * @param isMolting the isMolting to set
     */
    public void setIsMolting(boolean isMolting) {
        this.isMolting = isMolting;
    }

    /**
     * @return the numberOfLaidEggsInAMonth
     */
    public int getNumberOfLaidEggsInAMonth() {
        return numberOfLaidEggsInAMonth;
    }

    /**
     * @param numberOfLaidEggsInAMonth the numberOfLaidEggsInAMonth to set
     */
    public void setNumberOfLaidEggsInAMonth(int numberOfLaidEggsInAMonth) {
        this.numberOfLaidEggsInAMonth = numberOfLaidEggsInAMonth;
    }

    /**
     * @return the isMeat
     */
    public boolean isIsMeat() {
        return isMeat;
    }

    /**
     * @param isMeat the isMeat to set
     */
    public void setIsMeat(boolean isMeat) {
        this.isMeat = isMeat;
    }
    
    
    
}
