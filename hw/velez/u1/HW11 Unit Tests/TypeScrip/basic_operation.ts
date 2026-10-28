/**
 * Operaciones básicas + Ley de Ohm, con pruebas unitarias.
 * Ejecutar:  node --test basic_operation.ts
 * @author Yandry Velez, CodeLex, @ESPE
 */
import { describe, test, beforeEach } from "node:test";
import assert from "node:assert/strict";

class Operation {
  add(a: number, b: number): number {
    return Math.round((a + b) * 100) / 100;
  }
  subtract(a: number, b: number): number {
    return a - b;
  }
  multiply(a: number, b: number): number {
    return a * b;
  }
  divide(a: number, b: number): number {
    return a / b; // b = 0 devuelve Infinity (igual que en Java)
  }
}

class Square {
  calculateVoltage(current: number, resistance: number): number {
    return current * resistance;
  }
  calculateCurrent(voltage: number, resistance: number): number {
    return voltage / resistance;
  }
  calculateResistance(voltage: number, current: number): number {
    return voltage / current;
  }
}

// Compara decimales con tolerancia (como assertEquals con delta en JUnit)
function assertClose(expected: number, result: number, delta = 0.01): void {
  assert.ok(
    Math.abs(expected - result) <= delta,
    `expected: ${expected} but was: ${result}`
  );
}

describe("BasicOperationTest", () => {
  let op: Operation;
  let sq: Square;

  beforeEach(() => {
    // se ejecuta antes de cada prueba (@BeforeEach)
    op = new Operation();
    sq = new Square();
  });

  // ---- Operation (5) ----
  test("testAdd", () => assertClose(4.8, op.add(1.4, 3.4)));

  test("testSubtract", () => assert.equal(op.subtract(2.0, 4.0), -2.0));

  test("testMultiply", () => assert.equal(op.multiply(3.0, 2.0), 6.0));

  test("testDivide", () => assert.equal(op.divide(6.0, 2.0), 3.0));

  test("testDivideByZero", () => assert.equal(op.divide(1, 0), Infinity));

  // ---- Square / Ley de Ohm (5) ----
  test("testCalculateVoltage", () =>
    assert.equal(sq.calculateVoltage(4.0, 5.0), 20.0));

  test("testCalculateCurrent", () =>
    assert.equal(sq.calculateCurrent(500.0, 2.5), 200.0));

  test("testCalculateResistance", () =>
    assert.equal(sq.calculateResistance(500.0, 2.5), 200.0));

  test("testCalculateResistanceDecimal", () =>
    // 2/3 = 0.6666...
    assertClose(0.67, sq.calculateResistance(2.0, 3.0)));

  test("testCalculateCurrentDecimal", () =>
    // 13/3 = 4.3333...
    assertClose(4.33, sq.calculateCurrent(13.0, 3.0)));
});
