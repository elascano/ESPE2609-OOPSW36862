/*
 * Click nbfs://nbhost/SystemFileSystem/Templates/Licenses/license-default.txt to change this license
 * Click nbfs://nbhost/SystemFileSystem/Templates/Classes/Class.java to edit this template
 */
package ec.edu.espe.animalfarmapp.model;

import java.util.Arrays;
import java.util.Date;

/**
 *
 * @author Enrique Ayala, LogicaLords, @ESPE
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
        System.out.println("the pig has gained " + weightGain + " kg of weight");
    }

    /**
     * @param weightGainPerWeek the weightGainPerWeek to set
     */
    public void setWeightGainPerWeek(float weightGainPerWeek) {
        this.weightGainPerWeek = weightGainPerWeek;
    }

}
