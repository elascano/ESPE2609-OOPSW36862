/*
 * Click nbfs://nbhost/SystemFileSystem/Templates/Licenses/license-default.txt to change this license
 */

package ec.edu.espe.basicoperations.view;

import ec.edu.espe.basicoperations.model.Operation;
import ec.edu.espe.basicoperations.model.Square;

/**
 *
 * @author Yandry Velez, Codelex, @ESPE
 */
public class BasicOperations {

    public static void main(String[] args) {
        System.out.println("Operations");
        Operation operation;
        operation = new Operation();
        System.out.println("The addition of 1.2 and 2.4 is --->" + operation.add(1.2F, 2.4F));
        System.out.println("divide--->" + operation.divide(1,0));
        
        System.out.println("Ohm's Law");
        Square square;
        square = new Square();
        System.out.println("voltage--->" + square.calculateVoltage(2.0F, 6.0F));
        System.out.println("current--->" + square.calculateCurrent(12.0F, 6.0F));
        System.out.println("resistance--->" + square.calculateResistance(12.0F, 2.0F));
        
    }
}
