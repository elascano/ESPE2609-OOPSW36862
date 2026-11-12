/*
 * Click nbfs://nbhost/SystemFileSystem/Templates/Licenses/license-default.txt to change this license
 * Click nbfs://nbhost/SystemFileSystem/Templates/Classes/Class.java to edit this template
 */
package ec.edu.espe.animalfarmapp.model;

import java.util.Date;

/**
 *
 * @author Julian Jaramillo RuntimeDynamic, @ESPE
 */
public class Chicken extends FarmAnimal{
    private boolean isMolting;
    private int numberOfLastEggsPerWeek;
    private boolean isMeat;

    public Chicken(boolean isMolting, int numberOfLastEggsPerWeek, boolean isMeat, int id, String breed, Date bornonDate) {
        super(id, breed, bornonDate);
        this.isMolting = isMolting;
        this.numberOfLastEggsPerWeek = numberOfLastEggsPerWeek;
        this.isMeat = isMeat;
    }

    @Override
    public String toString() {
        return "Chicken{"+ super.toString() + "isMolting=" + isMolting + ", numberOfLastEggsPerWeek=" + numberOfLastEggsPerWeek + ", isMeat=" + isMeat + '}';
    }

    /**
     * @return the isMolting
     */
    public boolean isIsMolting() {
        return isMolting;
    }

    /**
     * @param isMolting the isMolting to set
     */
    public void setIsMolting(boolean isMolting) {
        this.isMolting = isMolting;
    }

    /**
     * @return the numberOfLastEggsPerWeek
     */
    public int getNumberOfLastEggsPerWeek() {
        return numberOfLastEggsPerWeek;
    }

    /**
     * @param numberOfLastEggsPerWeek the numberOfLastEggsPerWeek to set
     */
    public void setNumberOfLastEggsPerWeek(int numberOfLastEggsPerWeek) {
        this.numberOfLastEggsPerWeek = numberOfLastEggsPerWeek;
    }

    /**
     * @return the isMeat
     */
    public boolean isIsMeat() {
        return isMeat;
    }

    /**
     * @param isMeat the isMeat to set
     */
    public void setIsMeat(boolean isMeat) {
        this.isMeat = isMeat;
    }
    
    
}
