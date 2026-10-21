

package ec.edu.espe.chickenfarmapp.view;

import ec.edu.espe.chickenfarmapp.model.Chicken;
import ec.edu.espe.chickenfarmapp.model.ChickenCoop;
import ec.edu.espe.chickenfarmapp.model.Egg;
import ec.edu.espe.chickenfarmapp.model.Poop;

import java.time.DateTimeException;
import java.time.LocalDate;
import java.time.Period;
import java.time.ZoneId;
import java.util.Date;
import java.util.Random;
import java.util.Scanner;

public class ChickenFarmApp {

    private static final Scanner scanner = new Scanner(System.in);
    private static final Random random = new Random();

    public static void main(String[] args) {

        System.out.println("Renata Olalla's Chicken Farm App");
        System.out.println("=================================");

        Chicken chicken;
        Chicken chicken1;
        Egg egg;
        Poop poop;
        Chicken[] chickens = new Chicken[10];
        poop = new Poop(1, "brown", 'S');
        egg = new Egg(1, 'M');

        chicken = new Chicken(1,"Lucy","brown and white",new Date(2024 - 1900, 8, 15),0,true);

        chicken1 = new Chicken(2,"Renata","white",new Date(2024 - 1900, 8, 15),0,false);

        chicken.setAgeInYears();
        chicken1.setAgeInYears();

        System.out.println("chicken --> " + chicken);
        System.out.println("egg --> " + egg);
        System.out.println("egg size --> " + egg.getSize());
        System.out.println("poop --> " + poop);
        System.out.println("chicken1 --> " + chicken1);

        int localAge = chicken.computeAgeInYears();

        System.out.println(
                "The age of " + chicken.getName()
                + " is " + localAge + " years."
        );

        chicken.doStuff();

        chickens[0] = new Chicken(3, "Pepita","black",new Date(2020 - 1900, 2, 2),0,true);

        chickens[1] = chicken;

        System.out.println("\nInitial chickens array:");

        for (int i = 0; i < chickens.length; i++) {
            System.out.println("chickens[" + i + "] --> " + chickens[i]);
        }

        int birthYear;

        for (int i = 0; i < chickens.length; i++) {

            birthYear = 2010 + i;

            chickens[i] = new Chicken(i + 1,"Chicken " + (i + 1),"white",new Date(birthYear - 1900, 3, 3),0,true);

            chickens[i].setAgeInYears();
        }

        System.out.println("\nGenerated chickens array:");

        for (int i = 0; i < chickens.length; i++) {
            System.out.println("chickens[" + i + "] --> " + chickens[i]);
        }

        System.out.println(
                "Length of chickens array --> " + chickens.length
        );

        Chicken[] coopChickens = new Chicken[10];
        ChickenCoop chickenCoop = new ChickenCoop(1, coopChickens);

        int option;

        do {
            System.out.println("\n===== CHICKEN COOP MENU =====");
            System.out.println("1. Add a chicken");
            System.out.println("2. Remove a chicken");
            System.out.println("3. Show chickens");
            System.out.println("4. Exit");

            option = readInt("Choose an option: ");

            switch (option) {

                case 1:
                    addChicken(chickenCoop, coopChickens);
                    break;

                case 2:
                    removeChicken(chickenCoop);
                    break;

                case 3:
                    showChickens(chickenCoop);
                    break;

                case 4:
                    System.out.println("Exiting Chicken Coop...");
                    break;

                default:
                    System.out.println(
                            "Invalid option. Choose a number from 1 to 4."
                    );
            }

        } while (option != 4);

        scanner.close();
    }

    private static void addChicken(
            ChickenCoop chickenCoop,
            Chicken[] coopChickens) {int id = generateUniqueId(coopChickens);

        System.out.println("\nAdding a new chicken");
        System.out.println("Generated chicken ID: " + id);

        String name = readText("Enter chicken name: ");
        String color = readText("Enter chicken color: ");

        LocalDate birthDate = readBirthDate();
        boolean molting = readBoolean(
                "Is the chicken molting? (true/false): "
        );

        Date date = Date.from(
                birthDate.atStartOfDay(ZoneId.systemDefault()).toInstant()
        );

        Chicken newChicken = new Chicken(id,name,color,date,0,molting);

        newChicken.setAgeInYears();

        chickenCoop.add(newChicken);

        System.out.println("Chicken added successfully.");
        System.out.println(newChicken);
    }

    private static int generateUniqueId(Chicken[] chickens) {

        int id;
        boolean repeated;

        do {
            id = random.nextInt(9000) + 1000;
            repeated = false;

            for (Chicken chicken : chickens) {
                if (chicken != null && chicken.getId() == id) {
                    repeated = true;
                    break;
                }
            }

        } while (repeated);

        return id;
    }

    private static void removeChicken(ChickenCoop chickenCoop) {

        int chickenId = readInt(
                "Enter the ID of the chicken to remove: "
        );

        chickenCoop.remove(chickenId);
    }

    private static void showChickens(ChickenCoop chickenCoop) {

        System.out.println("\n===== CHICKENS IN THE COOP =====");

        chickenCoop.resetIteration();

        Chicken nextChicken;
        boolean found = false;

        while ((nextChicken = chickenCoop.next()) != null) {
            System.out.println(nextChicken);
            found = true;
        }

        if (!found) {
            System.out.println("There are no chickens in the coop.");
        }
    }

    private static int readInt(String message) {

        while (true) {
            System.out.print(message);

            String input = scanner.nextLine().trim();

            try {
                return Integer.parseInt(input);
            } catch (NumberFormatException e) {
                System.out.println("Invalid input. Enter a whole number.");
            }
        }
    }

    private static String readText(String message) {

        while (true) {
            System.out.print(message);
            String input = scanner.nextLine().trim();

            if (!input.isEmpty()) {
                return input;
            }

            System.out.println("This field cannot be empty.");
        }
    }

    private static boolean readBoolean(String message) {

        while (true) {
            System.out.print(message);
            String input = scanner.nextLine().trim();

            if (input.equalsIgnoreCase("true")) {
                return true;
            }

            if (input.equalsIgnoreCase("false")) {
                return false;
            }

            System.out.println("Enter true or false.");
        }
    }

    private static LocalDate readBirthDate() {

        while (true) {
            int year = readInt("Enter birth year: ");
            int month = readInt("Enter birth month (1-12): ");
            int day = readInt("Enter birth day: ");

            try {
                LocalDate birthDate = LocalDate.of(year, month, day);

                if (birthDate.isAfter(LocalDate.now())) {
                    System.out.println(
                            "The birth date cannot be in the future."
                    );
                    continue;
                }

                return birthDate;

            } catch (DateTimeException e) {
                System.out.println(
                        "Invalid date. Check the year, month and day."
                );
            }
        }
    }

    private static Date newDate() {
        return new Date();
    }
}
