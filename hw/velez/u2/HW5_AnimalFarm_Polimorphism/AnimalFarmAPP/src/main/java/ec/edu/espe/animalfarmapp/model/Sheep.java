package ec.edu.espe.animalfarmapp.model;

import java.util.Date;

/**
 *
 * @author Yandry Velez, Codelex, @ESPE
 */
public class Sheep extends FarmAnimal implements GrazeAnimal {

    private boolean isSheared;
    private Date lastSheering;

    public void shearSheep() {
        System.out.println("Sheep is sheared");
    }

    @Override
    public void feed(Food food, int amount) {
        System.out.println("feeding the sheep with " + amount + " kg of " + food);
    }

    public void sendToGraze(Location location, Date date) {
        System.out.println("send the sheep to " + location + " at " + date.getHours() + " : " + date.getMinutes());
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

    @Override
    public void sendToGaraze(Location location, Date date) {
        throw new UnsupportedOperationException("Not supported yet."); // Generated from nbfs://nbhost/SystemFileSystem/Templates/Classes/Code/GeneratedMethodBody
    }

}