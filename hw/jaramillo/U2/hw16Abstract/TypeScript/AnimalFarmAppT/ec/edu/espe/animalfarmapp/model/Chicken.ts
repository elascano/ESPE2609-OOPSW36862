import { FarmAnimal } from "./FarmAnimal";

export class Chicken extends FarmAnimal {
    private isMolting: boolean;
    private numberOfLaidEggsInMonth: number;
    private isMeat: boolean;

    constructor(
        isMolting: boolean,
        numberOfLaidEggsInMonth: number,
        isMeat: boolean,
        id: number,
        breed: string,
        bornOnDate: Date
    ) {
        super(id, breed, bornOnDate);
        this.isMolting = isMolting;
        this.numberOfLaidEggsInMonth = numberOfLaidEggsInMonth;
        this.isMeat = isMeat;
    }

    public override toString(): string {
        return `chicken{${super.toString()}isMolting=${this.isMolting}, numberOfLaidEggsInMonth=${this.numberOfLaidEggsInMonth}, isMeat=${this.isMeat}}`;
    }

    public isIsMolting(): boolean {
        return this.isMolting;
    }

    public setIsMolting(isMolting: boolean): void {
        this.isMolting = isMolting;
    }

    public getNumberOfLaidEggsInMonth(): number {
        return this.numberOfLaidEggsInMonth;
    }

    public setNumberOfLaidEggsInMonth(numberOfLaidEggsInMonth: number): void {
        this.numberOfLaidEggsInMonth = numberOfLaidEggsInMonth;
    }

    public isIsMeat(): boolean {
        return this.isMeat;
    }

    public setIsMeat(isMeat: boolean): void {
        this.isMeat = isMeat;
    }

    public layAnEgg(): void {
        console.log("The chicken is laying an Egg");
    }
}