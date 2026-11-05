import { BodyMassIndex } from "./bodyMassIndex";

describe("BodyMassIndex", () => {
  const bmi = new BodyMassIndex();

  test("negative weight returns a negative BMI", () => {
    expect(bmi.calculateBMI(-64, 1.6)).toBeCloseTo(-25, 2);
  });

  test("zero height throws", () => {
    expect(() => bmi.calculateBMI(60, 0)).toThrow("Height cannot be zero");
  });

  test("calculates BMI for 70 kg and 1.7 m", () => {
    expect(bmi.calculateBMI(70, 1.7)).toBeCloseTo(24.22, 2);
  });

  test("zero weight", () => {
    expect(bmi.calculateBMI(0, 1.7)).toBe(0);
  });

  test("integer values", () => {
    expect(bmi.calculateBMI(80, 2)).toBe(20);
  });

  test("small weight", () => {
    expect(bmi.calculateBMI(20, 1.264911)).toBeCloseTo(12.5, 1);
  });

  test("large weight", () => {
    expect(bmi.calculateBMI(100, 1.581139)).toBeCloseTo(40, 1);
  });

  test("height one", () => {
    expect(bmi.calculateBMI(60, 1)).toBe(60);
  });

  test("decimal values", () => {
    expect(bmi.calculateBMI(65, 1.687)).toBeCloseTo(22.84, 1);
  });

  test("negative height gives the same BMI as positive height", () => {
    expect(bmi.calculateBMI(64, -1.6)).toBeCloseTo(25, 2);
  });
});
