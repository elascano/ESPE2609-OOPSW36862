package ec.edu.espe.model;

import java.util.Date;
import java.text.SimpleDateFormat;

/**
 *
 * @author Cristopher Guañuna, Runtime Dynamic @ESPE
 */
public class Cow extends FarmAnimal implements GrazeAnimalI {

    private boolean isProducingMilk;
    private float litersOfMilkPerWeek;
    private String food;

    @Override
    public void senToGraze(Location location, Date date) {
        System.out.println("Senfing the cow to ---> " + location + "at" + date);
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
    public void feed(Food food, int amount) {
        System.out.println("Feeding a cow with ---> " + amount + " of " + food);
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