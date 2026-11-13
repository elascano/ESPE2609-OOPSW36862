
package ec.edu.espe.animalfarmapp.model;

import java.util.Date;

/**
 *
 * @author David Clavón, Infinite Loops, @ESPE
 */
public class Chicken extends FarmAnimal{
    private boolean isMolting;
    private int numberOfLaidEggsInMonth;
    private boolean isMeat;

    public Chicken(boolean isMolting, int numberOfLaidEggsInMonth, boolean isMeat, int id, String breed, Date bornOnDate) {
        super(id, breed, bornOnDate);
        this.isMolting = isMolting;
        this.numberOfLaidEggsInMonth = numberOfLaidEggsInMonth;
        this.isMeat = isMeat;
    }

    @Override
    public String toString() {
        return "chicken{" + super.toString() + "isMolting=" + isMolting + ", numberOfLaidEggsInMonth=" + numberOfLaidEggsInMonth + ", isMeat=" + isMeat + '}';
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
     * @return the numberOfLaidEggsInMonth
     */
    public int getNumberOfLaidEggsInMonth() {
        return numberOfLaidEggsInMonth;
    }

    /**
     * @param numberOfLaidEggsInMonth the numberOfLaidEggsInMonth to set
     */
    public void setNumberOfLaidEggsInMonth(int numberOfLaidEggsInMonth) {
        this.numberOfLaidEggsInMonth = numberOfLaidEggsInMonth;
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
    
    public void layAnEgg(){
        System.out.println("The chicken is laying an Egg");
    }
}
