/**
 * @author Isaac Acurio, @ESPE
 */
export class Ellipse {
  computeArea(semiAxisA: number, semiAxisB: number): number {
    if (semiAxisA < 0 || semiAxisB < 0) {
      throw new Error("Semi-axes cannot be negative");
    }
    return Math.PI * semiAxisA * semiAxisB;
  }
}