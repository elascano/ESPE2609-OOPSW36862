package ec.edu.espe.basicoperation.model;

/**
 *
 * @author Andres Pachacama, Byte Work, @ESPE
 */
public class ParallelResistance {

    // 1 / Req = 1 / R1 + 1 / R2 + ... + 1 / Rn
    public float calculateEquivalentResistance(float... resistances) {
        if (resistances == null || resistances.length == 0) {
            throw new IllegalArgumentException("At least one resistance is required");
        }

        float sumOfInverses = 0;
        for (float resistance : resistances) {
            if (resistance <= 0) {
                throw new IllegalArgumentException("Resistance must be greater than zero");
            }
            sumOfInverses = sumOfInverses + (1 / resistance);
        }

        float equivalentResistance;
        equivalentResistance = 1 / sumOfInverses;
        return equivalentResistance;
    }

}
