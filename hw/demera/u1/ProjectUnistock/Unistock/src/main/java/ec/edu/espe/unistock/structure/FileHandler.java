package ec.edu.espe.unistock.structure;

/**
 *
 * @author Dennis Criollo, Leslye Cuichan, Francisco Demera, Byte & Fabric Devs, @ESPE
 */

import java.io.File;
import java.io.FileNotFoundException;
import java.io.FileWriter;
import java.io.IOException;
import java.io.PrintWriter;
import java.util.Scanner;

public class FileHandler {

    public static void saveList(String filePath, SimpleList<String> list) {
        try (PrintWriter writer = new PrintWriter(new FileWriter(filePath))) {
            list.resetIteration();
            while (list.hasNext()) {
                writer.println(list.next());
            }
        } catch (IOException e) {
            System.out.println("Error al guardar en el archivo " + filePath + ": " + e.getMessage());
        }
    }

 
    public static SimpleList<String> loadList(String filePath) {
        SimpleList<String> list = new SimpleList<>();
        File file = new File(filePath);

        if (!file.exists()) {
            return list;
        }

        try (Scanner scanner = new Scanner(file)) {
            while (scanner.hasNextLine()) {
                String line = scanner.nextLine().trim();
                if (!line.isEmpty()) {
                    list.add(line);
                }
            }
        } catch (FileNotFoundException e) {
            System.out.println("Archivo no encontrado: " + filePath);
        }
        return list;
    }
}
