import { Distance } from './Distance';

describe('DistanceTest', () => {
    const floatMax = 3.4028235e+38;
    const floatMin = 1.4e-45;

    beforeAll(() => {
        // setUpClass
    });

    afterAll(() => {
        // tearDownClass
    });

    beforeEach(() => {
        // setUp
    });

    afterEach(() => {
        // tearDown
    });

    test('testComputeDistanceComplexDecimal', () => {
        console.log("computeDistance with complex decimals");
        const speed = 123.56;
        const time = 21.37;
        const instance = new Distance();
        const expResult = 2640.4772;
        const result = instance.computeDistance(speed, time);
        expect(Math.abs(result - expResult)).toBeLessThanOrEqual(0.001);
    });

    test('testComputeDistanceSmallValues', () => {
        console.log("computeDistance with small decimal values");
        const speed = 0.000123;
        const time = 0.00456;
        const instance = new Distance();
        const expResult = 0.00000056088;
        const result = instance.computeDistance(speed, time);
        expect(Math.abs(result - expResult)).toBeLessThanOrEqual(0.000000001);
    });

    test('testComputeDistanceLargeValues', () => {
        console.log("computeDistance with large values");
        const speed = 98765.43;
        const time = 123.45;
        const instance = new Distance();
        const expResult = 12192592.0;
        const result = instance.computeDistance(speed, time);
        expect(Math.abs(result - expResult)).toBeLessThanOrEqual(1.0);
    });

    test('testComputeDistanceNegativeTime', () => {
        console.log("computeDistance with negative time");
        const speed = 123.56;
        const time = -21.0;
        const instance = new Distance();
        const expResult = 0.0;
        const result = instance.computeDistance(speed, time);
        expect(result).toBe(expResult);
    });

    test('testComputeDistanceOverflow', () => {
        console.log("computeDistance with float overflow");
        const speed = floatMax;
        const time = 1000.0;
        const instance = new Distance();
        const expResult = floatMax;
        const result = instance.computeDistance(speed, time);
        expect(result).toBe(expResult);
    });

    test('testComputeDistanceIndetermination', () => {
        console.log("computeDistance with NaN input");
        const speed = NaN;
        const time = 123.45;
        const instance = new Distance();
        const expResult = 0.0;
        const result = instance.computeDistance(speed, time);
        expect(result).toBe(expResult);
    });

    test('testComputeDistanceInfiniteInput', () => {
        console.log("computeDistance with infinite input");
        const speed = Infinity;
        const time = 999.99;
        const instance = new Distance();
        const expResult = 999.99;
        const result = instance.computeDistance(speed, time);
        expect(result).toBe(expResult);
    });

    test('testComputeDistanceNegativeSpeed', () => {
        console.log("computeDistance with negative speed");
        const speed = -987.654;
        const time = 123.456;
        const instance = new Distance();
        const expResult = 0.0;
        const result = instance.computeDistance(speed, time);
        expect(result).toBe(expResult);
    });

    test('testComputeDistanceNegativeOverflow', () => {
        console.log("computeDistance with negative overflow");
        const speed = -floatMax;
        const time = 2.0;
        const instance = new Distance();
        const expResult = 0.0;
        const result = instance.computeDistance(speed, time);
        expect(result).toBe(expResult);
    });

    test('testComputeDistanceMinimumFloat', () => {
        console.log("computeDistance with minimum positive float");
        const speed = floatMin;
        const time = 0.5;
        const instance = new Distance();
        const expResult = floatMin;
        const result = instance.computeDistance(speed, time);
        expect(result).toBe(expResult);
    });
});