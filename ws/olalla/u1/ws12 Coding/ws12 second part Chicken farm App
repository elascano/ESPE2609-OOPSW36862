
package ec.edu.espe.chickenfarmapp.view;

import ec.edu.espe.chickenfarmapp.model.Chicken;
import ec.edu.espe.chickenfarmapp.model.Egg;
import ec.edu.espe.chickenfarmapp.model.Poop;
import java.util.Date;

public class ChickenFarmApp {

    public static void main(String args[]) {

        System.out.println("Renata Olalla's Chicken Farm App");
        System.out.println("=================================");

        Chicken chicken;
        Chicken chicken1;
        Egg egg;
        Poop poop;

        Chicken[] chickens = new Chicken[10];

        poop = new Poop(1, "brown", 'S');
        egg = new Egg(1, 'M');

        chicken = new Chicken(1, "Lucy", "brown and white",
                new Date(2024 - 1900, 8, 15), 0, true);

        chicken1 = new Chicken(2, "Renata", "white",
                new Date(2024 - 1900, 8, 15), 0, false);

        System.out.println("chicken --> " + chicken);
        System.out.println("egg --> " + egg);
        System.out.println("poop --> " + poop);
        System.out.println("chicken1 --> " + chicken1);

        chicken.doStuff();

        for (int i = 0; i < chickens.length; i++) {
            System.out.println("chickens --> " + chickens[i]);
        }

        int ageForTheLoop = 0;

        for (int i = 0; i < chickens.length; i++) {
            ageForTheLoop = 2010 + i;

            chickens[i] = new Chicken(
                    i + 1,
                    "chicken " + (i + 1),
                    "white",
                    new Date(ageForTheLoop - 1900, 3, 3),
                    0,
                    true
            );

            chickens[i].setAgeInYears();
        }

        for (int i = 0; i < chickens.length; i++) {
            System.out.println("chickens --> " + chickens[i]);
        }

        System.out.println("length of chickens --> " + chickens.length);

        int localAge = chicken.computeAgeInYears();
        System.out.println("Chicken's age: " + localAge);
    }

    private static Date newDate() {
        return new Date();
    }
}
