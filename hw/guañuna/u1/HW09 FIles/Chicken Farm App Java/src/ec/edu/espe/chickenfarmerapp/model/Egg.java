/*
 * Click nbfs://nbhost/SystemFileSystem/Templates/Licenses/license-default.txt to change this license
 * Click nbfs://nbhost/SystemFileSystem/Templates/Classes/Class.java to edit this template
 */
package ec.edu.espe.chickenfarmerapp.model;

/**
 *
 * @author Cristopher Guañuna, Runtime Dynamic, @ESPE
 */
public class Egg {
    private int id;
    private char size;
    
    public Egg(int id, char size){
        this.id=id;
        this.size= size;
        
    }
    
    public Egg() {
    }

    @Override
    public String toString() {
        return "Egg{" + "id=" + getId() + ", size=" + getSize() + '}';
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
     * @return the size
     */
    public char getSize() {
        return size;
    }

    /**
     * @param size the size to set
     */
    public void setSize(char size) {
        this.size = size;
    }
    
    
}
