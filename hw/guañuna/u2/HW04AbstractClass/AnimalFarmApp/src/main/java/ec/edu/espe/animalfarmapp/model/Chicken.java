package ec.edu.espe.animalfarmapp.model;

import java.util.Date;

/**
 *
 * @author Cristopher Guañuna, Runtime Dynamics, ESPE
 */
public class Chicken extends FarmAnimal {
    private boolean isMolting;
    private int numberOfLaidEggInMonth;
    private boolean isMeat;

    public Chicken(boolean isMolting, int numberOfLaidEggInMonth, boolean isMeat, int id, String breed, Date bornOnDate) {
        super(id, breed, bornOnDate);
        this.isMolting = isMolting;
        this.numberOfLaidEggInMonth = numberOfLaidEggInMonth;
        this.isMeat = isMeat;
    }

    public void layAnEgg() {
        System.out.println("chicken is laying an egg()");
    }

    @Override
    public String toString() {
        return "Chicken{" + super.toString() + "isMolting=" + isMolting + 
               ", numberOfLaidEggInMonth=" + numberOfLaidEggInMonth + 
               ", isMeat=" + isMeat + '}';
    }

    public boolean isIsMolting() { return isMolting; }
    public void setIsMolting(boolean isMolting) { this.isMolting = isMolting; }

    public int getNumberOfLaidEggInMonth() { return numberOfLaidEggInMonth; }
    public void setNumberOfLaidEggInMonth(int numberOfLaidEggInMonth) { this.numberOfLaidEggInMonth = numberOfLaidEggInMonth; }

    public boolean isIsMeat() { return isMeat; }
    public void setIsMeat(boolean isMeat) { this.isMeat = isMeat; }
}