/**
 * @author Leslye Cuichan, Infinite loops, @eESPE
 */

import { Operation } from '../src/model/operation';

describe('OperationTest', () => {
    let instance: Operation;

    beforeEach(() => {
        instance = new Operation();
    });

    /**
     * Test of add method, of class Operation.
     */
    test('testAdd', () => {
        console.log("add");
        const addend1 = 1.2;
        const addend2 = 3.4;
        const expResult = 4.6;
        const result = instance.add(addend1, addend2);
        expect(result).toBeCloseTo(expResult, 5);
    });

    /**
     * Test of subtract method, of class Operation.
     */
    test('testSubtract', () => {
        console.log("subtract");
        const minuhend = 2.0;
        const subtrahend = 4.0;
        const expResult = -2.0;
        const result = instance.subtract(minuhend, subtrahend);
        expect(result).toBeCloseTo(expResult, 5);
    });

    /**
     * Test of multiply method, of class Operation.
     */
    test('testMultiply', () => {
        console.log("multiply");
        const multiplicand = 10.0;
        const multiplier = 0.0;
        const expResult = 0.0;
        const result = instance.multiply(multiplicand, multiplier);
        expect(result).toBeCloseTo(expResult, 5);
    });

    /**
     * Test of divide method, of class Operation.
     */
    test('testDivide', () => {
        console.log("divide");
        const dividend = 0.0;
        const divisor = 0.0;
        const expResult = 0.0;
        const result = instance.divide(dividend, divisor);
        expect(result).toBeCloseTo(expResult, 5);
    });
});