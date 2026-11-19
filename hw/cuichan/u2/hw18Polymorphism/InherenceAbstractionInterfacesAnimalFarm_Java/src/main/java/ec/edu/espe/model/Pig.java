package ec.edu.espe.model;

import java.util.Date;

/**
 *
 * @author Leslye Cuichan, Byte Fabric & devs, @ESPE
 */
public class Pig extends FarmAnimal {
    private double weight;
    private boolean isMeat;

    public Pig(int id, String breed, Date bornOn, double weight, boolean isMeat) {
        super(id, breed, bornOn);
        this.weight = weight;
        this.isMeat = isMeat;
    }

    @Override
    public void feed(Food food, int amountOfFood) {
        System.out.println("Pig " + getId() + " is eating " + amountOfFood + " units of " + food.getDescription());
    }

    @Override
    public String toString() {
        return "Pig{" + super.toString() + " weight=" + weight + ", isMeat=" + isMeat + "}";
    }

    public double getWeight() {
        return weight;
    }

    public void setWeight(double weight) {
        this.weight = weight;
    }

    public boolean isIsMeat() {
        return isMeat;
    }

    public void setIsMeat(boolean isMeat) {
        this.isMeat = isMeat;
    }
}