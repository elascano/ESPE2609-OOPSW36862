export class Operation {
  add(addend1: number, addend2: number): number {
    const sum = addend1 + addend2;
    return Math.round(sum * 100) / 100;
  }

  subtract(minuend: number, subtrahend: number): number {
    return minuend - subtrahend;
  }

  multiply(multiplicand: number, multiplier: number): number {
    return multiplicand * multiplier;
  }

  divide(dividend: number, divisor: number): number {
    if (divisor === 0) {
      throw new Error("Cannot divide by zero");
    }
    return dividend / divisor;
  }
}
