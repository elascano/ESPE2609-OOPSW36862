package ec.edu.espe.animalfarmapp.model;

import java.time.LocalDate;

/**
 * Subclass representing a cow on the farm.
 *
 * @author Lanchimba
 */
public class Cow extends FarmAnimal {

    private boolean isProducingMilk;

    public Cow(int id, String breed, LocalDate bornOn, boolean isProducingMilk) {
        super(id, breed, bornOn);
        this.isProducingMilk = isProducingMilk;
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
