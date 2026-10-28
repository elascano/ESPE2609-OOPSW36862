/**
 * @author David Clavón, Infinite Loops, @ESPE
 */
export class Operation {
  add(addend1: number, addend2: number): number {
    const sum = addend1 + addend2;
    return sum;
  }

  substract(minuhed: number, subtrahend: number): number {
    const difference = minuhed - subtrahend;
    return difference;
  }

  multiply(multiplicand: number, multiplier: number): number {
    const product = multiplicand * multiplier;
    return product;
  }

  // Igual que Java (float): x/0 -> ±Infinity, 0/0 -> NaN
  divide(dividend: number, divisor: number): number {
    const quotient = dividend / divisor;
    return quotient;
  }
}
