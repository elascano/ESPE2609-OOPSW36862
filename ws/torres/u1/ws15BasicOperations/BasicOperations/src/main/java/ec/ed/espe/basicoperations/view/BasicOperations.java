package ec.edu.espe.basicoperations.view;

import ec.edu.espe.basicoperations.model.Operation;

/**
 *
 * @author Andy Tiban, IV Studio, @ESPE
 */
public class BasicOperations {
    
  public static void main(String[] args) {
    System.out.println("Operations!");
    //declare and instantiate an object
    //declare and aniliazi parameters
    //call the method
    Operation operation;
    operation = new Operation();
    System.out.println("the addition of 2 and 3 is --->"+operation.add(1.2F,2.4F));
    System.out.println("the addition of 2 and 3 is --->"+operation.add(1.2F,2.4F));
    System.out.println("the addition of 2 and 3 is --->"+operation.add(1.2F,2.4F));
    
      System.out.println("divide --->" +operation.divide(8,0));
}
    

    
}
