package ec.edu.espe.basicoperations.model;

/**
 *
 * @author Yandry Velez, Codelex, @ESPE
 */
public class Square {
    public float calculateVoltage(float current, float resistance) {
    float voltage;
    voltage = current * resistance;
    return voltage;
}

public float calculateCurrent(float voltage, float resistance) {
    float current;
    current = voltage / resistance;
    return current;
}

public float calculateResistance(float voltage, float current) {
    float resistance;
    resistance = voltage / current;
    return resistance;
}
}
