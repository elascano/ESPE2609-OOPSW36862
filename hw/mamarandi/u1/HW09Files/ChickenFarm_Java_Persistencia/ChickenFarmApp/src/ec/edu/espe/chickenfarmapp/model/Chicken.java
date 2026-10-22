package ec.edu.espe.chickenfarmapp.model;

import java.util.Calendar;
import java.util.Date;

/**
 *
 * @author Marcos Mamarandi, Binary Bosses,@ESPE
 */
public class Chicken {
    private int id;
    private String name;
    private String color;
    private Date born0nDate;
    private int ageInYears;
    private boolean molting;
    
    public void doStuff(){
        cluck();
        wander();
        drink(1.3F);
        System.out.println("poop --> " + poop());
        System.out.println("egg --> " + layAnEgg());
        
    }
    
    private void cluck(){
        System.out.println("the chicken"+ name + "is clucking ...");
    }
    private void wander(){
        System.out.println("the chicken"+ name + "is wandering ...");
    }
    private void eat(){
        System.out.println("the chicken"+ name + "is eating ...");
    }
    private void drink(float amount0fWater){
        System.out.println("the chicken"+ name + "is drinking --> " + amount0fWater + "ml of water");
    }
    private Poop poop(){
        Poop poop; 
        poop = new Poop(2, "green", 'M');
        return poop; 
    }
    private Egg layAnEgg(){
        Egg egg;
        egg = new Egg (2, 'S');
        return egg;
    }
    
    
    
public int computeAgeInYears() {
    int age;
    
 
    Calendar birth = Calendar.getInstance();
    birth.setTime(born0nDate);

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
        return "Chicken{" + "id=" + id + ", name=" + name + ", color=" + color + ", born0nDate=" + born0nDate + ", age=" + ageInYears + ", molting=" + molting + '}';
    }
    
    

    public Chicken(int id, String name, String color, Date born0nDate, boolean molting) {
        this.id = id;
        this.name = name;
        this.color = color;
        this.born0nDate = born0nDate;
        this.ageInYears = computeAgeInYears();
        this.molting = molting;
    }
    
    

    /**
     * @return the id
     */
    public int getId() {
        return id;
    }

    /**
     * @param id the id to set
     */
    public void setId(int id) {
        this.id = id;
    }

    /**
     * @return the name
     */
    public String getName() {
        return name;
    }

    /**
     * @param name the name to set
     */
    public void setName(String name) {
        this.name = name;
    }

    /**
     * @return the color
     */
    public String getColor() {
        return color;
    }

    /**
     * @param color the color to set
     */
    public void setColor(String color) {
        this.color = color;
    }

    /**
     * @return the born0nDate
     */
    public Date getBorn0nDate() {
        return born0nDate;
    }

    /**
     * @param born0nDate the born0nDate to set
     */
    public void setBorn0nDate(Date born0nDate) {
        this.born0nDate = born0nDate;
    }

    /**
     * @return the ageInYears
     */
    public int getAgeInYears() {
        return ageInYears;
    }

    /**
     * @param ageInYears the ageInYears to set
     */
    public void setAgeInYears(int ageInYears) {
        this.ageInYears = ageInYears;
    }
    
    public void setAgeInYears() {
    this.ageInYears = computeAgeInYears();
    }


    /**
     * @return the molting
     */
    public boolean isMolting() {
        return molting;
    }

    /**
     * @param molting the molting to set
     */
    public void setMolting(boolean molting) {
        this.molting = molting;
    }
    
    
}
