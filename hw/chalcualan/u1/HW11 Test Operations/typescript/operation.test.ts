import { Operation } from "./operation";

describe("Operation", () => {
  const operation = new Operation();

  test("adds and rounds to two decimal places", () => {
    expect(operation.add(1.2, 3.4)).toBe(4.6);
  });

  test("subtracts", () => {
    expect(operation.subtract(0, 0)).toBe(0);
  });

  test("multiplies", () => {
    expect(operation.multiply(0, 0)).toBe(0);
  });

  test("divides", () => {
    expect(operation.divide(6, 3)).toBe(2);
  });

  test("throws when dividing by zero", () => {
    expect(() => operation.divide(1, 0)).toThrow("Cannot divide by zero");
  });
});
