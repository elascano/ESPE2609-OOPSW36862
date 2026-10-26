/*
 * Click nbfs://nbhost/SystemFileSystem/Templates/Licenses/license-default.txt to change this license
 * Click nbfs://nbhost/SystemFileSystem/Templates/Classes/Class.java to edit this template
 */
package ec.edu.espe.basicoperation.view;

import ec.edu.espe.basicoperation.model.Operation;
import ec.edu.espe.basicoperation.model.ParallelResistance;

/**
 *
 * @author Andres Pachacama, Byte Work, @ESPE
 */
public class BasicOperation {
   public static void main(String[] args) {
    System.out.println("Operations!");

    //declare and instantiate an object
    //declare and aniliazi parameters
    //call the method

    Operation operation;
    operation = new Operation();
    System.out.println("the additon of 1.2 and 2.4 is ---> " + operation.add(1.2F, 2.4F));
    System.out.println("the additon of 1.2 and 2.4 is ---> " + operation.add(1.2F, 2.4F));
    System.out.println("the additon of 1.2 and 2.4 is ---> " + operation.add(1.2F, 2.4F));

    System.out.println("divide ---> " + operation.divide(1,0));

    //enter data from keyboard
    //call the method
    //observ the results

    ParallelResistance parallelResistance;
    parallelResistance = new ParallelResistance();
    System.out.println("the equivalent resistance of 100 and 100 ohms in parallel is ---> " + parallelResistance.calculateEquivalentResistance(100F, 100F) + " ohms");
    System.out.println("the equivalent resistance of 10, 20 and 30 ohms in parallel is ---> " + parallelResistance.calculateEquivalentResistance(10F, 20F, 30F) + " ohms");

    }
    
}
