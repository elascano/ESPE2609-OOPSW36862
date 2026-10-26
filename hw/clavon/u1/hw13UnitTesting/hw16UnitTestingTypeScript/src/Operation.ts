export class Operation {

    public add(addend1: number, addend2: number): number {
        let sum: number = addend1 + addend2;
        sum = Math.round(sum * 100.0) / 100.0;
        return sum;
    }

    public substract(minuhed: number, subtrahend: number): number {
        return minuhed - subtrahend;
    }

    public multiply(multiplicand: number, multiplier: number): number {
        return multiplicand * multiplier;
    }

    public divide(dividend: number, divisor: number): number {
        if (divisor === 0) {
            throw new Error("No se puede dividir por cero");
        }
        return dividend / divisor;
    }
}