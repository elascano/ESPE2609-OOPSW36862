import { Operation } from "./operation";

console.log("Operations");
const operation = new Operation();
console.log("the addition of 2 and 3 is -->", operation.add(2, 3));
console.log("the addition of 1.2 and 2.4 is -->", operation.add(1.2, 2.4));

for (const [dividend, divisor] of [[0, 0], [1, 0]]) {
  try {
    console.log("divide -->", operation.divide(dividend, divisor));
  } catch (error) {
    console.log("divide --> Error:", error instanceof Error ? error.message : error);
  }
}
