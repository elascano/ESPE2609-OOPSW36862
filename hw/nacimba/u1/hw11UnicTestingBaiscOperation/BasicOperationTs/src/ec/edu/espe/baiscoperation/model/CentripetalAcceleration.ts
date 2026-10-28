import { toFloat32 } from "../../../../../utils/javaFloat";

/** @author Nacimba Mateo, Group5, @ESPE */
export class CentripetalAcceleration {
  calculate(velocity: number, radius: number): number {
    const v = toFloat32(velocity);
    const acceleration = toFloat32(toFloat32(v * v) / toFloat32(radius));
    return acceleration;
  }
}
