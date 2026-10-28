export class Operation {
    public add(addend1: number, addend2: number): number {
        let sum: number = addend1 + addend2;
        sum = Math.round(sum * 100.0) / 100.0;
        return sum;
    }

    public subtract(minuhend: number, subtrahend: number): number {
        let difference: number = minuhend - subtrahend;
        return difference;
    }

    public multiply(multiplicand: number, multiplier: number): number {
        let product: number = multiplicand * multiplier;
        return product;
    }

    public divide(dividend: number, divisor: number): number {
        if (divisor === 0) {
            throw new Error("Division by zero");
        }
        let quotient: number = dividend / divisor;
        return quotient;
    }
}