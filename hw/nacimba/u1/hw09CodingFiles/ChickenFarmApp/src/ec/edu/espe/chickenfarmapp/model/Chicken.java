package ec.edu.espe.chickenfarmapp.model;

import java.util.Calendar;
import java.util.Date;

/**
 *
 * @author Paucar Matias, ByteWorks, @ESPE
 */
public class Chicken {
    private int id;
    private String name;
    private String color;
    private Date bornOnDate;
    private int ageInYears;
    private boolean molting;
    private Poop poop;

    public Chicken(int id, String name, String color, Date bornOnDate, boolean molting) {
        this(id, name, color, bornOnDate, molting, null);
    }

    public Chicken(int id, String name, String color, Date bornOnDate, boolean molting, Poop poop) {
        this.id = id;
        this.name = name;
        this.color = color;
        this.bornOnDate = bornOnDate;
        this.ageInYears = computeAgeInYears();
        this.molting = molting;
        this.poop = poop;
    }

    public void doStuff() {
        cluck();
        wander();
        eat();
        drink(1.3F);
        System.out.println("poop --> " + poop());
        System.out.println("egg --> " + layAnEgg());
    }

    private void cluck() {
        System.out.println("The chicken " + name + " is clucking ...");
    }

    private void wander() {
        System.out.println("The chicken " + name + " is wandering ...");
    }

    private void eat() {
        System.out.println("The chicken " + name + " is eating ...");
    }

    private void drink(float amountOfWater) {
        System.out.println("The chicken " + name + " is drinking " + amountOfWater + " ml of water ...");
    }

    private Poop poop() {
        if (poop == null) {
            poop = new Poop(id, "green", 'M');
        }
        return poop;
    }

    private Egg layAnEgg() {
        return new Egg(id, 'S');
    }

    public int computeAgeInYears() {
        Calendar birth = Calendar.getInstance();
        birth.setTime(bornOnDate);

        Calendar today = Calendar.getInstance();

        int age = today.get(Calendar.YEAR) - birth.get(Calendar.YEAR);

        if (today.get(Calendar.MONTH) < birth.get(Calendar.MONTH)) {
            age--;
        } else if (today.get(Calendar.MONTH) == birth.get(Calendar.MONTH)
                && today.get(Calendar.DAY_OF_MONTH) < birth.get(Calendar.DAY_OF_MONTH)) {
            age--;
        }

        return age;
    }

    /**
     * Recalculates the age using the current date and the birth date.
     */
    public void updateAgeInYears() {
        this.ageInYears = computeAgeInYears();
    }

    @Override
    public String toString() {
        return "Chicken{" + "id=" + id + ", name=" + name + ", color=" + color
                + ", bornOnDate=" + bornOnDate + ", age=" + ageInYears
                + ", molting=" + molting + ", poop=" + poop + '}';
    }

    public int getId() {
        return id;
    }

    public void setId(int id) {
        this.id = id;
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

    public boolean isMolting() {
        return molting;
    }

    public void setMolting(boolean molting) {
        this.molting = molting;
    }

    public Poop getPoop() {
        return poop;
    }

    public void setPoop(Poop poop) {
        this.poop = poop;
    }
}
    
    