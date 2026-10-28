package utils;

import exceptions.ValidationException;

public final class Validation {
    private Validation() { }

    public static void requireText(String value, String fieldName) {
        if (value == null || value.isBlank()) {
            throw new ValidationException("El campo " + fieldName + " es obligatorio.");
        }
    }

    public static void requireValue(Object value, String fieldName) {
        if (value == null) {
            throw new ValidationException("El campo " + fieldName + " es obligatorio.");
        }
    }

    public static void requirePositiveInt(int value, String fieldName) {
        if (value <= 0) {
            throw new ValidationException("El campo " + fieldName + " debe ser mayor a 0.");
        }
    }

    public static void requireNonNegativeAmount(double value, String fieldName) {
        if (!Double.isFinite(value) || value < 0) {
            throw new ValidationException("El campo " + fieldName + " debe ser finito y mayor o igual a 0.");
        }
    }

    public static void requirePositiveAmount(double value, String fieldName) {
        if (!Double.isFinite(value) || value <= 0) {
            throw new ValidationException("El campo " + fieldName + " debe ser finito y mayor a 0.");
        }
    }

    public static boolean sameIdentifier(String first, String second) {
        return first != null && second != null && first.strip().equalsIgnoreCase(second.strip());
    }
}
