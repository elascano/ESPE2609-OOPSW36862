package ec.edu.espe.chickenfarmapp.view;

import ec.edu.espe.chickenfarmapp.model.Chicken;
import ec.edu.espe.chickenfarmapp.model.ChickenCoop;
import java.util.Date;
import java.util.Scanner;

/**
 *
 * @author David Clavón, Infinity Loops, @ESPE
 */
public class ChickenFarmApp {

    private static final Scanner scanner = new Scanner(System.in);

    public static void main(String[] args) {
        ChickenCoop coop = new ChickenCoop(101);
        int option = 0;

        System.out.println("=========================================");
        System.out.println("  David Clavon's Chicken Farm App");
        System.out.println("=========================================");

        do {
            showMenu(coop.getChickenCoopId());
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
                    coop.resetIteration();
                    System.out.println("\nCoop iterator reset to the first position.");
                    break;

                case 6:
                    System.out.println("\n--- CHICKEN COOP STATUS ---");
                    System.out.println(coop);
                    break;

                case 7:
                    System.out.println("\nExiting Chicken Farm App...");
                    break;

                default:
                    System.out.println("\nInvalid option. Please try again.");
                    break;
            }

        } while (option != 7);
    }

    private static void showMenu(int coopId) {
        System.out.println("\n--- CHICKEN COOP MENU (ID: " + coopId + ") ---");
        System.out.println("1. Add a chicken");
        System.out.println("2. Remove a chicken by ID");
        System.out.println("3. View all chickens");
        System.out.println("4. View next chicken");
        System.out.println("5. Reset iteration reader");
        System.out.println("6. Display full coop status");
        System.out.println("7. Exit");
    }

    private static void addChickenToCoop(ChickenCoop coop) {
        System.out.println("\n--- REGISTER NEW CHICKEN ---");
        int id = readInt("Enter chicken ID: ");
        String name = readString("Enter Name: ");
        String color = readString("Enter Color: ");
        boolean isMolting = readBoolean("Is molting? (yes/no or true/false): ");

        Chicken newChicken = new Chicken(id, name, color, new Date(), isMolting);
        if (coop.add(newChicken)) {
            System.out.println("Chicken " + name + " successfully added to the coop.");
        } else {
            System.out.println("Could not add chicken. The coop is full or the object is invalid.");
        }
    }

    private static void removeChickenFromCoop(ChickenCoop coop) {
        System.out.println("\n--- REMOVE CHICKEN ---");
        int removeId = readInt("Enter chicken ID to remove: ");
        if (coop.remove(removeId)) {
            System.out.println("Chicken with ID " + removeId + " was successfully removed.");
        } else {
            System.out.println("No chicken found with ID " + removeId + ".");
        }
    }

    private static void showAllChickens(ChickenCoop coop) {
        System.out.println("\n--- DISPLAYING ALL REGISTERED CHICKENS ---");
        coop.resetIteration();
        Chicken current = coop.next();
        if (current == null) {
            System.out.println("The coop is empty.");
        } else {
            while (current != null) {
                System.out.println("-> " + current);
                current = coop.next();
            }
        }
    }

    private static void showNextChicken(ChickenCoop coop) {
        System.out.println("\n--- NAVIGATE TO NEXT CHICKEN ---");
        Chicken nextChicken = coop.next();
        if (nextChicken != null) {
            int position = coop.getCurrentPosition();
            int total = coop.getCount();
            System.out.println("Viewing Chicken " + position + " of " + total + ":");
            System.out.println("-> " + nextChicken);
        } else {
            System.out.println("Reached the end of the list (or you need to reset the iteration).");
        }
    }


    private static int readInt(String prompt) {
        while (true) {
            System.out.print(prompt);
            try {
                String input = scanner.nextLine().trim();
                return Integer.parseInt(input);
            } catch (NumberFormatException e) {
                System.out.println("Error: Please enter a valid integer number.");
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
            System.out.println("Error: Field cannot be empty.");
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
                System.out.println("Error: Please enter a valid response ('yes'/'no' or 'true'/'false').");
            }
        }
    }
}