package ec.edu.espe.chickenfarmapp.view;

import ec.edu.espe.chickenfarmapp.model.Chicken;
import ec.edu.espe.chickenfarmapp.model.Egg;
import ec.edu.espe.chickenfarmapp.model.Poop;
import java.util.ArrayList;
import java.util.Date;
import java.util.Scanner;

/**
 * @author Josselyn Ulcuango, Codelex, @ESPE
 */
public class ChickenFarmApp {
    public static void main(String args[]) {
        Scanner scanner = new Scanner(System.in);
        ArrayList<Chicken> chickenList = new ArrayList<>();
        
        System.out.println("Josselyn Ulcuango's Chicken Farm App");
        System.out.println("=====================================");
        
        Chicken chicken = new Chicken(1, "Lucy", "brown and white", new Date(124, 8, 15), 0, true);
        Egg egg = new Egg(1, 'M');
        Poop poop = new Poop(1, "brown", 'S');
        Chicken chicken1 = new Chicken(1, "Renata", "white", new Date(124, 8, 15), 0, false);
        
        System.out.println("chicken --> " + chicken);
        System.out.println("egg --> " + egg);
        System.out.println("poop --> " + poop);
        System.out.println("chicken1 --> " + chicken1);
        
        chicken.doStuff();

        for (int i = 1; i <= 10; i++) {
            int birthYear = 2009 + i;
            Chicken c = new Chicken(i, "chicken " + i, "white", new Date(birthYear - 1900, 3, 3), 0, true);
            c.setAgeInYear(c.computeAgeInYears());
            chickenList.add(c);
        }

        int option = 0;
        int nextId = 11; 

        do {
            System.out.println("\n=====================================");
            System.out.println("         CHICKEN FARM MENU           ");
            System.out.println("=====================================");
            System.out.println("1. Add new chickens");
            System.out.println("2. View registered chickens and actions");
            System.out.println("3. Remove a chicken");
            System.out.println("4. Exit");
            System.out.print("Select an option: ");
            
            if (scanner.hasNextInt()) {
                option = scanner.nextInt();
                scanner.nextLine(); 
            } else {
                System.out.println("Please enter a valid number.");
                scanner.next();
                continue;
            }

            switch (option) {
                case 1:
                    System.out.print("\nHow many chickens do you want to add?: ");
                    if (scanner.hasNextInt()) {
                        int quantity = scanner.nextInt();
                        scanner.nextLine(); 
                        
                        for (int i = 0; i < quantity; i++) {
                            System.out.println("\n--- Registering Chicken " + (i + 1) + " of " + quantity + " ---");
                            System.out.print("Enter the chicken's name: ");
                            String name = scanner.nextLine();
                            
                            System.out.print("Enter the chicken's color: ");
                            String color = scanner.nextLine();
                            
                            System.out.print("Enter birth year (e.g., 2024): ");
                            int year = scanner.nextInt();
                            System.out.print("Enter birth month (1-12): ");
                            int month = scanner.nextInt();
                            System.out.print("Enter birth day (1-31): ");
                            int day = scanner.nextInt();
                            scanner.nextLine(); 
                            
                            Date bornDate = new Date(year - 1900, month - 1, day);
                            
                            System.out.print("Is it molting? (true/false): ");
                            boolean molting = scanner.nextBoolean();
                            scanner.nextLine(); 
                            
                            Chicken newChicken = new Chicken(nextId, name, color, bornDate, 0, molting);
                            newChicken.setAgeInYear(newChicken.computeAgeInYears());
                            chickenList.add(newChicken);
                            
                            System.out.println("Chicken '" + name + "' successfully added with ID: " + nextId + "!");
                            nextId++;
                        }
                    } else {
                        System.out.println("Invalid quantity.");
                        scanner.next();
                    }
                    break;

                case 2:
                    System.out.println("\n--- CURRENT CHICKENS LIST AND ACTIONS ---");
                    if (chickenList.isEmpty()) {
                        System.out.println("There are no chickens registered on the farm.");
                    } else {
                        for (int i = 0; i < chickenList.size(); i++) {
                            Chicken c = chickenList.get(i);
                            c.setAgeInYear(c.computeAgeInYears()); 
                            System.out.println("\n[Index " + i + "] chickens --> " + c);
                            System.out.println("--> Action of " + c.getName() + ":");
                            c.doStuff();
                        }
                        System.out.println("\nlength of chickens --> " + chickenList.size());
                    }
                    break;

                case 3:
                    System.out.println("\n--- REMOVE A CHICKEN ---");
                    if (chickenList.isEmpty()) {
                        System.out.println("There are no chickens available to remove.");
                    } else {
                        System.out.println("Chickens on the farm:");
                        for (int i = 0; i < chickenList.size(); i++) {
                            System.out.println("Index [" + i + "] -> Name: " + chickenList.get(i).getName() + " (ID: " + chickenList.get(i).getId() + ")");
                        }
                        System.out.print("Enter the index of the chicken you want to remove: ");
                        if (scanner.hasNextInt()) {
                            int index = scanner.nextInt();
                            scanner.nextLine();
                            
                            if (index >= 0 && index < chickenList.size()) {
                                Chicken removed = chickenList.remove(index);
                                System.out.println("Chicken '" + removed.getName() + "' has been successfully removed from the farm!");
                            } else {
                                System.out.println("Index out of range. Please try again.");
                            }
                        } else {
                            System.out.println("Invalid input.");
                            scanner.next();
                        }
                    }
                    break;

                case 4:
                    System.out.println("\nExiting Chicken Farm App. Goodbye, Josselyn!");
                    break;

                default:
                    System.out.println("Invalid option. Please choose between 1 and 4.");
            }
        } while (option != 4);
        
        scanner.close();
    }
}