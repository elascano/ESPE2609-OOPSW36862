/*
 * Click nbfs://nbhost/SystemFileSystem/Templates/Licenses/license-default.txt to change this license
 */

package ec.edu.espe.basicoperations.view;

import ec.edu.espe.basicoperations.model.Operation;

/**
 *
 * @author Cristopher Guañuna, Runtime Dynamic, @ESPE
 */
public class BasicOperations {

    public static void main(String[] args) {
        System.out.println("Operations!");
        
        //declare and instantiate an object
        //declare and initialize parameters
        //call the method
        Operation operation;
        operation = new Operation();
        System.out.println("the addition of 1.2 and 2.4 is --> " + operation.add(1.2F,2.4F));
        System.out.println("the addition of 1.2 and 2.4 is --> " + operation.add(1.2F,2.4F));
        System.out.println("the addition of 1.2 and 2.4 is --> " + operation.add(1.2F,2.4F));
        
        System.out.println("divide -->" + operation.divide(1,0));
        //enter data from keyboard
        //call the method
        //observ the results
    }
}
