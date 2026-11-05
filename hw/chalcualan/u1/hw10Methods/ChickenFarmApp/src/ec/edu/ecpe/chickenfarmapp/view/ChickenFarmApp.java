package ec.edu.ecpe.chickenfarmapp.view;
import ec.edu.espe.chickenfarmapp.model.Chicken;
import ec.edu.espe.chickenfarmapp.model.ChickenCoop;
import ec.edu.espe.chickenfarmapp.model.Egg;
import ec.edu.espe.chickenfarmapp.model.Poop;
import java.util.Date;
/**
 *
 * @author Isaac Acurio, Liquor Store App Simulation, @ESPE
 */
public class ChickenFarmApp {

    public static void main(String args[]) {
        System.out.println("Hello, Isaac Acurio Chicken Farm App");
        System.out.println("------------------------------------------");

        Chicken chicken;
        Chicken chicken1;
        Egg egg;
        Poop poop;

        chicken = new Chicken(1, "Lucy", "brown and white", new Date(2020, 02, 28), true);
        egg = new Egg(1, 'M');
        poop = new Poop(1, "brown", 'S');
        chicken1 = new Chicken(2, "Arturita", "white", new Date(2007, 04, 28), true);
        Chicken[] chickens = new Chicken[10];

        System.out.println("chicken ---" + chicken);
        System.out.println("egg ---" + egg);
        System.out.println("poop---" + poop);
        System.out.println("chicken 2 ---" + chicken1);

        int localAge = chicken.computeAgeInYears();
        System.out.println("the age of the chicken " + chicken.getName() + " is " + localAge);
        chicken.doStuff();

        chickens[0] = new Chicken(3, "Pepita", "Gray", new Date(2020, 2, 2), true);
        chickens[1] = chicken;
        chickens[2] = chicken1;
        
        ChickenCoop chickenCoop = new ChickenCoop(1, chickens);

        for (int i = 0; i < chickens.length; i++) {
            if (i > 2) {
                chickens[i] = new Chicken(i + 1, "chicken", "black", new Date(2020 - i + 1, 4, 27), true);
            }
            System.out.println("chicken----" + chickens[i]);
        }

        // TESTING METHODS 
        
        System.out.println("\n--- TESTING ITERATION WITH next() AND resetIteration() ---");
        chickenCoop.resetIteration();
        
        Chicken current;
        while ((current = chickenCoop.next()) != null) {
            System.out.println("Iterated chicken: " + current.getName() + " (ID: " + current.getId() + ")");
        }

        System.out.println("\n--- TESTING REMOVE (remove) ---");
        System.out.println("Removing chicken with ID 1 (Lucy)...");
        chickenCoop.remove(1);

        System.out.println("\n--- TESTING ADD (add) ---");
        Chicken newChicken = new Chicken(99, "Little Chick", "Yellow", new Date(), true);
        System.out.println("Adding " + newChicken.getName() + " into available space...");
        chickenCoop.add(newChicken);

        System.out.println("\nFinal Chicken Coop status:");
        System.out.println(chickenCoop);
    }
}