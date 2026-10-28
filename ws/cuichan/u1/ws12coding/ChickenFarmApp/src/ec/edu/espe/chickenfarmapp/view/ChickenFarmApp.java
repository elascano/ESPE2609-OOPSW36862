
package ec.edu.espe.chickenfarmapp.view;

import ec.edu.espe.chickenfarmapp.model.Chicken;
import ec.edu.espe.chickenfarmapp.model.ChickenCoop;
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
      
      chicken = new Chicken(1, "Lucy", "brown and white", new Date(2000, 9, 20), 0, true);
      chicken.setAgeInYears();
        egg = new Egg(1, 'M');
        poop = new Poop(1, "brown", 'S');
    Chicken chicken1 = new Chicken(2, "Lucy", "white", new Date(), 0, false);

        System.out.println("chicken --> " + chicken);
        System.out.println("egg --> " + egg);
        System.out.println("poop --> " + poop);
        System.out.println("chicken 2 --> " + chicken1);
    int localAge = chicken.computeAgeInYears();
    System.out.println("the age of the chicken --> " + chicken.getName() + ", is --> " + localAge);
    System.out.println("the age of the chicken ---> " + chicken.getName() + ", is ---> " + chicken.getAgeInYears());
    chicken.doStuff();
    Poop poop2 = new Poop(2, "green", 'N');
      System.out.println("poop--> " + poop2);

      Egg egg2 = new Egg(2, 'O');
      System.out.println("egg--> " + egg2);

      ChickenCoop coop = new ChickenCoop(1);

      for (int i = 0; i < 10; i++) {
          System.out.println("chickens --> " + coop.next());
          Chicken chickens[] = new Chicken[10];

        chickens[0] = new Chicken(3, "Pepita", "black", new Date(2020, 2, 2), 0, true);
        chickens[1] = chicken;

        for (i = 0; i < 10; i++) {
            System.out.println("chickens --> " + chickens[i]);
        }

        int ageForTheLoop = 0;
        for (i = 0; i < chickens.length; i++) {
            ageForTheLoop = 2010 + i;
            chickens[i] = new Chicken(i, "chicken " + (i + 1), "white ", new Date(ageForTheLoop, 3, 3), 0, true);
            chickens[i].setAgeInYears();
        }

        for (i = 0; i < 10; i++) {
            System.out.println("chickens --> " + chickens[i]);
        }

      }
    
    }
}
          