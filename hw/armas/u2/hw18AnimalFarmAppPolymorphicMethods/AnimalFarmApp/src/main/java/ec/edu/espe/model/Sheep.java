package ec.edu.espe.model;

import java.util.Date;

public class Sheep extends FarmAnimal {
    private Date lastShearing;
    private final boolean isProducingWool;
    private double kgOfWoolForMonth;

    public Sheep(Date lastShearing, boolean isProducingWool, double kgOfWoolForMonth,
            int id, String breed, Date bornOnDate) {
        super(id, breed, bornOnDate);
        this.lastShearing = lastShearing;
        this.isProducingWool = isProducingWool;
        this.kgOfWoolForMonth = kgOfWoolForMonth;
    }

    @Override
    public void feed(Food food, int amount) {
        System.out.println("Feeding the sheep " + amount + " lb of " + food + ".");
    }

    public String shear() {
        return "The sheep is being sheared.";
    }

    public String shear(double kilograms) {
        if (!Double.isFinite(kilograms) || kilograms <= 0) {
            throw new IllegalArgumentException("The wool weight must be positive and finite.");
        }
        kgOfWoolForMonth += kilograms;
        lastShearing = new Date();
        return "Collected " + kilograms + " kg of wool.";
    }

    public Date getLastShearing() {
        return lastShearing;
    }

    public boolean isProducingWool() {
        return isProducingWool;
    }

    public double getKgOfWoolForMonth() {
        return kgOfWoolForMonth;
    }
}
