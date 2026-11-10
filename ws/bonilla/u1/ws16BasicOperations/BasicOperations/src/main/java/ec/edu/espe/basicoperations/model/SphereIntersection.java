package ec.edu.espe.basicoperations.model;

/**
 *
 * @author Angel Bonilla,Infinite Loops, @ESPE
 */
public class SphereIntersection {

    public float computeVolume(float radius1, float radius2, float distance) {
        if (radius1 < 0 || radius2 < 0 || distance < 0) {
            throw new IllegalArgumentException("Radii and distance cannot be negative");
        }
        if (radius1 == 0 || radius2 == 0 || distance >= radius1 + radius2) {
            return 0.0F;
        }
        if (distance <= Math.abs(radius1 - radius2)) {
            float minRadius = Math.min(radius1, radius2);
            return (float) ((4.0 / 3.0) * Math.PI * Math.pow(minRadius, 3));
        }

        float part1 = (radius1 + radius2 - distance) * (radius1 + radius2 - distance);
        float part2 = (distance * distance) + (2 * distance * radius1) - (3 * radius1 * radius1)
                + (2 * distance * radius2) + (6 * radius1 * radius2) - (3 * radius2 * radius2);

        return (float) (Math.PI * part1 * part2 / (12.0F * distance));
    }
}