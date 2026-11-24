package ec.edu.espe.animalfarmapp.model;

import java.time.LocalDate;

/**
 * Concrete subclass representing a chicken on the farm.
 *
 * @author Lanchimba
 */
public class Chicken extends FarmAnimal {

    private boolean isMolting;

    public Chicken(int id, String breed, LocalDate bornOn, boolean isMolting) {
        super(id, breed, bornOn);
        this.isMolting = isMolting;
    }

    public Chicken(int id, String breed) {
        super(id, breed);
        this.isMolting = false;
    }

    @Override
    public float computeFoodCost() {
        return isMolting ? 10.50f : 8.50f;
    }

    @Override
    public String produce() {
        if (!isMolting) {
            return "Laying fresh eggs.";
        }
        return "Not laying eggs right now (currently molting).";
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
