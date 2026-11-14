package ec.edu.espe.animalfarmapp.model;

import java.util.Date;

/**
 *
 * @ESPE LAB
 */
public class Chicken extends FarmAnimal {

    private boolean isMolting;
    private int numberOfLaidEggsPerWeek;

    public void layAnEgg() {
        System.out.println("chicken is laying an egg");
    }

    public Chicken(boolean isMolting, int numberOfLaidEggsPerWeek, int id, String breed, Date bornOnDate) {
        super(id, breed, bornOnDate, isMolting);
        this.isMolting = isMolting;
        this.numberOfLaidEggsPerWeek = numberOfLaidEggsPerWeek;
    }

    @Override
    public String toString() {
        return "Chicken{" + super.toString() + "isMolting=" + isMolting + ", numberOfLaidEggsPerWeek=" + numberOfLaidEggsPerWeek + '}';
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

}
