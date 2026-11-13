package ec.edu.espe.animalfarmapp.model;

import java.util.Date;
/**
 *
 * @author Josselyn Ulcuango, CodeLex, @ESPE
 */
public class Sheep extends FarmAnimal {
    private Date lastSheering;

    public Sheep(int id, String bred, Date bornOnDate, Date lastSheering) {
        super(id, bred, bornOnDate);
        this.lastSheering = lastSheering;
    }

    @Override
    public String toString() {
        return "Sheep{" + super.toString() + "lastSheering=" + lastSheering + '}';
    }

    public Date getLastSheering() {
        return lastSheering;
    }

    public void setLastSheering(Date lastSheering) {
        this.lastSheering = lastSheering;
    }
}