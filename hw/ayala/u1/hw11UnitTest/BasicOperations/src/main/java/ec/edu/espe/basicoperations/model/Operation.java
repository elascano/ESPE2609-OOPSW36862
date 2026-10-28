package ec.edu.espe.basicoperations.model;

/**
 *
 * @author Demian Armas, Logical Lords, @ESPE
 */
public class Operation {

    public float add(float addend1, float addend2) {
        float sum;
        sum = addend1 + addend2;
        sum= Math.round(sum*100.0f)/100.0f;
        return sum;
    }

    public float subtract(float minuhend, float subtrahend) {
        float diference;
        diference = minuhend - subtrahend;
        return diference;
    }

    public float multiply(float multiplicand, float multiplier) {
        float prodcut;
        prodcut = multiplicand * multiplier;
        return prodcut;
    }

    public float divide(float dividend, float divisor) {
        float quotient;
        quotient = dividend / divisor;
        return quotient;
        
 
    }
}

