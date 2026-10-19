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
 * @author Nacimba Mateo, Group5, @ESPE
 */
public class ChickenFramApp {
    public static void main(String args[]){
        System.out.println("Mateo Nacimba Chicken Farm App");
        System.out.println("===============================");
         Chicken chicken; //declaration
        Chicken chicken1;
        Egg egg;
        Poop poop;
       
        
        chicken = new Chicken(1, "Lucy", "brown and white", new Date(2024,4,15), true); //instantiation
        egg = new Egg(1, 'M');
        poop = new Poop(1, "brown",'S');
        chicken1 = new Chicken(2, "Renata", "white", new Date(2007,07,13), true);
        
        System.out.println("chiken -->" + chicken);
        System.out.println("egg -->" + egg);
        System.out.println("poop -->" + poop);
        System.out.println("chicken2 -->" + chicken1);
        System.out.println("chicken" + chicken1);
        int localAge = chicken.computeAgeInYears();
        System.out.println("the age of the chicken -->" + chicken.getName()+ ", is -->" + localAge);
        
        chicken.doStuff();
        
        Chicken chickens[] = new Chicken[10];

        
        for (int i = 0; i < chickens.length; i++) {
            System.out.println("chickens --> " + chickens[i]);
        }

       
        for (int i = 0; i < chickens.length; i++) {
            chickens[i] = new Chicken(i + 1, "chicken " + (i + 1), "white", new Date(2010 + i, 3, 3), true);
            System.out.println("chickens --> " + chickens[i]);
        }

        System.out.println("length of chickens --> " + chickens.length);
        
      
        int chickensAge = chickens[2].computeAgeInYears();
        System.out.println("Chicken's age: " + chickensAge);
       
        
        
    }
}
        
           
     
    

