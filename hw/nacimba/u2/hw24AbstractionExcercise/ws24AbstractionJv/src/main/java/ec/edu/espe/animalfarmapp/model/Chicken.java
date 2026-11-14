package ec.edu.espe.animalfarmapp.model;

import java.util.Date;

/**
 *
 * @author Mateo, Group5, @ESPE
 */
public class Chicken extends FarmAnimal{
    private boolean isMolting;
    private int numberOfLaidEggsInAMonth;
    
    public void layAnEgg(){
        System.out.println("chicken is laying an egg");
    }
    

    @Override
    public String toString() {
        return "Chicken{" +super.toString()+ "isMolting=" + isMolting + ", numberOfLaidEggsInAMonth=" + numberOfLaidEggsInAMonth + ", isMeat="  + '}';
    }

    public Chicken(boolean isMolting, int numberOfLaidEggsInAMonth, int id, String breed, Date bornOnDate, boolean isMeat) {
        super(id, breed, bornOnDate, isMeat);
        this.isMolting = isMolting;
        this.numberOfLaidEggsInAMonth = numberOfLaidEggsInAMonth;
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

    
}
