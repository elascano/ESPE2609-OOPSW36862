/**
 * Representa una tarjeta SD.
 * El atributo id es obligatorio; se agregan capacidad, marca y clase de velocidad.
 */
export class SDCard {
  constructor(
    public id: number,
    public capacityGB: number,
    public brand: string,
    public speedClass: string
  ) {
    if (!Number.isInteger(id) || id <= 0) {
      throw new Error("El id debe ser un entero positivo.");
    }
    if (!Number.isFinite(capacityGB) || capacityGB <= 0) {
      throw new Error("La capacidad debe ser un número positivo.");
    }
    if (!brand.trim() || !speedClass.trim()) {
      throw new Error("La marca y la clase de velocidad son obligatorias.");
    }
  }
}
