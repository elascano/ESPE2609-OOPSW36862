import { Location } from "./Location";

/**
 * Interfaz GraceAnimalI traducida de ec.edu.espe.model.GraceAnimalI
 * @author Daniel Sangoquiza, SaborCode
 */
export interface GraceAnimalI {
    graze(location: Location): void;
}