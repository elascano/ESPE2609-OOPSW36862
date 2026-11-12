import { FarmAnimal } from "./FarmAnimal";

/**
 * @author David Clavón, Infinite Loops, @ESPE
 */
export class Chicken extends FarmAnimal {
    private isMolting: boolean;
    private numberOfLaidEggInMonth: number;
    private isMeat: boolean;

    constructor(
        isMolting: boolean,
        numberOfLaidEggInMonth: number,
        isMeat: boolean,
        id: number,
        breed: string,
        bornOnDate: Date
    ) {
        super(id, breed, bornOnDate);
        this.isMolting = isMolting;
        this.numberOfLaidEggInMonth = numberOfLaidEggInMonth;
        this.isMeat = isMeat;
    }

    public override toString(): string {
        return `Chicken{${super.toString()}isMolting=${this.isMolting}, numberOfLaidEggInMonth=${this.numberOfLaidEggInMonth}, isMeat=${this.isMeat}}`;
    }

    public isIsMolting(): boolean {
        return this.isMolting;
    }

    public setIsMolting(isMolting: boolean): void {
        this.isMolting = isMolting;
    }

    public getNumberOfLaidEggInMonth(): number {
        return this.numberOfLaidEggInMonth;
    }

    public setNumberOfLaidEggInMonth(numberOfLaidEggInMonth: number): void {
        this.numberOfLaidEggInMonth = numberOfLaidEggInMonth;
    }

    public isIsMeat(): boolean {
        return this.isMeat;
    }

    public setIsMeat(isMeat: boolean): void {
        this.isMeat = isMeat;
    }
}