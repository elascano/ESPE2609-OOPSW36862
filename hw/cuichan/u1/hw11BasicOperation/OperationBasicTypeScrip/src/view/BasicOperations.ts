/**
 * @author Leslye Cuichan, Infinite loops, @eESPE
 */

import { Operation } from '../model/operation';
import { DistanceCalculation } from '../model/distance_calculation';

export class BasicOperations {
    public static main(): void {
        console.log("Operations!");
        
        // declare and instantiate an object
        // declare and initialize parameters
        // call the method
        
        let operation: Operation;
        operation = new Operation();
        console.log("the addition of 2 and 3 is --> " + operation.add(1.2, 2.4));
        console.log("the addition of 1.2 and 2.4 is --> " + operation.add(1.2, 2.4));
        console.log("the addition of 1.2 and 2.4 is --> " + operation.add(1.2, 2.4));
        console.log("the division of 1 and 0 is --> " + operation.divide(1, 0));

        let distanceCalculation: DistanceCalculation;
        distanceCalculation = new DistanceCalculation();
        console.log("the distance for speed 60.0 and time 2.0 is --> " + distanceCalculation.calculateDistance(60.0, 2.0));
    }
}

// Ejecutar el método principal
BasicOperations.main();