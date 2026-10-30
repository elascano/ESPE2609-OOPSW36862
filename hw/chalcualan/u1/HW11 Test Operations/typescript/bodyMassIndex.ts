export class BodyMassIndex {
  calculateBMI(weight: number, height: number): number {
    if (height === 0) {
      throw new Error("Height cannot be zero");
    }
    return weight / (height * height);
  }
}
