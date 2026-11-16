package ec.edu.espe.animalfarmapp.model;

import java.util.Date;

/**
 *
 * @ESPE LAB
 */
public class Chicken extends FarmAnimal {

    private boolean isMolting;
    private int numberOfLaidEggsPerWeek;

    public Chicken(boolean isMolting, int numberOfLaidEggsPerWeek, int id, String breed, Date bornOnDate, boolean isMeat) {
        super(id, breed, bornOnDate, isMeat);
        this.isMolting = isMolting;
        this.numberOfLaidEggsPerWeek = numberOfLaidEggsPerWeek;
    }

    public void layAnEgg() {
        System.out.println("chicken is laying an egg");
    }

    @Override
    public void feed(Food food, int amount) {
        System.out.println("Feeding a chicken with " + amount + " of " + food);
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
