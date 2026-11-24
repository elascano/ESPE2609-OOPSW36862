package ec.edu.espe.animalfarmapp.model;

import java.time.LocalDate;
import java.time.Period;

/**
 * Abstract superclass representing a generic farm animal.
 * Cannot be instantiated directly; concrete subclasses must implement
 * the abstract behaviors.
 *
 * @author Lanchimba
 */
public abstract class FarmAnimal {

    private int id;
    private String breed;
    private LocalDate bornOn;

    public FarmAnimal(int id, String breed, LocalDate bornOn) {
        this.id = id;
        this.breed = breed;
        this.bornOn = bornOn;
    }

    /**
     * Calculates the exact age of the animal in completed months
     * from its birth date to the current date.
     *
     * @return age in months
     */
    public int getAgeInMonths() {
        LocalDate today = LocalDate.now();
        if (bornOn == null || bornOn.isAfter(today)) {
            return 0;
        }
        Period period = Period.between(bornOn, today);
        return period.getYears() * 12 + period.getMonths();
    }

    /**
     * Defines how this specific farm animal feeds.
     *
     * @return feeding behavior description
     */
    public abstract String feed();

    /**
     * Defines the characteristic sound of this farm animal.
     *
     * @return animal sound
     */
    public abstract String makeSound();

    /**
     * Defines the specific farm output or production of this animal.
     *
     * @return production description
     */
    public abstract String produce();

    public int getId() {
        return id;
    }

    public void setId(int id) {
        this.id = id;
    }

    public String getBreed() {
        return breed;
    }

    public void setBreed(String breed) {
        this.breed = breed;
    }

    public LocalDate getBornOn() {
        return bornOn;
    }

    public void setBornOn(LocalDate bornOn) {
        this.bornOn = bornOn;
    }

    @Override
    public String toString() {
        return "FarmAnimal{" + "id=" + id + ", breed='" + breed + '\'' + ", bornOn=" + bornOn + '}';
    }
}
