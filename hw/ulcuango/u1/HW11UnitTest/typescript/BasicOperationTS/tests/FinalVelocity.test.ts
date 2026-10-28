import { FinalVelocity } from '../src/model/FinalVelocity';
describe('FinalVelocityTest', () => {
    test('calculateFinalVelocity', () => {
        const fv = new FinalVelocity(10.0, 2.0, 3.0);
        const expResult = 16.0;
        expect(fv.calculateFinalVelocity()).toBe(expResult);
    });

    test('negative time exception', () => {
        expect(() => {
            new FinalVelocity(10.0, 2.0, -1.0);
        }).toThrow("Time cannot be negative");
    });

    test('set negative time exception', () => {
        const fv = new FinalVelocity(10.0, 2.0, 3.0);
        expect(() => {
            fv.setTime(-5.0);
        }).toThrow("Time cannot be negative");
    });
});