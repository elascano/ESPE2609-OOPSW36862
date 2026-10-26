package ec.edu.espe.basicoperations.view;

import ec.edu.espe.basicoperations.model.Operation;
import ec.edu.espe.basicoperations.model.DistanceCalculation;
/**
 *
 * @author Leslye Cuichan, Infinite loops, @eESPE
 */
public class BasicOperations {

    public static void main(String[] args) {
        System.out.println("Operations!");
        
        //declare and instantiate an object
        //declare and aniliazi parameters
        //call the method
        
        Operation operation;
        operation = new Operation();
        System.out.println("the addition of 2 and 3 is --> " + operation.add(1.2F , 2.4F));
        System.out.println("the addition of 1.2 and 2.4 is --> " + operation.add(1.2F, 2.4F));
        System.out.println("the addition of 1.2 and 2.4 is --> " + operation.add(1.2F, 2.4F));
        System.out.println("the division of 1 and 0 is --> " + operation.divide(1, 0));
        DistanceCalculation distanceCalculation;
        distanceCalculation = new DistanceCalculation();
        System.out.println("the distance for speed 60.0 and time 2.0 is --> " + distanceCalculation.calculateDistance(60.0F, 2.0F));
        
    }
    
    
}
