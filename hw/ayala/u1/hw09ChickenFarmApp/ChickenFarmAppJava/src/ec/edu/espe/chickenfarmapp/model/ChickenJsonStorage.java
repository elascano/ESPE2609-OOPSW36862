package ec.edu.espe.chickenfarmapp.model;

import com.google.gson.Gson;
import com.google.gson.GsonBuilder;
import java.io.File;
import java.io.FileReader;
import java.io.FileWriter;
import java.io.IOException;
import java.io.Reader;
import java.io.Writer;

/**
 *
 * @author Enrique Ayala, LogicLords,@ESPE
 */
public class ChickenJsonStorage {
    private final String filePath;
    private final Gson gson = new GsonBuilder()
            .setPrettyPrinting()
            .setDateFormat("yyyy-MM-dd")
            .create();

    public ChickenJsonStorage(String filePath) {
        this.filePath = filePath;
    }

    public void save(ChickenCoop coop) {
        try (Writer writer = new FileWriter(filePath)) {
            gson.toJson(coop.toArray(), writer);
        } catch (IOException e) {
            System.out.println("Could not save the file: " + e.getMessage());
        }
    }

    public void load(ChickenCoop coop) {
        File file = new File(filePath);
        if (!file.exists()) {
            return; // first run, nothing to load yet
        }
        try (Reader reader = new FileReader(file)) {
            Chicken[] loaded = gson.fromJson(reader, Chicken[].class);
            if (loaded != null) {
                for (Chicken chicken : loaded) {
                    chicken.setAgeInYears(); // the saved age may be outdated
                    coop.add(chicken);
                }
            }
        } catch (IOException e) {
            System.out.println("Could not read the file: " + e.getMessage());
        }
    }
}