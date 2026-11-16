/** @author Mateo Nacimba, @ESPE */
export class Location {
  constructor(public description: string) {}

  toString(): string {
    return `Location{description=${this.description}}`;
  }
}
