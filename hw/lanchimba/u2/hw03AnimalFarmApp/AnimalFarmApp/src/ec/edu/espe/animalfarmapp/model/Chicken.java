package ec.edu.espe.animalfarmapp.model;

import java.time.LocalDate;

/**
 * Subclass representing a chicken on the farm.
 *
 * @author Lanchimba
 */
public class Chicken extends FarmAnimal {

    private boolean isMolting;

    public Chicken(int id, String breed, LocalDate bornOn, boolean isMolting) {
        super(id, breed, bornOn);
        this.isMolting = isMolting;
    }

    public boolean isMolting() {
        return isMolting;
    }

    public void setMolting(boolean isMolting) {
        this.isMolting = isMolting;
    }

    @Override
    public String toString() {
        return "Chicken{" + super.toString() + ", isMolting=" + isMolting + '}';
    }
}
