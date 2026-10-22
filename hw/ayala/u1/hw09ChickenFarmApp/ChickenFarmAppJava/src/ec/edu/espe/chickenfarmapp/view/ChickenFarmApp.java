package ec.edu.espe.chickenfarmapp.view;

import ec.edu.espe.chickenfarmapp.model.Chicken;
import ec.edu.espe.chickenfarmapp.model.ChickenCoop;
import ec.edu.espe.chickenfarmapp.model.ChickenJsonStorage;
import java.util.Date;
import java.util.GregorianCalendar;
import java.util.Scanner;

/**
 *
 * @author Enrique Ayala, LogicLords,@ESPE
 */
public class ChickenFarmApp {
    private static final String FILE_PATH = "chickens.json";

    public static void main(String[] args) {
        System.out.println("Enrique Ayala Chicken Farm App");
        System.out.println("==============================");

        Scanner scanner = new Scanner(System.in);
        ChickenCoop coop = new ChickenCoop(1);
        ChickenJsonStorage storage = new ChickenJsonStorage(FILE_PATH);
        storage.load(coop);

        int option;
        do {
            System.out.println();
            System.out.println("1. Add chicken");
            System.out.println("2. Remove chicken");
            System.out.println("3. Update chicken");
            System.out.println("4. Next chicken");
            System.out.println("5. Reset iteration");
            System.out.println("6. Show coop");
            System.out.println("0. Exit");
            System.out.print("Choose an option: ");
            option = Integer.parseInt(scanner.nextLine());

            switch (option) {
                case 1:
                    addChicken(scanner, coop, storage);
                    break;
                case 2:
                    removeChicken(scanner, coop, storage);
                    break;
                case 3:
                    updateChicken(scanner, coop, storage);
                    break;
                case 4:
                    Chicken next = coop.next();
                    if (next == null) {
                        System.out.println("No more chickens. Use option 5 to start again.");
                    } else {
                        System.out.println(next);
                        next.doStuff();
                    }
                    break;
                case 5:
                    coop.resetIteration();
                    System.out.println("Iteration reset.");
                    break;
                case 6:
                    System.out.println(coop);
                    break;
                case 0:
                    System.out.println("Bye!");
                    break;
                default:
                    System.out.println("Invalid option.");
            }
        } while (option != 0);
    }

    private static void addChicken(Scanner scanner, ChickenCoop coop,
                                   ChickenJsonStorage storage) {
        System.out.print("Id: ");
        int id = Integer.parseInt(scanner.nextLine());
        System.out.print("Name: ");
        String name = scanner.nextLine();
        System.out.print("Color: ");
        String color = scanner.nextLine();
        Date bornOnDate = readDate(scanner);
        boolean molting = readMolting(scanner);

        Chicken chicken = new Chicken(id, name, color, bornOnDate, molting);
        if (coop.add(chicken)) {
            storage.save(coop);
            System.out.println("Chicken added.");
        }
    }

    private static void removeChicken(Scanner scanner, ChickenCoop coop,
                                      ChickenJsonStorage storage) {
        System.out.print("Id of the chicken to remove: ");
        int id = Integer.parseInt(scanner.nextLine());
        if (coop.remove(id)) {
            storage.save(coop);
            System.out.println("Chicken removed.");
        }
    }

    private static void updateChicken(Scanner scanner, ChickenCoop coop,
                                      ChickenJsonStorage storage) {
        System.out.print("Id of the chicken to update: ");
        int id = Integer.parseInt(scanner.nextLine());
        System.out.print("New name: ");
        String name = scanner.nextLine();
        System.out.print("New color: ");
        String color = scanner.nextLine();
        Date bornOnDate = readDate(scanner);
        boolean molting = readMolting(scanner);

        if (coop.update(id, name, color, bornOnDate, molting)) {
            storage.save(coop);
            System.out.println("Chicken updated.");
        }
    }

    private static Date readDate(Scanner scanner) {
        System.out.print("Birth year (e.g. 2022): ");
        int year = Integer.parseInt(scanner.nextLine());
        System.out.print("Birth month (1-12): ");
        int month = Integer.parseInt(scanner.nextLine());
        System.out.print("Birth day (1-31): ");
        int day = Integer.parseInt(scanner.nextLine());
        // month - 1 because GregorianCalendar starts months at 0
        return new GregorianCalendar(year, month - 1, day).getTime();
    }

    private static boolean readMolting(Scanner scanner) {
        System.out.print("Is it molting? (y/n): ");
        return scanner.nextLine().trim().equalsIgnoreCase("y");
    }
}