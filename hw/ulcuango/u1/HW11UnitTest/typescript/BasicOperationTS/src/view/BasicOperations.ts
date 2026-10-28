import { Operation } from '../model/Operation';

export class BasicOperations {
    public static main(): void {
        console.log("Operations!");

        const operation = new Operation();
        console.log("the adition of 1.2 and 2.4 is -->" + operation.add(1.2, 2.4));
    }
}

BasicOperations.main();