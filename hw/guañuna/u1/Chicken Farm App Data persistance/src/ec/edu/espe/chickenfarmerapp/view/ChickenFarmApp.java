
package ec.edu.espe.chickenfarmerapp.view;

import ec.edu.espe.chickenfarmerapp.model.Chicken;
import ec.edu.espe.chickenfarmerapp.model.Egg;
import ec.edu.espe.chickenfarmerapp.model.Poop;
import ec.edu.espe.chickenfarmerapp.model.ChickenCoop;
import ec.edu.espe.chickenfarmerapp.model.Persistencia;
import java.util.Scanner;
import java.util.Date;

/**
 *
 * @author Cristopher Guañuna, Runtime Dynamic, @ESPE
 */
public class ChickenFarmApp {

    public static void main(String[] args) {

        System.out.println("Chicken Farm App Cristopher Guanuna");
        System.out.println("===================================");

        Chicken chicken;
        Chicken chicken1;
        Egg egg;
        Poop poop;
        Chicken[] chickens = new Chicken[10];

        chicken = new Chicken(
                1, "Lucy", "brown and white",
                new Date(2020, 7, 10), true
        );

        egg = new Egg(1, 'H');
        poop = new Poop(1, "brown", 'S');

        chicken1 = new Chicken(
                2, "Renata", "White",
                new Date(2025, 12, 20), false
        );

        chicken1.setName("Edith");

        int localAge = chicken.computeAgeInYears();
        int localAge1 = chicken1.computeAgeInYears();

        chicken.setAgeInYears(localAge);
        chicken1.setAgeInYears(localAge1);

        System.out.println("chicken --> " + chicken);
        System.out.println("egg --> " + egg);
        System.out.println("poop --> " + poop);
        System.out.println("chicken 2 --> " + chicken1);

        chicken.doStuff();

        System.out.println(
                "I am printing the name of my chicken --> "
                + chicken.getName()
        );

        chickens[0] = new Chicken(
                3, "Pepita", "black",
                new Date(2020, 2, 2), true
        );

        chickens[1] = chicken;

        for (Chicken chicken2 : chickens) {
            System.out.println("chickens --> " + chicken2);
        }

        int ageForTheLoop;

        for (int i = 0; i < chickens.length; i++) {
            chickens[i] = new Chicken(
                    i, "chicken" + (i + 1), "white",
                    new Date(2020 + i, 3, 3), true
            );

            ageForTheLoop = chickens[i].computeAgeInYears();
            chickens[i].setAgeInYears(ageForTheLoop);
        }

        for (Chicken chicken2 : chickens) {
            System.out.println("chickens --> " + chicken2);
        }


        System.out.println("Cargando datos del corral...");
        ChickenCoop chickenCoop = Persistencia.cargar();
        
        if (chickenCoop == null) {
            chickenCoop = new ChickenCoop();
        }

        System.out.println("\n=== INITIALIZED CHICKENS ===");

        chickenCoop.resetIteration();

        Chicken chickenCoopItem;

        while ((chickenCoopItem = chickenCoop.next()) != null) {
            System.out.println(chickenCoopItem);
        }

        System.out.println("============================");

        short addend1;
        addend1 = (short) 40000;

        float addend2;
        addend2 = addend1;

        addend1 = (short) addend2;

        System.out.println("addend1 --> " + addend1);
        System.out.println("addend2 --> " + addend2);

        Scanner scanner = new Scanner(System.in);
        int option = 0;

        do {
            System.out.println("\n=== CHICKEN COOP MENU ===");
            System.out.println("1. Add chicken");
            System.out.println("2. Remove chicken");
            System.out.println("3. Show all chickens");
            System.out.println("4. Save data"); // Nueva opción
            System.out.println("5. Exit");
            System.out.print("Choose an option: ");

            while (!scanner.hasNextInt()) {
                System.out.println("Invalid option. Enter a number:");
                scanner.next();
            }

            option = scanner.nextInt();

            switch (option) {
                case 1 -> {
                    chickenCoop.add();
                }
                case 2 -> {
                    chickenCoop.remove();
                }
                case 3 -> {
                    System.out.println("\n=== CHICKENS IN THE COOP ===");
                    chickenCoop.resetIteration();
                    while ((chickenCoopItem = chickenCoop.next()) != null) {
                        System.out.println(chickenCoopItem);
                    }
                    System.out.println("============================");
                }
                case 4 -> Persistencia.guardar(chickenCoop); 
                case 5 -> {
                    System.out.println("Saving data and exiting menu...");
                    Persistencia.guardar(chickenCoop); 
                }
                default -> System.out.println("Invalid option.");
            }

        } while (option != 5);

        scanner.close();
    }
}

