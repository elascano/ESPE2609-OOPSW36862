package ec.edu.espe.animalfarmapp.model;

import java.util.Date;

/**
 *
 * @author Francisco Demera,Byte & Fabric Devs,@ESPE
 */
public class Cow extends FarmAnimal implements GrazeAnimalI {

    private boolean isProducingMilk;
    private float litersOfMilkPerWeek;

    public void milk() {

        System.out.println("this cow has produced --->" + litersOfMilkPerWeek + "liters per week");
    }

    public void milk(float liters) {
        litersOfMilkPerWeek += liters;
        System.out.println("milking the cow... we got --->" + liters + " liters today");
        System.out.println("this cow has produced --->" + litersOfMilkPerWeek + "liters per week");
    }

    public void milk(int liters) {
        litersOfMilkPerWeek += liters;
        System.out.println("milking the cow... we got --->" + liters + " liters today");
        System.out.println("this cow has produced --->" + litersOfMilkPerWeek + "liters per week");
    }

    @Override
    public void feed(Food food, int amount) {
        System.out.println("feeding the cow with " + amount + " of " + food);
    }

    public Cow(boolean isProducingMilk, float litersOfMilkPerWeek, int id, String breed, Date bornOnDate, boolean isMeat) {
        super(id, breed, bornOnDate, isMeat);
        this.isProducingMilk = isProducingMilk;
        this.litersOfMilkPerWeek = litersOfMilkPerWeek;
    }

    @Override
    public void sendToGraze(Location location, Date date) {
        System.out.println("send the cow to " + location + " at " + date.getHours() + " : " + date.getMinutes());
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
