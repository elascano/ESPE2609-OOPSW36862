package ec.edu.espe.chickenfarmapp.view;

import ec.edu.espe.chickenfarmapp.model.Chicken;
import ec.edu.espe.chickenfarmapp.model.ChickenCoop;
import ec.edu.espe.chickenfarmapp.utils.FileManager;
import java.io.IOException;
import java.text.SimpleDateFormat;
import java.util.Calendar;
import java.util.Date;
import java.util.Scanner;
/**
 *
 * @author Daniel Sangoquiza, Saborcode, @ESPE
 */
public class ChickenFarmApp {

    private static final int WIDTH = 60;                   
    private static final String FILE_NAME = "chickens.csv"; 

    private static final Scanner scanner = new Scanner(System.in);
    private static ChickenCoop coop;                        
    private static boolean unsavedChanges = false;         

    public static void main(String args[]) {
        loadAtStart();

        int option;
        do {
            printMenu();
            option = readInt(" Choose an option: ");

            switch (option) {
                case 1:
                    insertChickens();
                    break;
                case 2:
                    saveChickens();
                    break;
                case 3:
                    readSavedChickens();
                    break;
                case 4:
                    showCoop();
                    break;
                case 5:
                    removeChicken();
                    break;
                case 6:
                    walkThroughCoop();
                    break;
                case 0:
                    exitApp();
                    break;
                default:
                    System.out.println(" [X] Invalid option, choose a number from the menu");
            }

            if (option != 0) {
                pause();
            }
        } while (option != 0);
    }

    private static void printMenu() {
        System.out.println();
        printHeader();

        String status;
        if (unsavedChanges) {
            status = "unsaved changes (*)";
        } else {
            status = "everything saved";
        }
        System.out.println(" Coop #" + coop.getChickenCoopId()
                + "   |   chickens: " + coop.getCount() + "/" + coop.getCapacity()
                + "   |   " + status);
        printLine('-');
        System.out.println("   [1]  Insert chickens");
        System.out.println("   [2]  Save chickens to file");
        System.out.println("   [3]  Read saved chickens");
        System.out.println("   [4]  Show chickens in the coop");
        System.out.println("   [5]  Remove a chicken");
        System.out.println("   [6]  Walk through the coop (next / reset)");
        System.out.println("   [0]  Exit");
        printLine('-');
    }

    private static void pause() {
        System.out.println();
        System.out.print(" Press Enter to go back to the menu...");
        scanner.nextLine();
    }

    private static void loadAtStart() {
        try {
            coop = FileManager.load(FILE_NAME, 1);
            if (coop.isEmpty()) {
                System.out.println(" No saved chickens yet, starting with an empty coop.");
            } else {
                System.out.println(" [OK] " + coop.getCount() + " chickens loaded from " + FILE_NAME);
            }
        } catch (IOException e) {
            coop = new ChickenCoop(1);
            System.out.println(" [X] The file could not be read: " + e.getMessage());
        }
    }

    private static void insertChickens() {
        printSection("INSERT CHICKENS");
        boolean addAnother = true;

        while (addAnother) {
            if (coop.isFull()) {
                System.out.println(" [X] The coop is full (" + coop.getCapacity() + " chickens)");
                return;
            }

            System.out.println();
            System.out.println(" Chicken #" + (coop.getCount() + 1));
            Chicken chicken = readChicken();
            coop.add(chicken);
            unsavedChanges = true;
            System.out.println(" [OK] " + chicken.getName() + " was added to the coop ("
                    + coop.getCount() + "/" + coop.getCapacity() + ")");

            addAnother = askYesNo(" Add another chicken? (y/n): ");
        }
        System.out.println(" Remember to save them with option [2]");
    }

    private static void saveChickens() {
        printSection("SAVE CHICKENS TO FILE");
        try {
            FileManager.save(coop, FILE_NAME);
            unsavedChanges = false;
            System.out.println(" [OK] " + coop.getCount() + " chickens saved in:");
            System.out.println("      " + FileManager.fullPath(FILE_NAME));
        } catch (IOException e) {
            System.out.println(" [X] The chickens could not be saved: " + e.getMessage());
        }
    }

    private static void readSavedChickens() {
        if (!FileManager.exists(FILE_NAME)) {
            printSection("SAVED CHICKENS (" + FILE_NAME + ")");
            System.out.println(" There is no saved file yet, use option [2] first");
            return;
        }
        try {
            ChickenCoop savedCoop = FileManager.load(FILE_NAME, coop.getChickenCoopId());
            printChickenTable(savedCoop, "SAVED CHICKENS (" + FILE_NAME + ")");
            if (unsavedChanges) {
                System.out.println(" (*) The coop has changes that are not saved in the file yet");
            }
        } catch (IOException e) {
            System.out.println(" [X] The file could not be read: " + e.getMessage());
        }
    }

    private static void showCoop() {
        printChickenTable(coop, "CHICKENS IN THE COOP");
        System.out.println();
        System.out.println(" coop --> " + coop);
    }

    private static void removeChicken() {
        printChickenTable(coop, "REMOVE A CHICKEN");
        if (coop.isEmpty()) {
            return;
        }

        int id = readInt(" Enter the id of the chicken to remove: ");
        int before = coop.getCount();
        coop.remove(id);

        if (coop.getCount() < before) {
            unsavedChanges = true;
            System.out.println(" [OK] The chicken with id " + id + " was removed");
            System.out.println(" Remember to save the change with option [2]");
        } else {
            System.out.println(" [X] There is no chicken with id " + id);
        }
    }

    private static void walkThroughCoop() {
        printSection("WALK THROUGH THE COOP");
        if (coop.isEmpty()) {
            System.out.println(" The coop is empty");
            return;
        }

        coop.resetIteration();
        System.out.println(" resetIteration()  ->  we start at the first chicken");
        System.out.println(" [n] next()   [r] resetIteration()   [b] back to the menu");

        String option;
        do {
            option = readText(" > ").toLowerCase();
            switch (option) {
                case "n":
                    Chicken chicken = coop.next();
                    if (chicken != null) {
                        System.out.println("   next()            ->  " + chicken.getName()
                                + " (id " + chicken.getId() + ")");
                    } else {
                        System.out.println("   next()            ->  null (no more chickens, press r)");
                    }
                    break;
                case "r":
                    coop.resetIteration();
                    System.out.println("   resetIteration()  ->  back to the first chicken");
                    break;
                case "b":
                    break;
                default:
                    System.out.println("   [X] Use n, r or b");
            }
        } while (!option.equals("b"));
    }


    private static void exitApp() {
        if (unsavedChanges) {
            System.out.println();
            boolean save = askYesNo(" You have unsaved changes. Save them before leaving? (y/n): ");
            if (save) {
                saveChickens();
            }
        }
        printSection("GOODBYE");
        System.out.println(" Thanks for using the Chicken Farm App!");
        printLine('=');
    }

    private static void printChickenTable(ChickenCoop chickenCoop, String title) {
        printSection(title);

        if (chickenCoop.isEmpty()) {
            System.out.println(" The coop is empty");
            return;
        }

        SimpleDateFormat dateFormat = new SimpleDateFormat("yyyy-MM-dd");

        System.out.printf(" %-4s| %-11s| %-11s| %-11s| %-4s| %s%n",
                "ID", "NAME", "COLOR", "BORN", "AGE", "MOLT");
        System.out.println(" ----+------------+------------+------------+-----+-----");

        chickenCoop.resetIteration();
        Chicken chicken = chickenCoop.next();
        while (chicken != null) {
            String molting;
            if (chicken.isMolting()) {
                molting = "yes";
            } else {
                molting = "no";
            }
            System.out.printf(" %-4d| %-11.10s| %-11.10s| %-11s| %-4d| %s%n",
                    chicken.getId(), chicken.getName(), chicken.getColor(),
                    dateFormat.format(chicken.getBornOnDate()), chicken.getAgeInYears(), molting);
            chicken = chickenCoop.next();
        }

        System.out.println(" Total: " + chickenCoop.getCount() + "/" + chickenCoop.getCapacity() + " chickens");
    }

    private static Chicken readChicken() {
        int id = readInt("   id .................. ");
        while (coop.exists(id)) {
            System.out.println("   [X] There is already a chicken with id " + id);
            id = readInt("   id .................. ");
        }
        String name = readRequiredText("   name ................ ");
        String color = readRequiredText("   color ............... ");
        Date bornOnDate = readDate();
        boolean molting = askYesNo("   molting? (y/n) ...... ");

        Chicken chicken = new Chicken(id, name, color, bornOnDate, 0, molting);
        chicken.setAgeInYears();
        return chicken;
    }

    private static Date readDate() {
        while (true) {
            int year = readInt("   birth year .......... ");
            int month = readInt("   birth month (1-12) .. ");
            int day = readInt("   birth day ........... ");

            Calendar calendar = Calendar.getInstance();
            calendar.setLenient(false);          
            calendar.clear();
            calendar.set(year, month - 1, day);  

            try {
                Date date = calendar.getTime();
                if (date.after(new Date())) {
                    System.out.println("   [X] The chicken can not be born in the future, try again");
                } else {
                    return date;
                }
            } catch (IllegalArgumentException e) {
                System.out.println("   [X] That date does not exist, try again");
            }
        }
    }

    private static String readText(String message) {
        System.out.print(message);
        return scanner.nextLine().trim();
    }

    private static String readRequiredText(String message) {
        String text = readText(message);
        while (text.isEmpty()) {
            System.out.println("   [X] This field can not be empty");
            text = readText(message);
        }
        return text;
    }

    private static int readInt(String message) {
        while (true) {
            String text = readText(message);
            try {
                return Integer.parseInt(text);
            } catch (NumberFormatException e) {
                System.out.println("   [X] Please enter a whole number");
            }
        }
    }

    private static boolean askYesNo(String message) {
        while (true) {
            String answer = readText(message);
            if (answer.equalsIgnoreCase("y")) {
                return true;
            }
            if (answer.equalsIgnoreCase("n")) {
                return false;
            }
            System.out.println("   [X] Please answer y or n");
        }
    }

    private static void printHeader() {
        printBoxLine('=');
        printBoxText("Daniel Sangoquiza CHICKEN FARM APP");
        printBoxText("version 3.0");
        printBoxLine('=');
    }

    private static void printBoxLine(char character) {
        System.out.println("+" + repeat(character, WIDTH - 2) + "+");
    }

    private static void printBoxText(String text) {
        int inside = WIDTH - 2;
        int left = (inside - text.length()) / 2;
        int right = inside - text.length() - left;
        System.out.println("|" + repeat(' ', left) + text + repeat(' ', right) + "|");
    }

    private static void printSection(String title) {
        String text = " " + title + " ";
        int left = (WIDTH - text.length()) / 2;
        int right = WIDTH - text.length() - left;

        System.out.println();
        System.out.println(repeat('-', left) + text + repeat('-', right));
    }

    private static void printLine(char character) {
        System.out.println(repeat(character, WIDTH));
    }

    private static String repeat(char character, int times) {
        String text = "";
        for (int i = 0; i < times; i++) {
            text = text + character;
        }
        return text;
    }
}