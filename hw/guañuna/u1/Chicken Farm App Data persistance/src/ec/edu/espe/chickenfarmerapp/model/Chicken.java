/*
 * Click nbfs://nbhost/SystemFileSystem/Templates/Licenses/license-default.txt to change this license
 * Click nbfs://nbhost/SystemFileSystem/Templates/Classes/Class.java to edit this template
 */
package ec.edu.espe.chickenfarmerapp.model;

import java.util.Date;

/**
 *
 * @author Cristopher Guañuna, Runtime Dynamic, @ESPE
 */
public class Chicken {
    private int id;
    private String name;
    private String color;
    private Date bornOnDate;
    private int ageInYears;
    private boolean molting;
    
    public void doStuff(){
        cluck();
        wander();
        drink(1.3F);
        System.out.println("poop -->" + poop());
        System.out.println("egg -->" + layAnEgg());
    }
    
    public Chicken() {
    }       
    
    private void cluck(){
        System.out.println("The chicken " + name + " is clucking...");
    }
    
    public void wander(){
        System.out.println("The chicken " + name + " is wandering...");
    }
    
    public void drink(float amountOfWater){
        System.out.println("The chicken " + name + " is drinking...");
    }
    
    private Poop poop() {
        Poop poop;
        poop = new Poop(2, "green", 'M');
        return poop;
    }
    
    private Egg layAnEgg() {
        Egg egg;
        egg = new Egg(2, 'S');
        return egg;
    }
    
    public int computeAgeInYears() {
        int age;
        Date today = new Date();

        age = today.getYear() - bornOnDate.getYear();

        if (today.getMonth() < bornOnDate.getMonth()) {
            age--;
        } 
        else if (today.getMonth() == bornOnDate.getMonth()) {
            if (today.getDate() < bornOnDate.getDate()) {
                age--;
            } else if (today.getDate() == bornOnDate.getDate()) {
                System.out.println("Today is this chicken's birthday!."); 
            }
        }

        return age;
    }

    public Chicken(int id, String name, String color, Date bornOnDate, boolean molting) {
        this.id = id;
        this.name = name;
        this.color = color;
        this.bornOnDate = bornOnDate;
        this.ageInYears = 0;
        this.molting = molting;
    }

    @Override
    public String toString() {
        return "Chicken{" + "id=" + getId()
                + ", name=" + getName()
                + ", color=" + getColor()
                + ", bornOnDate=" + getBornOnDate()
                + ", age=" + computeAgeInYears()
                + ", molting=" + isMolting() + '}';
    }

    public int getId() {
        return id;
    }

    public void setId(int id) {
        this.id = id;
    }

    public String getName() {
        return name;
    }

    public void setName(String name) {
        this.name = name;
    }

    public String getColor() {
        return color;
    }

    public void setColor(String color) {
        this.color = color;
    }

    public Date getBornOnDate() {
        return bornOnDate;
    }

    public void setBornOnDate(Date bornOnDate) {
        this.bornOnDate = bornOnDate;
    }

    public int getAgeInYears() {
        return ageInYears;
    }

    public void setAgeInYears(int ageInYears) {
        this.ageInYears = ageInYears;
    }

    public boolean isMolting() {
        return molting;
    }

    public void setMolting(boolean molting) {
        this.molting = molting;
    }
}
