import { Location } from "./Location";

/**
 * @author David Clavón, Infinite loops, @ESPE
 */
export interface GrazeAnimal {
    graze(location: Location): void;
}