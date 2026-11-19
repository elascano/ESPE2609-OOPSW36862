import { FarmAnimal } from './FarmAnimal';

export class Chicken extends FarmAnimal {
    private isMolting: boolean;
    private numberOfLaidEggInMonth: number;
    private isMeat: boolean;

    constructor(isMolting: boolean, numberOfLaidEggInMonth: number, isMeat: boolean, id: number, breed: string, bornOnDate: Date) {
        super(id, breed, bornOnDate);
        this.isMolting = isMolting;
        this.numberOfLaidEggInMonth = numberOfLaidEggInMonth;
        this.isMeat = isMeat;
    }

    public layAnEgg(): void {
        console.log("chicken is laying an egg()");
    }

    public getIsMolting(): boolean { return this.isMolting; }
    public setIsMolting(v: boolean): void { this.isMolting = v; }

    public getNumberOfLaidEggInMonth(): number { return this.numberOfLaidEggInMonth; }
    public setNumberOfLaidEggInMonth(v: number): void { this.numberOfLaidEggInMonth = v; }

    public getIsMeat(): boolean { return this.isMeat; }
    public setIsMeat(v: boolean): void { this.isMeat = v; }

    public toString(): string {
        return `Chicken{${super.toString()}isMolting=${this.isMolting}, numberOfLaidEggInMonth=${this.numberOfLaidEggInMonth}, isMeat=${this.isMeat}}`;
    }
}