package com.mycompany.unistock.structure;

import java.io.File;
import java.io.FileNotFoundException;
import java.io.FileWriter;
import java.io.IOException;
import java.io.PrintWriter;
import java.util.Scanner;
/**
 *
 * @author Leslye Cuichan, Infinite loops, @eESPE
 */

public class FileHandler {

    // Guarda los elementos de una SimpleList<String> en un archivo .txt
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

    // Lee las líneas de un archivo .txt y las carga en una SimpleList<String>
    public static SimpleList<String> loadList(String filePath) {
        SimpleList<String> list = new SimpleList<>();
        File file = new File(filePath);

        if (!file.exists()) {
            return list; // Retorna lista vacía si el archivo no existe aún
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
