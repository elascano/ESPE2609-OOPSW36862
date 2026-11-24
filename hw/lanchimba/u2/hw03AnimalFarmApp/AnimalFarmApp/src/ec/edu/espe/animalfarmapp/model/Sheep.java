package ec.edu.espe.animalfarmapp.model;

import java.time.LocalDate;

/**
 * Subclass representing a sheep on the farm.
 *
 * @author Lanchimba
 */
public class Sheep extends FarmAnimal {

    private LocalDate lastSheering;

    public Sheep(int id, String breed, LocalDate bornOn, LocalDate lastSheering) {
        super(id, breed, bornOn);
        this.lastSheering = lastSheering;
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
