package ec.edu.espe.chickenfarmapp.view;

import ec.edu.espe.chickenfarmapp.controler.ChickenFiles;
import ec.edu.espe.chickenfarmapp.model.Chicken;
import ec.edu.espe.chickenfarmapp.model.ChickenCoop;
import ec.edu.espe.chickenfarmapp.model.Egg;
import ec.edu.espe.chickenfarmapp.model.Poop;
import java.text.ParseException;
import java.text.SimpleDateFormat;
import java.util.Calendar;
import java.util.Date;
import java.util.List;
import java.util.Scanner;

/**
 *
 * @author Nacimba Mateo, Group5, @ESPE
 */
public class ChickenFramApp {

    public static void main(String[] args) {
        System.out.println("Mateo Nacimba Chicken Farm App");
        System.out.println("===============================");

        Chicken chicken = new Chicken(1, "Lucy", "brown and white", buildDate(2024, Calendar.APRIL, 15), true);
        Egg egg = new Egg(1, 'M');
        Poop poop = new Poop(1, "brown", 'S');
        Chicken chicken1 = new Chicken(2, "Renata", "white", buildDate(2007, Calendar.JULY, 13), true);

        System.out.println("chicken --> " + chicken);
        System.out.println("egg --> " + egg);
        System.out.println("poop --> " + poop);
        System.out.println("chicken2 --> " + chicken1);

        int localAge = chicken.computeAgeInYears();
        System.out.println("The age of the chicken --> " + chicken.getName() + ", is --> " + localAge);

        chicken.doStuff();

        System.out.println("\n===============================");
        System.out.println("CHICKEN COOP MANAGEMENT");
        System.out.println("===============================");

        Scanner scanner = new Scanner(System.in);
        ChickenCoop coop = new ChickenCoop(1);
        ChickenFiles files = new ChickenFiles("chickens.csv");
        loadSavedChickens(coop, files);

        int option;
        do {
            System.out.println("\n----------- MENU -----------");
            System.out.println("1. Add chickens");
            System.out.println("2. Remove a chicken");
            System.out.println("3. List chickens");
            System.out.println("0. Exit");
            option = readInt(scanner, "Choose an option: ");

            switch (option) {
                case 1:
                    addChickens(scanner, coop, files);
                    break;
                case 2:
                    removeChicken(scanner, coop, files);
                    break;
                case 3:
                    listChickens(coop);
                    break;
                case 0:
                    System.out.println("Goodbye!");
                    break;
                default:
                    System.out.println("Invalid option. Try again.");
            }
        } while (option != 0);

        scanner.close();
    }

    // ------------------------- Menu actions -------------------------

    private static void loadSavedChickens(ChickenCoop coop, ChickenFiles files) {
        List<Chicken> saved = files.loadAll();
        if (saved.isEmpty()) {
            return;
        }
        Chicken[] array = new Chicken[ChickenCoop.CAPACITY];
        int count = Math.min(saved.size(), array.length);
        for (int i = 0; i < count; i++) {
            array[i] = saved.get(i);
        }
        coop.setChickens(array);
        System.out.println("Loaded " + count + " chicken(s) from file.");
        if (saved.size() > array.length) {
            System.out.println("Warning: the file has more chickens than the coop capacity.");
        }
    }

    private static void addChickens(Scanner scanner, ChickenCoop coop, ChickenFiles files) {
        if (coop.isFull()) {
            System.out.println("The chicken coop is full! (Limit of " + ChickenCoop.CAPACITY + " chickens reached).");
            return;
        }

        int remaining = ChickenCoop.CAPACITY - coop.getChickenCount();
        int quantity = readIntInRange(scanner,
                "How many chickens do you want to add? (1-" + remaining + "): ", 1, remaining);

        for (int i = 1; i <= quantity; i++) {
            System.out.println("\n--- Register chicken " + i + " of " + quantity + " ---");

            int id = coop.generateId();
            System.out.println("Generated ID: " + id);

            String name = readNonEmpty(scanner, "Enter chicken name: ");
            String color = readNonEmpty(scanner, "Enter chicken color: ");
            Date bornOnDate = readPastDate(scanner, "Enter birth date (dd/MM/yyyy): ");
            boolean molting = readYesNo(scanner, "Is the chicken molting? (y/n): ");

            System.out.println("-- Poop data --");
            String poopColor = readNonEmpty(scanner, "Enter poop color: ");
            char consistency = readConsistency(scanner,
                    "Enter poop consistency (S = solid, M = medium, L = liquid): ");

            Poop chickenPoop = new Poop(id, poopColor, consistency);
            Chicken newChicken = new Chicken(id, name, color, bornOnDate, molting, chickenPoop);
            if (coop.add(newChicken)) {
                files.add(newChicken);
            }
        }
    }

    private static void removeChicken(Scanner scanner, ChickenCoop coop, ChickenFiles files) {
        if (coop.getChickenCount() == 0) {
            System.out.println("The coop is empty. There is nothing to remove.");
            return;
        }

        listChickens(coop);
        int id = readInt(scanner, "Enter the ID of the chicken to remove: ");

        if (!coop.existsId(id)) {
            System.out.println("Chicken with ID " + id + " not found.");
            return;
        }

        if (readYesNo(scanner, "Are you sure you want to remove chicken " + id + "? (y/n): ")) {
            if (coop.remove(id)) {
                files.delete(id);
            }
        } else {
            System.out.println("Operation cancelled.");
        }
    }

    private static void listChickens(ChickenCoop coop) {
        System.out.println("\n--- List of Chickens in the Coop ---");
        coop.resetIteration();
        Chicken currentChicken = coop.next();

        if (currentChicken == null) {
            System.out.println("The coop is empty.");
        } else {
            while (currentChicken != null) {
                System.out.println(currentChicken);
                currentChicken = coop.next();
            }
        }
        System.out.println("Total chickens in the coop: " + coop.getChickenCount()
                + " / " + ChickenCoop.CAPACITY);
    }

    // ------------------------- Input helpers -------------------------

    private static Date buildDate(int year, int month, int day) {
        Calendar calendar = Calendar.getInstance();
        calendar.clear();
        calendar.set(year, month, day);
        return calendar.getTime();
    }

    private static int readInt(Scanner scanner, String message) {
        while (true) {
            System.out.print(message);
            String input = scanner.nextLine().trim();
            try {
                return Integer.parseInt(input);
            } catch (NumberFormatException e) {
                System.out.println("Invalid number. Please try again.");
            }
        }
    }

    private static int readIntInRange(Scanner scanner, String message, int min, int max) {
        while (true) {
            int value = readInt(scanner, message);
            if (value >= min && value <= max) {
                return value;
            }
            System.out.println("The number must be between " + min + " and " + max + ".");
        }
    }

    private static String readNonEmpty(Scanner scanner, String message) {
        while (true) {
            System.out.print(message);
            String input = scanner.nextLine().trim();
            if (!input.isEmpty()) {
                return input;
            }
            System.out.println("This field cannot be empty.");
        }
    }

    private static Date readPastDate(Scanner scanner, String message) {
        SimpleDateFormat format = new SimpleDateFormat("dd/MM/yyyy");
        format.setLenient(false);
        while (true) {
            System.out.print(message);
            String input = scanner.nextLine().trim();
            try {
                Date date = format.parse(input);
                if (date.after(new Date())) {
                    System.out.println("The birth date cannot be in the future.");
                } else {
                    return date;
                }
            } catch (ParseException e) {
                System.out.println("Invalid date. Use the format dd/MM/yyyy (e.g. 15/04/2024).");
            }
        }
    }

    private static boolean readYesNo(Scanner scanner, String message) {
        while (true) {
            System.out.print(message);
            String input = scanner.nextLine().trim().toLowerCase();
            if (input.equals("y")) {
                return true;
            }
            if (input.equals("n")) {
                return false;
            }
            System.out.println("Please answer 'y' or 'n'.");
        }
    }

    private static char readConsistency(Scanner scanner, String message) {
        while (true) {
            System.out.print(message);
            String input = scanner.nextLine().trim().toUpperCase();
            if (input.length() == 1 && "SML".indexOf(input.charAt(0)) >= 0) {
                return input.charAt(0);
            }
            System.out.println("Invalid value. Use S, M or L.");
        }
    }
}