package model;

import com.google.gson.Gson;
import com.google.gson.GsonBuilder;
import com.google.gson.reflect.TypeToken;
import java.io.*;
import java.lang.reflect.Type;
import java.util.ArrayList;
import java.util.List;

public class Truck {
    private String id;
    private String brand;
    private int capacity_tons;
    private int year;

    public Truck(String id, String brand, int capacity_tons, int year) {
        this.id = id;
        this.brand = brand;
        this.capacity_tons = capacity_tons;
        this.year = year;
    }

    private String getFilePath() {
        File rootDir = new File(System.getProperty("user.dir"));
        return new File(rootDir, "data/trucks.json").getAbsolutePath();
    }

    public void saveInformation() {
        String filePath = getFilePath();
        File file = new File(filePath);
        file.getParentFile().mkdirs();

        Gson gson = new GsonBuilder().setPrettyPrinting().create();
        List<Truck> trucksData = LoadInformation();
        
        trucksData.add(this);

        try (Writer writer = new FileWriter(filePath)) {
            gson.toJson(trucksData, writer);
        } catch (IOException e) {
            System.out.println("Error al guardar la información.");
        }
    }

    public static List<Truck> LoadInformation() {
        File rootDir = new File(System.getProperty("user.dir"));
        String filePath = new File(rootDir, "data/trucks.json").getAbsolutePath();
        File file = new File(filePath);
        
        if (!file.exists()) {
            return new ArrayList<>();
        }

        Gson gson = new Gson();
        try (Reader reader = new FileReader(filePath)) {
            Type listType = new TypeToken<ArrayList<Truck>>(){}.getType();
            List<Truck> trucks = gson.fromJson(reader, listType);
            return trucks != null ? trucks : new ArrayList<>();
        } catch (IOException e) {
            return new ArrayList<>();
        }
    }

    @Override
    public String toString() {
        return "ID: " + id + " | Marca: " + brand + " | Capacidad: " + capacity_tons + " ton | Año: " + year;
    }
}