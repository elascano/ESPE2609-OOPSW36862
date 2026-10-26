package ec.edu.espe.chickenfarmapp.controler;

import ec.edu.espe.chickenfarmapp.model.Chicken;
import ec.edu.espe.chickenfarmapp.model.Poop;
import java.io.IOException;
import java.nio.charset.StandardCharsets;
import java.nio.file.Files;
import java.nio.file.Path;
import java.nio.file.Paths;
import java.nio.file.StandardOpenOption;
import java.text.ParseException;
import java.text.SimpleDateFormat;
import java.util.ArrayList;
import java.util.Date;
import java.util.List;

/**
 * Saves, deletes and loads chickens in a CSV file.
 *
 * @author Nacimba Mateo, Group5, @ESPE
 */
public class ChickenFiles {

    private static final String HEADER =
            "id,name,color,bornOnDate,ageInYears,molting,poopColor,poopConsistency";
    private static final String DATE_FORMAT = "dd/MM/yyyy";

    private final Path filePath;

    public ChickenFiles(String fileName) {
        this.filePath = Paths.get(fileName);
    }

    /**
     * Appends a chicken with all its information to the CSV file.
     * Creates the file (with header) if it does not exist yet.
     */
    public boolean add(Chicken chicken) {
        try {
            if (Files.notExists(filePath) || Files.size(filePath) == 0) {
                Files.write(filePath, (HEADER + System.lineSeparator()).getBytes(StandardCharsets.UTF_8),
                        StandardOpenOption.CREATE, StandardOpenOption.TRUNCATE_EXISTING);
            }
            String line = toCsvLine(chicken) + System.lineSeparator();
            Files.write(filePath, line.getBytes(StandardCharsets.UTF_8),
                    StandardOpenOption.CREATE, StandardOpenOption.APPEND);
            System.out.println("Chicken saved in file: " + filePath.toAbsolutePath());
            return true;
        } catch (IOException e) {
            System.out.println("Error saving chicken in file: " + e.getMessage());
            return false;
        }
    }

    /**
     * Removes the chicken with the given id from the CSV file.
     * @return true if the chicken was found and removed from the file.
     */
    public boolean delete(int chickenId) {
        if (Files.notExists(filePath)) {
            System.out.println("The file does not exist yet. Nothing to delete.");
            return false;
        }
        try {
            List<String> lines = Files.readAllLines(filePath, StandardCharsets.UTF_8);
            List<String> remaining = new ArrayList<>();
            boolean found = false;

            for (int i = 0; i < lines.size(); i++) {
                String line = lines.get(i);
                if (i == 0 || line.trim().isEmpty()) {
                    remaining.add(line); // header
                    continue;
                }
                List<String> fields = parseCsvLine(line);
                if (!fields.isEmpty() && fields.get(0).equals(String.valueOf(chickenId))) {
                    found = true;
                } else {
                    remaining.add(line);
                }
            }

            if (found) {
                Files.write(filePath, remaining, StandardCharsets.UTF_8);
                System.out.println("Chicken with ID " + chickenId + " deleted from file.");
            } else {
                System.out.println("Chicken with ID " + chickenId + " not found in file.");
            }
            return found;
        } catch (IOException e) {
            System.out.println("Error deleting chicken from file: " + e.getMessage());
            return false;
        }
    }

    /**
     * Loads all the chickens stored in the CSV file.
     */
    public List<Chicken> loadAll() {
        List<Chicken> chickens = new ArrayList<>();
        if (Files.notExists(filePath)) {
            return chickens;
        }
        try {
            List<String> lines = Files.readAllLines(filePath, StandardCharsets.UTF_8);
            for (int i = 1; i < lines.size(); i++) { // skip header
                String line = lines.get(i);
                if (line.trim().isEmpty()) {
                    continue;
                }
                try {
                    chickens.add(fromCsvLine(line));
                } catch (NumberFormatException | ParseException | IndexOutOfBoundsException e) {
                    System.out.println("Skipping invalid line " + (i + 1) + " in file.");
                }
            }
        } catch (IOException e) {
            System.out.println("Error reading file: " + e.getMessage());
        }
        return chickens;
    }

    // ------------------------- CSV helpers -------------------------

    private String toCsvLine(Chicken chicken) {
        SimpleDateFormat format = new SimpleDateFormat(DATE_FORMAT);
        Poop poop = chicken.getPoop();
        String poopColor = (poop == null) ? "" : poop.getColor();
        String poopConsistency = (poop == null) ? "" : String.valueOf(poop.getConsistency());

        return String.join(",",
                String.valueOf(chicken.getId()),
                escape(chicken.getName()),
                escape(chicken.getColor()),
                format.format(chicken.getBornOnDate()),
                String.valueOf(chicken.getAgeInYears()),
                String.valueOf(chicken.isMolting()),
                escape(poopColor),
                escape(poopConsistency));
    }

    private Chicken fromCsvLine(String line) throws ParseException {
        List<String> f = parseCsvLine(line);
        SimpleDateFormat format = new SimpleDateFormat(DATE_FORMAT);
        format.setLenient(false);

        int id = Integer.parseInt(f.get(0));
        String name = f.get(1);
        String color = f.get(2);
        Date bornOnDate = format.parse(f.get(3));
        boolean molting = Boolean.parseBoolean(f.get(5));

        Poop poop = null;
        if (f.size() > 7 && !f.get(6).isEmpty() && !f.get(7).isEmpty()) {
            poop = new Poop(id, f.get(6), f.get(7).charAt(0));
        }
        return new Chicken(id, name, color, bornOnDate, molting, poop);
    }

    private String escape(String value) {
        if (value.contains(",") || value.contains("\"") || value.contains("\n")) {
            return "\"" + value.replace("\"", "\"\"") + "\"";
        }
        return value;
    }

    private List<String> parseCsvLine(String line) {
        List<String> fields = new ArrayList<>();
        StringBuilder current = new StringBuilder();
        boolean inQuotes = false;

        for (int i = 0; i < line.length(); i++) {
            char c = line.charAt(i);
            if (inQuotes) {
                if (c == '"') {
                    if (i + 1 < line.length() && line.charAt(i + 1) == '"') {
                        current.append('"');
                        i++;
                    } else {
                        inQuotes = false;
                    }
                } else {
                    current.append(c);
                }
            } else if (c == '"') {
                inQuotes = true;
            } else if (c == ',') {
                fields.add(current.toString());
                current.setLength(0);
            } else {
                current.append(c);
            }
        }
        fields.add(current.toString());
        return fields;
    }
}