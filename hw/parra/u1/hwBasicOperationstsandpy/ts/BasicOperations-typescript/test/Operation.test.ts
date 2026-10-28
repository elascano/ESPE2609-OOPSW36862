import { test } from "node:test";
import assert from "node:assert/strict";
import { Operation } from "../src/model/Operation.js";

const near = (a: number, b: number) => assert.ok(Math.abs(a - b) < 1e-9, `${a} != ${b}`);

test("add", () => {
  const op = new Operation();
  near(op.add(1.2, 2.4), 3.6);
  assert.equal(op.add(0, 0), 0);
});

test("substract", () => {
  const op = new Operation();
  near(op.substract(5.5, 2.5), 3);
  assert.equal(op.substract(0, 0), 0);
});

test("multiply", () => {
  const op = new Operation();
  near(op.multiply(1.5, 4), 6);
  assert.equal(op.multiply(0, 7), 0);
});

test("divide", () => {
  near(new Operation().divide(9, 3), 3);
});

test("divide by zero returns Infinity", () => {
  const op = new Operation();
  assert.equal(op.divide(1, 0), Infinity);
  assert.equal(op.divide(-1, 0), -Infinity);
});

test("0/0 is NaN (el caso que fallaba en el test generado de Java)", () => {
  assert.ok(Number.isNaN(new Operation().divide(0, 0)));
});
