
package ec.edu.espe.chickenfarmapp.view;

import ec.edu.espe.chickenfarmapp.model.Chicken;
import ec.edu.espe.chickenfarmapp.model.Egg;
import ec.edu.espe.chickenfarmapp.model.Poop;
import java.util.Date;
import ec.edu.espe.chickenfarmapp.model.ChickenCoop;
import java.util.Scanner;

public class ChickenFarmApp {

    public static void main(String args[]) {

        System.out.println("Dennis Criollo's Chicken Farm App");
        System.out.println("=================================");

        Chicken chicken;
        Chicken chicken1;
        Egg egg;
        Poop poop;
        ChickenCoop Coop;

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

        for (Chicken chicken2 : chickens) {
            System.out.println("chickens --> " + chicken2);
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

        for (Chicken chicken2 : chickens) {
            System.out.println("chickens --> " + chicken2);
        }

        System.out.println("length of chickens --> " + chickens.length);

     
        int localAge = chicken.computeAgeInYears();
        System.out.println("Chicken's age: " + localAge);
        //menu 
        Scanner scanner = new Scanner(System.in);
        ChickenCoop coop = new ChickenCoop(1, new Chicken[10]);

        int option = 0;
        do {
            System.out.println("\n--- MENU ---");
            System.out.println("1. Add Chicken");
            System.out.println("2. Remove Chicken");
            System.out.println("3. Display All Chickens");
            System.out.println("4. Exit");
            System.out.print("Select an option (1-4): ");

            if (scanner.hasNextInt()) {
                option = scanner.nextInt();
                scanner.nextLine();
            } else {
                scanner.nextLine();
                continue;
            }

            switch (option) {
                case 1:
                    System.out.println("\n--- ADD CHICKEN ---");
                    int generatedId = (int) (Math.random() * 90) + 10;
                    System.out.println("Generated Unique ID: " + generatedId);

                    System.out.print("Enter Name: ");
                    String name = scanner.nextLine();

                    System.out.print("Enter Color: ");
                    String color = scanner.nextLine();

                    System.out.print("Enter Birth Year (e.g., 2020): ");
                    int year = scanner.nextInt();

                    System.out.print("Enter Birth Month (1-12): ");
                    int month = scanner.nextInt();

                    System.out.print("Enter Birth Day (1-31): ");
                    int day = scanner.nextInt();
                    scanner.nextLine();

                    Chicken newChicken = new Chicken(
                            generatedId,
                            name,
                            color,
                            new Date(year - 1900, month - 1, day),
                            0,
                            true
                    );
                    newChicken.setAgeInYears();

                    coop.add(newChicken);
                    System.out.println("Chicken added successfully!");
                    break;

                case 2:
                    System.out.println("\n--- REMOVE CHICKEN ---");
                    System.out.print("Enter Chicken ID to remove: ");
                    int removeId = scanner.nextInt();
                    scanner.nextLine();
                    coop.remove(removeId);
                    System.out.println("Chicken removed successfully!");
                    break;

                case 3:
                    System.out.println("\n--- DISPLAY ALL CHICKENS ---");
                    coop.resetIteration();
                    Chicken current = coop.next();
                    boolean isEmpty = true;
                    while (current != null) {
                        System.out.println("chicken --> " + current);
                        isEmpty = false;
                        current = coop.next();
                    }
                    if (isEmpty) {
                        System.out.println("Coop is empty.");
                    }
                    break;

                case 4:
                    System.out.println("Exiting application...");
                    break;

                default:
                    System.out.println("Invalid option. Choose between 1 and 4.");
            }
        } while (option != 4);

        scanner.close();
    }
    private static Date newDate() {
        return new Date();
    }
    
}

