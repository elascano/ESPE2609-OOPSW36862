/**
 * @author Paucar Matias, ByteWorks, @ESPE
 */
import { Operation } from "../model/Operation";

function main(): void {
    console.log("Operations!");

    // declare and instantiate an object
    // declare and initialize parameters
    // call the method

    let operation: Operation;
    operation = new Operation();
    console.log("The addition of 1.2 and 2.4 is -->" + operation.add(1.2, 2.4));
    console.log("The addition of 1.2 and 2.4 is -->" + operation.add(1.2, 2.4));
    console.log("The addition of 1.2 and 2.4 is -->" + operation.add(1.2, 2.4));

    console.log("divide --> " + operation.divide(1, 0));

    // enter data from keyboard
    // call the method
    // observe the result
}

main();
