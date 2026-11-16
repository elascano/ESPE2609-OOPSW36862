package ec.edu.espe.animalfarmapp.model;

import java.util.Date;

public class Pig extends FarmAnimal implements GrazeAnimalI {
    private float weightInKg;
    private boolean isHungry;

    public Pig(float weightInKg, boolean isHungry, int id, String breed, Date bornOnDate) {
        super(id, breed, bornOnDate);
        this.weightInKg = weightInKg;
        this.isHungry = isHungry;
    }

    @Override
    public void feed(Food food, int amount) {
        System.out.println("Feeding a pig with ---> " + amount + " of " + food);
    }

    @Override
    public void senToGraze(Location location, Date date) {
        System.out.println("Sending the pig to ---> " + location + " at " + date);
    }

    // Sobrecarga para el chancho: comer y engordar
    public void eat() {
        System.out.println("Pig is eating slop. Just maintaining weight.");
    }

    public void eat(float kgOfFood) {
        weightInKg += (kgOfFood * 0.8f);
        isHungry = false;
        System.out.println("Pig ate " + kgOfFood + "kg of food. New weight: " + weightInKg + " kg. Hungry? " + isHungry);
    }

    @Override
    public String toString() {
        return "Pig{" + super.toString() + "weightInKg=" + weightInKg + 
               ", isHungry=" + isHungry + '}';
    }
}