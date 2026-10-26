package ec.edu.espe.basicoperations.view;

import ec.edu.espe.basicoperations.model.Operation;

/**
 *
 * @author Demian Armas, Logical Lords, @ESPE
 */
public class BasicOperations {

public static void main(String[] args){
    System.out.println("Operations!");
    
    //
    //declare and analiazit
    //call the method
    Operation operation;
    operation = new Operation();
    System.out.println("the addition of 1.2 and 2.4 is --> " + operation.add(1.2F, 2.4F));
    System.out.println("the addition of 1.2 and 2.4 is --> " + operation.subtract(1.2F, 2.4F));
    System.out.println("the addition of 1.2 and 2.4 is --> " + operation.multiply(1.2F, 2.4F));
    
    System.out.println("divide --> " + operation.divide(1, 0));
    
    //enter data from keyboard
    //call the method
    //observ the results

}
    
}
