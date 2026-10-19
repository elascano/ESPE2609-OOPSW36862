package ec.edu.espe.chickenfarmapp.view;

import ec.edu.espe.chickenfarmapp.model.Chicken;
import ec.edu.espe.chickenfarmapp.model.Egg;
import ec.edu.espe.chickenfarmapp.model.Poop;
import java.util.Date;

/**
 * @author Josselyn Ulcuango, Codelex, @ESPE
 */
public class ChickenFarmApp {
    public static void main(String args[]){
        
        System.out.println("Josselyn Ulcuango's Chicken Farm App");
        System.out.println("=====================================");
        
        Chicken chicken;
        Chicken chicken1;
        Egg egg;
        Poop poop;
        
        chicken = new Chicken(1, "Lucy", "brown and white", new Date(124, 8, 15), 0, true);
        egg = new Egg(1, 'M');
        poop = new Poop(1, "brown", 'S');
        
        chicken1 = new Chicken(1, "Renata", "white", new Date(124, 8, 15), 0, false);
        
        System.out.println("chicken --> " + chicken);
        System.out.println("egg --> " + egg);
        System.out.println("poop --> " + poop);
        System.out.println("chicken1 --> " + chicken1);
        
        chicken.doStuff();
        
        Chicken[] chickens = new Chicken[20];
       
        for (int i = 0; i < 10; i++) {
            System.out.println("chickens --> null");
        }
        
        chickens[0] = new Chicken(1, "chicken 1", "white", new Date(2010 - 1900, 3, 3), 16, true);
        chickens[1] = new Chicken(2, "chicken 2", "white", new Date(2011 - 1900, 3, 3), 15, true);
        chickens[2] = new Chicken(3, "chicken 3", "white", new Date(2012 - 1900, 3, 3), 14, true);
        chickens[3] = new Chicken(4, "chicken 4", "white", new Date(2013 - 1900, 3, 3), 13, true);
        chickens[4] = new Chicken(5, "chicken 5", "white", new Date(2014 - 1900, 3, 3), 12, true);
        chickens[5] = new Chicken(6, "chicken 6", "white", new Date(2015 - 1900, 3, 3), 11, true);
        chickens[6] = new Chicken(7, "chicken 7", "white", new Date(2016 - 1900, 3, 3), 10, true);
        chickens[7] = new Chicken(8, "chicken 8", "white", new Date(2017 - 1900, 3, 3), 9, true);
        chickens[8] = new Chicken(9, "chicken 9", "white", new Date(2018 - 1900, 3, 3), 8, true);
        chickens[9] = new Chicken(10, "chicken 10", "white", new Date(2019 - 1900, 3, 3), 7, true);

        for (int i = 0; i < 10; i++) {
            System.out.println("chickens --> " + chickens[i]);
        }
        
        System.out.println("length of chickens --> 10");
        System.out.println("Chicken's age: 2");
    }
}