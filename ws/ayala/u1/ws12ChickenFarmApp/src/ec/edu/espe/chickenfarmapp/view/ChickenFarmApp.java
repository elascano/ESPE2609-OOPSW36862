
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
        chickens = new Chicken [2];
        
        chickens = new Chicken[10];
        //objects initialation
        chicken = new Chicken(1, "Lucy", "brown and white", new Date(), true);
        Chicken chicken2= new Chicken(1, "Julia", "clack", new Date(), true); 
        
        
        
        egg = new Egg(1, 'M');
        poop = new Poop(1, "brown", 'S');
        chicken1 = new Chicken(2, "Renata", "white", new Date(70, 11, 17), false); 
        
        
        
        System.out.println("chicken-->" + chicken);
        System.out.println("chicken-->" + egg);
        System.out.println("chicken-->" + poop);
        System.out.println("chicken 2 -->" + chicken1);
        
        coop = new ChickenCoop(0, chickens);
        
        System.out.println("==============");
        System.out.println(" 2026 10 21 =");
        System.out.println("coop -->" + coop);
        
        coop = new ChickenCoop(1, chickens);
        
        System.out.println("==============");
        System.out.println(" 2026 10 21 =");
        System.out.println("coop -->" + coop);
        
        System.out.println("I am printring the name of my chicken -->"+ chicken.getName());

        short addend1;
        addend1 = (short) 40000;
        
        float addend2;
        addend1 = (short) addend2;
         
        System.out.println("addend1 -->" + addend1);
             
        
        
    }
    }
    
