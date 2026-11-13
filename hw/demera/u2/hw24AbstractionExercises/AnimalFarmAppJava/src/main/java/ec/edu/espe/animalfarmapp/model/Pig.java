/*
 * Click nbfs://nbhost/SystemFileSystem/Templates/Licenses/license-default.txt to change this license
 * Click nbfs://nbhost/SystemFileSystem/Templates/Classes/Class.java to edit this template
 */
package ec.edu.espe.animalfarmapp.model;

import java.util.Arrays;
import java.util.Date;

/**
 *
 * @author Francisco Demera, Bytes & Fabric Devs, @ESPE
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
     * @return the weightGainPerWeek
     */
    public double getWeightGainPerWeek() {
        return weightGainPerWeek;
    }

    /**
     * @param weightGainPerWeek the weightGainPerWeek to set
     */
    public void setWeightGainPerWeek(float weightGainPerWeek) {
        this.weightGainPerWeek = weightGainPerWeek;
    }

}
