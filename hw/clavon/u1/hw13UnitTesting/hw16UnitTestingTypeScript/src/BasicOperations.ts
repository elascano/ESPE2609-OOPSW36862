import { Operation } from './Operation';

export class BasicOperations {
    public static main(): void {
        console.log("Operations");

        const operation: Operation = new Operation();

        console.log("The addition of 1.2 and 2.4 ---> " + operation.add(1.2, 2.4));
        console.log("The addition of 1.2 and 2.4 ---> " + operation.add(1.2, 2.4));
        console.log("The addition of 1.2 and 2.4 ---> " + operation.add(1.2, 2.4));

        try {
            console.log("divide ---> " + operation.divide(1, 0));
        } catch (error: any) {
            console.log("divide ---> Error: " + error.message);
        }
    }
}

BasicOperations.main();