package ec.edu.espe.unistock.view;

/**
 *
 * @author Dennis Criollo, Leslye Cuichan, Francisco Demera, Byte & Fabric Devs, @ESPE
 */

import java.math.BigDecimal;
import java.util.Scanner;

public class ConsoleAdapter {

    private final Scanner scanner = new Scanner(System.in);

    public String readText(String prompt) {
        System.out.print(prompt);
        return scanner.nextLine().trim();
    }

    public int readInt(String prompt) {
        while (true) {
            try {
                return Integer.parseInt(readText(prompt));
            } catch (NumberFormatException e) {
                System.out.println("Ingrese un numero entero valido.");
            }
        }
    }

    public BigDecimal readMoney(String prompt) {
        while (true) {
            try {
                return new BigDecimal(readText(prompt));
            } catch (NumberFormatException e) {
                System.out.println("Ingrese un valor numerico valido.");
            }
        }
    }
}
