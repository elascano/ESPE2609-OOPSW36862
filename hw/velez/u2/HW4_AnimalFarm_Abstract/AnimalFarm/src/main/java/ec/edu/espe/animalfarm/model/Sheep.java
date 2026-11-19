package ec.edu.espe.animalfarm.model;

import java.util.Date;

/**
 *
 * @author Yandry Velez, Codelex, @ESPE
 */
public class Sheep extends FarmAnimal {

    private boolean isSheared;
    private Date lastSheering;

    public void shearSheep(){
        System.out.println("Sheep is sheared");
    }
    @Override
    public String toString() {
        return "Sheep{" + super.toString() + "isSheared=" + isSheared + ", lastSheering=" + lastSheering + '}';
    }

    public Sheep(boolean isSheared, Date lastSheering, int id, String breed, Date bornOnDate, boolean isMeat) {
        super(id, breed, bornOnDate, isMeat);
        this.isSheared = isSheared;
        this.lastSheering = lastSheering;
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
