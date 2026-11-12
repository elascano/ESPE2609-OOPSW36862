package ec.edu.espe.animalfarm.model;

import java.util.Date;

/**
 *
 * @author David Clavón, Infinite Loops, @ESPE
 */
public class Sheep extends FarmAnimal {
    private Date lastSheering;
    private boolean isProducingWool;
    private float kgOfWoolForMonth;

    public void shear() {
        System.out.println("shearing the sheep");
    }

    public Sheep(Date lastSheering, boolean isProducingWool, float kgOfWoolForMonth, int id, String breed, Date bornOnDate) {
        super(id, breed, bornOnDate);
        this.lastSheering = lastSheering;
        this.isProducingWool = isProducingWool;
        this.kgOfWoolForMonth = kgOfWoolForMonth;
    }

    @Override
    public String toString() {
        return "Sheep{" + super.toString() + 
                "lastSheering=" + lastSheering.getDate() + 
                "/" + (lastSheering.getMonth() + 1) + 
                "/" + (lastSheering.getYear() + 1900) + 
                ", isProducingWool=" + isProducingWool + 
                ", kgOfWoolForMonth=" + kgOfWoolForMonth + '}';
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

    /**
     * @return the isProducingWool
     */
    public boolean isIsProducingWool() {
        return isProducingWool;
    }

    /**
     * @param isProducingWool the isProducingWool to set
     */
    public void setIsProducingWool(boolean isProducingWool) {
        this.isProducingWool = isProducingWool;
    }

    /**
     * @return the kgOfWoolForMonth
     */
    public float getKgOfWoolForMonth() {
        return kgOfWoolForMonth;
    }

    /**
     * @param kgOfWoolForMonth the kgOfWoolForMonth to set
     */
    public void setKgOfWoolForMonth(float kgOfWoolForMonth) {
        this.kgOfWoolForMonth = kgOfWoolForMonth;
    }
}