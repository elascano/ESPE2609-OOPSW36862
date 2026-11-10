package ec.edu.espe.electromechanicalmaintenancemanagementsystem.view;

import java.time.LocalDate;
import java.time.format.DateTimeParseException;
import java.util.Scanner;

/** Reads and validates user input from the console. Every method repeats the prompt until the input is valid. */
public class ConsoleInput {
    private final Scanner scanner;

    public ConsoleInput(Scanner scanner) {
        this.scanner = scanner;
    }

    public int readInt(String message) {
        while (true) {
            try {
                System.out.print(message);
                return Integer.parseInt(scanner.nextLine().trim());
            } catch (NumberFormatException exception) {
                System.out.println("Invalid input. Enter a whole number.");
            }
        }
    }

    public int readIntInRange(String message, int min, int max) {
        while (true) {
            int value = readInt(message);
            if (value >= min && value <= max) {
                return value;
            }
            System.out.println("Enter a number between " + min + " and " + max + ".");
        }
    }

    public int readPositiveInt(String message) {
        while (true) {
            int value = readInt(message);
            if (value > 0) {
                return value;
            }
            System.out.println("The value must be greater than zero.");
        }
    }

    public int readNonNegativeInt(String message) {
        while (true) {
            int value = readInt(message);
            if (value >= 0) {
                return value;
            }
            System.out.println("The value cannot be negative.");
        }
    }

    public float readNonNegativeFloat(String message) {
        while (true) {
            float value = readFloat(message);
            if (value >= 0) {
                return value;
            }
            System.out.println("The value cannot be negative.");
        }
    }

    public String readText(String message) {
        while (true) {
            System.out.print(message);
            String value = scanner.nextLine().trim();
            if (!value.isEmpty()) {
                return value;
            }
            System.out.println("This field cannot be empty.");
        }
    }

    public String readName(String message) {
        while (true) {
            String name = readText(message);
            if (name.matches("[\\p{L}][\\p{L} .'-]*")) {
                return name;
            }
            System.out.println("Use letters only. Spaces, accents, apostrophes and hyphens are allowed.");
        }
    }

    public String readPhone(String message) {
        while (true) {
            String phone = readText(message);
            if (phone.matches("0[0-9]{9}")) {
                return phone;
            }
            System.out.println("The phone number must contain exactly 10 digits and start with 0.");
        }
    }

    public String readDate(String message) {
        while (true) {
            String input = readText(message);
            try {
                LocalDate date = LocalDate.parse(input);
                if (!date.isBefore(LocalDate.now())) {
                    return date.toString();
                }
                System.out.println("The date must be today or later.");
            } catch (DateTimeParseException exception) {
                System.out.println("Invalid date. Use the format YYYY-MM-DD, for example 2026-11-27.");
            }
        }
    }

    public String readChoice(String message, String... choices) {
        while (true) {
            String value = readText(message);
            for (String choice : choices) {
                if (value.equalsIgnoreCase(choice)) {
                    return choice;
                }
            }
            System.out.println("Invalid option. Allowed values: " + String.join(", ", choices));
        }
    }

    public boolean readBoolean(String message) {
        while (true) {
            String value = readText(message);
            if (value.equalsIgnoreCase("yes") || value.equalsIgnoreCase("y")) {
                return true;
            }
            if (value.equalsIgnoreCase("no") || value.equalsIgnoreCase("n")) {
                return false;
            }
            System.out.println("Enter yes or no.");
        }
    }

    private float readFloat(String message) {
        while (true) {
            try {
                System.out.print(message);
                float value = Float.parseFloat(scanner.nextLine().trim().replace(',', '.'));
                if (Float.isFinite(value)) {
                    return value;
                }
                System.out.println("Enter a finite number.");
            } catch (NumberFormatException exception) {
                System.out.println("Invalid number. Example: 12.50");
            }
        }
    }
}
