package ec.edu.espe.animalfarmapp.model;

import java.util.Date;

/**
 *
 * @author Francisco Demera,Byte & Fabric Devs,@ESPE
 */
public class Chicken extends FarmAnimal{
    private boolean isMolting;
    private int numberOfLaidEggsPerWeek;
    private boolean isMeat;

    public Chicken(boolean isMolting, int numberOfLaidEggsPerWeek, boolean isMeat, int id, String breed, Date bornOnDate) {
        super(id, breed, bornOnDate);
        this.isMolting = isMolting;
        this.numberOfLaidEggsPerWeek = numberOfLaidEggsPerWeek;
        this.isMeat = isMeat;
    }

    @Override
    public String toString() {
        return "Chicken{" + super.toString() + "isMolting=" + isMolting + ", numberOfLaidEggsPerWeek=" + numberOfLaidEggsPerWeek + ", isMeat=" + isMeat + '}';
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
     * @return the numberOfLaidEggsPerWeek
     */
    public int getNumberOfLaidEggsPerWeek() {
        return numberOfLaidEggsPerWeek;
    }

    /**
     * @param numberOfLaidEggsPerWeek the numberOfLaidEggsPerWeek to set
     */
    public void setNumberOfLaidEggsPerWeek(int numberOfLaidEggsPerWeek) {
        this.numberOfLaidEggsPerWeek = numberOfLaidEggsPerWeek;
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
