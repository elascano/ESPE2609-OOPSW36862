/*
 * Click nbfs://nbhost/SystemFileSystem/Templates/Licenses/license-default.txt to change this license
 * Click nbfs://nbhost/SystemFileSystem/Templates/Classes/Class.java to edit this template
 */
package ec.edu.espe.animalfarmapp.model;

import java.util.Date;

/**
 *
 * @author Andres Pachacama, Byte Works, ESPE
 */
public class Cow extends FarmAnimal{
    private float litersOfMilkPerDay;
    private boolean isPregnant;
    private float weightInKg;
    private boolean isMilking;

    public Cow(float litersOfMilkPerDay, boolean isPregnant, float weightInKg, boolean isMilking, int id, String breed, Date bornOnDate) {
        super(id, breed, bornOnDate);
        this.litersOfMilkPerDay = litersOfMilkPerDay;
        this.isPregnant = isPregnant;
        this.weightInKg = weightInKg;
        this.isMilking = isMilking;
    }

    @Override
    public String toString() {
        return "Cow{" + super.toString()+" litersOfMilkPerDay=" + getLitersOfMilkPerDay() + ", isPregnant=" + isIsPregnant() + ", weightInKg=" + getWeightInKg() + ", isMilking=" + isIsMilking() + '}';
    }

    /**
     * @return the litersOfMilkPerDay
     */
    public float getLitersOfMilkPerDay() {
        return litersOfMilkPerDay;
    }

    /**
     * @param litersOfMilkPerDay the litersOfMilkPerDay to set
     */
    public void setLitersOfMilkPerDay(float litersOfMilkPerDay) {
        this.litersOfMilkPerDay = litersOfMilkPerDay;
    }

    /**
     * @return the isPregnant
     */
    public boolean isIsPregnant() {
        return isPregnant;
    }

    /**
     * @param isPregnant the isPregnant to set
     */
    public void setIsPregnant(boolean isPregnant) {
        this.isPregnant = isPregnant;
    }

    /**
     * @return the weightInKg
     */
    public float getWeightInKg() {
        return weightInKg;
    }

    /**
     * @param weightInKg the weightInKg to set
     */
    public void setWeightInKg(float weightInKg) {
        this.weightInKg = weightInKg;
    }

    /**
     * @return the isMilking
     */
    public boolean isIsMilking() {
        return isMilking;
    }

    /**
     * @param isMilking the isMilking to set
     */
    public void setIsMilking(boolean isMilking) {
        this.isMilking = isMilking;
    }

}
