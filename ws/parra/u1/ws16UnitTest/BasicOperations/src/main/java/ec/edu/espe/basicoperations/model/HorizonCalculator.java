package ec.edu.espe.basicoperations.model;

public class HorizonCalculator {

    public double calculateDistance(double heightInMeters) {
        if (heightInMeters < 0) {
            throw new IllegalArgumentException("Height cannot be negative");
        }
        return 3.57 * Math.sqrt(heightInMeters);
    }
}