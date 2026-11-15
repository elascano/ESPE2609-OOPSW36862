import { Location } from './Location';

export interface GrazeAnimal {
    sendtoGraze(location: Location): void;
}