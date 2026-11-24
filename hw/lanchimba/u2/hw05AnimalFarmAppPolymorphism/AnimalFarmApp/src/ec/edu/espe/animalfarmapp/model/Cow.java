package ec.edu.espe.animalfarmapp.model;

import java.time.LocalDate;

/**
 * Concrete subclass representing a cow on the farm.
 *
 * @author Lanchimba
 */
public class Cow extends FarmAnimal {

    private boolean isProducingMilk;

    public Cow(int id, String breed, LocalDate bornOn, boolean isProducingMilk) {
        super(id, breed, bornOn);
        this.isProducingMilk = isProducingMilk;
    }

    public Cow(int id, String breed) {
        super(id, breed);
        this.isProducingMilk = false;
    }

    @Override
    public float computeFoodCost() {
        return isProducingMilk ? 120.00f : 85.00f;
    }

    @Override
    public String produce() {
        if (isProducingMilk) {
            return "Producing fresh dairy milk.";
        }
        return "Not producing milk at the moment.";
    }

    public boolean isProducingMilk() {
        return isProducingMilk;
    }

    public void setProducingMilk(boolean isProducingMilk) {
        this.isProducingMilk = isProducingMilk;
    }

    @Override
    public String toString() {
        return "Cow{" + super.toString() + ", isProducingMilk=" + isProducingMilk + '}';
    }
}
