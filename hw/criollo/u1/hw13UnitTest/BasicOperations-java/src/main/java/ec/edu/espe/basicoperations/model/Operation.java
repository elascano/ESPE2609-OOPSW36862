/*
 * Click nbfs://nbhost/SystemFileSystem/Templates/Licenses/license-default.txt to change this license
 * Click nbfs://nbhost/SystemFileSystem/Templates/Classes/Class.java to edit this template
 */
package ec.edu.espe.basicoperations.model;

/**
 *
 * @author Dennis Criollo, Infinite loops, @eESPE
 */
public class Operation {
    
    
    public float add(float addend1, float addend2) {
        float sum;
        sum = addend1 + addend2;
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
    if (divisor == 0.0F) {
        return 0.0F; // Retorna 0.0 para evitar NaN cuando se divide por cero
    }
    float quotient;
    quotient = dividend / divisor;
    return quotient;

    }
    
}
    
   
