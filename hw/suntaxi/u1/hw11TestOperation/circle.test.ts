import { Circle } from './circle';

describe('CircleTest', () => {
    let circle: Circle;

    beforeEach(() => {
        circle = new Circle();
    });

    test('testCalculateAreaRadiusOne', () => {
        const result = circle.calculateArea(1.0);
        expect(result).toBeCloseTo(Math.PI, 3);
    });

    test('testCalculateAreaRadiusFive', () => {
        const result = circle.calculateArea(5.0);
        expect(result).toBeCloseTo(78.5398, 3);
    });

    test('testCalculateAreaRadiusDecimal', () => {
        const result = circle.calculateArea(2.5);
        expect(result).toBeCloseTo(19.6349, 3);
    });

    test('testCalculateAreaRadiusZero', () => {
        const result = circle.calculateArea(0.0);
        expect(result).toBeCloseTo(0.0, 3);
    });

    test('testCalculateAreaRadiusLarge', () => {
        const result = circle.calculateArea(100.0);
        expect(result).toBeCloseTo(31415.9265, 3);
    });

    test('testCalculateAreaRadiusNegative', () => {
        expect(() => {
            circle.calculateArea(-5.0);
        }).toThrow("Radius cannot be negative");
    });

    test('testCalculatePerimeterRadiusThree', () => {
        const result = circle.calculatePerimeter(3.0);
        expect(result).toBeCloseTo(18.8495, 3);
    });

    test('testCalculatePerimeterRadiusSmall', () => {
        const result = circle.calculatePerimeter(0.5);
        expect(result).toBeCloseTo(3.1415, 3);
    });

    test('testCalculatePerimeterRadiusTen', () => {
        const result = circle.calculatePerimeter(10.0);
        expect(result).toBeCloseTo(62.8318, 3);
    });

    test('testCalculatePerimeterRadiusNegative', () => {
        expect(() => {
            circle.calculatePerimeter(-2.0);
        }).toThrow("Radius cannot be negative");
    });
});