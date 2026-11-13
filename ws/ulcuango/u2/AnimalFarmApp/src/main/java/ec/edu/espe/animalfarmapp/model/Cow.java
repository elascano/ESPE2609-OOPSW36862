package ec.edu.espe.animalfarmapp.model;

import java.util.Date;
/**
 *
 * @author Josselyn Ulcuango, CodeLex, @ESPE
 */
public class Cow extends FarmAnimal {
    private boolean isProducingMilk;

    public Cow(int id, String bred, Date bornOnDate, boolean isProducingMilk) {
        super(id, bred, bornOnDate);
        this.isProducingMilk = isProducingMilk;
    }
     public void milk(){
         System.out.println("Cow is milking");
     }
    @Override
    public String toString() {
        return "Cow{" + super.toString() + "isProducingMilk=" + isProducingMilk + '}';
    }

    public boolean isIsProducingMilk() {
        return isProducingMilk;
    }

    public void setIsProducingMilk(boolean isProducingMilk) {
        this.isProducingMilk = isProducingMilk;
    }
}