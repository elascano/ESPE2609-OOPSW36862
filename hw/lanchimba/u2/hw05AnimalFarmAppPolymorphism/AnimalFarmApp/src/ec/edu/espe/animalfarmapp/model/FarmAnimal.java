package ec.edu.espe.animalfarmapp.model;

import java.time.LocalDate;
import java.time.Period;
import java.util.Locale;

/**
 * Abstract superclass implementing IFarmAnimal.
 * Demonstrates static polymorphism (constructor and method overloading)
 * and defines abstract hooks for dynamic polymorphism (overriding).
 *
 * @author Lanchimba
 */
public abstract class FarmAnimal implements IFarmAnimal {

    private int id;
    private String breed;
    private LocalDate bornOn;

    /**
     * Default constructor (static polymorphism - constructor overloading).
     */
    public FarmAnimal() {
        this(0, "Unknown", LocalDate.now());
    }

    /**
     * Minimal constructor defaulting birth date to today (constructor overloading).
     *
     * @param id    animal identifier
     * @param breed animal breed
     */
    public FarmAnimal(int id, String breed) {
        this(id, breed, LocalDate.now());
    }

    /**
     * Full constructor with all attributes (constructor overloading).
     *
     * @param id     animal identifier
     * @param breed  animal breed
     * @param bornOn birth date
     */
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
     * Feeds the animal with a standard ration (overloaded method - no parameters).
     *
     * @return feeding action description
     */
    @Override
    public String feed() {
        return getClass().getSimpleName() + " #" + id + " (" + breed
                + ") is being fed a standard daily ration.";
    }

    /**
     * Feeds the animal with a specific food type and quantity (static polymorphism - overloading).
     *
     * @param foodType type of food
     * @param quantity amount in kilograms
     * @return feeding action description
     */
    public String feed(String foodType, float quantity) {
        return String.format(
                Locale.US,
                "%s #%d (%s) is being fed %.2f kg of %s.",
                getClass().getSimpleName(),
                id,
                breed,
                quantity,
                foodType
        );
    }

    @Override
    public abstract float computeFoodCost();

    @Override
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
