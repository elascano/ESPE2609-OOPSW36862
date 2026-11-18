import { Location } from "./Location.ts";

/**
 * @author Angel Bonilla, Infinite loops, @ESPE
 */
export interface GrazeAnimal {
    graze(location: Location): void;
}