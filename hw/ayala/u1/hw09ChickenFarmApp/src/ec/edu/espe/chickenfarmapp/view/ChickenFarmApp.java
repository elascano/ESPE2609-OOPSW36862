
package ec.edu.espe.chickenfarmapp.view;

import ec.edu.espe.chickenfarmapp.model.Chicken;
import ec.edu.espe.chickenfarmapp.model.ChickenCoop;
import ec.edu.espe.chickenfarmapp.model.Egg;
import ec.edu.espe.chickenfarmapp.model.Poop;
import java.util.Date;
import java.util.GregorianCalendar;
import java.util.Scanner;

/**
 *
 * @author Enrique Ayala, LogicLords,@ESPE
 */
public class ChickenFarmApp {
    public static void main(String[] args) {
        System.out.println("Enrique Ayala Chicken Farm App");
        System.out.println("==============================");

        Scanner scanner = new Scanner(System.in);
        ChickenCoop coop = new ChickenCoop(1);
        int option;

        do {
            System.out.println();
            System.out.println("1. Add chicken");
            System.out.println("2. Remove chicken");
            System.out.println("3. Next chicken");
            System.out.println("4. Reset iteration");
            System.out.println("5. Show coop");
            System.out.println("0. Exit");
            System.out.print("Choose an option: ");
            option = Integer.parseInt(scanner.nextLine());

            switch (option) {
                case 1:
                    System.out.print("Id: ");
                    int id = Integer.parseInt(scanner.nextLine());
                    System.out.print("Name: ");
                    String name = scanner.nextLine();
                    System.out.print("Color: ");
                    String color = scanner.nextLine();
                    System.out.print("Birth year (e.g. 2022): ");
                    int year = Integer.parseInt(scanner.nextLine());
                    System.out.print("Birth month (1-12): ");
                    int month = Integer.parseInt(scanner.nextLine());
                    System.out.print("Birth day (1-31): ");
                    int day = Integer.parseInt(scanner.nextLine());

                    // month - 1 because GregorianCalendar starts months at 0
                    Chicken chicken = new Chicken(id, name, color,
                            new GregorianCalendar(year, month - 1, day).getTime(), false);
                    chicken.setAgeInYears();
                    coop.add(chicken);
                    break;
                case 2:
                    System.out.print("Id of the chicken to remove: ");
                    coop.remove(Integer.parseInt(scanner.nextLine()));
                    break;
                case 3:
                    Chicken next = coop.next();
                    if (next == null) {
                        System.out.println("No more chickens. Use option 4 to start again.");
                    } else {
                        System.out.println(next);
                        next.doStuff();
                    }
                    break;
                case 4:
                    coop.resetIteration();
                    System.out.println("Iteration reset.");
                    break;
                case 5:
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
}