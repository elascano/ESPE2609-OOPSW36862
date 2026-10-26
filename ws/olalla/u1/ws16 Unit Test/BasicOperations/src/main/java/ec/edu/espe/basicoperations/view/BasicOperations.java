/*
 * Click nbfs://nbhost/SystemFileSystem/Templates/Licenses/license-default.txt to change this license
 */

package ec.edu.espe.basicoperations.view;

import ec.edu.espe.basicoperations.model.Operation;

/**
 *
 * @author Renata Olalla, Binary Bosses, @ESPE
 */
public class BasicOperations {

    public static void main(String[] args) {
        System.out.println("Operations");
        Operation operation;
operation = new Operation();
        System.out.println("the addition of 2 and 3 is-->" +operation.add(2, 3));
        System.out.println("the addition of 1.2 and 2.4 is-->" +operation.add(1.2F,2.4F));
        System.out.println("divide--> "+ operation.divide(0,0));  
        System.out.println("divide--> "+ operation.divide(1,0)); 
    }
}
