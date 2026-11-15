/*
 * Click nbfs://nbhost/SystemFileSystem/Templates/Licenses/license-default.txt to change this license
 * Click nbfs://nbhost/SystemFileSystem/Templates/Classes/Class.java to edit this template
 */
package ec.edu.espe.animalfarmapp.model;

/**
 *
 * @author Dennis Criollo,Byte & Fabric Devs,@ESPE
 */
public class Location {

    private String description;

    @Override
    public String toString() {
        return "Location{" + "description=" + description + '}';
    }

    public Location(String description) {
        this.description = description;
    }

    /**
     * @return the description
     */
    public String getDescription() {
        return description;
    }

    /**
     * @param description the description to set
     */
    public void setDescription(String description) {
        this.description = description;
    }

}
