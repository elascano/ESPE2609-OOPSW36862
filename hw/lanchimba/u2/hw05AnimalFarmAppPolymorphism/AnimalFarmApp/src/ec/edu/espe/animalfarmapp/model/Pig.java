package ec.edu.espe.animalfarmapp.model;

import java.time.LocalDate;

/**
 * Concrete subclass representing a pig on the farm.
 *
 * @author Lanchimba
 */
public class Pig extends FarmAnimal {

    public Pig(int id, String breed, LocalDate bornOn) {
        super(id, breed, bornOn);
    }

    public Pig(int id, String breed) {
        super(id, breed);
    }

    @Override
    public float computeFoodCost() {
        return 45.00f + (getAgeInMonths() * 0.50f);
    }

    @Override
    public String produce() {
        return "Growing for farm meat production.";
    }

    @Override
    public String toString() {
        return "Pig{" + super.toString() + '}';
    }
}
