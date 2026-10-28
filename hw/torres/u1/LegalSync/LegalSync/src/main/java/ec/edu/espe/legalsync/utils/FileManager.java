/*
 * Click nbfs://nbhost/SystemFileSystem/Templates/Licenses/license-default.txt to change this license
 * Click nbfs://nbhost/SystemFileSystem/Templates/Classes/Class.java to edit this template
 */
package ec.edu.espe.legalsync.utils;

import java.io.File;
import java.io.FileNotFoundException;
import java.io.FileWriter;
import java.io.IOException;
import java.util.ArrayList;
import java.util.List;
import java.util.Scanner;

/**
 *
 * @author CodeLex, @ESPE
 */
public class FileManager {

    public static List<String> readLines(String fileName) {
        List<String> lines = new ArrayList<>();
        try {
            Scanner fileScanner = new Scanner(new File(fileName));
            while (fileScanner.hasNextLine()) {
                lines.add(fileScanner.nextLine());
            }
            fileScanner.close();
        } catch (FileNotFoundException e) {
            System.out.println("The file " + fileName + " was not found");
        }
        return lines;
    }

    public static boolean appendLine(String fileName, String line) {
        try {
            FileWriter writer = new FileWriter(fileName, true);
            writer.write(line + "\n");
            writer.close();
            return true;
        } catch (IOException e) {
            System.out.println("Error writing the file " + fileName + ": " + e.getMessage());
            return false;
        }
    }

    public static boolean writeText(String fileName, String text) {
        try {
            FileWriter writer = new FileWriter(fileName);
            writer.write(text);
            writer.close();
            return true;
        } catch (IOException e) {
            System.out.println("Error writing the file " + fileName + ": " + e.getMessage());
            return false;
        }
    }

    public static boolean exists(String fileName) {
        File file = new File(fileName);
        return file.exists();
    }
}
