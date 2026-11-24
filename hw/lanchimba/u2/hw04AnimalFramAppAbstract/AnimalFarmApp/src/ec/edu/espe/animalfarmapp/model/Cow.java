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

    @Override
    public String feed() {
        return "Cow #" + getId() + " (" + getBreed() + ") is grazing on fresh grass and hay.";
    }

    @Override
    public String makeSound() {
        return "Moo!";
    }

    @Override
    public String produce() {
        if (isProducingMilk) {
            return "Producing fresh milk.";
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
