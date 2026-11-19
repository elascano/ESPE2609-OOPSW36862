package ec.edu.espe.animalfarmapp.model;

import java.util.Date;

public class Chicken extends FarmAnimal implements GrazeAnimalI {
    private boolean isMolting;
    private int numberOfLaidEggsInAMonth;
    private boolean isMeat;
    
    public Chicken(boolean isMolting, int numberOfLaidEggsInAMonth, boolean isMeat, int id, String breed, Date bornOnDate) {
        super(id, breed, bornOnDate);
        this.isMolting = isMolting;
        this.numberOfLaidEggsInAMonth = numberOfLaidEggsInAMonth;
        this.isMeat = isMeat;
    }
    
    @Override
    public void feed(Food food, int amount){
        System.out.println("Feeding a chicken with ---> " + amount + " pounds of " + food);
    }
    
    @Override
    public void senToGraze(Location location, Date date) {
        System.out.println("Sending the chicken to ---> " + location + " at " + date);
    }
    
    // Sobrecarga para el pollo
    public void layAnEgg(){
        System.out.println("Chicken is laying 1 egg naturally");
    }
    
    public void layAnEgg(int amount){
        numberOfLaidEggsInAMonth += amount;
        System.out.println("Chicken is laying " + amount + " eggs. Total this month: " + numberOfLaidEggsInAMonth);
    }
    
    @Override
    public String toString() {
        return "Chicken{" + super.toString() + "isMolting=" + isMolting + 
               ", numberOfLaidEggsInAMonth=" + numberOfLaidEggsInAMonth + 
               ", isMeat=" + isMeat + '}';
    }
}