package ec.edu.espe.animalfarmapp.model;

import java.util.Date;

/**
 *
 * @author Cristopher Guañuna, Runtime Dynamics, ESPE
 */
public class Cow extends FarmAnimal {
    private boolean isProducingMilk;
    private float litersOfMilkForWeek;

    public Cow(boolean isProducingMilk, float litersOfMilkForWeek, int id, String breed, Date bornOnDate) {
        super(id, breed, bornOnDate);
        this.isProducingMilk = isProducingMilk;
        this.litersOfMilkForWeek = litersOfMilkForWeek;
    }

    public void milk() {
        System.out.println("milking the cow()");
    }

    @Override
    public String toString() {
        return "Cow{" + super.toString() + "isProducingMilk=" + isProducingMilk + 
               ", litersOfMilkForWeek=" + litersOfMilkForWeek + '}';
    }

    public boolean isIsProducingMilk() { return isProducingMilk; }
    public void setIsProducingMilk(boolean isProducingMilk) { this.isProducingMilk = isProducingMilk; }

    public float getLitersOfMilkForWeek() { return litersOfMilkForWeek; }
    public void setLitersOfMilkForWeek(float litersOfMilkForWeek) { this.litersOfMilkForWeek = litersOfMilkForWeek; }
}