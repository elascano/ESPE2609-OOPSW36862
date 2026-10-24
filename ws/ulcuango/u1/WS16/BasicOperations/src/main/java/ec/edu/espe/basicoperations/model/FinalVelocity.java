package ec.edu.espe.basicoperations.model;

public class FinalVelocity {
    private double initialVelocity;
    private double acceleration;
    private double time;

    public FinalVelocity(double initialVelocity, double acceleration, double time) {
        if (time < 0) {
            throw new IllegalArgumentException("Time cannot be negative");
        }
        this.initialVelocity = initialVelocity;
        this.acceleration = acceleration;
        this.time = time;
    }

    public double calculateFinalVelocity() {
        return this.initialVelocity + (this.acceleration * this.time);
    }

    public double getInitialVelocity() {
        return initialVelocity;
    }

    public void setInitialVelocity(double initialVelocity) {
        this.initialVelocity = initialVelocity;
    }

    public double getAcceleration() {
        return acceleration;
    }

    public void setAcceleration(double acceleration) {
        this.acceleration = acceleration;
    }

    public double getTime() {
        return time;
    }

    public void setTime(double time) {
        if (time < 0) {
            throw new IllegalArgumentException("Time cannot be negative");
        }
        this.time = time;
    }
}