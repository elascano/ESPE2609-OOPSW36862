package ec.edu.espe.chickenfarmapp.view;

import ec.edu.espe.chickenfarmapp.controller.JsonManager;
import ec.edu.espe.chickenfarmapp.model.Chicken;
import ec.edu.espe.chickenfarmapp.model.ChickenCoop;
import ec.edu.espe.chickenfarmapp.model.ChickenFarmer;

import java.text.ParseException;
import java.text.SimpleDateFormat;
import java.util.ArrayList;
import java.util.Calendar;
import java.util.Date;
import java.util.Scanner;

/**
 * @author Josselyn Ulcuango, Codelex, @ESPE
 */
public class ChickenFarmApp {
    public static void main(String args[]) {
        Scanner scanner = new Scanner(System.in);
        SimpleDateFormat dateFormat = new SimpleDateFormat("yyyy-MM-dd");
        dateFormat.setLenient(false);
        
        System.out.println("Josselyn Ulcuango's Chicken Farm App");
        System.out.println("=====================================");
        
        ChickenFarmer farmer = new ChickenFarmer("Josselyn");

        ArrayList<ChickenCoop> loadedCoops = JsonManager.loadCoops();
        if (loadedCoops != null && !loadedCoops.isEmpty()) {
            farmer.setCoops(loadedCoops);
        } else {
            ChickenCoop defaultCoop = new ChickenCoop(1);
            farmer.addCoop(defaultCoop);
        }

        int option = 0;
        do {
            System.out.println("\n--- CHICKEN FARM MENU ---");
            System.out.println("1. Add chickens");
            System.out.println("2. Show chickens");
            System.out.println("3. Remove chicken");
            System.out.println("4. Exit");
            System.out.print("Select an option: ");

            while (!scanner.hasNextInt()) {
                System.out.println("Please enter a valid number.");
                scanner.next();
            }
            option = scanner.nextInt();
            scanner.nextLine(); 

            switch (option) {
                case 1:
                    System.out.print("How many chickens do you want to add?: ");
                    while (!scanner.hasNextInt()) {
                        System.out.println("Please enter a valid number.");
                        scanner.next();
                    }
                    int amount = scanner.nextInt();
                    scanner.nextLine(); 

                    ChickenCoop currentCoop = farmer.getCoops().get(0);

                    for (int i = 0; i < amount; i++) {
                        System.out.println("\n--- Chicken #" + (i + 1) + " Data ---");
                        
                        int generatedId = 1;
                        for (ChickenCoop coop : farmer.getCoops()) {
                            for (Chicken c : coop.getChickens()) {
                                if (c.getId() >= generatedId) {
                                    generatedId = c.getId() + 1;
                                }
                            }
                        }
                        System.out.println("Assigned ID: " + generatedId);
                        
                        System.out.print("Enter Name: ");
                        String name = scanner.nextLine();
                        
                        System.out.print("Enter Color: ");
                        String color = scanner.nextLine();
                        
                        Date bornOnDate = null;
                        while (bornOnDate == null) {
                            System.out.print("Enter Birth Date (yyyy-MM-dd): ");
                            String dateStr = scanner.nextLine();
                            try {
                                bornOnDate = dateFormat.parse(dateStr);
                            } catch (ParseException e) {
                                System.out.println("Invalid format. Please use yyyy-MM-dd.");
                            }
                        }
                        
                        int age = calculateAge(bornOnDate);
                        
                        System.out.print("Is molting? (true/false): ");
                        boolean isMolting = scanner.nextBoolean();
                        scanner.nextLine(); 

                        Chicken chicken = new Chicken(generatedId, name, color, bornOnDate, age, isMolting);
                        currentCoop.addChicken(chicken);
                        
                        System.out.println("Chicken '" + name + "' added successfully!");
                    }

                    JsonManager.saveCoops(farmer.getCoops()); 
                    System.out.println("\nAll chickens have been registered successfully!");
                    break;

                case 2:
                    System.out.println("\n--- List of Chickens in the Farm ---");
                    boolean hasChickens = false;
                    for (ChickenCoop coop : farmer.getCoops()) {
                        for (Chicken chicken : coop.getChickens()) {
                            System.out.println(chicken);
                            hasChickens = true;
                        }
                    }
                    if (!hasChickens) {
                        System.out.println("No chickens registered yet.");
                    }
                    break;

                case 3:
                    System.out.print("Enter the ID of the chicken you want to remove: ");
                    while (!scanner.hasNextInt()) {
                        System.out.println("Please enter a valid ID number.");
                        scanner.next();
                    }
                    int targetId = scanner.nextInt();
                    scanner.nextLine();

                    boolean removed = false;
                    for (ChickenCoop coop : farmer.getCoops()) {
                        ArrayList<Chicken> chickens = coop.getChickens();
                        for (int j = 0; j < chickens.size(); j++) {
                            if (chickens.get(j).getId() == targetId) {
                                chickens.remove(j);
                                removed = true;
                                break;
                            }
                        }
                        if (removed) {
                            break;
                        }
                    }

                    if (removed) {
                        JsonManager.saveCoops(farmer.getCoops());
                        System.out.println("Chicken with ID " + targetId + " has been successfully removed.");
                    } else {
                        System.out.println("Chicken with ID " + targetId + " was not found.");
                    }
                    break;

                case 4:
                    System.out.println("\nExiting Chicken Farm App. Goodbye, Josselyn!");
                    break;

                default:
                    System.out.println("Invalid option. Please choose between 1 and 4.");
                    break;
            }
        } while (option != 4);

        scanner.close();
    }

    private static int calculateAge(Date birthDate) {
        Calendar birth = Calendar.getInstance();
        birth.setTime(birthDate);
        Calendar today = Calendar.getInstance();
        
        int age = today.get(Calendar.YEAR) - birth.get(Calendar.YEAR);
        if (today.get(Calendar.DAY_OF_YEAR) < birth.get(Calendar.DAY_OF_YEAR)) {
            age--;
        }
        return Math.max(0, age);
    }
}