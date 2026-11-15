import { Location } from "./Location";

export interface GrazeAnimalI {
    sendToGraze(location: Location, date: Date): void;
}
