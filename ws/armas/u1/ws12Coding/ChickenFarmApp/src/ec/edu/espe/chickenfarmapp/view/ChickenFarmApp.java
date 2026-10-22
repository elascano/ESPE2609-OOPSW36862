package ec.edu.espe.chickenfarmapp.view;

import ec.edu.espe.chickenfarmapp.model.Chicken;
import ec.edu.espe.chickenfarmapp.model.ChickenCoop;
import ec.edu.espe.chickenfarmapp.model.Egg;
import ec.edu.espe.chickenfarmapp.model.Poop;
import java.util.Date;
import java.util.Scanner;

/**
 *
 * @author Demian Armas, Logical Lords, @ESPE
 */
public class ChickenFarmApp {

    private static final Scanner scanner = new Scanner(System.in);

    public static void main(String args[]) {
        System.out.println("Demian Armas's Chicken Farm App");
        System.out.println("===============================");

        ChickenCoop coop = new ChickenCoop(1);
        int option;

        do {
            printMenu();
            option = readInt("Choose an option: ", 0, 6);
            System.out.println();

            switch (option) {
                case 1:
                    addChicken(coop);
                    break;
                case 2:
                    removeChicken(coop);
                    break;
                case 3:
                    coop.next();
                    break;
                case 4:
                    coop.resetIteration();
                    break;
                case 5:
                    System.out.println(coop);
                    break;
                case 6:
                    runDemo();
                    break;
                case 0:
                    System.out.println(".");
                    break;
            }
            System.out.println();
        } while (option != 0);
    }

    private static void printMenu() {
        System.out.println("---------- MENU ----------");
        System.out.println("1. Add chicken");
        System.out.println("2. Remove chicken");
        System.out.println("3. Next chicken");
        System.out.println("4. Reset iteration");
        System.out.println("5. Show coop");
        System.out.println("6. Run demo");
        System.out.println("0. Exit");
    }

    private static void addChicken(ChickenCoop coop) {
        int id = readInt("Id: ", 0, Integer.MAX_VALUE);
        System.out.print("Name: ");
        String name = scanner.nextLine().trim();
        System.out.print("Color: ");
        String color = scanner.nextLine().trim();

        System.out.println("Birth date:");
        int day = readInt("  Day (1-31): ", 1, 31);
        int month = readInt("  Month (1-12): ", 1, 12);
        int year = readInt("  Year (e.g. 2022): ", 1900, 2100);

        Date bornOnDate = new Date(year - 1900, month - 1, day);

        Chicken chicken = new Chicken(id, name, color, bornOnDate);
        chicken.setAgeInYears();
        coop.add(chicken);
    }

    private static void removeChicken(ChickenCoop coop) {
        int id = readInt("Id of the chicken to remove: ", 0, Integer.MAX_VALUE);
        coop.remove(id);
    }

    private static int readInt(String message, int min, int max) {
        while (true) {
            System.out.print(message);
            try {
                int value = Integer.parseInt(scanner.nextLine().trim());
                if (value >= min && value <= max) {
                    return value;
                }
                System.out.println("Enter a number between " + min + " and " + max + ".");
            } catch (NumberFormatException e) {
                System.out.println("Invalid number. Try again.");
            }
        }
    }

    private static void runDemo() {
        Chicken chicken;
        Chicken chicken1;
        Egg egg;
        Poop poop;
        Chicken chickens[];
        chickens = new Chicken[10];

        chicken = new Chicken(1, "Lucy", "brown and withe", new Date(1970, 12, 17));
        chicken.setAgeInYears();
        egg = new Egg(1, 'M');
        poop = new Poop(1, "brown", 'S');
        chicken1 = new Chicken(2, "Renata", "white", new Date(1970, 11, 17));
        chicken1.setAgeInYears();

        System.out.println("chicken -->" + chicken);
        System.out.println("egg -->" + egg);
        System.out.println("poop -->" + poop);
        System.out.println("chicken 2-->" + chicken1);
        System.out.println("The age of the chicken --> " + chicken.getName() + " is " + chicken.computeAgeInYears());

        chicken.doStuff();
        chicken1.doStuff();

        chickens[0] = new Chicken(3, "Pepita", "black", new Date(2020, 2, 2));
        chickens[1] = chicken;
        chickens[2] = chicken1;

        for (int i = 0; i < 10; i++) {
            System.out.println("chickens -->" + chickens[i]);
        }

        int ageForTheLoop = 0;
        for (int i = 0; i < chickens.length; i++) {
            ageForTheLoop = 2010 + i;
            chickens[i] = new Chicken(i, "chicken " + (i + 1), "white ", new Date(ageForTheLoop, 0, 1));
            chickens[i].setAgeInYears();
        }

        for (int i = 0; i < 10; i++) {
            System.out.println("chickens --> " + chickens[i]);
        }
    }

}