package ec.edu.espe.animalfarm.model;

import java.util.Date;

/**
 *
 * @author David Clavón, Infinite Loops, @ESPE
 */
public class Pig extends FarmAnimal {
    private float weight;
    private boolean isForMeat;

    public Pig(float weight, boolean isForMeat, int id, String breed, Date bornOnDate) {
        super(id, breed, bornOnDate);
        this.weight = weight;
        this.isForMeat = isForMeat;
    }

    @Override
    public String toString() {
        return "Pig{" + super.toString() + "weight=" + weight + ", isForMeat=" + isForMeat + '}';
    }

    /**
     * @return the weight
     */
    public float getWeight() {
        return weight;
    }

    /**
     * @param weight the weight to set
     */
    public void setWeight(float weight) {
        this.weight = weight;
    }

    /**
     * @return the isForMeat
     */
    public boolean isIsForMeat() {
        return isForMeat;
    }

    /**
     * @param isForMeat the isForMeat to set
     */
    public void setIsForMeat(boolean isForMeat) {
        this.isForMeat = isForMeat;
    }
}