/** Emulación del tipo `float` (32 bits, IEEE 754) de Java. */

export const toFloat32 = (value: number): number => Math.fround(value);

/** Equivale a Float.toString(): 4.6000004, 1.0, NaN, Infinity, 1.0E7 ... */
export function formatFloat(value: number): string {
  const v = Math.fround(value);
  if (Number.isNaN(v)) return "NaN";
  if (!Number.isFinite(v)) return v > 0 ? "Infinity" : "-Infinity";
  const sign = Object.is(v, -0) || v < 0 ? "-" : "";
  const a = Math.abs(v);
  if (a === 0) return `${sign}0.0`;

  let text = a.toExponential(0);
  for (let precision = 1; precision <= 9; precision++) {
    text = a.toExponential(precision - 1);
    if (Math.fround(parseFloat(text)) === a) break;
  }
  const [mantissa, expText] = text.split("e");
  const exp = parseInt(expText, 10);
  const digits = mantissa.replace(".", "").replace(/0+$/, "") || "0";

  if (a >= 1e-3 && a < 1e7) {
    if (exp >= 0) {
      const intPart = digits.slice(0, exp + 1).padEnd(exp + 1, "0");
      const frac = digits.slice(exp + 1) || "0";
      return `${sign}${intPart}.${frac}`;
    }
    return `${sign}0.${"0".repeat(-exp - 1)}${digits}`;
  }
  return `${sign}${digits[0]}.${digits.slice(1) || "0"}E${exp}`;
}
