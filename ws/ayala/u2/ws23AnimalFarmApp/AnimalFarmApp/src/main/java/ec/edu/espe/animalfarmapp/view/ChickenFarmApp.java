/*
 * Click nbfs://nbhost/SystemFileSystem/Templates/Licenses/license-default.txt to change this license
 * Click nbfs://nbhost/SystemFileSystem/Templates/Classes/Class.java to edit this template
 */
package ec.edu.espe.animalfarmapp.view;

import ec.edu.espe.animalfarmapp.model.Chicken;
import ec.edu.espe.animalfarmapp.model.FarmAnimal;
import java.util.Date;

/**
 *
 * @author Enrique Ayala, @Logical Lords, @ESPE
 */


public class ChickenFarmApp {
    public static void main(String[] args) {
        System.out.println("Enrique Farm App");
        System.out.println("=============================");
        FarmAnimal farmAnimal;
        farmAnimal = new FarmAnimal(1, "Molstein", new Date());
        
        System.out.println("MY farm Animal is --->"+farmAnimal);
        
        Chicken chicken = new Chicken(true, 10, false, 2, "palish", new Date());
        System.out.println("Chicken --->"+ chicken);
    }
}

