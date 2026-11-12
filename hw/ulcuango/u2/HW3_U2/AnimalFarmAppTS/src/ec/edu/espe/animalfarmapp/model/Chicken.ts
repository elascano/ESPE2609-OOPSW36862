import { FarmAnimal } from './FarmAnimal';

export class Chicken extends FarmAnimal {
    private isMolting: boolean;
    private numberOfLaidEggsInAMonth: number;
    private isMeat: boolean;

    constructor(isMolting: boolean, numberOfLaidEggsInAMonth: number, isMeat: boolean, id: number, breed: string, bornOnDate: Date) {
        super(id, breed, bornOnDate);
        this.isMolting = isMolting;
        this.numberOfLaidEggsInAMonth = numberOfLaidEggsInAMonth;
        this.isMeat = isMeat;
    }

    public toString(): string {
        return `Chicken{${super.toString()}isMolting=${this.isMolting}, numberOfLaidEggsInAMonth=${this.numberOfLaidEggsInAMonth}, isMeat=${this.isMeat}}`;
    }

    public getIsMolting(): boolean {
        return this.isMolting;
    }

    public setIsMolting(isMolting: boolean): void {
        this.isMolting = isMolting;
    }

    public getNumberOfLaidEggsInAMonth(): number {
        return this.numberOfLaidEggsInAMonth;
    }

    public setNumberOfLaidEggsInAMonth(numberOfLaidEggsInAMonth: number): void {
        this.numberOfLaidEggsInAMonth = numberOfLaidEggsInAMonth;
    }

    public getIsMeat(): boolean {
        return this.isMeat;
    }

    public setIsMeat(isMeat: boolean): void {
        this.isMeat = isMeat;
    }
}