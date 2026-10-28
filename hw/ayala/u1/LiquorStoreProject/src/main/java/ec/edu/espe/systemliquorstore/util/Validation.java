package ec.edu.espe.systemliquorstore.util;

import java.time.LocalDate;
import java.time.format.DateTimeParseException;

public final class Validation {
    private Validation() {
    }
    public static double roundMoney(double value) {
        if (!isFinite(value)) {
            throw new IllegalArgumentException("El resultado numérico no es válido.");
        }
        if (!isFinite(value * 100.0)) {
            throw new IllegalArgumentException("El resultado es demasiado grande.");
        }
        return Math.round(value * 100.0) / 100.0;
    }

    public static boolean isMoney(double value) {
        return isFinite(value) && value == roundMoney(value);
    }

    public static boolean isFinite(double value) {
        return !Double.isNaN(value) && !Double.isInfinite(value);
    }

    public static boolean isValidDate(String date) {
        if (date == null || date.length() != 10) {
            return false;
        }
        try {
            return LocalDate.parse(date).getYear() > 0;
        } catch (DateTimeParseException exception) {
            return false;
        }
    }

    public static boolean isText(String text) {
        return text != null && !text.trim().isEmpty();
    }
}

