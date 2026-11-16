package ec.edu.espe.animalfarmapp.model;

import java.text.SimpleDateFormat;
import java.util.Date;

/**
 *
 * @ESPE LAB
 */
public class Sheep extends FarmAnimal {

    private boolean isSheared;
    private Date lastSheering;

    public Sheep(boolean isSheared, Date lastSheering, int id, String breed, Date bornOnDate, boolean isMeat) {
        super(id, breed, bornOnDate, isMeat);
        this.isSheared = isSheared;
        this.lastSheering = lastSheering;
    }

    public void shearSheep() {
        System.out.println("Sheep is sheared");
    }

    @Override
    public void feed(Food food, int amount) {
        System.out.println("Feeding the sheep with " + amount + " of " + food);
    }

    @Override
    public String toString() {
        SimpleDateFormat format = new SimpleDateFormat("dd / MM / yyyy");
        return "Sheep{" + super.toString() + "isSheared=" + isSheared + ", lastSheering=" + format.format(lastSheering) + '}';
    }

    /**
     * @return the isSheared
     */
    public boolean isIsSheared() {
        return isSheared;
    }

    /**
     * @param isSheared the isSheared to set
     */
    public void setIsSheared(boolean isSheared) {
        this.isSheared = isSheared;
    }

    /**
     * @return the lastSheering
     */
    public Date getLastSheering() {
        return lastSheering;
    }

    /**
     * @param lastSheering the lastSheering to set
     */
    public void setLastSheering(Date lastSheering) {
        this.lastSheering = lastSheering;
    }
}
