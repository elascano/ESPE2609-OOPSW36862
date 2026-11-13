import { FarmAnimal } from "./FarmAnimal";

export class Chicken extends FarmAnimal {
    private isMolting: boolean;
    private numberOfLaidEggsPerWeek: number;

    constructor(isMolting: boolean, numberOfLaidEggsPerWeek: number,
                id: number, breed: string, bornOnDate: Date,
                isMeat: boolean = false) {
        super(id, breed, bornOnDate, isMeat);
        this.isMolting = isMolting;
        this.numberOfLaidEggsPerWeek = numberOfLaidEggsPerWeek;
    }

    public toString(): string {
        return `Chicken{${super.toString()}, isMolting=${this.isMolting}, numberOfLaidEggsPerWeek=${this.numberOfLaidEggsPerWeek}}`;
    }

    public getIsMolting(): boolean {
        return this.isMolting;
    }

    public setIsMolting(isMolting: boolean): void {
        this.isMolting = isMolting;
    }

    public getNumberOfLaidEggsPerWeek(): number {
        return this.numberOfLaidEggsPerWeek;
    }

    public setNumberOfLaidEggsPerWeek(number: number): void {
        this.numberOfLaidEggsPerWeek = number;
    }
}
