/**
 * @author Andres Pachacama, Byte Work, @ESPE
 */
export class Egg {
    id: number;
    size: string;

    constructor(id: number, size: string) {
        this.id = id;
        this.size = size;
    }

    toString(): string {
        return "Egg{id=" + this.id + ", size=" + this.size + "}";
    }
}
