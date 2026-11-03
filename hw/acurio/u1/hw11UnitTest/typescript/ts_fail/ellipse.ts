/**
 * @author Isaac Acurio, @ESPE  (VERSIÓN CON ERRORES)
 */
export class Ellipse {
  computeArea(semiAxisA: number, semiAxisB: number): number {
    // ERROR 1: la validación está mal (usa '&&' y '<= 0')
    if (semiAxisA <= 0 && semiAxisB <= 0) {
      throw new Error("Semi-axes cannot be negative");
    }
    // ERROR 2: usa 3.14 en vez de Math.PI
    return 3.14 * semiAxisA * semiAxisB;
  }
}