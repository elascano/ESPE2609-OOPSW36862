package ec.edu.espe.animalfarmapp.model;

import java.time.LocalDate;

/**
 * Concrete subclass representing a sheep on the farm.
 *
 * @author Lanchimba
 */
public class Sheep extends FarmAnimal {

    private LocalDate lastSheering;

    public Sheep(int id, String breed, LocalDate bornOn, LocalDate lastSheering) {
        super(id, breed, bornOn);
        this.lastSheering = lastSheering;
    }

    public Sheep(int id, String breed) {
        super(id, breed);
        this.lastSheering = LocalDate.now();
    }

    @Override
    public float computeFoodCost() {
        return 30.00f + (getAgeInMonths() * 0.25f);
    }

    @Override
    public String produce() {
        return "Producing wool (last sheared on " + lastSheering + ").";
    }

    public LocalDate getLastSheering() {
        return lastSheering;
    }

    public void setLastSheering(LocalDate lastSheering) {
        this.lastSheering = lastSheering;
    }

    @Override
    public String toString() {
        return "Sheep{" + super.toString() + ", lastSheering=" + lastSheering + '}';
    }
}
