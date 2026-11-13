package ec.edu.espe.animalfarmapp.model;

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
    public String toString() {
        return "Sheep{" + super.toString() + "lastSheering=" + lastSheering + ", isProducingWool=" + isProducingWool + ", isMeat=" + isMeat + '}';
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
     * @return the isMeat
     */
    public boolean isIsMeat() {
        return isMeat;
    }

    /**
     * @param isMeat the isMeat to set
     */
    public void setIsMeat(boolean isMeat) {
        this.isMeat = isMeat;
    }
}