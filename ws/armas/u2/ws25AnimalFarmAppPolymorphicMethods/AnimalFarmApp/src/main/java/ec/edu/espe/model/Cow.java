package ec.edu.espe.model;

import java.util.Date;


/**
 *
* @author Demian Armas, Logical Lords, @ESPE
*/
public class Cow extends FarmAnimal implements grazeAnimal {

    private boolean isProducingMilk;
    private float litersOfMilkPerWeek;
    private String food;

    @Override
    public void graze(Location location) {
        System.out.println("Feeding a cow in the stable with ---> " + food);
    }

    public void milk(int amountOfLiters) {
        litersOfMilkPerWeek += amountOfLiters;
        System.out.println("milking the cow: " + amountOfLiters + " liters");
    }

    public void milk(float amountOfLiters) {
        litersOfMilkPerWeek += amountOfLiters;
        System.out.println("milking the cow: " + amountOfLiters + " liters");
    }

    public void milk() {
        System.out.println("milking the cow as I need");
    }


    @Override
    public void feed(Food food, int amount) {
        System.out.println("Feeding a cow with ---> " +amount + "of "+ food);
    }

    public Cow(boolean isProducingMilk, float litersOfMilkPerWeek,
            int id, String breed, Date bornOnDate) {

        super(id, breed, bornOnDate);
        this.isProducingMilk = isProducingMilk;
        this.litersOfMilkPerWeek = litersOfMilkPerWeek;
    }

    @Override
    public String toString() {
        return "Cow{" + super.toString()
                + ", isProducingMilk=" + isProducingMilk()
                + ", litersOfMilkPerWeek=" + getLitersOfMilkPerWeek()
                + '}';
    }

    public boolean isProducingMilk() {
        return isProducingMilk;
    }

    public void setProducingMilk(boolean producingMilk) {
        this.isProducingMilk = producingMilk;
    }

    public float getLitersOfMilkPerWeek() {
        return litersOfMilkPerWeek;
    }

    public void setLitersOfMilkPerWeek(float litersOfMilkPerWeek) {
        this.litersOfMilkPerWeek = litersOfMilkPerWeek;
    }
}