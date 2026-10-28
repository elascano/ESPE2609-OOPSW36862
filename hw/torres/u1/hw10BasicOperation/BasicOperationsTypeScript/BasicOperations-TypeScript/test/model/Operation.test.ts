/**
 * @author Paucar Matias, ByteWorks, @ESPE
 */
import { describe, test, expect, beforeEach } from "vitest";
import { Operation } from "../../src/model/Operation";

describe("OperationTest", () => {
    let instance: Operation;

    beforeEach(() => {
        instance = new Operation();
    });

    test("testAdd", () => {
        console.log("add");
        const addend1 = 1.0;
        const addend2 = 3.4;
        const expResult = 4.6;
        const result = instance.add(addend1, addend2);
        expect(result).toBe(expResult);
    });

    test("testAddOtherDecimals", () => {
        console.log("add");
        // read from file and initialize the values
        const addend1 = 1.4;
        const addend2 = 3.4;
        const expResult = 4.8;
        const result = instance.add(addend1, addend2);
        expect(result).toBe(expResult);
    });

    test("testSubtract", () => {
        console.log("subtract");
        const minuhend = 2.0;
        const subtrahend = 4.0;
        const expResult = -2.0;
        const result = instance.subtract(minuhend, subtrahend);
        expect(result).toBe(expResult);
    });

    test("testMultiply", () => {
        console.log("multiply");
        const multiplicand = 3.0;
        const multiplier = 2.0;
        const expResult = 6.0;
        const result = instance.multiply(multiplicand, multiplier);
        expect(result).toBe(expResult);
    });

    test("testDivide", () => {
        console.log("divide");
        const dividend = 6.0;
        const divisor = 2.0;
        const expResult = 3.0;
        const result = instance.divide(dividend, divisor);
        expect(result).toBe(expResult);
    });
});
