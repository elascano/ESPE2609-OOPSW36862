package ec.edu.espe.animalfarmapp.model;

import java.util.Date;

/**
 *
 * @author Cristopher Guañuna, Runtime Dynamics, ESPE
 */
public class Sheep extends FarmAnimal {
    private Date lastSheering;
    private float woolWeightInKg;

    public Sheep(Date lastSheering, float woolWeightInKg, int id, String breed, Date bornOnDate) {
        super(id, breed, bornOnDate);
        this.lastSheering = lastSheering;
        this.woolWeightInKg = woolWeightInKg;
    }

    public void shear() {
        System.out.println("shearing the sheep()");
    }

    @Override
    public String toString() {
        return "Sheep{" + super.toString() + "lastSheering=" + lastSheering + 
               ", woolWeightInKg=" + woolWeightInKg + '}';
    }

    public Date getLastSheering() { return lastSheering; }
    public void setLastSheering(Date lastSheering) { this.lastSheering = lastSheering; }

    public float getWoolWeightInKg() { return woolWeightInKg; }
    public void setWoolWeightInKg(float woolWeightInKg) { this.woolWeightInKg = woolWeightInKg; }
}