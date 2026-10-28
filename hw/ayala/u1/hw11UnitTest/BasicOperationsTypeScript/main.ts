import { Operation } from "./src/Operation.ts";
import { AreaOfCircle } from "./src/AreaOfCircle.ts";

const operation = new Operation();
const circle = new AreaOfCircle();

console.log("Suma (1.2 + 3.4):", operation.add(1.2, 3.4));
console.log("Resta (2 - 4):", operation.subtract(2.0, 4.0));
console.log("Multiplicación (3 * 2):", operation.multiply(3.0, 2.0));
console.log("División (6 / 2):", operation.divide(6.0, 2.0));
console.log("Área del círculo (radio 2):", circle.calculateAreaOfCircle(2.0));
