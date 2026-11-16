package ec.edu.espe.animalfarmapp.model;

import java.util.Date;

/**
 *
 * @author Cristopher Guañuna, Runtime Dynamics, @ESPE
 */
public class Pig extends FarmAnimal {

    public Pig(int id, String breed, Date bornOnDate) {
        super(id, breed, bornOnDate);
    }

    @Override
    public String toString() {
        return "Pig{" + super.toString() + '}';
    }
}