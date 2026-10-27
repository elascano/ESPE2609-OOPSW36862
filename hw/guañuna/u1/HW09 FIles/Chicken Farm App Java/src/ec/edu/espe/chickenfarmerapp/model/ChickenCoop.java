package ec.edu.espe.chickenfarmerapp.model;

import java.util.Arrays;
import java.util.Date;
import java.util.Scanner;

/**
 *
 * @author Cristopher Guañuna, Runtime Dynamic, @ESPE
 */
public class ChickenCoop {

    private int chickenCoopId;
    private Chicken[] chickens;
    private int currentIndex;
    private int iterationIndex;

    public ChickenCoop() {
        chickenCoopId = 1;
        chickens = new Chicken[10];

        chickens[0] = new Chicken(
                1, "Lucy", "black", new Date(), false
        );

        chickens[1] = new Chicken(
                2, "Alejandra", "white", new Date(), false
        );

        currentIndex = 2;
        iterationIndex = 0;
    }

    public ChickenCoop(int chickenCoopId, Chicken[] chickens) {
        this.chickenCoopId = chickenCoopId;
        this.chickens = new Chicken[10];
        currentIndex = 0;
        iterationIndex = 0;

        if (chickens != null) {
            for (Chicken chicken : chickens) {
                if (chicken != null && currentIndex < this.chickens.length) {
                    this.chickens[currentIndex] = chicken;
                    currentIndex++;
                }
            }
        }
    }

    public void add() {
        if (currentIndex >= chickens.length) {
            System.out.println("The chicken coop is full.");
            return;
        }

        Scanner scanner = new Scanner(System.in);

        int id = -1;
        boolean idValido = false;
        while (!idValido) {
            System.out.print("Enter chicken ID: ");
            String idStr = scanner.nextLine();
            try {
                id = Integer.parseInt(idStr);
                
                boolean existe = false;
                for (int i = 0; i < currentIndex; i++) {
                    if (chickens[i].getId() == id) {
                        existe = true;
                        break;
                    }
                }
                
                if (existe) {
                    System.out.println(">> Error: A chicken with this ID (" + id + ") already exists.");
                    System.out.println("--- Registered Chickens ---");
                    for (int i = 0; i < currentIndex; i++) {
                        System.out.println("ID: " + chickens[i].getId() + " | Name: " + chickens[i].getName());
                    }
                    System.out.println("---------------------------");
                    System.out.println("Please try another ID.");
                } else {
                    idValido = true;       
                }
            } catch (NumberFormatException e) {
                System.out.println(">> Error: ID must be a valid number.");
            }
        }

        String name = "";
        while (name.isEmpty()) {
            System.out.print("Enter chicken name: ");
            name = scanner.nextLine().trim();
            if (name.isEmpty()) {
                System.out.println(">> Error: Name cannot be empty.");
            }
        }

        String color = "";
        while (color.isEmpty()) {
            System.out.print("Enter chicken color: ");
            color = scanner.nextLine().trim();
            if (color.isEmpty()) {
                System.out.println(">> Error: Color cannot be empty.");
            }
        }

        boolean molting = false;
        boolean moltingValido = false;
        while (!moltingValido) {
            System.out.print("Is the chicken molting? (true/false): ");
            String moltingStr = scanner.nextLine().trim().toLowerCase();
            if (moltingStr.equals("true") || moltingStr.equals("false")) {
                molting = Boolean.parseBoolean(moltingStr);
                moltingValido = true;
            } else {
                System.out.println(">> Error: Please enter 'true' or 'false'.");
            }
        }

        System.out.println("\nEnter the birth date of the chicken:");
        int year = 0, month = 0, day = 0;

        while (true) {
            System.out.print("Year: ");
            String yearStr = scanner.nextLine();
            try {
                year = Integer.parseInt(yearStr);
                if (year > 2000 && year <= 2026) { 
                    break;
                } else {
                    System.out.println(">> Error: Please enter a realistic year (e.g., > 2000).");
                }
            } catch (NumberFormatException e) {
                System.out.println(">> Error: Year must be a valid number.");
            }
        }

        while (true) {
            System.out.print("Month (1-12): ");
            String monthStr = scanner.nextLine();
            try {
                month = Integer.parseInt(monthStr);
                if (month >= 1 && month <= 12) {
                    break;
                } else {
                    System.out.println(">> Error: Month must be between 1 and 12.");
                }
            } catch (NumberFormatException e) {
                System.out.println(">> Error: Month must be a valid number.");
            }
        }

        while (true) {
            System.out.print("Day (1-31): ");
            String dayStr = scanner.nextLine();
            try {
                day = Integer.parseInt(dayStr);
                if (day >= 1 && day <= 31) {
                    break;
                } else {
                    System.out.println(">> Error: Day must be between 1 and 31.");
                }
            } catch (NumberFormatException e) {
                System.out.println(">> Error: Day must be a valid number.");
            }
        }

        Date bornOnDate = new Date(year - 1900, month - 1, day);
        // ----------------------------------------

        Chicken chicken = new Chicken(id, name, color, bornOnDate, molting);
        chickens[currentIndex] = chicken;
        currentIndex++;

        System.out.println("Chicken added successfully.");
    }


public void remove() {
        if (currentIndex == 0) {
            System.out.println("There are no chickens to remove.");
            return;
        }

        Scanner scanner = new Scanner(System.in);

        System.out.println("\n=== CHICKENS IN THE COOP ===");
        for (int i = 0; i < currentIndex; i++) {
            System.out.println(chickens[i]);
        }
        System.out.println("============================");

        int idABuscar = -1;
        boolean idValido = false;
        
        while (!idValido) {
            System.out.print("Enter the ID of the chicken to remove: ");
            String entrada = scanner.nextLine().trim();
            try {
                idABuscar = Integer.parseInt(entrada);
                idValido = true; // Si es un número, salimos del bucle
            } catch (NumberFormatException e) {
                System.out.println(">> Error: The ID must be a valid number.");
            }
        }

        int position = -1; 
        for (int i = 0; i < currentIndex; i++) {
            if (chickens[i].getId() == idABuscar) {
                position = i; // ¡La encontramos!
                break;
            }
        }

        if (position == -1) {
            System.out.println(">> Error: No chicken found with ID " + idABuscar + ".");
            return;
        }

        for (int i = position; i < currentIndex - 1; i++) {
            chickens[i] = chickens[i + 1];
        }

        chickens[currentIndex - 1] = null;
        currentIndex--;

        iterationIndex = 0;

        System.out.println("Chicken with ID " + idABuscar + " removed successfully.");
    }

    public void resetIteration() {
        iterationIndex = 0;
    }

    public Chicken next() {
        if (iterationIndex < currentIndex) {
            return chickens[iterationIndex++];
        }

        return null;
    }

    @Override
    public String toString() {
        return "ChickenCoop{"
                + "chickenCoopId=" + chickenCoopId
                + ", chickens="
                + Arrays.toString(Arrays.copyOf(chickens, currentIndex))
                + '}';
    }

    public int getChickenCoopId() {
        return chickenCoopId;
    }

    public void setChickenCoopId(int chickenCoopId) {
        this.chickenCoopId = chickenCoopId;
    }

    public Chicken[] getChickens() {
        return Arrays.copyOf(chickens, currentIndex);
    }

    public void setChickens(Chicken[] chickens) {
        this.chickens = new Chicken[10];
        currentIndex = 0;
        iterationIndex = 0;

        if (chickens != null) {
            for (Chicken chicken : chickens) {
                if (chicken != null && currentIndex < this.chickens.length) {
                    this.chickens[currentIndex] = chicken;
                    currentIndex++;
                }
            }
        }
    }
}