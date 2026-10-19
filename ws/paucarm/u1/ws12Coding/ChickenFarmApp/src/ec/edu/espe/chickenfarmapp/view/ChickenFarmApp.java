package ec.edu.espe.chickenfarmapp.view;

import ec.edu.espe.chickenfarmapp.model.Chicken;
import ec.edu.espe.chickenfarmapp.model.ChickenCoop;
import ec.edu.espe.chickenfarmapp.model.Egg;
import ec.edu.espe.chickenfarmapp.model.Poop;
import java.util.Date;

/**
 *
 * @author Paucar Matias, ByteWorks, @ESPE
 */
public class ChickenFarmApp {
    public static void main(String args[]){
        System.out.println("Matias Paucar's Chicken Farm App");
        System.out.println("#################################");
        
        Chicken chicken; //declaration
        Chicken chicken1;
        Egg egg;
        Poop poop;
        ChickenCoop coop;
        Chicken chickens[];
        chickens = new Chicken[10];
        
        
        chicken = new Chicken(1, "Lucy", "brown and white", new Date(2024,4,15), true); //instantiation
        chicken.setAgeInYears();
        egg = new Egg(1, 'M');
        poop = new Poop(1, "brown",'S');
        chicken1 = new Chicken(2, "Renata", "white", new Date(), true);
        chicken1.setAgeInYears();
        
        System.out.println("chiken -->" + chicken);
        System.out.println("egg -->" + egg);
        System.out.println("poop -->" + poop);
        System.out.println("chicken2 -->" + chicken1);
        
        int localAge = chicken.computeAgeInYears();
        System.out.println("the age of the chicken -->" + chicken.getName()+ ", is -->" + localAge);
        
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
