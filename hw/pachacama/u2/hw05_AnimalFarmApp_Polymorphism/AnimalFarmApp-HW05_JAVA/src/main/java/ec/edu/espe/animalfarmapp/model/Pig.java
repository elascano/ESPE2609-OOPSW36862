package ec.edu.espe.animalfarmapp.model;

import java.util.Arrays;
import java.util.Date;

/**
 * @author Andrés Pachacama, ByteWorks, @ESPE
 */
public class Pig extends FarmAnimal implements SellAnimal {
    private boolean isWallowing;
    private float weightGainPerWeek;

    public void showPigMeatCuts() {
        String meatCuts[] = {"head", "cheek", "picnic", "clear plate", "boston shoulder", "back fat", "loin", "ribs", "bacon", "leg", "ham", "hock"};
        System.out.println("Avalaible cuts for the pig" + Arrays.toString(meatCuts));
    }

    @Override
    public void feed(Food food, int amount) {
        System.out.println("feeding the pig with " + amount + " kg of " + food);
    }

    @Override
    public void sellAnimal(float price) {
        System.out.println("sold the pig in " + price + " dollars.");
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

    public void getWeightGainPerWeek(int weightGain) {
        System.out.println("the pig has gained " + weightGain + " kg of weight");
    }

    public void setWeightGainPerWeek(float weightGainPerWeek) {
        this.weightGainPerWeek = weightGainPerWeek;
    }
}
