/*
 * Click nbfs://nbhost/SystemFileSystem/Templates/Licenses/license-default.txt to change this license
 * Click nbfs://nbhost/SystemFileSystem/Templates/Classes/Class.java to edit this template
 */
package ec.edu.espe.animalfarmapp.model;

import java.util.Date;

/**
 *
 * @author Nacimba Mateo, Group5, @ESPE
 */
public class Pig extends FarmAnimal{
    private boolean isWallowing;
    private float weightGainPerWeek;

    public Pig(boolean isWallowing, float weightGainPerWeek, int id, String breed, Date bornOnDate, boolean isMeat) {
        super(id, breed, bornOnDate, isMeat);
        this.isWallowing = isWallowing;
        this.weightGainPerWeek = weightGainPerWeek;
    }

   

    @Override
    public String toString() {
        return "Pig{"+super.toString() + "isWallowing=" + isWallowing + ", weightGainPerWeek=" + weightGainPerWeek + '}';
    }
    
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
    public float getWeightGainPerWeek() {
        return weightGainPerWeek;
    }

    /**
     * @param weightGainPerWeek the weightGainPerWeek to set
     */
    public void setWeightGainPerWeek(float weightGainPerWeek) {
        this.weightGainPerWeek = weightGainPerWeek;
    }
    
}
