import { Location } from './Location';

export interface GrazeAnimalI {
    senToGraze(location: Location, date: Date): void;
}