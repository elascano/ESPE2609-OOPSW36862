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

    @Override
    public String feed() {
        return "Pig #" + getId() + " (" + getBreed() + ") is eating corn, vegetables, and farm feed.";
    }

    @Override
    public String makeSound() {
        return "Oink, oink!";
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
