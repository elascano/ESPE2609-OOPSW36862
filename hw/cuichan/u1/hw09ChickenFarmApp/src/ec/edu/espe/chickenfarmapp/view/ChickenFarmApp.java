package ec.edu.espe.chickenfarmapp.view;

import ec.edu.espe.chickenfarmapp.model.Chicken;
import ec.edu.espe.chickenfarmapp.model.ChickenCoop;
import ec.edu.espe.chickenfarmapp.model.Egg;
import ec.edu.espe.chickenfarmapp.model.Poop;
import java.util.Date;
import java.util.Scanner;

/**
 *
 * @author Leslye Cuichan
 */
public class ChickenFarmApp {

    private static final Scanner scanner = new Scanner(System.in);

    public static void main(String[] args) {

        System.out.println("Leslye Cuichan's Chicken Farm App");
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
        }

        Chicken chickens[] = new Chicken[10];

        // Llenamos el arreglo y el gallinero directamente
        int ageForTheLoop = 0;
        for (int i = 0; i < chickens.length; i++) {
            ageForTheLoop = 2010 + i;
            chickens[i] = new Chicken(i, "chicken " + (i + 1), "white ", new Date(ageForTheLoop, 3, 3), 0, true);
            chickens[i].setAgeInYears();
            coop.add(chickens[i]);
        }

        // Una sola impresión con las gallinas ya registradas
        for (int i = 0; i < 10; i++) {
            System.out.println("chickens --> " + chickens[i]);
        }

        int option = 0;

        do {
            showMenu();
            option = readInt("Select an option: ");

            switch (option) {
                case 1:
                    addChickenToCoop(coop);
                    break;
                case 2:
                    removeChickenFromCoop(coop);
                    break;
                case 3:
                    showAllChickens(coop);
                    break;
                case 4:
                    showNextChicken(coop);
                    break;
                case 5:
                    System.out.println("\n--- CURRENT COOP DETAILS ---");
                    System.out.println(coop);
                    break;
                case 6:
                    System.out.println("\nClosing application...");
                    break;
                default:
                    System.out.println("\nInvalid option. Try again.");
                    break;
            }

        } while (option != 6);
    }

    private static void showMenu() {
        System.out.println("\n---MENU---");
        System.out.println("1. Register new chicken");
        System.out.println("2. Delete chicken by ID");
        System.out.println("3. Display chicken");
        System.out.println("4. Inspect next chicken");
        System.out.println("5. View coop status");
        System.out.println("6. Exit");
    }

    private static void addChickenToCoop(ChickenCoop coop) {
        System.out.println("\n--- REGISTER CHICKEN ---");
        int id = readInt("Enter ID: ");
        String name = readString("Enter Name: ");
        String color = readString("Enter Color: ");
        boolean isMolting = readBoolean("Is molting? (yes/no): ");

        Chicken newChicken = new Chicken(id, name, color, new Date(), 0, isMolting);
        newChicken.setAgeInYears();

        coop.add(newChicken);
        System.out.println("Chicken successfully registered.");
    }

    private static void removeChickenFromCoop(ChickenCoop coop) {
        System.out.println("\n--- DELETE CHICKEN ---");
        int removeId = readInt("Enter ID to delete: ");
        coop.remove(removeId);
        System.out.println("Delete request completed.");
    }

    private static void showAllChickens(ChickenCoop coop) {
        System.out.println("\n--- REGISTERED CHICKENS ---");
        coop.resetIteration();
        Chicken current = coop.next();
        if (current == null) {
            System.out.println("No chickens found.");
        } else {
            while (current != null) {
                System.out.println("-> " + current);
                current = coop.next();
            }
        }
    }

    private static void showNextChicken(ChickenCoop coop) {
        System.out.println("\n--- NEXT ENTRY ---");
        Chicken nextChicken = coop.next();
        if (nextChicken != null) {
            System.out.println("-> " + nextChicken);
        } else {
            System.out.println("End of list reached.");
        }
    }

    private static int readInt(String prompt) {
        while (true) {
            System.out.print(prompt);
            try {
                String input = scanner.nextLine().trim();
                return Integer.parseInt(input);
            } catch (NumberFormatException e) {
                System.out.println("Error: Enter a valid integer.");
            }
        }
    }

    private static String readString(String prompt) {
        while (true) {
            System.out.print(prompt);
            String input = scanner.nextLine().trim();
            if (!input.isEmpty()) {
                return input;
            }
            System.out.println("Error: Value cannot be empty.");
        }
    }

    private static boolean readBoolean(String prompt) {
        while (true) {
            System.out.print(prompt);
            String input = scanner.nextLine().trim().toLowerCase();
            if (input.equals("true") || input.equals("y") || input.equals("yes")) {
                return true;
            } else if (input.equals("false") || input.equals("n") || input.equals("no")) {
                return false;
            } else {
                System.out.println("Error: Answer 'yes' or 'no'.");
            }
        }
    }
}