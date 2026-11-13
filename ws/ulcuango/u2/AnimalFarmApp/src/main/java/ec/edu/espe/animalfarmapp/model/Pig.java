package ec.edu.espe.animalfarmapp.model;

import java.util.Date;
/**
 *
 * @author Josselyn Ulcuango, CodeLex, @ESPE
 */
public class Pig extends FarmAnimal {

    public Pig(int id, String bred, Date bornOnDate) {
        super(id, bred, bornOnDate);
    }

    @Override
    public String toString() {
        return "Pig{" + super.toString() + '}';
    }
}