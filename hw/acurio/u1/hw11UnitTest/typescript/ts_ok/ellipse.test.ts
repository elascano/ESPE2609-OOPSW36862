import { Ellipse } from "./ellipse";

/**
 * @author Isaac Acurio, @ESPE
 */
const DELTA = 0.01;

function assertClose(expected: number, actual: number, delta: number = DELTA): void {
  expect(Math.abs(expected - actual)).toBeLessThanOrEqual(delta);
}

describe("EllipseTest", () => {
  // 1. Caso normal: a=3, b=2 -> 18.8496
  test("testComputeAreaNormalCase", () => {
    assertClose(18.8496, new Ellipse().computeArea(3, 2));
  });

  // 2. Círculo (a = b): a=5, b=5 -> 78.5398
  test("testComputeAreaCircle", () => {
    assertClose(78.5398, new Ellipse().computeArea(5, 5));
  });

  // 3. Valores unitarios: a=1, b=1 -> 3.1416
  test("testComputeAreaUnitValues", () => {
    assertClose(3.1416, new Ellipse().computeArea(1, 1));
  });

  // 4. Decimales: a=2.5, b=1.5 -> 11.7810
  test("testComputeAreaDecimals", () => {
    assertClose(11.781, new Ellipse().computeArea(2.5, 1.5));
  });

  // 5. Valores grandes: a=100, b=50 -> 15707.9633
  test("testComputeAreaLargeValues", () => {
    assertClose(15707.9633, new Ellipse().computeArea(100, 50), 0.1);
  });

  // 6. Valores pequeños: a=0.5, b=0.5 -> 0.7854
  test("testComputeAreaSmallValues", () => {
    assertClose(0.7854, new Ellipse().computeArea(0.5, 0.5));
  });

  // 7. Un semieje es cero: a=0, b=5 -> 0
  test("testComputeAreaZeroSemiAxis", () => {
    assertClose(0, new Ellipse().computeArea(0, 5));
  });

  // 8. Conmutatividad: area(a,b) == area(b,a)
  test("testComputeAreaIsCommutative", () => {
    const e = new Ellipse();
    assertClose(e.computeArea(4, 7), e.computeArea(7, 4));
  });

  // 9. Elipse alargada: a=10, b=1 -> 31.4159
  test("testComputeAreaElongatedEllipse", () => {
    assertClose(31.4159, new Ellipse().computeArea(10, 1));
  });

  // 10. Valor negativo: debe lanzar excepción
  test("testComputeAreaNegativeValue", () => {
    expect(() => new Ellipse().computeArea(-3, 2)).toThrow();
  });
});