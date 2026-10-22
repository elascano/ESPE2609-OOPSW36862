package ec.edu.espe.chickenfarmapp.view;

import ec.edu.espe.chickenfarmapp.model.Chicken;
import ec.edu.espe.chickenfarmapp.model.ChickenCoop;
import java.io.*;
import java.text.SimpleDateFormat;
import java.util.Date;
import java.util.Scanner;

/**
 *
 * @author Leslye Cuichan
 */
public class ChickenFarmApp {

    private static final Scanner scanner = new Scanner(System.in);
    private static final String FILE_NAME = "chickens.csv";
    private static final SimpleDateFormat DATE_FORMAT = new SimpleDateFormat("yyyy-MM-dd");

    public static void main(String[] args) {

        System.out.println("Leslye Cuichan's Chicken Farm App");
        System.out.println("================================");

        ChickenCoop coop = new ChickenCoop(1);

        loadChickens(coop);

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
                    saveChickens(coop);
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

        if (coop.add(newChicken)) {
            saveChickens(coop);
            System.out.println("Chicken successfully registered.");
        } else {
            System.out.println("Error: Coop is full.");
        }
    }

    private static void removeChickenFromCoop(ChickenCoop coop) {
        System.out.println("\n--- DELETE CHICKEN ---");
        int removeId = readInt("Enter ID to delete: ");
        if (coop.remove(removeId)) {
            saveChickens(coop);
            System.out.println("Delete request completed.");
        } else {
            System.out.println("Error: Chicken with ID " + removeId + " not found.");
        }
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

    private static void saveChickens(ChickenCoop coop) {
        try (PrintWriter writer = new PrintWriter(new FileWriter(FILE_NAME))) {
            coop.resetIteration();
            Chicken current = coop.next();
            while (current != null) {
                String line = current.getId() + ";" +
                              current.getName() + ";" +
                              current.getColor() + ";" +
                              DATE_FORMAT.format(current.getBornOnDate()) + ";" +
                              current.getAgeInYears() + ";" +
                              current.isMolting();
                writer.println(line);
                current = coop.next();
            }
        } catch (IOException e) {
            System.out.println("Error saving data: " + e.getMessage());
        }
    }

    private static void loadChickens(ChickenCoop coop) {
        File file = new File(FILE_NAME);
        if (!file.exists()) {
            return;
        }

        try (BufferedReader reader = new BufferedReader(new FileReader(file))) {
            String line;
            while ((line = reader.readLine()) != null) {
                if (line.trim().isEmpty()) continue;
                String[] parts = line.split(";");
                if (parts.length == 6) {
                    int id = Integer.parseInt(parts[0]);
                    String name = parts[1];
                    String color = parts[2];
                    Date birthDate = DATE_FORMAT.parse(parts[3]);
                    int age = Integer.parseInt(parts[4]);
                    boolean isMolting = Boolean.parseBoolean(parts[5]);

                    Chicken chicken = new Chicken(id, name, color, birthDate, age, isMolting);
                    coop.add(chicken);
                }
            }
        } catch (Exception e) {
            System.out.println("Error loading data: " + e.getMessage());
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