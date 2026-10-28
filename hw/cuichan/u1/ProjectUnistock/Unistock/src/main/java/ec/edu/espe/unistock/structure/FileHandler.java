package ec.edu.espe.unistock.structure;

/**
 *
 * @author Dennis Criollo, Leslye Cuichan, Francisco Demera, Byte & Fabric Devs, @ESPE
 */

import com.google.gson.Gson;
import com.google.gson.GsonBuilder;
import com.google.gson.reflect.TypeToken;
import java.io.File;
import java.io.FileReader;
import java.io.FileWriter;
import java.io.IOException;
import java.io.PrintWriter;
import java.lang.reflect.Type;
import java.util.ArrayList;
import java.util.List;

public class FileHandler {

    private static final Gson gson = new GsonBuilder().setPrettyPrinting().create();

    /**
     * Guarda una lista genérica SimpleList<T> en un archivo JSON.
     */
    public static <T> void saveListToJson(String filePath, SimpleList<T> list) {
        if (filePath == null || filePath.isBlank() || list == null) {
            return;
        }

        String jsonPath = filePath.endsWith(".json") ? filePath : filePath + ".json";

        List<T> javaList = new ArrayList<>();
        list.resetIteration();
        while (list.hasNext()) {
            T item = list.next();
            if (item != null) {
                javaList.add(item);
            }
        }

        try (FileWriter writer = new FileWriter(jsonPath)) {
            gson.toJson(javaList, writer);
        } catch (IOException e) {
            System.out.println("Error al guardar JSON en " + jsonPath + ": " + e.getMessage());
        }
    }

    /**
     * Carga una lista genérica SimpleList<T> desde un archivo JSON.
     */
    public static <T> SimpleList<T> loadListFromJson(String filePath, Class<T> clazz) {
        SimpleList<T> list = new SimpleList<>();
        if (filePath == null || filePath.isBlank()) {
            return list;
        }

        String jsonPath = filePath.endsWith(".json") ? filePath : filePath + ".json";
        File file = new File(jsonPath);
        if (!file.exists()) {
            return list;
        }

        try (FileReader reader = new FileReader(file)) {
            Type listType = TypeToken.getParameterized(ArrayList.class, clazz).getType();
            List<T> javaList = gson.fromJson(reader, listType);

            if (javaList != null) {
                for (T item : javaList) {
                    list.add(item);
                }
            }
        } catch (IOException e) {
            System.out.println("Error al cargar JSON desde " + jsonPath + ": " + e.getMessage());
        }
        return list;
    }

    /**
     * Guarda una lista SimpleList de Strings (compatibilidad con catálogos).
     */
    public static void saveList(String filePath, SimpleList<String> list) {
        saveListToJson(filePath, list);
    }

    /**
     * Carga una lista SimpleList de Strings (compatibilidad con catálogos).
     */
    public static SimpleList<String> loadList(String filePath) {
        return loadListFromJson(filePath, String.class);
    }

    /**
     * Exporta datos en formato CSV para la generación de reportes.
     */
    public static void exportToCSV(String filePath, String header, SimpleList<String> rows) {
        if (filePath == null || filePath.isBlank() || rows == null) {
            return;
        }

        String csvPath = filePath.endsWith(".csv") ? filePath : filePath + ".csv";

        try (PrintWriter writer = new PrintWriter(new FileWriter(csvPath))) {
            if (header != null && !header.isBlank()) {
                writer.println(header);
            }
            rows.resetIteration();
            while (rows.hasNext()) {
                String row = rows.next();
                if (row != null) {
                    writer.println(row);
                }
            }
            System.out.println("Reporte CSV generado exitosamente en: " + csvPath);
        } catch (IOException e) {
            System.out.println("Error al generar reporte CSV en " + csvPath + ": " + e.getMessage());
        }
    }
}