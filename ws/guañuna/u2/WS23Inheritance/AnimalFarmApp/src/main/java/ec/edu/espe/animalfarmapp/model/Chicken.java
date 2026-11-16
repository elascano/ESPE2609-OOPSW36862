package ec.edu.espe.animalfarmapp.model;

import java.util.Date;

/**
 *
 * @author Cristopher Guañuna, Runtime Dynamics, @ESPE
 */
public class Chicken extends FarmAnimal {
    private boolean isMolting;

    public Chicken(boolean isMolting, int id, String breed, Date bornOnDate) {
        super(id, breed, bornOnDate);
        this.isMolting = isMolting;
    }

    public boolean isIsMolting() {
        return isMolting;
    }

    public void setIsMolting(boolean isMolting) {
        this.isMolting = isMolting;
    }

    @Override
    public String toString() {
        return "Chicken{" + "isMolting=" + isMolting + ", " + super.toString() + '}';
    }
}