package ec.edu.espe.chickenfarmapp.view;

import ec.edu.espe.chickenfarmapp.model.Chicken;
import ec.edu.espe.chickenfarmapp.model.ChickenCoop;
import java.util.Scanner;

/**
 *
 * @author Francisco Demera, Byte and Fabric Devs, @ESPE
 */
public class ChickenFarmApp {

    public static void main(String args[]) {
        var scanner = new Scanner(System.in);
        Chicken[] chickens = new Chicken[100];
        ChickenCoop coop = new ChickenCoop(1, chickens);
        int option;
        do {
            System.out.println("Hello, Francisco Demera's Chicken Farm App");
            System.out.println("------------------------------------------");
            System.out.println("-----------------Welcome -----------------");
            System.out.println("----------What you will do today----------");
            System.out.println("1. Add chicken");
            System.out.println("2. Save Chicken Coop");
            System.out.println("3. Remove chicken");
            System.out.println("4. Show Chicken Coop");
            System.out.println("5. Exit");
            System.out.println("Press any Option----->");
            
            option = Integer.parseInt(scanner.nextLine());
            switch (option) {
                case 1 ->
                    coop.add(chickens, scanner);
                case 2 ->
                    coop.saveChickenCoop(chickens, scanner);
                case 3 ->
                    coop.remove(chickens, scanner);
                case 4 ->
                    coop.showChickenCoop(chickens);
                case 5 ->
                    System.out.println("Exiting Chicken Farm App...");
                default ->
                    System.out.println("Invalid option. Try again.");
            }
        } while (option != 5);

    }
}
