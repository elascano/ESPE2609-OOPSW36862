package ec.edu.espe.basicoperations.model;

/**
 *
 * @author Carlos Parra, ByteWorks, @ESPE
 */
public class Operation {
    
    public float add(float addend1,float addend2){
        float sum;
        sum = addend1 + addend2;
        return sum;
    }
    
    public float substract(float minuhed, float subtrahend){
        float difference;
        difference = minuhed - subtrahend;
        return difference;
    }
    
    public float multiply(float multiplicand, float multiplier){
        float product;
        product = multiplicand * multiplier;
        return product;
    }
    
    public float divide(float dividend, float divisor){
        float quotient;
        quotient = dividend/divisor;
        return quotient;
    }
    
    
}
