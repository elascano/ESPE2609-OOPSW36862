package ec.edu.espe.animalfarmapp.model;

import java.util.Date;

/**
 * @author Andrés Pachacama, ByteWorks, @ESPE
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

    public boolean isIsMolting() {
        return isMolting;
    }

    public void setIsMolting(boolean isMolting) {
        this.isMolting = isMolting;
    }

    public int getNumberOfLaidEggsPerWeek() {
        return numberOfLaidEggsPerWeek;
    }

    public void setNumberOfLaidEggsPerWeek(int numberOfLaidEggsPerWeek) {
        this.numberOfLaidEggsPerWeek = numberOfLaidEggsPerWeek;
    }
}
