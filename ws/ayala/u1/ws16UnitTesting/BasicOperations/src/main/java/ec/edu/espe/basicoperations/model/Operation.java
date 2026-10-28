package ec.edu.espe.basicoperations.model;

/**
 *
 * @author Enrique Ayala, LogicalLords, @ESPE
 */
public class Operation {
    
    public float add(float addend1,float addend2){
        float sum;
        sum = addend1 + addend2;
        sum = Math.round(sum*100.0F)/100.0F;
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
