import { CentripetalAcceleration } from "../../../../../../src/ec/edu/espe/baiscoperation/model/CentripetalAcceleration";
import { assertEquals } from "../../../../../assertEquals";

/** @author Nacimba Mateo, Group5, @ESPE */
describe("CentripetalAccelerationTest", () => {
  beforeAll(() => {});
  afterAll(() => {});
  beforeEach(() => {});
  afterEach(() => {});

  /** Test of calculate method, of class CentripetalAcceleration. */
  test("testCalculate", () => {
    console.log("calculate");
    const velocity = 0.0;
    const radius = 0.0;
    const instance = new CentripetalAcceleration();
    const expResult = 0.0;
    const result = instance.calculate(velocity, radius);
    assertEquals(expResult, result, 0);
  });

  // PASA: 10² / 5 = 20
  test("testCalculate1", () => {
    console.log("calculate1");
    const velocity = 10.0;
    const radius = 5.0;
    const instance = new CentripetalAcceleration();
    const expResult = 20.0;
    const result = instance.calculate(velocity, radius);
    assertEquals(expResult, result, 0);
  });

  // PASA: velocidad 0 → aceleración 0
  test("testCalculate2", () => {
    console.log("calculate2");
    const velocity = 0.0;
    const radius = 5.0;
    const instance = new CentripetalAcceleration();
    const expResult = 0.0;
    const result = instance.calculate(velocity, radius);
    assertEquals(expResult, result, 0);
  });

  // PASA: 3² / 4 = 2.25
  test("testCalculate3", () => {
    console.log("calculate3");
    const velocity = 3.0;
    const radius = 4.0;
    const instance = new CentripetalAcceleration();
    const expResult = 2.25;
    const result = instance.calculate(velocity, radius);
    assertEquals(expResult, result, 0);
  });

  // PASA: 6² / 3 = 12
  test("testCalculate4", () => {
    console.log("calculate4");
    const velocity = 6.0;
    const radius = 3.0;
    const instance = new CentripetalAcceleration();
    const expResult = 12.0;
    const result = instance.calculate(velocity, radius);
    assertEquals(expResult, result, 0);
  });

  // PASA: (-4)² / 2 = 8
  test("testCalculate5", () => {
    console.log("calculate5");
    const velocity = -4.0;
    const radius = 2.0;
    const instance = new CentripetalAcceleration();
    const expResult = 8.0;
    const result = instance.calculate(velocity, radius);
    assertEquals(expResult, result, 0);
  });

  // PASA: 5² / 2 = 12.5
  test("testCalculate6", () => {
    console.log("calculate6");
    const velocity = 5.0;
    const radius = 2.0;
    const instance = new CentripetalAcceleration();
    const expResult = 12.5;
    const result = instance.calculate(velocity, radius);
    assertEquals(expResult, result, 0);
  });

  // PASA: 20² / 10 = 40
  test("testCalculate7", () => {
    console.log("calculate7");
    const velocity = 20.0;
    const radius = 10.0;
    const instance = new CentripetalAcceleration();
    const expResult = 40.0;
    const result = instance.calculate(velocity, radius);
    assertEquals(expResult, result, 0);
  });

  // PASA: 10² / -5 = -20 (radio negativo da resultado negativo)
  test("testCalculate8", () => {
    console.log("calculate8");
    const velocity = 10.0;
    const radius = -5.0;
    const instance = new CentripetalAcceleration();
    const expResult = -20.0;
    const result = instance.calculate(velocity, radius);
    assertEquals(expResult, result, 0);
  });
});
