package ec.edu.espe.chickenfarmapp.view;

import ec.edu.espe.chickenfarmapp.controller.FilesChicken;
import ec.edu.espe.chickenfarmapp.model.Chicken;
import ec.edu.espe.chickenfarmapp.model.ChickenCoop;
import java.util.Date;
import java.util.Scanner;

/**
 *
 * @author Paucar Matias, ByteWorks, @ESPE
 */
public class ChickenFarmApp {

    static Scanner scanner = new Scanner(System.in);

    static final int COOP_ID = 1;

    //the coop is loaded from the file when the program starts
    static ChickenCoop coop;

    //counter to generate the ids automatically
    static int nextChickenId = 1;

    public static void main(String args[]){
        System.out.println("Matias Paucar's Chicken Farm App");
        System.out.println("#################################");

        coop = FilesChicken.load(COOP_ID);
        updateNextChickenId();
        System.out.println("Chicken coop id: " + COOP_ID);
        System.out.println(coop.getNumberOfChickens() + " chicken(s) loaded from " + FilesChicken.FILE_NAME);


        int option;

        do {
            System.out.println("\n----------- MENU -----------");
            System.out.println("1. Add chickens");
            System.out.println("2. Remove a chicken");
            System.out.println("3. List chickens");
            System.out.println("0. Exit");
            option = readInt("Choose an option: ");

            switch (option) {
                case 1:
                    addChickens();
                    break;
                case 2:
                    removeChickens();
                    break;
                case 3:
                    showChickens();
                    break;
                case 0:
                    System.out.println("Goodbye!");
                    break;
                default:
                    System.out.println("Invalid option, try again");
            }

        } while (option != 0);

    }

    //option 1: the customer says how many chickens to add
    public static void addChickens() {
        int freeSpaces = 10 - coop.getNumberOfChickens();

        if (freeSpaces == 0) {
            System.out.println("The coop is full, we can not add any more chickens");
            return;
        }

        int quantityToAdd;
        do {
            quantityToAdd = readInt("How many chickens do you want to add? (1-" + freeSpaces + "): ");
            if (quantityToAdd < 1 || quantityToAdd > freeSpaces) {
                System.out.println("Invalid quantity, it must be between 1 and " + freeSpaces);
            }
        } while (quantityToAdd < 1 || quantityToAdd > freeSpaces);

        for (int i = 0; i < quantityToAdd; i++) {
            System.out.println("\n--- Chicken " + (i + 1) + " ---");
            Chicken chicken = readChicken();
            if (coop.add(chicken)) {
                System.out.println("The chicken " + chicken.getName() + " was added to the coop");
            } else {
                System.out.println("The coop is full, we can not add any more chickens");
            }
        }

        //persistence: the file is updated with the old chickens and the new ones
        FilesChicken.save(coop);
        System.out.println("The file " + FilesChicken.FILE_NAME + " was updated");
    }

    //option 2: the customer says how many chickens to remove
    public static void removeChickens() {
        if (coop.getNumberOfChickens() == 0) {
            System.out.println("The coop is empty, there are no chickens to remove");
            return;
        }

        showChickens();

        int quantityToRemove;
        do {
            quantityToRemove = readInt("How many chickens do you want to remove? (1-" + coop.getNumberOfChickens() + "): ");
            if (quantityToRemove < 1 || quantityToRemove > coop.getNumberOfChickens()) {
                System.out.println("Invalid quantity, it must be between 1 and " + coop.getNumberOfChickens());
            }
        } while (quantityToRemove < 1 || quantityToRemove > coop.getNumberOfChickens());

        for (int i = 0; i < quantityToRemove; i++) {
            int idToRemove = readInt("Enter the id of the chicken to remove: ");
            Chicken chicken = coop.findById(idToRemove);
            if (chicken != null && coop.remove(idToRemove)) {
                System.out.println("The chicken " + chicken.getName() + " was removed from the coop");
            } else {
                System.out.println("There is no chicken with the id " + idToRemove);
            }
        }

        //persistence: the file is updated without the removed chickens
        FilesChicken.save(coop);
        System.out.println("The file " + FilesChicken.FILE_NAME + " was updated");
    }

    //option 3: shows the chickens that are saved in the file
    public static void showChickens() {
        //persistence: we read the file again to show what is really saved
        coop = FilesChicken.load(COOP_ID);

        if (coop.getNumberOfChickens() == 0) {
            System.out.println("The coop is empty");
            return;
        }

        System.out.println("\n=== Chickens in the coop (" + FilesChicken.FILE_NAME + ") ===");
        coop.resetIteration();
        while (coop.hasNext()) {
            Chicken current = coop.next();
            System.out.println("chickens --> " + current);
            System.out.println("Chicken's age: " + current.computeAgeInYears());
            current.doStuff();
        }
        System.out.println("number of chickens --> " + coop.getNumberOfChickens());
    }

    //this method asks the customer all the data of one chicken
    public static Chicken readChicken() {
        int id = nextChickenId;
        nextChickenId++;
        System.out.println("Chicken id: " + id);

        String name = readText("Name: ");
        String color = readText("Color: ");

        int year = readInt("Year of birth (example 2024): ");
        int month;
        do {
            month = readInt("Month of birth (1-12): ");
        } while (month < 1 || month > 12);
        int day;
        do {
            day = readInt("Day of birth (1-31): ");
        } while (day < 1 || day > 31);

        System.out.print("Is it molting? (true/false): ");
        boolean molting = Boolean.parseBoolean(scanner.nextLine().trim());

        //month - 1 because in Date the months start in 0
        return new Chicken(id, name, color, new Date(year, month - 1, day), molting);
    }

    //the next id is the biggest id of the file + 1, so the ids are never repeated
    public static void updateNextChickenId() {
        int maxId = 0;
        coop.resetIteration();
        while (coop.hasNext()) {
            Chicken chicken = coop.next();
            if (chicken.getId() > maxId) {
                maxId = chicken.getId();
            }
        }
        nextChickenId = maxId + 1;
    }

    //this method reads a text that can not be empty
    public static String readText(String message) {
        while (true) {
            System.out.print(message);
            String text = scanner.nextLine().trim();
            if (!text.isEmpty()) {
                return text;
            }
            System.out.println("This field is required");
        }
    }

    //this method reads a number and asks again if the customer writes letters
    public static int readInt(String message) {
        while (true) {
            System.out.print(message);
            try {
                return Integer.parseInt(scanner.nextLine().trim());
            } catch (NumberFormatException e) {
                System.out.println("Please enter a valid number");
            }
        }
    }

}