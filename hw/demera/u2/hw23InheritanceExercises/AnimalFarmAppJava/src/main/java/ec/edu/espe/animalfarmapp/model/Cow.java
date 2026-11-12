package ec.edu.espe.animalfarmapp.model;

import java.util.Date;

/**
 *
 * @author Francisco Demera,Byte & Fabric Devs,@ESPE
 */
public class Cow extends FarmAnimal {

    private boolean isProducingMilk;
    private float litersOfMilkPerWeek;

    public Cow(boolean isProducingMilk, float litersOfMilkPerWeek, int id, String breed, Date bornOnDate, boolean isMeat) {
        super(id, breed, bornOnDate, isMeat);
        this.isProducingMilk = isProducingMilk;
        this.litersOfMilkPerWeek = litersOfMilkPerWeek;
    }

    @Override
    public String toString() {
        return "Cow{" + super.toString() + "isProducingMilk=" + isProducingMilk + ", litersOfMilkPerWeek=" + litersOfMilkPerWeek + '}';
    }

    /**
     * @return the isProducingMilk
     */
    public boolean isIsProducingMilk() {
        return isProducingMilk;
    }

    /**
     * @param isProducingMilk the isProducingMilk to set
     */
    public void setIsProducingMilk(boolean isProducingMilk) {
        this.isProducingMilk = isProducingMilk;
    }

    /**
     * @return the litersOfMilkPerWeek
     */
    public float getLitersOfMilkPerWeek() {
        return litersOfMilkPerWeek;
    }

    /**
     * @param litersOfMilkPerWeek the litersOfMilkPerWeek to set
     */
    public void setLitersOfMilkPerWeek(float litersOfMilkPerWeek) {
        this.litersOfMilkPerWeek = litersOfMilkPerWeek;
    }

}
