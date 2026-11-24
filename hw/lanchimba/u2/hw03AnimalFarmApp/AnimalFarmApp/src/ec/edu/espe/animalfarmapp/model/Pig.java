package ec.edu.espe.animalfarmapp.model;

import java.time.LocalDate;

/**
 * Subclass representing a pig on the farm.
 *
 * @author Lanchimba
 */
public class Pig extends FarmAnimal {

    public Pig(int id, String breed, LocalDate bornOn) {
        super(id, breed, bornOn);
    }

    @Override
    public String toString() {
        return "Pig{" + super.toString() + '}';
    }
}
