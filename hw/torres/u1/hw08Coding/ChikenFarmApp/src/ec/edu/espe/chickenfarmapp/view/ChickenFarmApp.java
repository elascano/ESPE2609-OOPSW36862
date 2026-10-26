/*
 * Click nbfs://nbhost/SystemFileSystem/Templates/Licenses/license-default.txt to change this license
 * Click nbfs://nbhost/SystemFileSystem/Templates/Classes/Class.java to edit this template
 */
package ec.edu.espe.chickenfarmapp.view;

import ec.edu.espe.chickenfarmapp.model.Chicken;
import ec.edu.espe.chickenfarmapp.model.ChickenCoop;
import ec.edu.espe.chickenfarmapp.model.Egg;
import ec.edu.espe.chickenfarmapp.model.Poop;
import java.util.Date;
import java.util.Scanner;

/**
 *
 * @author Christopher Torres, CodeLex, @ESPE
 */
public class ChickenFarmApp {
    
    static Scanner scanner = new Scanner(System.in);
    
    //counters to generate the ids automatically
    static int nextChickenId = 1;
    static int nextEggId = 1;
    static int nextPoopId = 1;
    static int nextCoopId = 1;
    
    public static void main(String args[]){
        System.out.println("Ostin Torres' Chicken Farm App");
        System.out.println("==============================");
        
        Chicken chicken; //declaration
        Egg egg;
        Poop poop;
        ChickenCoop coop;
        
        //the coop
        int coopId = nextCoopId;
        nextCoopId++;
        coop = new ChickenCoop(coopId); //instantiation
        System.out.println("Chicken coop id: " + coopId);
        
        //the customer says how many chickens to add
        int quantityToAdd;
        do {
            System.out.print("\nHow many chickens do you want to add? (1-10): ");
            quantityToAdd = Integer.parseInt(scanner.nextLine());
            if (quantityToAdd < 1 || quantityToAdd > 10) {
                System.out.println("Invalid quantity, it must be between 1 and 10");
            }
        } while (quantityToAdd < 1 || quantityToAdd > 10);
        
        for (int i = 0; i < quantityToAdd; i++) {
            System.out.println("\n--- Chicken " + (i + 1) + " ---");
            chicken = readChicken();
            coop.add(chicken);
        }
        
        //the customer enters the data of the egg
        System.out.println("\n--- Egg ---");
        int eggId = nextEggId;
        nextEggId++;
        System.out.println("Egg id: " + eggId);
        System.out.print("Egg size (S/M/L): ");
        char eggSize = scanner.nextLine().toUpperCase().charAt(0);
        egg = new Egg(eggId, eggSize);
        
        //the customer enters the data of the poop
        System.out.println("\n--- Poop ---");
        int poopId = nextPoopId;
        nextPoopId++;
        System.out.println("Poop id: " + poopId);
        System.out.print("Poop color: ");
        String poopColor = scanner.nextLine();
        System.out.print("Poop size (S/M/L): ");
        char poopSize = scanner.nextLine().toUpperCase().charAt(0);
        poop = new Poop(poopId, poopColor, poopSize);
        
        System.out.println("\negg -->" + egg);
        System.out.println("poop -->" + poop);
        
        //we go through the coop
        System.out.println("\n=== Chickens in the coop ===");
        showChickens(coop, true);
        System.out.println("number of chickens --> " + coop.getNumberOfChickens());
        
        //the customer says how many chickens to remove
        int quantityToRemove;
        do {
            System.out.print("\nHow many chickens do you want to remove? (0-" + coop.getNumberOfChickens() + "): ");
            quantityToRemove = Integer.parseInt(scanner.nextLine());
            if (quantityToRemove < 0 || quantityToRemove > coop.getNumberOfChickens()) {
                System.out.println("Invalid quantity, it must be between 0 and " + coop.getNumberOfChickens());
            }
        } while (quantityToRemove < 0 || quantityToRemove > coop.getNumberOfChickens());
        
        for (int i = 0; i < quantityToRemove; i++) {
            System.out.print("Enter the id of the chicken to remove: ");
            int idToRemove = Integer.parseInt(scanner.nextLine());
            coop.remove(idToRemove);
        }
        
        System.out.println("\n=== Chickens in the coop after removing ===");
        showChickens(coop, false);
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
        
        System.out.print("Year of birth (example 2024): ");
        int year = Integer.parseInt(scanner.nextLine());
        
        System.out.print("Month of birth (1-12): ");
        int month = Integer.parseInt(scanner.nextLine());
        
        System.out.print("Day of birth: ");
        int day = Integer.parseInt(scanner.nextLine());
        
        System.out.print("Is it molting? (true/false): ");
        boolean molting = Boolean.parseBoolean(scanner.nextLine());
        
        //month - 1 because in Date the months start in 0
        return new Chicken(id, name, color, new Date(year, month - 1, day), molting);
    }
    
    //this method shows all the chickens of the coop
    public static void showChickens(ChickenCoop coop, boolean doStuff) {
        if (coop.getNumberOfChickens() == 0) {
            System.out.println("The coop is empty");
            return;
        }
        
        coop.resetIteration();
        while (coop.hasNext()) {
            Chicken current = coop.next();
            System.out.println("chickens --> " + current);
            System.out.println("Chicken's age: " + current.computeAgeInYears());
            if (doStuff) {
                current.doStuff();
            }
        }
    }
    
}