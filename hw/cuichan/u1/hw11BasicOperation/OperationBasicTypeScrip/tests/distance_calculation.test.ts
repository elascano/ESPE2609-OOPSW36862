/**
 * @author Leslye Cuichan, Infinite loops, @eESPE
 */

import { DistanceCalculation } from '../src/model/distance_calculation';

describe('DistanceCalculationTest', () => {
    let instance: DistanceCalculation;

    beforeEach(() => {
        instance = new DistanceCalculation();
    });

    test('testCalculateDistanceZeroValues', () => {
        console.log("calculate with zero values");
        const speed = 0.0;
        const time = 0.0;
        const expResult = 0.0;
        const result = instance.calculateDistance(speed, time);
        expect(result).toBeCloseTo(expResult, 3);
    });

    test('testCalculateDistanceValues', () => {
        console.log("calculate with zero values");
        const speed = 4.0;
        const time = 0.0;
        const expResult = 0.0;
        const result = instance.calculateDistance(speed, time);
        expect(result).toBeCloseTo(expResult, 3);
    });

    test('testCalculateDistanceNegativeSpeedFail', () => {
        console.log("calculate with negative speed");
        const speed = -60.0;
        const time = 2.0;
        const expResult = 0.0;
        const result = instance.calculateDistance(speed, time);
        expect(result).toBeCloseTo(expResult, 3);
    });

    test('testCalculateDistanceNegativeTimeFail', () => {
        console.log("calculate with negative time");
        const speed = 80.0;
        const time = -1.5;
        const expResult = 0.0;
        const result = instance.calculateDistance(speed, time);
        expect(result).toBeCloseTo(expResult, 3);
    });

    test('testCalculateDistanceBothNegativeFail', () => {
        console.log("calculate with both negative");
        const speed = -50.0;
        const time = -2.0;
        const expResult = 0.0;
        const result = instance.calculateDistance(speed, time);
        expect(result).toBeCloseTo(expResult, 3);
    });

    test('testCalculateDistance', () => {
        console.log("calculate with precision decimals");
        const speed = 100.0;
        const time = 3.0001;
        const expResult = 300.01;
        const result = instance.calculateDistance(speed, time);
        expect(result).toBeCloseTo(expResult, 5);
    });

    test('testCalculateDistanceDecimalPrecision', () => {
        console.log("calculate with decimals");
        const speed = 12.5;
        const time = 0.030;
        const expResult = 0.375;
        const result = instance.calculateDistance(speed, time);
        expect(result).toBeCloseTo(expResult, 3);
    });

    test('testCalculateDistanceZeroDeltaFail', () => {
        console.log("calculate with zero delta");
        const speed = 10.1;
        const time = 3.0;
        const expResult = 30.3;
        const result = instance.calculateDistance(speed, time);
        expect(result).toBe(expResult);
    });

    test('testCalculateDistanceZeroSpeed', () => {
        console.log("calculate with zero speed");
        const speed = 0.0;
        const time = 15.5;
        const expResult = 0.0;
        const result = instance.calculateDistance(speed, time);
        expect(result).toBeCloseTo(expResult, 2);
    });

    test('testCalculateDistanceZeroTime', () => {
        console.log("calculate with zero time");
        const speed = 95.0;
        const time = 0.0;
        const expResult = 0.0;
        const result = instance.calculateDistance(speed, time);
        expect(result).toBe(expResult);
    });

    test('testCalculateDistanceLargeNumbersFail', () => {
        console.log("calculate with large numbers");
        const speed = 3.4;
        const time = 7.82e1;
        const expResult = 6.8;
        const result = instance.calculateDistance(speed, time);
        expect(result).toBeCloseTo(expResult, 3);
    });
});