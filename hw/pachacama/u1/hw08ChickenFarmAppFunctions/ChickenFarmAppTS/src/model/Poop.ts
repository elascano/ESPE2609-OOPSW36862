/**
 * @author Andres Pachacama, Byte Work, @ESPE
 */
export class Poop {
    id: number;
    color: string;
    consistency: string;

    constructor(id: number, color: string, consistency: string) {
        this.id = id;
        this.color = color;
        this.consistency = consistency;
    }

    toString(): string {
        return "Poop{id=" + this.id + ", color=" + this.color + ", consistency=" + this.consistency + "}";
    }
}
