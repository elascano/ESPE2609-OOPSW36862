/**
 * @author Leslye Cuichan, Infinite loops, @eESPE
 */

export class Operation {

    public add(addend1: number, addend2: number): number {
        let sum: number;
        sum = addend1 + addend2;
        sum = Math.round(sum * 100.0) / 100.0;
        return sum;
    }

    public subtract(minuhend: number, subtrahend: number): number {
        let difference: number;
        difference = minuhend - subtrahend;
        return difference;
    }

    public multiply(multiplicand: number, multiplier: number): number {
        let product: number;
        product = multiplicand * multiplier;
        return product;
    }

    public divide(dividend: number, divisor: number): number {
        let quotient: number;
        quotient = dividend / divisor;
        return quotient;
    }
}