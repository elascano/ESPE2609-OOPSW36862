package ec.edu.espe.animalfarmapp.model;

import java.util.Date;

/**
 *
 * @author Francisco Demera,Byte & Fabric Devs,@ESPE
 */
public class Chicken extends FarmAnimal implements SellAnimal {

    private boolean isMolting;
    private int numberOfLaidEggsPerWeek;

    /**
     * feed the chickens with pounds of corn
     *
     * @param food this is corn
     * @param amount this is in pound
     */
    @Override
    public void feed(Food food, int amount) {
        System.out.println("feeding a chicken with " + amount + " pound of " + food);
    }

    @Override
    public void sellAnimal(float price) {
        System.out.println("sold the chicken in " + price + " dollars.");
    }

    public void layAnEgg() {
        numberOfLaidEggsPerWeek++;
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
