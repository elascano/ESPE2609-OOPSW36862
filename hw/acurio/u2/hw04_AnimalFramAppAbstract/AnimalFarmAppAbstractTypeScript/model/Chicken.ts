import { FarmAnimal } from "./FarmAnimal";

export class Chicken extends FarmAnimal {
    private isMolting: boolean;
    private numberOfLaidEggsPerWeek: number;

    constructor(isMolting: boolean, numberOfLaidEggsPerWeek: number,
                id: number, breed: string, bornOnDate: Date) {
        super(id, breed, bornOnDate, isMolting);
        this.isMolting = isMolting;
        this.numberOfLaidEggsPerWeek = numberOfLaidEggsPerWeek;
    }

    public layAnEgg(): void {
        console.log("chicken is laying an egg");
    }

    public toString(): string {
        return `Chicken{${super.toString()}isMolting=${this.isMolting}, numberOfLaidEggsPerWeek=${this.numberOfLaidEggsPerWeek}}`;
    }

    public isIsMolting(): boolean {
        return this.isMolting;
    }

    public setIsMolting(isMolting: boolean): void {
        this.isMolting = isMolting;
    }

    public getNumberOfLaidEggsPerWeek(): number {
        return this.numberOfLaidEggsPerWeek;
    }

    public setNumberOfLaidEggsPerWeek(numberOfLaidEggsPerWeek: number): void {
        this.numberOfLaidEggsPerWeek = numberOfLaidEggsPerWeek;
    }
}
