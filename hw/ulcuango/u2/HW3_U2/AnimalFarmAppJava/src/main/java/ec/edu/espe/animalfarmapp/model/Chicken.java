package ec.edu.espe.animalfarmapp.model;

import java.util.Date;
/**
 *
 * @author Josselyn Ulcuango, CodeLex, @ESPE>
 */
public class Chicken extends FarmAnimal{
    private boolean isMolting;
    private int numberOfLaidEggsInAMonth;
    private boolean isMeat;

    @Override
    public String toString() {
        return "Chicken{" +super.toString()+ "isMolting=" + isMolting + ", numberOfLaidEggsInAMonth=" + numberOfLaidEggsInAMonth + ", isMeat=" + isMeat + '}';
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
