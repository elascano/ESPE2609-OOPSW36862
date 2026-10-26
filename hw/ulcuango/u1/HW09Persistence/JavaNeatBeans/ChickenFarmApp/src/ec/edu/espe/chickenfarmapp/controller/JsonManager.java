package ec.edu.espe.chickenfarmapp.controller;

import com.google.gson.Gson;
import com.google.gson.GsonBuilder;
import com.google.gson.reflect.TypeToken;
import ec.edu.espe.chickenfarmapp.model.ChickenCoop;

import java.io.FileReader;
import java.io.FileWriter;
import java.io.IOException;
import java.lang.reflect.Type;
import java.util.ArrayList;

/**
 * @author Josselyn Ulcuango, Codelex, @ESPE
 */
public class JsonManager {
    private static final String FILE_NAME = "chicken_farm_data.json";

    public static void saveCoops(ArrayList<ChickenCoop> coops) {
        Gson gson = new GsonBuilder().setPrettyPrinting().create();
        try (FileWriter writer = new FileWriter(FILE_NAME)) {
            gson.toJson(coops, writer);
        } catch (IOException e) {
            System.out.println("Error saving JSON file: " + e.getMessage());
        }
    }

    public static ArrayList<ChickenCoop> loadCoops() {
        Gson gson = new Gson();
        ArrayList<ChickenCoop> coops = new ArrayList<>();
        try (FileReader reader = new FileReader(FILE_NAME)) {
            Type coopListType = new TypeToken<ArrayList<ChickenCoop>>() {}.getType();
            coops = gson.fromJson(reader, coopListType);
            if (coops == null) {
                coops = new ArrayList<>();
            }
        } catch (IOException e) {

        }
        return coops;
    }
}