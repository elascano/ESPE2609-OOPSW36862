import type { Location } from "./location.ts";

export interface GrazeAnimal {
  graze(location: Location): string;
}
