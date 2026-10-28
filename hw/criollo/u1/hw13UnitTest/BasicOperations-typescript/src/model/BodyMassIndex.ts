/**
 * Domain model: Body Mass Index (BMI) calculation and classification.
 * Port of ec.edu.espe.bodymassindex.model.BodyMassIndex (Java).
 */

export enum BmiCategory {
  Underweight = "Underweight",
  NormalWeight = "Normal weight",
  Overweight = "Overweight",
  Obesity = "Obesity",
}

export class BodyMassIndex {
  static readonly UNDERWEIGHT_LIMIT = 18.5;
  static readonly NORMAL_LIMIT = 25.0;
  static readonly OVERWEIGHT_LIMIT = 30.0;

  /**
   * Returns BMI (kg / m²) rounded to 2 decimals.
   * Math.round rounds half up, same as Java's Math.round.
   *
   * @throws RangeError if weight or height is not a finite number greater than 0.
   */
  calculateBmi(weightKg: number, heightM: number): number {
    BodyMassIndex.validatePositive("weight", weightKg);
    BodyMassIndex.validatePositive("height", heightM);

    const bmi = weightKg / (heightM * heightM);
    return Math.round(bmi * 100) / 100;
  }

  /** Lower bounds are inclusive: 18.5 -> Normal weight. */
  classify(bmi: number): BmiCategory {
    if (bmi < BodyMassIndex.UNDERWEIGHT_LIMIT) return BmiCategory.Underweight;
    if (bmi < BodyMassIndex.NORMAL_LIMIT) return BmiCategory.NormalWeight;
    if (bmi < BodyMassIndex.OVERWEIGHT_LIMIT) return BmiCategory.Overweight;
    return BmiCategory.Obesity;
  }

  private static validatePositive(name: string, value: number): void {
    if (typeof value !== "number") {
      throw new TypeError(`The ${name} must be a number`);
    }
    // Number.isFinite also rejects NaN and ±Infinity (they slipped through in the Java version).
    if (!Number.isFinite(value) || value <= 0) {
      throw new RangeError(`The ${name} must be greater than 0`);
    }
  }
}
