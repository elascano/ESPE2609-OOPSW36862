import { describe, it } from "node:test";
import assert from "node:assert/strict";
import { BmiCategory, BodyMassIndex } from "../src/model/BodyMassIndex.js";

const near = (actual: number, expected: number, delta = 0.01) =>
  assert.ok(Math.abs(actual - expected) <= delta, `${actual} is not within ${delta} of ${expected}`);

describe("BodyMassIndex", () => {
  const bmi = new BodyMassIndex();

  describe("normal cases", () => {
    it("normal weight", () => {
      const result = bmi.calculateBmi(70, 1.75);
      near(result, 22.86);
      assert.equal(bmi.classify(result), BmiCategory.NormalWeight);
    });

    it("overweight", () => {
      const result = bmi.calculateBmi(85, 1.8);
      near(result, 26.23);
      assert.equal(bmi.classify(result), BmiCategory.Overweight);
    });

    it("underweight", () => {
      const result = bmi.calculateBmi(48, 1.7);
      near(result, 16.61);
      assert.equal(bmi.classify(result), BmiCategory.Underweight);
    });

    it("obesity", () => {
      assert.equal(bmi.classify(bmi.calculateBmi(110, 1.7)), BmiCategory.Obesity);
    });
  });

  describe("boundaries", () => {
    it("18.5 is Normal weight", () => {
      const result = bmi.calculateBmi(74, 2);
      assert.equal(result, 18.5);
      assert.equal(bmi.classify(result), BmiCategory.NormalWeight);
    });

    it("25.0 is Overweight", () => {
      const result = bmi.calculateBmi(100, 2);
      assert.equal(result, 25);
      assert.equal(bmi.classify(result), BmiCategory.Overweight);
    });

    it("30.0 is Obesity", () => {
      const result = bmi.calculateBmi(120, 2);
      assert.equal(result, 30);
      assert.equal(bmi.classify(result), BmiCategory.Obesity);
    });

    it("just below each boundary", () => {
      assert.equal(bmi.classify(18.49), BmiCategory.Underweight);
      assert.equal(bmi.classify(24.99), BmiCategory.NormalWeight);
      assert.equal(bmi.classify(29.99), BmiCategory.Overweight);
    });
  });

  it("rounds half up", () => {
    assert.equal(bmi.calculateBmi(0.5, 2), 0.13); // 0.125 -> 0.13
  });

  describe("invalid input", () => {
    it("weight = 0", () => {
      assert.throws(() => bmi.calculateBmi(0, 1.7), { name: "RangeError", message: /weight/ });
    });

    it("height = 0", () => {
      assert.throws(() => bmi.calculateBmi(70, 0), { name: "RangeError", message: /height/ });
    });

    it("negative weight", () => {
      assert.throws(() => bmi.calculateBmi(-70, 1.75), RangeError);
    });

    it("negative height", () => {
      assert.throws(() => bmi.calculateBmi(70, -1.75), RangeError);
    });

    it("NaN and Infinity", () => {
      for (const bad of [Number.NaN, Infinity, -Infinity]) {
        assert.throws(() => bmi.calculateBmi(bad, 1.75), RangeError);
        assert.throws(() => bmi.calculateBmi(70, bad), RangeError);
      }
    });

    it("non-numeric input", () => {
      assert.throws(() => bmi.calculateBmi("70" as unknown as number, 1.75), TypeError);
    });
  });
});
