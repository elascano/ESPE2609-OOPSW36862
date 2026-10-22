package ec.edu.espe.chickenfarmapp.controller;

import ec.edu.espe.chickenfarmapp.model.Chicken;
import ec.edu.espe.chickenfarmapp.model.ChickenCoop;
import java.io.BufferedReader;
import java.io.File;
import java.io.FileReader;
import java.io.FileWriter;
import java.io.IOException;
import java.io.PrintWriter;
import java.util.Date;

/**
 * Persistence: saves the chickens of the coop in a text file and reads them again.
 * Every line of the file is one chicken, with its data separated by ";".
 *
 * @author Paucar Matias, ByteWorks, @ESPE
 */
public class FilesChicken {

    public static final String FILE_NAME = "chickens.csv";
    private static final String SEPARATOR = ";";
    private static final String HEADER = "id;name;color;yearOfBirth;monthOfBirth;dayOfBirth;molting";

    /**
     * Writes ALL the chickens of the coop in the file.
     * The file is created again every time, so it always has the current chickens:
     * the ones that were already there, the new ones, and without the removed ones.
     */
    public static void save(ChickenCoop coop) {
        try (PrintWriter writer = new PrintWriter(new FileWriter(FILE_NAME))) {
            writer.println(HEADER);

            coop.resetIteration();
            while (coop.hasNext()) {
                Chicken chicken = coop.next();
                Date bornOnDate = chicken.getBornOnDate();

                //we save the date as year, month and day (the same numbers the customer wrote)
                //getYear() gives the year we used in new Date(year, ...), getMonth() starts in 0
                writer.println(chicken.getId() + SEPARATOR
                        + clean(chicken.getName()) + SEPARATOR
                        + clean(chicken.getColor()) + SEPARATOR
                        + bornOnDate.getYear() + SEPARATOR
                        + (bornOnDate.getMonth() + 1) + SEPARATOR
                        + bornOnDate.getDate() + SEPARATOR
                        + chicken.isMolting());
            }
        } catch (IOException e) {
            System.out.println("Error: the file " + FILE_NAME + " could not be saved: " + e.getMessage());
        }
    }

    /**
     * Reads the file and returns a coop with the chickens that are saved there.
     * If the file does not exist yet, it returns an empty coop.
     */
    public static ChickenCoop load(int chickenCoopId) {
        ChickenCoop coop = new ChickenCoop(chickenCoopId);
        File file = new File(FILE_NAME);

        if (!file.exists()) {
            return coop;
        }

        try (BufferedReader reader = new BufferedReader(new FileReader(file))) {
            String line;
            int lineNumber = 0;

            while ((line = reader.readLine()) != null) {
                lineNumber++;

                //we skip the header and the empty lines
                if (lineNumber == 1 || line.isBlank()) {
                    continue;
                }

                Chicken chicken = toChicken(line);
                if (chicken == null) {
                    System.out.println("Warning: line " + lineNumber + " of " + FILE_NAME + " is not valid and was skipped");
                } else if (!coop.add(chicken)) {
                    System.out.println("Warning: the coop is full, the other chickens of the file were not loaded");
                    break;
                }
            }
        } catch (IOException e) {
            System.out.println("Error: the file " + FILE_NAME + " could not be read: " + e.getMessage());
        }

        return coop;
    }

    /**
     * Converts one line of the file in a Chicken. Returns null if the line has an error.
     */
    private static Chicken toChicken(String line) {
        String[] data = line.split(SEPARATOR);
        if (data.length != 7) {
            return null;
        }
        try {
            int id = Integer.parseInt(data[0].trim());
            String name = data[1];
            String color = data[2];
            int year = Integer.parseInt(data[3].trim());
            int month = Integer.parseInt(data[4].trim());
            int day = Integer.parseInt(data[5].trim());
            boolean molting = Boolean.parseBoolean(data[6].trim());

            //month - 1 because in Date the months start in 0
            return new Chicken(id, name, color, new Date(year, month - 1, day), molting);
        } catch (NumberFormatException e) {
            return null;
        }
    }

    /**
     * The ";" separates the data in the file, so it can not be inside a name or a color.
     */
    private static String clean(String text) {
        return text.replace(SEPARATOR, ",");
    }
}