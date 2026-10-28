import { Operation } from '../src/Operation';

describe('Operation Test', () => {
    let instance: Operation;

    beforeEach(() => {
        instance = new Operation();
    });

    test('testAdd', () => {
        expect(instance.add(1.2, 3.4)).toBeCloseTo(4.6, 5);
    });

    test('testSubstract', () => {
        expect(instance.substract(2.0, 4.0)).toBe(-2.0);
    });

    test('testMultiply', () => {
        expect(instance.multiply(3.0, 2.0)).toBe(6.0);
    });

    test('testDivide', () => {
        expect(instance.divide(6.0, 2.0)).toBe(3.0);
    });
});