package ec.edu.espe.chickenfarmapp.view;
 
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
    
    //counters to generate the ids automatically
    static int nextChickenId = 1;
    static int nextCoopId = 1;
    
    public static void main(String args[]){
        System.out.println("Matias Paucar's Chicken Farm App");
        System.out.println("#################################");
        
        ChickenCoop coop; //declaration
        
        int coopId = nextCoopId;
        nextCoopId++;
        coop = new ChickenCoop(coopId); //instantiation
        System.out.println("Chicken coop id: " + coopId);
        
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
                    addChickens(coop);
                    break;
                case 2:
                    removeChickens(coop);
                    break;
                case 3:
                    showChickens(coop);
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
    public static void addChickens(ChickenCoop coop) {
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
            coop.add(chicken);
        }
    }
    
    //option 2: the customer says how many chickens to remove
    public static void removeChickens(ChickenCoop coop) {
        if (coop.getNumberOfChickens() == 0) {
            System.out.println("The coop is empty, there are no chickens to remove");
            return;
        }
        
        showChickens(coop);
        
        int quantityToRemove;
        do {
            quantityToRemove = readInt("How many chickens do you want to remove? (1-" + coop.getNumberOfChickens() + "): ");
            if (quantityToRemove < 1 || quantityToRemove > coop.getNumberOfChickens()) {
                System.out.println("Invalid quantity, it must be between 1 and " + coop.getNumberOfChickens());
            }
        } while (quantityToRemove < 1 || quantityToRemove > coop.getNumberOfChickens());
        
        for (int i = 0; i < quantityToRemove; i++) {
            int idToRemove = readInt("Enter the id of the chicken to remove: ");
            coop.remove(idToRemove);
        }
    }
    
    //option 3: shows all the chickens of the coop
    public static void showChickens(ChickenCoop coop) {
        if (coop.getNumberOfChickens() == 0) {
            System.out.println("The coop is empty");
            return;
        }
        
        System.out.println("\n=== Chickens in the coop ===");
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
        
        System.out.print("Name: ");
        String name = scanner.nextLine();
        
        System.out.print("Color: ");
        String color = scanner.nextLine();
        
        int year = readInt("Year of birth (example 2024): ");
        int month = readInt("Month of birth (1-12): ");
        int day = readInt("Day of birth: ");
        
        System.out.print("Is it molting? (true/false): ");
        boolean molting = Boolean.parseBoolean(scanner.nextLine());
        
        //month - 1 because in Date the months start in 0
        return new Chicken(id, name, color, new Date(year, month - 1, day), molting);
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