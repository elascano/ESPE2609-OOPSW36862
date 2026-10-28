/*
 * Click nbfs://nbhost/SystemFileSystem/Templates/Licenses/license-default.txt to change this license
 * Click nbfs://nbhost/SystemFileSystem/Templates/Classes/Class.java to edit this template
 */
package ec.edu.espe.basicoperation.model;

/**
 *
 * @author Andres Pachacama, Byte Work, @ESPE
 */
public class Operation {
    public float add(float addend1, float addend2){
        float sum;
        sum = addend1 + addend2;
        //complex alforithm
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
