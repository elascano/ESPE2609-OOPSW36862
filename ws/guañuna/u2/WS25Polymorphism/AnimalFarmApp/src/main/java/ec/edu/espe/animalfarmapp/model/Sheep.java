package ec.edu.espe.animalfarmapp.model;

import java.util.Date;

public class Sheep extends FarmAnimal implements GrazeAnimalI {
    private Date lastSheering;
    private float woolWeightInKg;

    public Sheep(Date lastSheering, float woolWeightInKg, int id, String breed, Date bornOnDate) {
        super(id, breed, bornOnDate);
        this.lastSheering = lastSheering;
        this.woolWeightInKg = woolWeightInKg;
    }

    @Override
    public void feed(Food food, int amount) {
        System.out.println("Feeding a sheep with ---> " + amount + " of " + food);
    }

    @Override
    public void senToGraze(Location location, Date date) {
        System.out.println("Sending the sheep to ---> " + location + " at " + date);
    }

    // Sobrecarga para la oveja: esquilar
    public void shear() {
        System.out.println("Shearing the sheep casually. Wool remains at: " + woolWeightInKg + " kg.");
    }

    public void shear(float kgOfWool) {
        woolWeightInKg += kgOfWool;
        System.out.println("Shearing the sheep for " + kgOfWool + " kg of wool. Total wool collected: " + woolWeightInKg + " kg.");
    }

    @Override
    public String toString() {
        return "Sheep{" + super.toString() + "lastSheering=" + lastSheering + 
               ", woolWeightInKg=" + woolWeightInKg + '}';
    }
}