package ec.edu.espe.animalfarmapp.model;

import java.util.Date;

/**
 *
 * @author Cristopher Guañuna, Runtime Dynamics, ESPE
 */
public class Pig extends FarmAnimal {
    private float weightInKg;
    private boolean isHungry;

    public Pig(float weightInKg, boolean isHungry, int id, String breed, Date bornOnDate) {
        super(id, breed, bornOnDate);
        this.weightInKg = weightInKg;
        this.isHungry = isHungry;
    }

    public void eat() {
        System.out.println("pig is eating slop()");
    }

    @Override
    public String toString() {
        return "Pig{" + super.toString() + "weightInKg=" + weightInKg + 
               ", isHungry=" + isHungry + '}';
    }

    public float getWeightInKg() { return weightInKg; }
    public void setWeightInKg(float weightInKg) { this.weightInKg = weightInKg; }

    public boolean isIsHungry() { return isHungry; }
    public void setIsHungry(boolean isHungry) { this.isHungry = isHungry; }
}