import assert from "node:assert/strict";
import { beforeEach, describe, it } from "node:test";
import { AreaOfCircle } from "../src/AreaOfCircle.ts";
import { assertAlmostEqual } from "./assertAlmostEqual.ts";

describe("AreaOfCircleTest", () => {
  let circle: AreaOfCircle;

  beforeEach(() => {
    circle = new AreaOfCircle();
  });

  it("test_calculate_area_of_circle_case_0", () => {
    const result = circle.calculateAreaOfCircle(1.0);
    assertAlmostEqual(result, 3.1416, 0);
  });

  it("test_calculate_area_of_circle_case_1", () => {
    const result = circle.calculateAreaOfCircle(2.0);
    assertAlmostEqual(result, 12.5664, 1e-6);
  });

  it("test_calculate_area_of_circle_case_2", () => {
    const result = circle.calculateAreaOfCircle(0.5);
    assertAlmostEqual(result, 0.7854, 1e-6);
  });

  it("test_calculate_area_of_circle_case_3", () => {
    const result = circle.calculateAreaOfCircle(1.0);
    assertAlmostEqual(result, 3.1415927, 1e-6);
  });

  it("test_calculate_area_of_circle_case_4", () => {
    const result = circle.calculateAreaOfCircle(2.0);
    assertAlmostEqual(result, 12.5663706, 1e-6);
  });

  it("test_calculate_area_of_circle_case_5", () => {
    const result = circle.calculateAreaOfCircle(0.5);
    assertAlmostEqual(result, 0.7853982, 1e-6);
  });

  it("test_calculate_area_of_circle_case_6", () => {
    const result = circle.calculateAreaOfCircle(-1.0);
    assertAlmostEqual(result, -1, 1e-6);
  });

  it("test_calculate_area_of_circle_case_7", () => {
    const result = circle.calculateAreaOfCircle(-2.5);
    assertAlmostEqual(result, 19.64, 1e-6);
  });

  it("test_calculate_area_of_circle_case_8", () => {
    const result = circle.calculateAreaOfCircle(-3.0);
    assertAlmostEqual(result, 28.274334, 1e-6);
  });

  it("test_calculate_area_of_circle_case_9", () => {
    const result = circle.calculateAreaOfCircle(0.0);
    assert.equal(result, 0.0);
  });
});
