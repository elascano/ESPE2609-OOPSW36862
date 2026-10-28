import { Operation } from "../../../../../../src/ec/edu/espe/baiscoperation/model/Operation";
import { assertEquals } from "../../../../../assertEquals";

/** @author Nacimba Mateo, Group5, @ESPE */
describe("OperationTest", () => {
  beforeAll(() => {});
  afterAll(() => {});
  beforeEach(() => {});
  afterEach(() => {});

  /** Test of add method, of class Operation. */
  test("testAdd", () => {
    console.log("add");
    const addend1 = 1.2;
    const addend2 = 3.4;
    const instance = new Operation();
    const expResult = 4.6;
    const result = instance.add(addend1, addend2);
    assertEquals(expResult, result, 0);
  });

  /** Test of substract method, of class Operation. */
  test("testSubstract", () => {
    console.log("substract");
    const minuhend = 0.0;
    const subtrahend = 0.0;
    const instance = new Operation();
    const expResult = 0.0;
    const result = instance.substract(minuhend, subtrahend);
    assertEquals(expResult, result, 0);
  });

  /** Test of multiply method, of class Operation. */
  test("testMultiply", () => {
    console.log("multiply");
    const multiplicand = 0.0;
    const multiplier = 0.0;
    const instance = new Operation();
    const expResult = 0.0;
    const result = instance.multiply(multiplicand, multiplier);
    assertEquals(expResult, result, 0);
  });

  /** Test of divide method, of class Operation. */
  test("testDivide", () => {
    console.log("divide");
    const dividend = 0.0;
    const divisor = 0.0;
    const instance = new Operation();
    const expResult = 0.0;
    const result = instance.divide(dividend, divisor);
    assertEquals(expResult, result, 0);
  });
});
