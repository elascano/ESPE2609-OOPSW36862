export class Chicken {
    constructor(
        public idChicken: number,
        public name: string,
        public color: string,
        public age: number,
        public molting: boolean,
        public idCoop: number,
        public dateOfBirth: Date
    ) {}

    public toString(): string {
        return `Chicken(id=${this.idChicken}, name='${this.name}', color='${this.color}', age=${this.age}, molting=${this.molting}, coop=${this.idCoop})`;
    }
}
