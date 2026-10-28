import { Operation } from "../model/Operation.js";

/**
 * @author David Clavón, Infinite Loops, @ESPE
 */
export function main(): void {
  console.log("Operations");

  const operation = new Operation();

  console.log("The addition of 1.2 and 2.4 ---> " + operation.add(1.2, 2.4));
  console.log("The addition of 1.2 and 2.4 ---> " + operation.add(1.2, 2.4));
  console.log("The addition of 1.2 and 2.4 ---> " + operation.add(1.2, 2.4));

  console.log("divide ---> " + operation.divide(1, 0));
}

// Ejecuta main() solo cuando se lanza este archivo directamente
import { fileURLToPath } from "node:url";
if (process.argv[1] && fileURLToPath(import.meta.url) === process.argv[1]) {
  main();
}
