import assert from "node:assert/strict";
import { beforeEach, describe, it } from "node:test";
import { Operation } from "../src/Operation.ts";
import { assertAlmostEqual } from "./assertAlmostEqual.ts";

describe("OperationTest", () => {
  let operation: Operation;

  beforeEach(() => {
    operation = new Operation();
  });

  it("test_add", () => {
    const result = operation.add(1.2, 3.4);
    assertAlmostEqual(result, 4.6, 0);
  });

  it("test_subtract", () => {
    const result = operation.subtract(2.0, 4.0);
    assertAlmostEqual(result, -2.0, 1e-6);
  });

  it("test_multiply", () => {
    const result = operation.multiply(3.0, 2.0);
    assertAlmostEqual(result, 6.0, 1e-6);
  });

  it("test_divide", () => {
    const result = operation.divide(6.0, 2.0);
    assertAlmostEqual(result, 3.0, 1e-6);
  });

  it("test_add_rounds_to_two_decimals", () => {
    const result = operation.add(1.236, 2.0);
    assertAlmostEqual(result, 3.24, 1e-6);
  });

  it("test_add_rounds_positive_half_as_java", () => {
    const result = operation.add(1.125, 0.0);
    assertAlmostEqual(result, 1.13, 1e-6);
  });

  it("test_add_rounds_negative_half_as_java", () => {
    const result = operation.add(-1.125, 0.0);
    assertAlmostEqual(result, -1.12, 1e-6);
  });

  it("test_add_negative_numbers", () => {
    const result = operation.add(-1.2, -3.4);
    assertAlmostEqual(result, -4.6, 1e-6);
  });

  it("test_subtract_decimals", () => {
    const result = operation.subtract(4.7, 1.2);
    assertAlmostEqual(result, 3.5, 1e-6);
  });

  it("test_multiply_by_zero", () => {
    const result = operation.multiply(7.5, 0.0);
    assert.equal(result, 0.0);
  });

  it("test_divide_decimal_result", () => {
    const result = operation.divide(7.0, 2.0);
    assertAlmostEqual(result, 3.5, 1e-6);
  });

  it("test_divide_negative_dividend", () => {
    const result = operation.divide(-6.0, 2.0);
    assertAlmostEqual(result, -3.0, 1e-6);
  });

  it("test_divide_by_zero", () => {
    assert.throws(() => operation.divide(6.0, 0.0), RangeError);
  });
});
