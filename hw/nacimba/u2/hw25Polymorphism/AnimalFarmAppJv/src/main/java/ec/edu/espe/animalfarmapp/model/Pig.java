/*
 * Click nbfs://nbhost/SystemFileSystem/Templates/Licenses/license-default.txt to change this license
 * Click nbfs://nbhost/SystemFileSystem/Templates/Classes/Class.java to edit this template
 */
package ec.edu.espe.animalfarmapp.model;

import java.util.Arrays;
import java.util.Date;

/**
 *
 * @author Mateo Nacimba, @ESPE
 */
public class Pig extends FarmAnimal implements SellAnimal {

    private boolean isWallowing;
    private float weightGainPerWeek;

    public void showPigMeatCuts() {
        String meatCuts[] = {"head", "cheek", "picnic", "clear plate", "boston shoulder", "back fat", "loin", "ribs", "bacon", "leg", "ham", "hock"};
        System.out.println("Meat cuts available from the pig: " + Arrays.toString(meatCuts));
    }

    @Override
    public void feed(Food food, int amount) {
        System.out.println("giving the pig " + amount + " kg of " + food + " to eat");
    }

    @Override
    public void sellAnimal(float price) {
        System.out.println("the pig was sold for " + price + " dollars.");
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

    /**
     * @return the isWallowing
     */
    public boolean isIsWallowing() {
        return isWallowing;
    }

    /**
     * @param isWallowing the isWallowing to set
     */
    public void setIsWallowing(boolean isWallowing) {
        this.isWallowing = isWallowing;
    }

    /**
     */
    public void getWeightGainPerWeek(int weightGain) {
        System.out.println("this week the pig put on " + weightGain + " kg");
    }

    /**
     * @param weightGainPerWeek the weightGainPerWeek to set
     */
    public void setWeightGainPerWeek(float weightGainPerWeek) {
        this.weightGainPerWeek = weightGainPerWeek;
    }

}
