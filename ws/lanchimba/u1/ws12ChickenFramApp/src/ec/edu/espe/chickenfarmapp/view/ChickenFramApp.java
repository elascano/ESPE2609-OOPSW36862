package ec.edu.espe.chickenfarmapp.view;

import ec.edu.espe.chickenfarmapp.model.*;
import java.util.Date;

/**
 *
 * @author Cristopher Lanchimba, Runtime Dynamic, @ESPE
 */
public class ChickenFramApp {

    public static void main(String[] args) {
        System.out.println("Cristopher Lanchimba Chicken Fram App");
        System.out.println("======================================");

        Chicken chicken;
        Egg egg;
        Poop poop;
        Chicken chicken1;
        ChickenCoop coop;
        Chicken chickens[];
        chickens = new Chicken[10];

        chicken = new Chicken(1, "Lucy", "brow and white", new Date(2006, 3, 15), 0, true);
        chicken.setAgeInYears(chicken.computeAgeInYears());
        chicken1 = new Chicken(2, "Renata", "white", new Date(2008, 1, 12), 1, true);
        egg = new Egg(1, 'M');
        poop = new Poop(1, "brown", 's');

        System.out.println("Chiken----->" + chicken.toString());
        System.out.println("Chiken 2 ----->" + chicken.toString());
        System.out.println("egg----->" + egg.toString());
        System.out.println("poop----->" + poop.toString());

        int tempo = chicken.computeAgeInYears();
        chicken.doStuff();
        chicken1.doStuff();

        chickens[0] = chicken;
        chickens[1] = chicken1;

        int ageForTheLoop = 0;

        for (int i = 0; i < chickens.length; i++) {
            ageForTheLoop = 2010 + i;

            chickens[i] = new Chicken(
                    i,
                    "chicken " + (i + 1),
                    "white",
                    new Date(ageForTheLoop - 1900, 3, 3),
                    0,
                    true
            );

            chickens[i].setAgeInYears(chickens[i].computeAgeInYears());
            System.out.println(chickens[i].toString());
        }
    }
}
