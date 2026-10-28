package ec.edu.espe.chickenfarmapp.model;

import java.util.Calendar;
import java.util.Date;

/**
 *
 * @author Yandry Velez, Codelex, @ESPE
 */
public class Chicken {
    private int ide;
    private String name;
    private String color;
    private Date bornOnDate;
    private int ageInYears;
    private boolean molting;
    
    public void doStuff(){
        cluck();
        wander();
        eat();
        drink(1.3F);
        System.out.println("poop --> " + poop());
        System.out.println("egg --> " + layAnEgg());
    }
    
    private void cluck (){
        System.out.println("The chicken " + name + " is clucking");
    }
    
    private void wander (){
        System.out.println("The chicken " + name + " is wandering");
    }
    
    private void eat (){
        System.out.println("The chicken " + name + " is eating");
    }
    
    private void drink (float amountOfWater){
        System.out.println("The chicken " + name + " is drinking ---> " + amountOfWater + " ml of water");
    }
    
    private Poop poop(){
        Poop poop = new Poop(2, "green", 'M');
        return poop;
    }
    
    private Egg layAnEgg(){
        Egg egg = new Egg(2, 'M');
        return egg;
    }

    public int computeAgeInYears() {
        Calendar birth = Calendar.getInstance();
        birth.setTime(bornOnDate);

        Calendar today = Calendar.getInstance();

        int age = today.get(Calendar.YEAR) - birth.get(Calendar.YEAR);

        if (today.get(Calendar.MONTH) < birth.get(Calendar.MONTH) ||
           (today.get(Calendar.MONTH) == birth.get(Calendar.MONTH) &&
            today.get(Calendar.DAY_OF_MONTH) < birth.get(Calendar.DAY_OF_MONTH))) {
            age--;
        }

        return Math.max(0, age); 
    }

    public Chicken(int ide, String name, String color, Date bornOnDate, boolean molting) {
        this.ide = ide;
        this.name = name;
        this.color = color;
        this.bornOnDate = bornOnDate;
        this.ageInYears = computeAgeInYears();
        this.molting = molting;
    }

    @Override
    public String toString() {
        return "Chicken{" + "ide=" + ide + ", name=" + name + ", color=" + color + 
               ", bornOnDate=" + bornOnDate + ", ageInYears=" + ageInYears + ", molting=" + molting + '}';
    }

    public int getIde() {
        return ide;
    }

    public void setIde(int ide) {
        this.ide = ide;
    }

    public String getName() {
        return name;
    }

    public void setName(String name) {
        this.name = name;
    }

    public String getColor() {
        return color;
    }

    public void setColor(String color) {
        this.color = color;
    }

    public Date getBornOnDate() {
        return bornOnDate;
    }

    public void setBornOnDate(Date bornOnDate) {
        this.bornOnDate = bornOnDate;
        this.ageInYears = computeAgeInYears();
    }

    public int getAgeInYears() {
        return ageInYears;
    }

    public void setAgeInYears() {
        this.ageInYears = computeAgeInYears();
    }

    public boolean isMolting() {
        return molting;
    }

    public void setMolting(boolean molting) {
        this.molting = molting;
    }
}