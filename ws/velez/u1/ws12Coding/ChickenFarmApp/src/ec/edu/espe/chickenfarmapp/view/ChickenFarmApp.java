package ec.edu.espe.chickenfarmapp.view;

import ec.edu.espe.chickenfarmapp.model.Chicken;
import java.io.BufferedReader;
import java.io.BufferedWriter;
import java.io.File;
import java.io.FileReader;
import java.io.FileWriter;
import java.io.IOException;
import java.text.SimpleDateFormat;
import java.util.ArrayList;
import java.util.Calendar;
import java.util.Date;
import java.util.Scanner;
import java.util.regex.Matcher;
import java.util.regex.Pattern;

/**
 *
 * @author Yandry Velez, Codelex, @ESPE
 */
public class ChickenFarmApp {

    private static final String FILE_NAME = "chickens.json";
    private static final SimpleDateFormat DATE_FORMAT = new SimpleDateFormat("yyyy-MM-dd");

    public static void main(String args[]) {
        Scanner scanner = new Scanner(System.in);
        
        // 1. Load existing chickens from chickens.json on startup
        ArrayList<Chicken> chickens = loadChickensFromJson();
        
        // Calculate the next automatic ID based on loaded data
        int nextId = 1;
        for (Chicken c : chickens) {
            if (c.getIde() >= nextId) {
                nextId = c.getIde() + 1;
            }
        }

        int option = 0;

        System.out.println("==================================");
        System.out.println("    Yandry Velez Chicken Farm     ");
        System.out.println("==================================");
        System.out.println("[Info] Loaded " + chickens.size() + " chicken(s) from " + FILE_NAME);

        do {
            System.out.println("\n--- MAIN MENU ---");
            System.out.println("1. Add a new chicken");
            System.out.println("2. View all chickens (description and actions)");
            System.out.println("3. Delete a chicken");
            System.out.println("4. Exit");
            
            option = readInt(scanner, "Select an option (1-4): ", 1, 4);

            switch (option) {
                case 1:
                    String addAnother;
                    do {
                        System.out.println("\n--- Enter chicken details (Automatic ID assigned: " + nextId + ") ---");

                        System.out.print("Name: ");
                        String name = scanner.nextLine().trim();
                        while (name.isEmpty()) {
                            System.out.print("Name cannot be empty. Enter the name: ");
                            name = scanner.nextLine().trim();
                        }

                        System.out.print("Color: ");
                        String color = scanner.nextLine().trim();
                        while (color.isEmpty()) {
                            System.out.print("Color cannot be empty. Enter the color: ");
                            color = scanner.nextLine().trim();
                        }

                        int currentYear = Calendar.getInstance().get(Calendar.YEAR);
                        int year = readInt(scanner, "Birth year (2000-" + currentYear + "): ", 2000, currentYear);
                        int month = readInt(scanner, "Birth month (1-12): ", 1, 12);
                        int day = readInt(scanner, "Birth day (1-31): ", 1, 31);

                        Calendar cal = Calendar.getInstance();
                        cal.set(year, month - 1, day);
                        Date bornOnDate = cal.getTime();

                        System.out.print("Is it molting? (y/n): ");
                        String moltingInput = scanner.nextLine().trim().toLowerCase();
                        while (!moltingInput.equals("y") && !moltingInput.equals("yes") && 
                               !moltingInput.equals("n") && !moltingInput.equals("no")) {
                            System.out.print("Invalid input. Type 'y' for yes or 'n' for no: ");
                            moltingInput = scanner.nextLine().trim().toLowerCase();
                        }
                        boolean molting = moltingInput.equals("y") || moltingInput.equals("yes");

                        Chicken newChicken = new Chicken(nextId, name, color, bornOnDate, molting);
                        chickens.add(newChicken);
                        
                        // Save immediately to chickens.json
                        saveChickensToJson(chickens);

                        System.out.println("--> Chicken '" + name + "' registered with ID #" + nextId + " and saved to " + FILE_NAME + "!");
                        nextId++; 

                        System.out.print("\nDo you want to add another chicken right now? (y/n): ");
                        addAnother = scanner.nextLine().trim().toLowerCase();

                    } while (addAnother.equals("y") || addAnother.equals("yes"));
                    break;

                case 2:
                    System.out.println("\n================ CHICKEN LIST ================");
                    if (chickens.isEmpty()) {
                        System.out.println("There are no chickens registered on the farm yet.");
                    } else {
                        for (Chicken c : chickens) {
                            System.out.println("\nChicken ID #" + c.getIde() + ":");
                            System.out.println("  -> Object (toString): " + c);
                            System.out.println("  -> Summary: Name: " + c.getName() + " | Color: " + c.getColor() +
                                               " | Age: " + c.getAgeInYears() + " years | Molting: " + (c.isMolting() ? "Yes" : "No"));
                            System.out.println("  -> What is it doing?");
                            c.doStuff();
                            System.out.println("-------------------------------------------------");
                        }
                    }
                    break;

                case 3:
                    System.out.println("\n================ DELETE A CHICKEN ================");
                    if (chickens.isEmpty()) {
                        System.out.println("There are no chickens available to delete.");
                    } else {
                        System.out.println("Current chickens:");
                        for (Chicken c : chickens) {
                            System.out.println("  - ID #" + c.getIde() + ": " + c.getName() + " (" + c.getColor() + ")");
                        }
                        
                        int idToDelete = readInt(scanner, "Enter the ID of the chicken you want to delete: ", 1, Integer.MAX_VALUE);
                        Chicken chickenToRemove = null;
                        
                        for (Chicken c : chickens) {
                            if (c.getIde() == idToDelete) {
                                chickenToRemove = c;
                                break;
                            }
                        }

                        if (chickenToRemove != null) {
                            chickens.remove(chickenToRemove);
                            // Update chickens.json after deletion
                            saveChickensToJson(chickens);
                            System.out.println("--> Chicken '" + chickenToRemove.getName() + "' (ID #" + idToDelete + ") was deleted from " + FILE_NAME + "!");
                        } else {
                            System.out.println("--> Error: No chicken found with ID #" + idToDelete + ".");
                        }
                    }
                    break;

                case 4:
                    saveChickensToJson(chickens);
                    System.out.println("All data saved to " + FILE_NAME + ". Exiting the farm simulator... Goodbye!");
                    break;
            }
        } while (option != 4);

        scanner.close();
    }

    private static void saveChickensToJson(ArrayList<Chicken> chickens) {
        try (BufferedWriter writer = new BufferedWriter(new FileWriter(FILE_NAME))) {
            writer.write("[\n");
            for (int i = 0; i < chickens.size(); i++) {
                Chicken c = chickens.get(i);
                String dateStr = DATE_FORMAT.format(c.getBornOnDate());
                writer.write("  {\n");
                writer.write("    \"ide\": " + c.getIde() + ",\n");
                writer.write("    \"name\": \"" + escapeJson(c.getName()) + "\",\n");
                writer.write("    \"color\": \"" + escapeJson(c.getColor()) + "\",\n");
                writer.write("    \"bornOnDate\": \"" + dateStr + "\",\n");
                writer.write("    \"ageInYears\": " + c.getAgeInYears() + ",\n");
                writer.write("    \"molting\": " + c.isMolting() + "\n");
                writer.write("  }" + (i < chickens.size() - 1 ? "," : "") + "\n");
            }
            writer.write("]\n");
        } catch (IOException e) {
            System.out.println("Error saving to JSON file: " + e.getMessage());
        }
    }

    private static ArrayList<Chicken> loadChickensFromJson() {
        ArrayList<Chicken> list = new ArrayList<>();
        File file = new File(FILE_NAME);
        if (!file.exists()) {
            return list;
        }

        StringBuilder jsonContent = new StringBuilder();
        try (BufferedReader reader = new BufferedReader(new FileReader(file))) {
            String line;
            while ((line = reader.readLine()) != null) {
                jsonContent.append(line).append("\n");
            }
        } catch (IOException e) {
            System.out.println("Error reading JSON file: " + e.getMessage());
            return list;
        }

        Pattern objectPattern = Pattern.compile("\\{([^\\}]*)\\}");
        Matcher matcher = objectPattern.matcher(jsonContent.toString());

        while (matcher.find()) {
            String objBlock = matcher.group(1);
            try {
                int ide = Integer.parseInt(extractValue(objBlock, "ide"));
                String name = extractValue(objBlock, "name");
                String color = extractValue(objBlock, "color");
                String bornOnDateStr = extractValue(objBlock, "bornOnDate");
                boolean molting = Boolean.parseBoolean(extractValue(objBlock, "molting"));

                Date bornOnDate = DATE_FORMAT.parse(bornOnDateStr);
                list.add(new Chicken(ide, name, color, bornOnDate, molting));
            } catch (Exception e) {
                // Skip malformed entry
            }
        }
        return list;
    }

    private static String extractValue(String block, String key) {
        Pattern p = Pattern.compile("\"" + key + "\"\\s*:\\s*(\"([^\"]*)\"|([^,\\n\\r\\}]+))");
        Matcher m = p.matcher(block);
        if (m.find()) {
            return m.group(2) != null ? m.group(2).trim() : m.group(3).trim();
        }
        return "";
    }

    private static String escapeJson(String text) {
        return text.replace("\\", "\\\\").replace("\"", "\\\"");
    }

    private static int readInt(Scanner scanner, String message, int min, int max) {
        int value;
        while (true) {
            System.out.print(message);
            try {
                value = Integer.parseInt(scanner.nextLine().trim());
                if (value >= min && value <= max) {
                    return value;
                }
                System.out.println("Error: Please enter a number between " + min + " and " + max + ".");
            } catch (NumberFormatException e) {
                System.out.println("Error: Invalid input. You must enter an integer number.");
            }
        }
    }
}