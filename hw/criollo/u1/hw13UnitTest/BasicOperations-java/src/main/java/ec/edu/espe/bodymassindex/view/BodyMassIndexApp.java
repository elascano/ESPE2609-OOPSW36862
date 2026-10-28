package ec.edu.espe.bodymassindex.view;

import ec.edu.espe.bodymassindex.model.BodyMassIndex;
import java.util.Scanner;

/**
 *
 * @author Leslye Cuichan, Infinite loops, @eESPE
 */
public class BodyMassIndexApp {

    public static void main(String[] args) {
        System.out.println("Body Mass Index - BMI");

        BodyMassIndex bodyMassIndex;
        bodyMassIndex = new BodyMassIndex();

        Scanner scanner = new Scanner(System.in);
        System.out.print("Enter the weight in kg: ");
        float weightKg = scanner.nextFloat();
        System.out.print("Enter the height in m: ");
        float heightM = scanner.nextFloat();

        try {
            float bmi = bodyMassIndex.calculateBmi(weightKg, heightM);
            System.out.println("Your BMI is --> " + bmi);
            System.out.println("Category --> " + bodyMassIndex.classify(bmi));
        } catch (IllegalArgumentException e) {
            System.out.println("Error --> " + e.getMessage());
        }
    }
}
