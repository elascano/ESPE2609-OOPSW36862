package ec.edu.espe.basicoperations.view;

import ec.edu.espe.basicoperations.model.Operation;

/**
 *
 * @author Carlos Parra, ByteWorks, @ESPE
 */
public class BasicOperations {
    public static void main (String[] args){
       System.out.println("Operations");
        
        Operation operation;
        operation = new Operation();
        
        System.out.println("The addition of 1.2 and 2.4 ---> "+operation.add(1.2F,2.4F));
        System.out.println("The addition of 1.2 and 2.4 ---> "+operation.add(1.2F,2.4F));
        System.out.println("The addition of 1.2 and 2.4 ---> "+operation.add(1.2F,2.4F));
        
        System.out.println("divide ---> "+ operation.divide(1, 0) );
    }
}
