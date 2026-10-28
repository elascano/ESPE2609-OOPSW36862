import { formatFloat, toFloat32 } from "../src/utils/javaFloat";

/** Equivale a assertEquals(float expected, float actual, float delta) de JUnit 5. */
export function assertEquals(expResult: number, result: number, delta = 0): void {
  const exp = toFloat32(expResult);
  const res = toFloat32(result);
  const bothNaN = Number.isNaN(exp) && Number.isNaN(res);
  if (!bothNaN && !(exp === res || Math.abs(exp - res) <= delta)) {
    throw new Error(`expected: <${formatFloat(exp)}> but was: <${formatFloat(res)}>`);
  }
}
