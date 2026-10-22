package ec.edu.espe.chickenfarmapp.model;

import java.io.FileWriter;
import java.io.IOException;
import java.util.Scanner;

/**
 *
 * @author Dennis Criollo, Byte and Fabric Devs, @ESPE
 */
public class ChickenCoop {

    private int chickenCoopId;
    private Chicken chickens[] = new Chicken[100];
    private int currentIndex;

    public ChickenCoop() {
        this.chickenCoopId = 1;
        this.chickens = new Chicken[10];
        this.currentIndex = 0;
    }

    public ChickenCoop(int chickenCoopId, Chicken[] chickens) {
        this.chickenCoopId = chickenCoopId;
        this.chickens = chickens;
        this.currentIndex = 0;
    }

    public void add(Chicken[] chickens1, Scanner scanner) {
        int position = -1;
        for (int i = 0; i < chickens.length; i++) {
            if (chickens[i] == null) {
                position = i;
                break;
            }
        }

        if (position == -1) {
            System.out.println("The chicken coop is full.");
            return;
        }

        System.out.println("\n--- ADD CHICKEN ---");
        System.out.print("Enter chicken ID: ");
        int id = Integer.parseInt(scanner.nextLine());
        System.out.print("Enter chicken name: ");
        String name = scanner.nextLine();
        System.out.print("Enter chicken color: ");
        String color = scanner.nextLine();
        System.out.print("Enter birth date (YYYY-MM-DD): ");
        java.sql.Date bornOnDate = java.sql.Date.valueOf(scanner.nextLine());
        System.out.print("Is the chicken molting? (true/false): ");
        boolean molting = Boolean.parseBoolean(scanner.nextLine());

        Chicken chicken = new Chicken(id, name, color, bornOnDate, molting);
        chickens[position] = chicken;
        System.out.println("Chicken added successfully!");
    }

    public void remove(Chicken[] chickens1, Scanner scanner) {
        System.out.print("Enter chicken ID to remove: ");
        int chickenId = Integer.parseInt(scanner.nextLine());

        for (int i = 0; i < chickens.length; i++) {
            if (chickens[i] != null&& chickens[i].getId() == chickenId) {

                System.out.println("Removing chicken: " + chickens[i].getName());
                chickens[i] = null;
                System.out.println("Chicken removed successfully!");
                return;
            }
        }
        System.out.println("Chicken not found.");
    }

    public void saveChickenCoop(Chicken[] chickens1, Scanner scanner) {
        try (FileWriter writer = new FileWriter("chickenCoop.txt")) {
            writer.write(this.toString());
            System.out.println("Chicken Coop saved successfully!");
        } catch (IOException e) {
            System.out.println("Error saving Chicken Coop: " + e.getMessage());
        }
    }

    public void showChickenCoop(Chicken[] chickens) {
        System.out.println("\n===== CHICKEN COOP =====");
        System.out.println("Coop ID: " + chickenCoopId);
        boolean found = false;
        for (Chicken chicken : chickens) {
            if (chicken != null) {
                System.out.println(chicken);
                found = true;
            }
        }
        if (!found) {
            System.out.println("There are no chickens in the coop.");
        }
    }

    public void resetIteration() {
        currentIndex = 0;
    }

    public Chicken next() {
        while (currentIndex < chickens.length) {
            Chicken chicken = chickens[currentIndex];
            currentIndex++;
            if (chicken != null) {
                return chicken;
            }
        }
        return null;
    }

    @Override
    public String toString() {
        String result = "ChickenCoop{" + "chickenCoopId=" + chickenCoopId + ", chickens=[\n";
        for (Chicken chicken : chickens) {
            if (chicken != null) {
                result += "    " + chicken.toString().replace("\n", "") + "\n";
            }
        }
        result += "]}";
        return result;
    }

    /**
     * @return the chickenCoopId
     */
    public int getChickenCoopId() {
        return chickenCoopId;
    }

    /**
     * @param chickenCoopId the chickenCoopId to set
     */
    public void setChickenCoopId(int chickenCoopId) {
        this.chickenCoopId = chickenCoopId;
    }

    /**
     * @return the chickens
     */
    public Chicken[] getChickens() {
        return chickens;
    }

    /**
     * @param chickens the chickens to set
     */
    public void setChickens(Chicken[] chickens) {
        this.chickens = chickens;
    }
}
