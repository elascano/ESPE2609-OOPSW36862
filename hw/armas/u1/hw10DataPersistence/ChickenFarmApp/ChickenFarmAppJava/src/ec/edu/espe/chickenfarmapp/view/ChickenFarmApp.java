package ec.edu.espe.chickenfarmapp.view;

import ec.edu.espe.chickenfarmapp.model.Chicken;
import ec.edu.espe.chickenfarmapp.model.ChickenCoop;
import ec.edu.espe.chickenfarmapp.model.Egg;
import ec.edu.espe.chickenfarmapp.model.Poop;

import java.io.File;
import java.io.FileWriter;
import java.io.IOException;
import java.nio.file.Files;
import java.nio.file.Paths;
import java.text.SimpleDateFormat;
import java.util.Date;
import java.util.Scanner;

public class ChickenFarmApp {

    private static final Scanner scanner = new Scanner(System.in);
    private static final String FILE_NAME = "chickens.json";
    private static final SimpleDateFormat DATE_FORMAT = new SimpleDateFormat("yyyy-MM-dd");

    public static void main(String args[]) {
        System.out.println("Demian Armas's Chicken Farm App");
        System.out.println("===============================");

        ChickenCoop coop = new ChickenCoop(1);
        loadCoopFromJson(coop);

        int option;

        do {
            printMenu();
            option = readInt("Choose an option: ", 0, 6);
            System.out.println();

            switch (option) {
                case 1:
                    addChicken(coop);
                    break;
                case 2:
                    removeChicken(coop);
                    break;
                case 3:
                    coop.next();
                    break;
                case 4:
                    coop.resetIteration();
                    break;
                case 5:
                    System.out.println(coop);
                    break;
                case 6:
                    runDemo();
                    break;
                case 0:
                    saveCoopToJson(coop);
                    System.out.println("Exiting and saving data...");
                    break;
            }
            System.out.println();
        } while (option != 0);
    }

    private static void loadCoopFromJson(ChickenCoop coop) {
        File file = new File(FILE_NAME);
        if (!file.exists()) {
            return;
        }

        try {
            String content = new String(Files.readAllBytes(Paths.get(FILE_NAME))).trim();
            if (content.startsWith("[") && content.endsWith("]")) {
                content = content.substring(1, content.length() - 1).trim();
                if (content.isEmpty()) {
                    return;
                }
                String[] objects = content.split("\\},\\s*\\{");
                for (String obj : objects) {
                    obj = obj.replace("{", "").replace("}", "");
                    int id = 0;
                    String name = "";
                    String color = "";
                    Date bornOnDate = new Date();
                    boolean molting = false;

                    String[] pairs = obj.split(",");
                    for (String pair : pairs) {
                        String[] keyValue = pair.split(":");
                        if (keyValue.length < 2) continue;
                        String key = keyValue[0].replace("\"", "").trim();
                        String value = keyValue[1].replace("\"", "").trim();

                        switch (key) {
                            case "id":
                                id = Integer.parseInt(value);
                                break;
                            case "name":
                                name = value;
                                break;
                            case "color":
                                color = value;
                                break;
                            case "bornOnDate":
                                bornOnDate = DATE_FORMAT.parse(value);
                                break;
                            case "molting":
                                molting = Boolean.parseBoolean(value);
                                break;
                        }
                    }

                    Chicken chicken = new Chicken(id, name, color, bornOnDate);
                    chicken.setMolting(molting);
                    chicken.setAgeInYears();
                    coop.add(chicken);
                }
                System.out.println("--> Data loaded successfully from " + FILE_NAME);
            }
        } catch (Exception e) {
            System.out.println("Error loading data from JSON: " + e.getMessage());
        }
    }

    private static void saveCoopToJson(ChickenCoop coop) {
        StringBuilder jsonBuilder = new StringBuilder();
        jsonBuilder.append("[\n");

        boolean first = true;
        for (Chicken chicken : coop.getChickens()) {
            if (chicken != null) {
                if (!first) {
                    jsonBuilder.append(",\n");
                }
                jsonBuilder.append("  {\n");
                jsonBuilder.append("    \"id\": ").append(chicken.getId()).append(",\n");
                jsonBuilder.append("    \"name\": \"").append(chicken.getName()).append("\",\n");
                jsonBuilder.append("    \"color\": \"").append(chicken.getColor()).append("\",\n");
                jsonBuilder.append("    \"bornOnDate\": \"").append(DATE_FORMAT.format(chicken.getBornOnDate())).append("\",\n");
                jsonBuilder.append("    \"ageInYears\": ").append(chicken.getAgeInYears()).append(",\n");
                jsonBuilder.append("    \"molting\": ").append(chicken.isMolting()).append("\n");
                jsonBuilder.append("  }");
                first = false;
            }
        }
        jsonBuilder.append("\n]");

        try (FileWriter fileWriter = new FileWriter(FILE_NAME)) {
            fileWriter.write(jsonBuilder.toString());
        } catch (IOException e) {
            System.out.println("Error saving data to JSON: " + e.getMessage());
        }
    }

    private static void printMenu() {
        System.out.println("---------- MENU ----------");
        System.out.println("1. Add chicken");
        System.out.println("2. Remove chicken");
        System.out.println("3. Next chicken");
        System.out.println("4. Reset iteration");
        System.out.println("5. Show coop");
        System.out.println("6. Run demo");
        System.out.println("0. Exit");
    }

    private static boolean readBoolean(String message) {
    while (true) {
        System.out.print(message);
        String input = scanner.nextLine().trim().toLowerCase();
        if (input.equals("true") || input.equals("yes") || input.equals("y") || input.equals("s") || input.equals("si")) {
            return true;
        } else if (input.equals("false") || input.equals("no") || input.equals("n")) {
            return false;
        }
        System.out.println("Invalid input. Please enter 'yes'/'no' or 'true'/'false'.");
    }
}
    
    private static void addChicken(ChickenCoop coop) {
    int id = readInt("Id: ", 0, Integer.MAX_VALUE);
    System.out.print("Name: ");
    String name = scanner.nextLine().trim();
    System.out.print("Color: ");
    String color = scanner.nextLine().trim();

    System.out.println("Birth date:");
    int day = readInt("  Day (1-31): ", 1, 31);
    int month = readInt("  Month (1-12): ", 1, 12);
    int year = readInt("  Year (e.g. 2022): ", 1900, 2100);

    boolean molting = readBoolean("Is molting? (yes/no): ");

    Date bornOnDate = new Date(year - 1900, month - 1, day);

    Chicken chicken = new Chicken(id, name, color, bornOnDate);
    chicken.setMolting(molting);
    chicken.setAgeInYears();

    if (coop.add(chicken)) {
        saveCoopToJson(coop);
    }
}

    private static void removeChicken(ChickenCoop coop) {
        int id = readInt("Id of the chicken to remove: ", 0, Integer.MAX_VALUE);
        if (coop.remove(id)) {
            saveCoopToJson(coop);
        }
    }

    private static int readInt(String message, int min, int max) {
        while (true) {
            System.out.print(message);
            try {
                int value = Integer.parseInt(scanner.nextLine().trim());
                if (value >= min && value <= max) {
                    return value;
                }
                System.out.println("Enter a number between " + min + " and " + max + ".");
            } catch (NumberFormatException e) {
                System.out.println("Invalid number. Try again.");
            }
        }
    }

    private static void runDemo() {
        Chicken chicken;
        Chicken chicken1;
        Egg egg;
        Poop poop;
        Chicken chickens[] = new Chicken[10];

        chicken = new Chicken(1, "Lucy", "brown and withe", new Date(1970 - 1900, 11, 17));
        chicken.setAgeInYears();
        egg = new Egg(1, 'M');
        poop = new Poop(1, "brown", 'S');
        chicken1 = new Chicken(2, "Renata", "white", new Date(1970 - 1900, 10, 17));
        chicken1.setAgeInYears();

        System.out.println("chicken -->" + chicken);
        System.out.println("egg -->" + egg);
        System.out.println("poop -->" + poop);
        System.out.println("chicken 2-->" + chicken1);
        System.out.println("The age of the chicken --> " + chicken.getName() + " is " + chicken.computeAgeInYears());

        chicken.doStuff();
        chicken1.doStuff();

        chickens[0] = new Chicken(3, "Pepita", "black", new Date(2020 - 1900, 1, 2));
        chickens[1] = chicken;
        chickens[2] = chicken1;

        for (int i = 0; i < 10; i++) {
            System.out.println("chickens -->" + chickens[i]);
        }

        int ageForTheLoop = 0;
        for (int i = 0; i < chickens.length; i++) {
            ageForTheLoop = 2010 + i;
            chickens[i] = new Chicken(i, "chicken " + (i + 1), "white ", new Date(ageForTheLoop - 1900, 0, 1));
            chickens[i].setAgeInYears();
        }

        for (int i = 0; i < 10; i++) {
            System.out.println("chickens --> " + chickens[i]);
        }
    }
}