import { Location } from "./Location";

/**
 * @author Andrés Pachacama, ByteWorks, @ESPE
 */
export interface GrazeAnimalI {
    sendToGraze(location: Location, date: Date): void;
}
