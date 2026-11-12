/*
 * Click nbfs://nbhost/SystemFileSystem/Templates/Licenses/license-default.txt to change this license
 * Click nbfs://nbhost/SystemFileSystem/Templates/Classes/Class.java to edit this template
 */
package ec.edu.espe.animalfarmapp.model;

import java.util.Date;

/**
 *
 * @author LABS-ESPE
 */
public class FarmAnimal {
    private int id;
    private String breed;
    private Date bornonDate;

    public FarmAnimal(int id, String breed, Date bornonDate) {
        this.id = id;
        this.breed = breed;
        this.bornonDate = bornonDate;
    }

   

    @Override
    public String toString() {
        return  "id=" + getId() + ", breed=" + breed + 
                ", bornonDate=" + bornonDate.getDate() + 
                "/"+ bornonDate.getMonth()+ "/"+ (bornonDate.getYear()+1900)
                + ",";
    }
    public int getAgeInMounth(){
        return 0;
    }
    /**
     * @return the id
     */
    public int getId() {
        return id;
    }

    /**
     * @param id the id to set
     */
    public void setId(int id) {
        this.id = id;
    }

    /**
     * @return the breed
     */
    public String getBreed() {
        return breed;
    }

    /**
     * @param breed the breed to set
     */
    public void setBreed(String breed) {
        this.breed = breed;
    }

    /**
     * @return the bornonDate
     */
    public Date getBornonDate() {
        return bornonDate;
    }

    /**
     * @param bornonDate the bornonDate to set
     */
    public void setBornonDate(Date bornonDate) {
        this.bornonDate = bornonDate;
    }
    
    
    
}
