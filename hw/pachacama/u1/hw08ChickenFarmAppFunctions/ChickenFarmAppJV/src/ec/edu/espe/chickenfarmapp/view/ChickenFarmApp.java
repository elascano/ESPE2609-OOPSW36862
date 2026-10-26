package ec.edu.espe.chickenfarmapp.view;

import ec.edu.espe.chickenfarmapp.model.Chicken;
import ec.edu.espe.chickenfarmapp.model.ChickenCoop;
import ec.edu.espe.chickenfarmapp.model.Egg;
import ec.edu.espe.chickenfarmapp.model.Poop;
import java.util.Date;
import java.util.Scanner;

/**
 *
 * @author Andres Pachacama, Byte Work, @ESPE
 */
public class ChickenFarmApp {

    private static final int WIDTH = 60;  

    public static void main(String args[]) {
        Scanner scanner = new Scanner(System.in);
        ChickenCoop coop = new ChickenCoop(1);

        printHeader("ANDRES PACHACAMA'S CHICKEN FARM APP");

        addChickens(scanner, coop);
        printChickens(coop);
        removeChickens(scanner, coop);
        iterationDemo(coop);

        printSection("GOODBYE");
        System.out.println(" Thanks for using the Chicken Farm App!");
        printLine('=');
    }

    private static void addChickens(Scanner scanner, ChickenCoop coop) {
        printSection("ADD CHICKENS");
        boolean addAnother = true;

        while (addAnother) {
            if (coop.getCount() == coop.getCapacity()) {
                System.out.println(" [X] The coop is full (" + coop.getCapacity() + " chickens)");
                return;
            }

            System.out.println();
            System.out.println(" Chicken #" + (coop.getCount() + 1));
            Chicken chicken = readChicken(scanner);
            coop.add(chicken);
            System.out.println(" [OK] " + chicken.getName() + " was added to the coop ("
                    + coop.getCount() + "/" + coop.getCapacity() + ")");

            addAnother = askYesNo(scanner, " Add another chicken? (y/n): ");
        }
    }

    private static void printChickens(ChickenCoop coop) {
        printSection("CHICKENS IN THE COOP");

        if (coop.getCount() == 0) {
            System.out.println(" The coop is empty");
            return;
        }

        System.out.printf(" %-4s| %-12s| %-16s| %-4s| %-7s%n", "ID", "NAME", "COLOR", "AGE", "MOLTING");
        System.out.println(" ----+-------------+-----------------+-----+--------");

        coop.resetIteration();
        Chicken chicken = coop.next();
        while (chicken != null) {
            String molting;
            if (chicken.isMolting()) {
                molting = "yes";
            } else {
                molting = "no";
            }
            System.out.printf(" %-4d| %-12.12s| %-16.16s| %-4d| %-7s%n",
                    chicken.getId(), chicken.getName(), chicken.getColor(),
                    chicken.getAgeInYears(), molting);
            chicken = coop.next();
        }

        System.out.println(" Total: " + coop.getCount() + "/" + coop.getCapacity() + " chickens");
    }

    private static void removeChickens(Scanner scanner, ChickenCoop coop) {
        printSection("REMOVE CHICKENS");
        boolean removeAnother = askYesNo(scanner, " Do you want to remove a chicken? (y/n): ");

        while (removeAnother) {
            if (coop.getCount() == 0) {
                System.out.println(" [X] The coop is empty, there is nothing to remove");
                return;
            }

            int id = readInt(scanner, " Enter the id of the chicken to remove: ");
            int before = coop.getCount();
            coop.remove(id);

            if (coop.getCount() < before) {          // si bajo el count, si se borro
                System.out.println(" [OK] The chicken with id " + id + " was removed");
                printChickens(coop);
            } else {
                System.out.println(" [X] There is no chicken with id " + id);
            }

            removeAnother = askYesNo(scanner, " Remove another chicken? (y/n): ");
        }
    }

    private static void iterationDemo(ChickenCoop coop) {
        printSection("ITERATION DEMO");

        coop.resetIteration();
        System.out.println(" resetIteration()  ->  back to the first chicken");

        Chicken chicken = coop.next();
        while (chicken != null) {
            System.out.println(" next()            ->  " + chicken.getName());
            chicken = coop.next();
        }
        System.out.println(" next()            ->  (no more chickens)");

        coop.resetIteration();
        System.out.println(" resetIteration()  ->  back to the first chicken");

        chicken = coop.next();
        if (chicken != null) {
            System.out.println(" next()            ->  " + chicken.getName());
        } else {
            System.out.println(" next()            ->  (the coop is empty)");
        }
    }

    private static Chicken readChicken(Scanner scanner) {
        int id = readInt(scanner, "   id .................. ");
        String name = readText(scanner, "   name ................ ");
        String color = readText(scanner, "   color ............... ");
        int year = readInt(scanner, "   birth year .......... ");
        int month = readInt(scanner, "   birth month (1-12) .. ");
        int day = readInt(scanner, "   birth day ........... ");
        boolean molting = askYesNo(scanner, "   molting? (y/n) ...... ");

        Chicken chicken = new Chicken(id, name, color, new Date(year, month - 1, day), molting);
        chicken.setAgeInYears();
        return chicken;
    }

    private static String readText(Scanner scanner, String message) {
        System.out.print(message);
        return scanner.nextLine().trim();
    }

    private static int readInt(Scanner scanner, String message) {
        while (true) {
            System.out.print(message);
            String text = scanner.nextLine().trim();
            try {
                return Integer.parseInt(text);
            } catch (NumberFormatException e) {
                System.out.println("   [X] Please enter a whole number");
            }
        }
    }

    private static boolean askYesNo(Scanner scanner, String message) {
        while (true) {
            System.out.print(message);
            String answer = scanner.nextLine().trim();
            if (answer.equalsIgnoreCase("y")) {
                return true;
            }
            if (answer.equalsIgnoreCase("n")) {
                return false;
            }
            System.out.println("   [X] Please answer y or n");
        }
    }

    private static void printHeader(String title) {
        int inside = WIDTH - 2;
        int left = (inside - title.length()) / 2;
        int right = inside - title.length() - left;

        System.out.println("+" + repeat('=', inside) + "+");
        System.out.println("|" + repeat(' ', left) + title + repeat(' ', right) + "|");
        System.out.println("+" + repeat('=', inside) + "+");
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

    // =================== PRUEBAS DE CLASE ===================

    private static void classPractice() {
        Chicken chicken; //declaration
        Chicken chicken1;
        Egg egg;
        Poop poop;
        Chicken chickens[];
        chickens = new Chicken[10];

        chicken = new Chicken(2, "Maruja", "white", new Date(1970, 10, 17), false);
        chicken.setAgeInYears();
        egg = new Egg(1, 'M');

        System.out.println("my chicken is ---> " + chicken);
        System.out.println("egg ---> " + egg);

        System.out.println("egg size ---> " + egg.getSize());

        poop = new Poop(1, "brown", 'S');
        System.out.println("poop ---> " + poop);

        chicken1 = new Chicken(1, "Lucy", "brown and white", new Date(1970, 12, 17), true);
        chicken1.setAgeInYears();
        System.out.println("chiken 1 ---> " + chicken1);

        int localAge = chicken.computeAgeInYears();
        System.out.println("the age of the chicken ---> " + chicken.getName() + ", is ---> " + localAge);

        chicken.doStuff();

        chickens[0] = new Chicken(3, "Pepita", "black", new Date(2020, 2, 2), true);
        chickens[1] = chicken;

        for (int i = 0; i < 10; i++) {
            System.out.println("chickens --> " + chickens[i]);
        }

        for (int i = 0; i < chickens.length; i++) {
            chickens[i] = new Chicken(i, "chicken " + (i + 1), "white ", new Date(2020, 3, 3), true);
        }
        for (int i = 0; i < 10; i++) {
            System.out.println("chickens --> " + chickens[i]);
        }
        
        
        
        
        
    }
}
