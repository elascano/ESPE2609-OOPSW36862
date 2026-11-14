
package ec.edu.espe.model;

import java.util.Collection;
import java.util.Date;

/**
 *
 * @author David Clavón, Infinite loops, @ESPE
 */
public class Cow extends FarmAnimal implements GrazeAnimal{
    private boolean isProducingMilk;
    private float litersOfMilkPerWeek;
    private Collection vacinnes;
    public Cow(boolean isProducingMilk, float litersOfMilkPerWeek, int id, String breed, Date bornOnDate) {
        super(id, breed, bornOnDate);
        this.isProducingMilk = isProducingMilk;
        this.litersOfMilkPerWeek = litersOfMilkPerWeek;
    }
    @Override
    public void graze(Location location) {
        System.out.println("Sending the cow to " + location);
    }
    /**
     *
     * @param food
     * @param amountOfFood
     */
    @Override
    public void feed(Food food, int amountOfFood) {
        System.out.println("Feeding a cow in the Stable with ---> "+ amountOfFood + "of" + food);
        
    }

    @Override
    public String toString() {
        return "Cow{" +super.toString()+ "isProducingMilk=" + isProducingMilk 
                + ", litersOfMilkPerWeek=" + litersOfMilkPerWeek + '}';
    }
    
    public boolean isIsProducingMilk() {
        return isProducingMilk;
    }

    public void setIsProducingMilk(boolean isProducingMilk) {
        this.isProducingMilk = isProducingMilk;
    }

    public float getLitersOfMilkPerWeek() {
        return litersOfMilkPerWeek;
    }

    public void setLitersOfMilkPerWeek(float litersOfMilkPerWeek) {
        this.litersOfMilkPerWeek = litersOfMilkPerWeek;
    }
    
    public void milk(float liters){
        litersOfMilkPerWeek += liters;
        System.out.println("miking the cow... we got "+liters+" today");
        System.out.println("this cow has produce:  " + liters );
    }
    public void milk(){
        System.out.println("Milking the Cow I need" );
    }
    public void milk(double amountOfLiters){
        litersOfMilkPerWeek += amountOfLiters;
        System.out.println("Milking the Cow obtend  " + amountOfLiters );
    }
    
    public void milk(float amountOfLiters, Date hour){
        litersOfMilkPerWeek += amountOfLiters;
        System.out.println("milking the cow for " + amountOfLiters + "liters, at "+ hour.getHours() + ":" + hour.getMinutes());
    }
    
    

    

    
}
