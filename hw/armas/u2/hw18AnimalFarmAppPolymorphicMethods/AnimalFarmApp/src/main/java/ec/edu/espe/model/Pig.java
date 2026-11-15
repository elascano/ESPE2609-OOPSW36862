package ec.edu.espe.model;

import java.util.Date;

public class Pig extends FarmAnimal {
    private final double weight;
    private final boolean isForMeat;
    private double totalCutWeight;

    public Pig(double weight, boolean isForMeat, int id, String breed, Date bornOnDate) {
        super(id, breed, bornOnDate);
        this.weight = weight;
        this.isForMeat = isForMeat;
    }

    @Override
    public void feed(Food food, int amount) {
        System.out.println("Feeding the pig " + amount + " lb of " + food + ".");
    }

    public String recordCut(String cutName, double kilograms) {
        if (cutName == null || cutName.isBlank() || !Double.isFinite(kilograms) || kilograms <= 0) {
            throw new IllegalArgumentException("A cut needs a name and a positive, finite weight.");
        }
        totalCutWeight += kilograms;
        return "Recorded " + kilograms + " kg of " + cutName.strip() + ".";
    }

    public double getWeight() {
        return weight;
    }

    public boolean isForMeat() {
        return isForMeat;
    }

    public double getTotalCutWeight() {
        return totalCutWeight;
    }
}
