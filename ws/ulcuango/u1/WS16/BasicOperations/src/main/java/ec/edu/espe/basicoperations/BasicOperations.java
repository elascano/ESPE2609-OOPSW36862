package ec.edu.espe.basicoperations.view;

import ec.edu.espe.basicoperations.model.Operation;

/**
 *
 * @author Josselyn Ulcuango, CodeLex, @ESPE
 */
public class BasicOperations {

    public static void main(String[] args) {
        System.out.println("Operations!");

        Operation operation;
        operation = new Operation ();
        System.out.println("the adition of 1.2 and 2.4 is -->" + operation.add(1.2F, 2.4F));
        
    }
}
