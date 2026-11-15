package ec.edu.espe.animalfarm.model;

import java.util.Date;

/**
 *
 * @author Enrique Ayala, LogicalLords, ESPE
 */

public class Cow extends FarmAnimal{
    private boolean isProducingMilk;
    private float litersOfMilkForWeek;
    
    public void milk(){
        System.out.println("milking the cow");
    }

    public Cow(boolean isProducingMilk, float litersOfMilkForWeek, int id, String breed, Date bornOnDate) {
        super(id, breed, bornOnDate);
        this.isProducingMilk = isProducingMilk;
        this.litersOfMilkForWeek = litersOfMilkForWeek;
    }                                                                                                                                                                 

    @Override
    public String toString() {
        return "Cow{" + super.toString() + "isProducingMilk=" + isProducingMilk + ", litersOfMilkForWeek=" + litersOfMilkForWeek + '}';
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
     * @return the litersOfMilkForWeek
     */
    public float getLitersOfMilkForWeek() {
        return litersOfMilkForWeek;
    }

    /**
     * @param litersOfMilkForWeek the litersOfMilkForWeek to set
     */
    public void setLitersOfMilkForWeek(float litersOfMilkForWeek) {
        this.litersOfMilkForWeek = litersOfMilkForWeek;
    }
    
    
}
