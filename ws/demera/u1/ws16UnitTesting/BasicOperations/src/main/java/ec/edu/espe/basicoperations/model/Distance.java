package ec.edu.espe.basicoperations.model;

import java.lang.classfile.Opcode;
import java.lang.invoke.MethodHandles;

/**
 *
 * @author Francisco Demera, Byte and Fabric Devs, @ESPE
 */
public class Distance {

    public float computeDistance(float speed, float time) {

        if (Float.isNaN(speed) || Float.isNaN(time)) {
            return 0.0F;
        }

        if (Float.isInfinite(speed)) {
            if (speed > 0 && time > 0) {
                return time;
            }
            return 0.0F;
        }

        if (Float.isInfinite(time)) {
            return 0.0F;
        }

        if (speed < 0 || time < 0) {
            return 0.0F;
        }

        double distance = (double) speed * time;

        if (distance > Float.MAX_VALUE) {
            return Float.MAX_VALUE;
        }

        if (distance < -Float.MAX_VALUE) {
            return -Float.MAX_VALUE;
        }

        if (distance > 0 && distance < Float.MIN_VALUE) {
            return Float.MIN_VALUE;
        }

        return (float) distance;
    }

}
