package ec.edu.espe.chickenfarmapp.model;

import java.util.Calendar;
import java.util.Date;

/**
 * @author Josselyn Ulcuango, Codelex, @ESPE
 */
public class Chicken {
    private int id;
    private String name;
    private String color;
    private Date bornOnDate;
    private int ageInYear;
    private boolean molting;
    
    public Chicken() {
    }
    
    public Chicken(int ide, String name, String color, Date bornOnDate, int age, boolean molting) {
        this.id = ide;
        this.name = name;
        this.color = color;
        this.bornOnDate = bornOnDate;
        this.ageInYear = age;
        this.molting = molting;
    }
    
    public void doStuff (){
        cluck();
        wander();
        drink (1.3F);
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
        System.out.println("The chicken " + name + " is drinking --> " + amountOfWater + " ml of water");
    }
    
    private Poop poop(){
        Poop poop = new Poop (2, "green", 'M');
        return poop;
    }
    
    private Egg layAnEgg() {
        Egg egg = new Egg(2, 'S');
        return egg;
    }
    
    public int computeAgeInYears(){
        int age = 0;
        Calendar birth = Calendar.getInstance();
        birth.setTime(bornOnDate);
        Calendar today = Calendar.getInstance();
        age = today.get(Calendar.YEAR) - birth.get(Calendar.YEAR);
        if (today.get(Calendar.MONTH) < birth.get(Calendar.MONTH)) {
            age--;
        } else if (today.get(Calendar.MONTH) == birth.get(Calendar.MONTH)
                && today.get(Calendar.DAY_OF_MONTH) < birth.get(Calendar.DAY_OF_MONTH)) {
            age--;
        }
        return age;
    }

    @Override
    public String toString() {
        return "Chicken{id=" + id + ", name=" + name + ", color=" + color + ", bornOnDate=" + bornOnDate + ", age=" + ageInYear + ", molting=" + molting + '}';
    }
    
    public int getId() { return id; }
    public void setId(int id) { this.id = id; }
    public String getName() { return name; }
    public void setName(String name) { this.name = name; }
    public String getColor() { return color; }
    public void setColor(String color) { this.color = color; }
    public Date getBornOnDate() { return bornOnDate; }
    public void setBornOnDate(Date bornOnDate) { this.bornOnDate = bornOnDate; }
    public int getAgeInYear() { return ageInYear; }
    public void setAgeInYear(int age) { this.ageInYear = age; }
    public boolean isMolting() { return molting; }
    public void setMolting(boolean molting) { this.molting = molting; }
}