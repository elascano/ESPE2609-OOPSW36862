package ec.edu.espe.animalfarmapp.model;

import java.util.Date;

/**
 * @author Andrés Pachacama, ByteWorks, @ESPE
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

    public boolean isIsSheared() {
        return isSheared;
    }

    public void setIsSheared(boolean isSheared) {
        this.isSheared = isSheared;
    }

    public Date getLastSheering() {
        return lastSheering;
    }

    public void setLastSheering(Date lastSheering) {
        this.lastSheering = lastSheering;
    }
}
