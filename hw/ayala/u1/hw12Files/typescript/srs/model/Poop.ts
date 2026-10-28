export class Poop {
    constructor(
        public id: number,
        public color: string,
        public consistency: string
    ) {}

    toString(): string {
        return `Poop{id=${this.id}, color=${this.color}, consistency=${this.consistency}}`;
    }
}