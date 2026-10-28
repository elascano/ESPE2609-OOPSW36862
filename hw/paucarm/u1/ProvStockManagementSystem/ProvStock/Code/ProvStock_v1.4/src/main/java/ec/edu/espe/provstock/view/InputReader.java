package ec.edu.espe.provstock.view;

import java.time.LocalDate;
import java.time.format.DateTimeParseException;
import java.util.Scanner;

/**
 * Reads the data that the user writes in the console. If the user writes
 * something wrong, it asks again so the program does not stop.
 *
 * @author Pachacama Andres, Parra Carlos, Paucar Matias, ByteWorks, @ESPE
 */
public class InputReader {

    private final Scanner scanner;

    public InputReader() {
        this.scanner = new Scanner(System.in);
    }

    /**
     * Reads a text that can be empty.
     */
    public String readOptionalText(String message) {
        System.out.print(message);
        if (!scanner.hasNextLine()) {
            //there is nothing more to read (for example, the input was closed)
            System.out.println();
            System.exit(0);
        }
        return scanner.nextLine().trim();
    }

    /**
     * Reads a text that can not be empty.
     */
    public String readText(String message) {
        while (true) {
            String text = readOptionalText(message);
            if (!text.isEmpty()) {
                return text;
            }
            System.out.println("  Este dato es obligatorio.");
        }
    }

    public int readInt(String message) {
        while (true) {
            String text = readOptionalText(message);
            try {
                return Integer.parseInt(text);
            } catch (NumberFormatException e) {
                System.out.println("  Ingrese un número entero válido.");
            }
        }
    }

    public double readDouble(String message) {
        while (true) {
            String text = readOptionalText(message).replace(',', '.');
            try {
                return Double.parseDouble(text);
            } catch (NumberFormatException e) {
                System.out.println("  Ingrese un número válido (por ejemplo 12.50).");
            }
        }
    }

    /**
     * Reads a number; if the user presses Enter, it returns the default value.
     */
    public double readDouble(String message, double defaultValue) {
        while (true) {
            String text = readOptionalText(message + " [" + defaultValue + "]: ").replace(',', '.');
            if (text.isEmpty()) {
                return defaultValue;
            }
            try {
                return Double.parseDouble(text);
            } catch (NumberFormatException e) {
                System.out.println("  Ingrese un número válido (por ejemplo 12.50).");
            }
        }
    }

    public double readPositiveDouble(String message) {
        while (true) {
            double value = readDouble(message);
            if (value > 0) {
                return value;
            }
            System.out.println("  El valor debe ser mayor que 0.");
        }
    }

    public double readNonNegativeDouble(String message) {
        while (true) {
            double value = readDouble(message);
            if (value >= 0) {
                return value;
            }
            System.out.println("  El valor no puede ser negativo.");
        }
    }

    /**
     * Reads a text; if the user presses Enter, it returns the current value.
     */
    public String readTextOrKeep(String message, String currentValue) {
        String text = readOptionalText(message + " [" + (currentValue == null ? "" : currentValue) + "]: ");
        return text.isEmpty() ? currentValue : text;
    }

    /**
     * Reads a date with the format yyyy-MM-dd.
     */
    public LocalDate readDate(String message) {
        while (true) {
            String text = readOptionalText(message + " (aaaa-mm-dd): ");
            try {
                return LocalDate.parse(text);
            } catch (DateTimeParseException e) {
                System.out.println("  Fecha no válida. Use el formato aaaa-mm-dd, por ejemplo 2026-10-21.");
            }
        }
    }

    /**
     * Reads a date; if the user presses Enter, it returns null (no filter).
     */
    public LocalDate readOptionalDate(String message) {
        while (true) {
            String text = readOptionalText(message + " (aaaa-mm-dd, Enter para omitir): ");
            if (text.isEmpty()) {
                return null;
            }
            try {
                return LocalDate.parse(text);
            } catch (DateTimeParseException e) {
                System.out.println("  Fecha no válida. Use el formato aaaa-mm-dd, por ejemplo 2026-10-21.");
            }
        }
    }

    public boolean readYesNo(String message) {
        while (true) {
            String text = readOptionalText(message + " (s/n): ").toLowerCase();
            if (text.equals("s") || text.equals("si") || text.equals("sí")) {
                return true;
            }
            if (text.equals("n") || text.equals("no")) {
                return false;
            }
            System.out.println("  Responda s o n.");
        }
    }

    /**
     * Shows a numbered list and returns the chosen value. A value must be chosen.
     */
    public String readOption(String title, String[] options) {
        System.out.println(title);
        printOptions(options);
        while (true) {
            int option = readInt("Elija una opción: ");
            if (isInRange(option, options)) {
                return options[option - 1];
            }
            System.out.println("  Opción no válida.");
        }
    }

    /**
     * Like readOption, but 0 means "all / none" and returns null (for example, all the categories).
     */
    public String readOptionalOption(String title, String[] options) {
        System.out.println(title);
        System.out.println("  0. (Todas / ninguna)");
        printOptions(options);
        while (true) {
            int option = readInt("Elija una opción: ");
            if (option == 0) {
                return null;
            }
            if (isInRange(option, options)) {
                return options[option - 1];
            }
            System.out.println("  Opción no válida.");
        }
    }

    private void printOptions(String[] options) {
        for (int i = 0; i < options.length; i++) {
            System.out.println("  " + (i + 1) + ". " + options[i]);
        }
    }

    private boolean isInRange(int option, String[] options) {
        return option >= 1 && option <= options.length;
    }
}
