export class Egg {
    public id: number;
    public size: string;

    constructor(id: number, size: string) {this.id = id; this.size = size;}

    toString(): string {return "Egg{" + "id=" + this.id + ", size=" + this.size + '}';}
}