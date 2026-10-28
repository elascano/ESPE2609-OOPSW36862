package ec.edu.espe.basicoperations.model;

/**
 *
 * @author Cristopher Guañuna, Runtime Dynamic, @ESPE
 */
public class Operation {
    
    public static double calculateSphereArea(double radius) {
        if (radius < 0) {
            return 0;
        }
        double area = 4 * Math.PI * Math.pow(radius, 2);
        area = Math.round(area * 1000.0) / 1000.0;

        return area;
    }
    
    public float add(float addend1, float addend2){
        float sum;
        sum= addend1 + addend2;
        sum=Math.round(sum*100.0F)/100.0f;
        return sum;
    }
    
    public float substract(float munihend, float subtrahend){
        float difference;
        difference= munihend - subtrahend;
        return difference;
    }
    
    public float multiply(float multiplicand, float multiplier){
        float product;
        product= multiplicand * multiplier;
        return product;
    }
    
    public float divide(float dividend, float divisor){
        float quotient;
        quotient= dividend / divisor;
        return quotient;
    }
        
}
