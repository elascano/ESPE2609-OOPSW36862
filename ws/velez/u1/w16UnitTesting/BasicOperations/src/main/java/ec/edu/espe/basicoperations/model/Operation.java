package ec.edu.espe.basicoperations.model;

/**
 *
 * @author Yandry Velez, Codelex, @ESPE
 */
public class Operation {
    
public float add(float adden1, float adden2){
        float sum;
        sum = adden1 + adden2;
        sum = Math.round(sum*100.0f)/100.0f;
        
        return sum;
    }
public float subtract(float minuhend, float subtrahend){
    float difference;
    difference = minuhend - subtrahend;
    return difference;
}

public float multiply(float multiplicand, float multiplier) {
    float product;
    product = multiplicand * multiplier;
    return product;
}

public float divide(float dividend, float divisor) {
    float quotient;
    quotient = dividend / divisor;
    return quotient;
}
}
