/** @author Mateo Nacimba, @ESPE */
import { Location } from "./Location";

export interface GrazeAnimal {
  sendToGraze(location: Location, date: Date): void;
}
