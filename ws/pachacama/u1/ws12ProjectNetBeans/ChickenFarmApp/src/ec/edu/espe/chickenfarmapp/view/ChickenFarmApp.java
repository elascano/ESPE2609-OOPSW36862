package ec.edu.espe.chickenfarmapp.view;

import ec.edu.espe.chickenfarmapp.model.Chicken;
import ec.edu.espe.chickenfarmapp.model.ChickenCoop;
import ec.edu.espe.chickenfarmapp.model.Egg;
import ec.edu.espe.chickenfarmapp.model.Poop;
import java.util.Date;

/**
 *
 * @author Andres Pachacama, Byte Work, @ESPE
 */
public class ChickenFarmApp {
    public static void main(String args[]){
        System.out.println("Andres Pachacama`s Chicken Farm App");
        System.out.println("************");
        
        Chicken chicken; //declaration
        Chicken chicken1;
        Egg egg;
        Poop poop;
        ChickenCoop coop;
        Chicken chickens[];
        chickens = new Chicken[10];
        
        chicken = new Chicken(2, "Maruja", "white", new Date(1970, 10, 17), false); 
        chicken.setAgeInYears();
        egg = new Egg(1, 'M');

        System.out.println("my chicken is ---> " + chicken);
        System.out.println("egg ---> " + egg);

        System.out.println("egg size ---> " + egg.getSize());

        poop = new Poop(1, "brown", 'S');
        System.out.println("poop ---> " + poop);

        chicken1 = new Chicken(1, "Lucy", "brown and white", new Date(1970, 12, 17), true);
        chicken1.setAgeInYears();
        System.out.println("chiken 1 ---> " + chicken1);

        int localAge = chicken.computeAgeInYears();
        System.out.println("the age of the chicken ---> " + chicken.getName() + ", is ---> " + localAge);
        
        chicken.doStuff();
        
        chickens[0] = new Chicken(3,"Pepita","black", new Date(2020,2,2), true);
        chickens[1] = chicken;
        //coop = new ChickenCoop(1);
        /*
        for (int i = 0;i < 10; i++){
        System.out.println("chickens -->"+chickens[i]);
        }
        
        System.out.println("length of chickens -->"+chickens);
        */
        
        for (int i = 0; i < 10; i++) {
            System.out.println("chickens --> " + chickens[i]);
        }

        for (int i = 0 ; i < chickens.length ; i++){
            chickens[i] = new Chicken(i, "chicken " + (i+1), "white ", new Date(2020,3,3), true);
        }
        for (int i = 0; i < 10; i++) {
            System.out.println("chickens --> " + chickens[i]);
        }
    }
}