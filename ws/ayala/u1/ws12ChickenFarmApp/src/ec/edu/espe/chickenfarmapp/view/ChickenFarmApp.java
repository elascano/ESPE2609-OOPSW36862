
package ec.edu.espe.chickenfarmapp.view;

import ec.edu.espe.chickenfarmapp.model.Chicken;
import ec.edu.espe.chickenfarmapp.model.ChickenCoop;
import ec.edu.espe.chickenfarmapp.model.Egg;
import ec.edu.espe.chickenfarmapp.model.Poop;
import java.util.Date;

/**
 *
 * @author Enrique Ayala, LogicLords,@ESPE
 */
public class ChickenFarmApp {
    public static void main (String[]args) {
        System.out.println("Enrique Ayala Chicken Farm App");
        System.out.println("==============================");
        
        //declaration
        Chicken chicken; 
        Chicken chicken1;
        Egg egg;
        Poop poop;
        ChickenCoop coop;
        Chicken chickens[];  
        
        chickens = new Chicken[10];
        //objects initialation
               chicken = new Chicken(1, "Lucy", "brown and white", new Date(78, 10, 2), true); 
        egg = new Egg(1, 'M');
        poop = new Poop(1, "brown", 'S');
        chicken1 = new Chicken(2, "Renata", "white", new Date(70, 11, 17), false); 
        
        
        System.out.println("chicken-->" + chicken);
        System.out.println("chicken-->" + egg);
        System.out.println("chicken-->" + poop);
        System.out.println("chicken 2 -->" + chicken1);
        
                int localAge = chicken.computeAgeInYears();
        System.out.println("The age of the chicken --> " + chicken.getName() + ", is --> " + localAge);
        
        chicken.doStuff();
        
        chickens[0] = new Chicken(3, "pepita","black",new Date(2020,2,2), true);
        chickens[1] = chicken;
        
        for(int i =0; i < 10; i++){
            System.out.println("chickens --->"+ chickens[i]);
        }
        int ageForTheLoop=0;
        for(int i=0 ; i< chickens.length ; i++){
            ageForTheLoop = 2010 + i;
            chickens[i] = new Chicken(i, "chicken"+ (i+1), "white", new Date(2020,3,3), true);
            chickens[i].setAgeInYears();
        }
        for (int i=0; i<10 ; i++){
            System.out.println("chickens -->" + chickens[i]);
        }
    }
    }
    
