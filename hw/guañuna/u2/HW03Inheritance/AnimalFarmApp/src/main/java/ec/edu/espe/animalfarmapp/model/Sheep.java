package ec.edu.espe.animalfarmapp.model;

import java.util.Date;

/**
 *
 * @author Cristopher Guañuna, Runtime Dynamics, @ESPE
 */
public class Sheep extends FarmAnimal {
    private Date lastSheering;

    public Sheep(Date lastSheering, int id, String breed, Date bornOnDate) {
        super(id, breed, bornOnDate);
        this.lastSheering = lastSheering;
    }

    public Date getLastSheering() {
        return lastSheering;
    }

    public void setLastSheering(Date lastSheering) {
        this.lastSheering = lastSheering;
    }

    @Override
    public String toString() {
        return "Sheep{" + "lastSheering=" + lastSheering + ", " + super.toString() + '}';
    }
}