package ec.edu.espe.animalfarm.model;

import java.util.Date;

/**
 *
 * @author David Clavón, Infinite Loops, @ESPE
 */
public class Chicken extends FarmAnimal{
    private boolean isMolting;
    private int numberOfLaidEggInMonth;
    private boolean isMeat;

    public Chicken(boolean isMolting, int numberOfLaidEggInMonth, boolean isMeat, int id, String breed, Date bornOnDate) {
        super(id, breed, bornOnDate);
        this.isMolting = isMolting;
        this.numberOfLaidEggInMonth = numberOfLaidEggInMonth;
        this.isMeat = isMeat;
    }

    @Override
    public String toString() {
        return "Chicken{" + super.toString() + "isMolting=" + isMolting + ", numberOfLaidEggInMonth=" + numberOfLaidEggInMonth + ", isMeat=" + isMeat + '}';
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
     * @return the numberOfLaidEggInMonth
     */
    public int getNumberOfLaidEggInMonth() {
        return numberOfLaidEggInMonth;
    }

    /**
     * @param numberOfLaidEggInMonth the numberOfLaidEggInMonth to set
     */
    public void setNumberOfLaidEggInMonth(int numberOfLaidEggInMonth) {
        this.numberOfLaidEggInMonth = numberOfLaidEggInMonth;
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
