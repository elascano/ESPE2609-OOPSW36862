package ec.edu.espe.basicoperations.view;

import ec.edu.espe.basicoperations.model.Operation;
/**
 *
 * @author Mateo Unda, CodeLex, @ESPE
 */
public class BasicOperations {

    public static void main(String[] args) {
        System.out.println("Operations");
        
        //declare and instantiate an object
        //declare and aniliazi parameters
        //call the methos
        
        Operation operation;
        operation = new Operation();
        operation.add(2, 3);
        System.out.println("the addition of 1.2 and 2.4 is -->" + operation.add(1.2F,2.4F));
        System.out.println("the addition of 1.2 and 2.4 is -->" + operation.add(1.2F,2.4F));
        System.out.println("the addition of 1.2 and 2.4 is -->" + operation.add(1.2F,2.4F));
        
        System.out.println("divide -->" + operation.divide(0, 0));
        //enter data from keyword
        //call the method
        //observ the results
        
    }
}
