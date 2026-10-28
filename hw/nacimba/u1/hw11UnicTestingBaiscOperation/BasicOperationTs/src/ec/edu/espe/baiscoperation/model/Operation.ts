import { toFloat32 } from "../../../../../utils/javaFloat";

/** @author Nacimba Mateo, Group5, @ESPE */
export class Operation {
  add(addend1: number, addend2: number): number {
    const sum = toFloat32(toFloat32(addend1) + toFloat32(addend2));
    return sum;
  }

  substract(minuhend: number, subtrahend: number): number {
    const difference = toFloat32(toFloat32(minuhend) - toFloat32(subtrahend));
    return difference;
  }

  multiply(multiplicand: number, multiplier: number): number {
    const product = toFloat32(toFloat32(multiplicand) * toFloat32(multiplier));
    return product;
  }

  divide(dividend: number, divisor: number): number {
    // JavaScript ya sigue IEEE 754: x/0 = ±Infinity, 0/0 = NaN
    const quotient = toFloat32(toFloat32(dividend) / toFloat32(divisor));
    return quotient;
  }
}
