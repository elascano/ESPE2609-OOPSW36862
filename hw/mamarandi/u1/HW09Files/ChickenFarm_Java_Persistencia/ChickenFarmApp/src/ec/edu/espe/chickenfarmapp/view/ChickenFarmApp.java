/*
 * Click nbfs://nbhost/SystemFileSystem/Templates/Licenses/license-default.txt to change this license
 * Click nbfs://nbhost/SystemFileSystem/Templates/Classes/Class.java to edit this template
 */
package ec.edu.espe.chickenfarmapp.view;

import ec.edu.espe.chickenfarmapp.model.Chicken;
import ec.edu.espe.chickenfarmapp.model.ChickenCoop;
import ec.edu.espe.chickenfarmapp.model.ChickenStorage;
import ec.edu.espe.chickenfarmapp.model.Egg;
import ec.edu.espe.chickenfarmapp.model.Poop;
import java.util.Date;

/**
 *
 * @author Marcos Mamarandi, Binary Bosses,@ESPE
 */

import java.util.Date;
import java.util.Scanner;
import java.util.Random;

public class ChickenFarmApp {
    public static void main(String args[]){
        System.out.println("Marcos Mamarandi Chicken Farm App");
        System.out.println("=================================");
        
        
        Chicken chicken;
        Egg egg;
        Poop poop;
        Chicken chicken1;
        ChickenCoop coop;
        Chicken chickens[];
        chickens = new Chicken[10];

        chicken = new Chicken(2, "Maruja", "white", new Date(1970, 11, 17), false);
        chicken.setAgeInYears();
        egg = new Egg(1, 'M');

        System.out.println("my chicken is --> " + chicken);
        System.out.println("egg --> " + egg);

        System.out.println("egg size --> " + egg.getSize());

        poop = new Poop(1, "brown", 'S');
        System.out.println("poop --> " + poop);

        chicken1 = new Chicken(1, "Lucy", "brown and white", new Date(1970, 12, 17),true);
        System.out.println("chicken 1 --> " + chicken1);
         chicken1.setAgeInYears();
        int localAge = chicken.computeAgeInYears();
        System.out.println("the age of the chicken --> " + chicken.getName() + ", is --> " + localAge);
        chicken.doStuff();
        
        chickens[0] = new Chicken(3, "Pepita", "black", new Date(2020, 2, 2), true);
        chickens[1] = chicken; 
        
        for (int i = 0; i<10; i++){
            System.out.println("chickens -->" + chickens[i]);
        }
        
        int ageForTheLoop = 0; 
        
        for (int i = 0; i<chickens.length; i++){
            ageForTheLoop = 2010 + i;
            chickens[i] = new Chicken(i, "chiken" + (i+1),"white", new Date(ageForTheLoop,3,3), true);
            chickens[i].setAgeInYears();
        }
        for (int i = 0; i<10; i++){
            System.out.println("chikens --> " + chickens[i]);
        }

       

        Chicken[] coopChickens = new Chicken[10];
        ChickenCoop chickenCoop = new ChickenCoop(1, coopChickens);
        System.out.println("Loaded " + ChickenStorage.load(coopChickens) + " chickens from file");

        Scanner scanner = new Scanner(System.in);
        Random random = new Random();
        int option;

        do {
            System.out.println("\n===== CHICKEN COOP MENU =====");
            System.out.println("1. Add a chicken");
            System.out.println("2. Remove a chicken");
            System.out.println("3. Show chickens");
            System.out.println("4. Exit");
            System.out.print("Choose an option: ");

            option = scanner.nextInt();
            scanner.nextLine();

            switch (option) {
                case 1:
                    int id;
                    boolean repeated;

                    do {
                        id = random.nextInt(9000) + 1000;
                        repeated = false;

                        for (Chicken c : coopChickens) {
                            if (c != null && c.getId() == id) {
                                repeated = true;
                                break;
                            }
                        }
                    } while (repeated);

                    System.out.print("Enter chicken name: ");
                    String name = scanner.nextLine();

                    System.out.print("Enter chicken color: ");
                    String color = scanner.nextLine();

                    System.out.print("Enter birth year: ");
                    int year = scanner.nextInt();

                    System.out.print("Enter birth month (1-12): ");
                    int month = scanner.nextInt();

                    System.out.print("Enter birth day: ");
                    int day = scanner.nextInt();
                    scanner.nextLine();

                    System.out.print("Is the chicken molting? (true/false): ");
                    boolean molting = scanner.nextBoolean();
                    scanner.nextLine();

                    Chicken newChicken = new Chicken(
                            id,
                            name,
                            color,
                            new Date(year - 1900, month - 1, day),
                            molting
                    );

                    newChicken.setAgeInYears();
                    chickenCoop.add(newChicken);
                    ChickenStorage.save(coopChickens);

                    System.out.println("Generated chicken ID: " + id);
                    break;

                case 2:
                    System.out.print("Enter the ID of the chicken to remove: ");
                    int chickenId = scanner.nextInt();
                    scanner.nextLine();

                    chickenCoop.remove(chickenId);
                    ChickenStorage.save(coopChickens);
                    break;

                case 3:
                    chickenCoop.resetIteration();

                    Chicken nextChicken;

                    while ((nextChicken = chickenCoop.next()) != null) {
                        System.out.println(nextChicken);
                    }
                    break;

                case 4:
                    System.out.println("Exiting Chicken Coop...");
                    break;

                default:
                    System.out.println("Invalid option");
            }

        } while (option != 4);

        scanner.close();
    }

    private static Date newDate() {
        return new Date();
    }
}

