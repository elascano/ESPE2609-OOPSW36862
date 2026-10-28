package ec.edu.espe.chickenfarmerapp.model;

import com.google.gson.Gson;
import com.google.gson.GsonBuilder;
import java.io.FileReader;
import java.io.FileWriter;
import java.io.IOException;

public class Persistencia {

    private static final String ARCHIVO_JSON = "chickencoop.json";

    private static final Gson gson = new GsonBuilder()
            .setPrettyPrinting()
            .setDateFormat("yyyy-MM-dd") 
            .create();

    public static void guardar(ChickenCoop coop) {
        try (FileWriter writer = new FileWriter(ARCHIVO_JSON)) {
            gson.toJson(coop, writer);
            System.out.println(">> Datos guardados exitosamente en " + ARCHIVO_JSON);
        } catch (IOException e) {
            System.out.println(">> Error al guardar los datos: " + e.getMessage());
        }
    }

    public static ChickenCoop cargar() {
        try (FileReader reader = new FileReader(ARCHIVO_JSON)) {
            ChickenCoop coop = gson.fromJson(reader, ChickenCoop.class);
            System.out.println(">> Datos cargados exitosamente desde " + ARCHIVO_JSON);
            return coop;
        } catch (IOException e) {
            System.out.println(">> No se encontró archivo previo o hubo un error. Creando un corral nuevo.");
            return null;
        }
    }
}