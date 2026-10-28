package ec.edu.espe.systemliquorstore.util;

import java.time.LocalDate;
import java.time.format.DateTimeParseException;
import java.util.Locale;

public class Validation {
    public double roundMoney(double value) {
        if (!isFinite(value)) {
            throw new IllegalArgumentException("El resultado numérico no es válido.");
        }
        double scaled = Math.abs(value) * 100.0;
        if (isFinite(scaled) && Math.ulp(scaled) < 0.5) {
            double halfCent = Math.floor(scaled) + 0.5;
            double tolerance = Math.min(Math.ulp(scaled) * 4.0, 0.00000001);
            if (Math.abs(scaled - halfCent) <= tolerance) {
                double rounded = Math.copySign((Math.floor(scaled) + 1.0) / 100.0, value);
                return rounded == 0 ? 0 : rounded;
            }
        }
        double result = Double.parseDouble(String.format(Locale.US, "%.2f", value));
        if (!isFinite(result)) {
            throw new IllegalArgumentException("El resultado es demasiado grande.");
        }
        return result == 0 ? 0 : result;
    }

    public boolean isMoney(double value) {
        return isFinite(value) && value == roundMoney(value);
    }

    public boolean isFinite(double value) {
        return !Double.isNaN(value) && !Double.isInfinite(value);
    }

    public boolean isValidDate(String date) {
        if (date == null || date.length() != 10) {
            return false;
        }
        try {
            return LocalDate.parse(date).getYear() > 0;
        } catch (DateTimeParseException exception) {
            return false;
        }
    }

    public boolean isText(String text) {
        return text != null && !text.trim().isEmpty();
    }
}

