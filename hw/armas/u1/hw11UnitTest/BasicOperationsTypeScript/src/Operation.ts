export class Operation {
  add(addend1: number, addend2: number): number {
    const total = addend1 + addend2;
    return Math.floor(total * 100.0 + 0.5);
  }

  subtract(minuend: number, subtrahend: number): number {
    return minuend - subtrahend;
  }

  multiply(multiplicand: number, multiplier: number): number {
    return multiplicand * multiplier;
  }

  divide(dividend: number, divisor: number): number {
    if (divisor === 0) {
      throw new RangeError("No se puede dividir entre cero.");
    }
    return dividend / divisor;
  }
}
