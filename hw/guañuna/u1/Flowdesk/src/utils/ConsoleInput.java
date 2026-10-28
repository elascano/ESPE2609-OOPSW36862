package utils;

import java.util.Scanner;
import java.util.concurrent.CancellationException;
import exceptions.ValidationException;
import exceptions.PersistenceException;

public final class ConsoleInput {
    private ConsoleInput() { }

    private static String readLine(Scanner scanner, String prompt) {
        System.out.print(prompt);
        String value = scanner.nextLine();
        if (value.trim().equalsIgnoreCase(":cancelar")) {
            throw new CancellationException("Operacion cancelada.");
        }
        return value;
    }

    public static String readRequiredText(Scanner scanner, String prompt) {
        while (true) {
            String value = readLine(scanner, prompt);
            if (!value.isBlank()) {
                return value;
            }
            System.out.println("Este campo es obligatorio.");
        }
    }

    public static int readInt(Scanner scanner, String prompt) {
        while (true) {
            try {
                return Integer.parseInt(readLine(scanner, prompt).trim());
            } catch (NumberFormatException exception) {
                System.out.println("Ingrese un numero entero valido.");
            }
        }
    }

    public static int readOption(Scanner scanner, String prompt, int minimum, int maximum) {
        while (true) {
            int option = readInt(scanner, prompt);
            if (option >= minimum && option <= maximum) {
                return option;
            }
            System.out.println("Opcion no valida. Ingrese un numero entre " + minimum + " y " + maximum + ".");
        }
    }

    public static int readPositiveInt(Scanner scanner, String prompt) {
        while (true) {
            int value = readInt(scanner, prompt);
            if (value > 0) {
                return value;
            }
            System.out.println("Ingrese un entero mayor a 0.");
        }
    }

    public static double readAmount(Scanner scanner, String prompt) {
        return readAmount(scanner, prompt, true);
    }

    public static double readPositiveAmount(Scanner scanner, String prompt) {
        return readAmount(scanner, prompt, false);
    }

    private static double readAmount(Scanner scanner, String prompt, boolean allowZero) {
        while (true) {
            try {
                double amount = Double.parseDouble(readLine(scanner, prompt).trim());
                if (Double.isFinite(amount) && (allowZero ? amount >= 0 : amount > 0)) {
                    return amount;
                }
            } catch (NumberFormatException exception) {

            }
            System.out.println(allowZero
                    ? "Ingrese un monto valido mayor o igual a 0 (use punto decimal)."
                    : "Ingrese un monto valido mayor a 0 (use punto decimal).");
        }
    }

    public static void runAction(Runnable action) {
        try {
            action.run();
        } catch (CancellationException exception) {
            System.out.println("Operacion cancelada.");
        } catch (ValidationException | PersistenceException exception) {
            System.out.println("Error: " + exception.getMessage());
        }
    }
}
