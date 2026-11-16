package ec.edu.espe.animalfarmapp.model;

import java.util.Arrays;
import java.util.Date;

/**
 * @author Andrés Pachacama, ByteWorks, @ESPE
 */
public class Pig extends FarmAnimal {
    private boolean isWallowing;
    private float weightGainPerWeek;

    public void showPigMeatCuts(){
        String meatCuts[] = {"head", "cheek", "picnic", "clear plate", "boston shoulder", "back fat", "loin", "ribs", "bacon", "leg", "ham", "hock"};
        System.out.println("Avalaible cuts for the pig" + Arrays.toString(meatCuts));
    }
    @Override
    public String toString() {
        return "Pig{" + super.toString() + "isWallowing=" + isWallowing + ", weightGainPerWeek=" + weightGainPerWeek + '}';
    }

    public Pig(boolean isWallowing, float weightGainPerWeek, int id, String breed, Date bornOnDate, boolean isMeat) {
        super(id, breed, bornOnDate, isMeat);
        this.isWallowing = isWallowing;
        this.weightGainPerWeek = weightGainPerWeek;
    }

    public boolean isIsWallowing() {
        return isWallowing;
    }

    public void setIsWallowing(boolean isWallowing) {
        this.isWallowing = isWallowing;
    }

    public double getWeightGainPerWeek() {
        return weightGainPerWeek;
    }

    public void setWeightGainPerWeek(float weightGainPerWeek) {
        this.weightGainPerWeek = weightGainPerWeek;
    }
}
