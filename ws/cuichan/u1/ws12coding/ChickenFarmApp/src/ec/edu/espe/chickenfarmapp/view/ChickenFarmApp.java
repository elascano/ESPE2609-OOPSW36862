/*
 * Click nbfs://nbhost/SystemFileSystem/Templates/Licenses/license-default.txt to change this license
 * Click nbfs://nbhost/SystemFileSystem/Templates/Classes/Class.java to edit this template
 */
package ec.edu.espe.chickenfarmapp.view;

import ec.edu.espe.chickenfarmapp.model.Chicken;
import ec.edu.espe.chickenfarmapp.model.Egg;
import ec.edu.espe.chickenfarmapp.model.Poop;
import java.util.Date;

/**
 *
 * @author Usuario
 */
public class ChickenFarmApp {
    public static void main(String args[]){ 
    
      System.out.println("Leslye Cuichan`s Chicken Farm App");
      System.out.println("================================");
      
      Chicken chicken;
      Egg egg;
      Poop poop;
      
      chicken = new Chicken(1, "Lucy", "brown and white", new Date(), 0, true);
        egg = new Egg(1, 'M');
        poop = new Poop(1, "brown", 'S');
    Chicken chicken1 = new Chicken(2, "Renata", "white", new Date(), 0, false);

        System.out.println("chicken --> " + chicken);
        System.out.println("egg --> " + egg);
        System.out.println("poop --> " + poop);
        System.out.println("chicken 2 --> " + chicken1);
    }
}
            
