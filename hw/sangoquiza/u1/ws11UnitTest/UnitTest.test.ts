export class TemperatureConverter {
    celsiusToFahrenheit(celsius: number): number {
        return (celsius * 9.0 / 5.0) + 32.0;
    }

    fahrenheitToCelsius(fahrenheit: number): number {
        return (fahrenheit - 32.0) * 5.0 / 9.0;
    }
}

describe('TemperatureConverter', () => {
    let instance: TemperatureConverter;

    beforeEach(() => {
        instance = new TemperatureConverter();
    });

    test('testCelsiusToFahrenheitFreezing', () => {
        expect(instance.celsiusToFahrenheit(0.0)).toBeCloseTo(32.0, 4);
    });

    test('testCelsiusToFahrenheitBoiling', () => {
        expect(instance.celsiusToFahrenheit(100.0)).toBeCloseTo(212.0, 4);
    });

    test('testCelsiusToFahrenheitCrossPoint', () => {
        expect(instance.celsiusToFahrenheit(-40.0)).toBeCloseTo(-40.0, 4);
    });

    test('testFahrenheitToCelsiusFreezing', () => {
        expect(instance.fahrenheitToCelsius(32.0)).toBeCloseTo(0.0, 4);
    });

    test('testFahrenheitToCelsiusBoiling', () => {
        expect(instance.fahrenheitToCelsius(212.0)).toBeCloseTo(100.0, 4);
    });

    test('testFahrenheitToCelsiusBodyTemp', () => {
        expect(instance.fahrenheitToCelsius(98.6)).toBeCloseTo(37.0, 4);
    });

    test('testFahrenheitToCelsiusCrossPoint', () => {
        expect(instance.fahrenheitToCelsius(-40.0)).toBeCloseTo(-40.0, 4);
    });

    test('testCelsiusToFahrenheitIntentionalFail', () => {
        expect(instance.celsiusToFahrenheit(10.0)).toBeCloseTo(60.0, 4);
    });

    test('testFahrenheitToCelsiusIntentionalFail', () => {
        expect(instance.fahrenheitToCelsius(50.0)).toBeCloseTo(0.0, 4);
    });

    test('testCelsiusToFahrenheitPrecisionFail', () => {
        expect(instance.celsiusToFahrenheit(37.0)).toBeCloseTo(98.0, 4);
    });
});