package ec.edu.espe.model;

import java.util.Date;

/**
 *
 * @author Leslye Cuichan, Byte Fabric & devs, @ESPE
 */
public class Sheep extends FarmAnimal {
    private Date lastSheering;
    private boolean isProducingWool;
    private boolean isMeat;

    public Sheep(Date lastSheering, boolean isProducingWool, boolean isMeat, int id, String breed, Date bornOnDate) {
        super(id, breed, bornOnDate);
        this.lastSheering = lastSheering;
        this.isProducingWool = isProducingWool;
        this.isMeat = isMeat;
    }

   @Override
    public void feed(Food food, int amountOfFood) {
        System.out.println("Sheep " + getId() + " is eating " + amountOfFood + " units of " + food.getDescription());
    }

    @Override
    public String toString() {
        return "Sheep{" + super.toString() + " lastSheering=" + lastSheering + ", isProducingWool=" + isProducingWool + ", isMeat=" + isMeat + '}';
    }

    public Date getLastSheering() {
        return lastSheering;
    }

    public void setLastSheering(Date lastSheering) {
        this.lastSheering = lastSheering;
    }

    public boolean isIsProducingWool() {
        return isProducingWool;
    }

    public void setIsProducingWool(boolean isProducingWool) {
        this.isProducingWool = isProducingWool;
    }

    public boolean isIsMeat() {
        return isMeat;
    }

    public void setIsMeat(boolean isMeat) {
        this.isMeat = isMeat;
    }
}