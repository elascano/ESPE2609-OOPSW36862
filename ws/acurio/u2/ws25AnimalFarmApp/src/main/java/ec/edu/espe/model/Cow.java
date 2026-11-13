package ec.edu.espe.model;

import java.util.Date;
import java.text.SimpleDateFormat;

/**
 *
 * @author Isaac Acurio, Logical Lords, @ESPE
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
        System.out.println("Milking the cow: " + amountOfLiters + " liters");
    }

    public void milk(float amountOfLiters) {
        litersOfMilkPerWeek += amountOfLiters;
        System.out.println("Milking the cow: " + amountOfLiters + " liters");
    }

    public void milk() {
        System.out.println("Milking the cow as I need");
    }

    public void milk(float amountOfLiters, Date hour) {
        litersOfMilkPerWeek += amountOfLiters;

        SimpleDateFormat format = new SimpleDateFormat("HH:mm");

        System.out.println("Milking the cow for " + amountOfLiters
                + " liters, at " + format.format(hour));
    }

    @Override
    public void feed(Food food, int amountOfFood) {
        System.out.println("Feeding a cow with ---> " + food);
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