
package ec.edu.espe.basicoperations.model;

public class BodyMassIndex {

public float calculateBMI(float weight, float height) {
    if (height == 0) {
        throw new IllegalArgumentException("Height cannot be zero");
    }

    return weight / (height * height);
}
}