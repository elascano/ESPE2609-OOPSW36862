import { Operation } from '../src/model/Operation';

describe('OperationTest', () => {
    test('testAdd', () => {
        console.log("add");
        const addend1 = 1.2;
        const addend2 = 3.4;
        const instance = new Operation();
        const expResult = 4.6;
        const result = instance.add(addend1, addend2);
        expect(result).toBeCloseTo(expResult, 1);
    });

    test('testSubtract', () => {
        console.log("subtract");
        const minuhend = 2.0;
        const subtrahend = 4.0;
        const instance = new Operation();
        const expResult = -2.0;
        const result = instance.subtract(minuhend, subtrahend);
        expect(result).toBe(expResult);
    });

    test('testMultiply', () => {
        console.log("multiply");
        const multiplicand = 3.0;
        const multiplier = 2.0;
        const instance = new Operation();
        const expResult = 6.0;
        const result = instance.multiply(multiplicand, multiplier);
        expect(result).toBe(expResult);
    });

    test('testDivide', () => {
        console.log("divide");
        const dividend = 6.0;
        const divisor = 2.0;
        const instance = new Operation();
        const expResult = 3.0;
        const result = instance.divide(dividend, divisor);
        expect(result).toBe(expResult);
    });
});