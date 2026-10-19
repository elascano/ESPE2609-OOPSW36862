package ec.edu.espe.chickenfarmapp.view;

import ec.edu.espe.chickenfarmapp.model.Chicken;
import ec.edu.espe.chickenfarmapp.model.ChickenCoop;
import ec.edu.espe.chickenfarmapp.model.Egg;
import ec.edu.espe.chickenfarmapp.model.Poop;
import java.util.Date;

/**
 *
 * @author Demian Armas, Logical Lords, @ESPE
 */
public class ChickenFarmApp {

    public static void main(String args[]) {
        System.out.println("Demian Armas´s Chicken Farm App");
        System.out.println("===============================");

        Chicken chicken;
        Chicken chicken1;
        Egg egg;
        Poop poop;
        ChickenCoop coop;
        Chicken chickens[];
        chickens = new Chicken[10];

        chicken = new Chicken(1, "Lucy", "brown and withe", new Date(1970, 12, 17));
        chicken.setAgeInYears();
        egg = new Egg(1, 'M');
        poop = new Poop(1, "brown", 'S');
        chicken1 = new Chicken(2, "Renata", "white", new Date(1970, 11, 17));
        chicken1.setAgeInYears();

        System.out.println("chicken -->" + chicken);
        System.out.println("egg -->" + egg);
        System.out.println("poop -->" + poop);
        System.out.println("chicken 2-->" + chicken1);
        System.out.println("The age of the chicken --> " + chicken.getName() + " is " + chicken.computeAgeInYears());

        chicken.doStuff();
        chicken1.doStuff();

        chickens[0] = new Chicken(3, "Pepita", "black", new Date(2020, 2, 2));
        chickens[1] = chicken;

        for (int i = 0; i < 10; i++) {
            System.out.println("chickens -->" + chickens[i]);
        }

            int ageForTheLoop = 0;
        for (int i = 0; i < chickens.length; i++) {
            ageForTheLoop = 2010 + i;
            chickens[i] = new Chicken(i, "chicken " + (i + 1), "white ", new Date(ageForTheLoop, 0, 1));
            chickens[i].setAgeInYears();
        }

        for (int i = 0; i < 10; i++) {
            System.out.println("chickens --> " + chickens[i]);
        }
    }

}
