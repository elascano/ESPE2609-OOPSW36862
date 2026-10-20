/*
 * Click nbfs://nbhost/SystemFileSystem/Templates/Licenses/license-default.txt to change this license
 * Click nbfs://nbhost/SystemFileSystem/Templates/Classes/Class.java to edit this template
 */
package ec.edu.espe.chickenfarmapp.view;

import ec.edu.espe.chickenfarmapp.model.Chicken;
import ec.edu.espe.chickenfarmapp.model.ChickenCoop;
import ec.edu.espe.chickenfarmapp.model.Egg;
import ec.edu.espe.chickenfarmapp.model.Poop;
import java.util.Date;

/**
 *
 * @author Christopher Torres, CodeLex, @ESPE
 */
public class ChickenFarmApp {
    public static void main(String args[]){
        System.out.println("Ostin Torres' Chicken Farm App");
        System.out.println("==============================");
                
        Chicken chicken;
        Chicken chicken1;
        Egg egg;
        Poop poop;
        ChickenCoop coop; 
        Chicken chickens[];
        
        chickens = new Chicken[10];
        
        chicken = new Chicken(1, "Lucy", "brown and white", new Date(2020 - 1900, 5, 19), true);
        egg = new Egg(1, 'M');
        poop = new Poop(1,"brown", 'S');
        chicken1 = new Chicken(2, "Renata", "white", new Date(2024 -1900, 6, 4), false);
        chicken.computeAgeInYears();
        chicken1.computeAgeInYears();
        
        System.out.println("chicken -->" + chicken);
        System.out.println("egg -->" + egg);
        System.out.println("poop -->" + poop);
        System.out.println("chicken 2 -->" + chicken1);
        
        chicken.doStuff();
                       
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
