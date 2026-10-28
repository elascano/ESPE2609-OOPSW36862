package ec.edu.espe.chickenfarmapp.utils;

import ec.edu.espe.chickenfarmapp.model.Chicken;
import ec.edu.espe.chickenfarmapp.model.ChickenCoop;
import java.io.BufferedReader;
import java.io.File;
import java.io.FileReader;
import java.io.FileWriter;
import java.io.IOException;
import java.io.PrintWriter;
import java.text.ParseException;
import java.text.SimpleDateFormat;
import java.util.Date;
/**
 *
 * @author Mateo Unda, CodeLex, @ESPE
 */
public class FileManager {
    private static final String SEPARATOR = ";";
    private static final String HEADER = "id;name;color;bornOnDate;molting";
    private static final String DATE_FORMAT = "yyyy-MM-dd";


    public static void save(ChickenCoop coop, String fileName) throws IOException {
        SimpleDateFormat dateFormat = new SimpleDateFormat(DATE_FORMAT);


        try (PrintWriter writer = new PrintWriter(new FileWriter(fileName))) {
            writer.println(HEADER);

            coop.resetIteration();
            Chicken chicken = coop.next();
            while (chicken != null) {
                writer.println(chicken.getId()
                        + SEPARATOR + clean(chicken.getName())
                        + SEPARATOR + clean(chicken.getColor())
                        + SEPARATOR + dateFormat.format(chicken.getBornOnDate())
                        + SEPARATOR + chicken.isMolting());
                chicken = coop.next();
            }
        }
    }


    public static ChickenCoop load(String fileName, int chickenCoopId) throws IOException {
        ChickenCoop coop = new ChickenCoop(chickenCoopId);

        if (!exists(fileName)) {
            return coop;
        }

        try (BufferedReader reader = new BufferedReader(new FileReader(fileName))) {
            String line = reader.readLine();   
            line = reader.readLine();

            while (line != null && !coop.isFull()) {
                Chicken chicken = toChicken(line);
                if (chicken != null) {          
                    coop.add(chicken);
                }
                line = reader.readLine();
            }
        }
        return coop;
    }

    public static boolean exists(String fileName) {
        return new File(fileName).exists();
    }

    public static String fullPath(String fileName) {
        return new File(fileName).getAbsolutePath();
    }


    private static Chicken toChicken(String line) {
        String parts[] = line.split(SEPARATOR);
        if (parts.length != 5) {
            return null;
        }

        try {
            int id = Integer.parseInt(parts[0].trim());
            String name = parts[1].trim();
            String color = parts[2].trim();

            SimpleDateFormat dateFormat = new SimpleDateFormat(DATE_FORMAT);
            dateFormat.setLenient(false);
            Date bornOnDate = dateFormat.parse(parts[3].trim());

            boolean molting = Boolean.parseBoolean(parts[4].trim());

            Chicken chicken = new Chicken(id, name, color, bornOnDate, 0, molting);
            chicken.setAgeInYears();       
            return chicken;
        } catch (NumberFormatException | ParseException e) {
            return null;
        }
    }


    private static String clean(String text) {
        return text.replace(SEPARATOR, ",");
    }
}
