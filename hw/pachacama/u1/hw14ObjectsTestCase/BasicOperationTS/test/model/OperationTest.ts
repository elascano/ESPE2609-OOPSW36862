import { describe, test, before, after, beforeEach, afterEach } from "node:test";
import assert from "node:assert/strict";
import { Operation } from "../../src/model/Operation.ts";

/**
 * @author Andres Pachacama, Byte Work, @ESPE
 */

// same as JUnit's assertEquals(expected, actual, delta)
function assertEquals(expResult: number, result: number, delta: number): void {
    if (expResult === result) {
        return;
    }
    assert.ok(Math.abs(expResult - result) <= delta,
        "expected: <" + expResult + "> but was: <" + result + ">");
}

describe("OperationTest", () => {

    before(() => {          // @BeforeAll  -> setUpClass
    });

    after(() => {           // @AfterAll   -> tearDownClass
    });

    beforeEach(() => {      // @BeforeEach -> setUp
    });

    afterEach(() => {       // @AfterEach  -> tearDown
    });

    /**
     * Test of add method, of class Operation.
     */
    test("testAdd", () => {
        console.log("add");
        const addend1 = 1.2;
        const addend2 = 2.4;
        const instance = new Operation();
        const expResult = 4.6;
        const result = instance.add(addend1, addend2);
        assertEquals(expResult, result, 0);
    });

    /**
     * Test of subtract method, of class Operation.
     */
    test("testSubtract", () => {
        console.log("subtract");
        const minuend = 2.0;
        const subtrahend = 4.0;
        const instance = new Operation();
        const expResult = -2.0;
        const result = instance.subtract(minuend, subtrahend);
        assertEquals(expResult, result, 0);
    });

    /**
     * Test of multiply method, of class Operation.
     */
    test("testMultiply", () => {
        console.log("multiply");
        const multiplicand = 3.0;
        const multiplier = 2.0;
        const instance = new Operation();
        const expResult = 6.0;
        const result = instance.multiply(multiplicand, multiplier);
        assertEquals(expResult, result, 0);
    });

    /**
     * Test of divide method, of class Operation.
     */
    test("testDivide", () => {
        console.log("divide");
        const dividend = 6.0;
        const divisor = 2.0;
        const instance = new Operation();
        const expResult = 3.0;
        const result = instance.divide(dividend, divisor);
        assertEquals(expResult, result, 0);
    });

});
