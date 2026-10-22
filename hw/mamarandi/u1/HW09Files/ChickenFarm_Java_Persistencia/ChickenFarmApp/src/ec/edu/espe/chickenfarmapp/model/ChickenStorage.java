package ec.edu.espe.chickenfarmapp.model;

import java.io.*;
import java.nio.charset.StandardCharsets;
import java.nio.file.*;
import java.util.*;

public class ChickenStorage {
    private static final Path FILE = Paths.get("chickens.txt");
    private static String encode(String value) {
        return Base64.getEncoder().encodeToString(value.getBytes(StandardCharsets.UTF_8));
    }
    private static String decode(String value) {
        return new String(Base64.getDecoder().decode(value), StandardCharsets.UTF_8);
    }
    public static void save(Chicken[] chickens) {
        List<String> lines = new ArrayList<>();
        for (Chicken c : chickens) {
            if (c != null) {
                lines.add(c.getId() + "\t" + encode(c.getName()) + "\t" + encode(c.getColor()) +
                    "\t" + c.getBorn0nDate().getTime() + "\t" + c.isMolting());
            }
        }
        try {
            Files.write(FILE, lines, StandardCharsets.UTF_8);
            System.out.println("Data saved in chickens.txt");
        } catch (IOException e) {
            System.out.println("Could not save: " + e.getMessage());
        }
    }
    public static int load(Chicken[] chickens) {
        if (!Files.exists(FILE)) return 0;
        try {
            List<String> lines = Files.readAllLines(FILE, StandardCharsets.UTF_8);
            if (lines.size() > chickens.length) throw new IOException("Too many chickens");
            int index = 0;
            for (String line : lines) {
                String[] parts = line.split("\t", -1);
                if (parts.length != 5 || !(parts[4].equals("true") || parts[4].equals("false"))) throw new IOException("Invalid record");
                chickens[index++] = new Chicken(Integer.parseInt(parts[0]), decode(parts[1]),
                    decode(parts[2]), new Date(Long.parseLong(parts[3])), Boolean.parseBoolean(parts[4]));
            }
            return index;
        } catch (IOException | IllegalArgumentException e) {
            throw new IllegalStateException("Could not read chickens.txt: " + e.getMessage(), e);
        }
    }
}
