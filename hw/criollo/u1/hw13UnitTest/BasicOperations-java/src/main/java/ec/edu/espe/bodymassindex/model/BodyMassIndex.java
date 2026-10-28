package ec.edu.espe.bodymassindex.model;

/**
 *
 * @author Leslye Cuichan, Infinite loops, @eESPE
 */
public class BodyMassIndex {

    public float calculateBmi(float weightKg, float heightM) {
        if (weightKg <= 0) {
            throw new IllegalArgumentException("The weight must be greater than 0");
        }
        if (heightM <= 0) {
            throw new IllegalArgumentException("The height must be greater than 0");
        }
        float bmi;
        bmi = weightKg / (heightM * heightM);
        bmi = Math.round(bmi * 100.0f) / 100.0f;
        return bmi;
    }

    public String classify(float bmi) {
        String category;
        if (bmi < 18.5f) {
            category = "Underweight";
        } else if (bmi < 25.0f) {
            category = "Normal weight";
        } else if (bmi < 30.0f) {
            category = "Overweight";
        } else {
            category = "Obesity";
        }
        return category;
    }
}
