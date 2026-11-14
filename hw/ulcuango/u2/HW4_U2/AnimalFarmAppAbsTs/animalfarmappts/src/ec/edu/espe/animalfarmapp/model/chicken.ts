import { FarmAnimal } from './farm_animal';

export class Chicken extends FarmAnimal {
    private isMolting: boolean;
    private numberOfLaidEggPerWeek: number;
    private isMeat: boolean;

    constructor(isMolting: boolean, numberOfLaidEggPerWeek: number, isMeat: boolean, id: number, bred: string, bornOnDate: Date) {
        super(id, bred, bornOnDate);
        this.isMolting = isMolting;
        this.numberOfLaidEggPerWeek = numberOfLaidEggPerWeek;
        this.isMeat = isMeat;
    }

    public assignCoop(): void {
        // Método vacío
    }

    public layAnEgg(): void {
        console.log("The chicken is layin an Egg");
    }

    public toString(): string {
        const superStr = super.toString().replace("}", "");
        return `Chicken{${superStr}, isMolting=${this.isMolting}, numberOfLaidEggPerWeek=${this.numberOfLaidEggPerWeek}, isMeat=${this.isMeat}}`;
    }

    public getIsMolting(): boolean {
        return this.isMolting;
    }

    public setIsMolting(isMolting: boolean): void {
        this.isMolting = isMolting;
    }

    public getNumberOfLaidEggPerWeek(): number {
        return this.numberOfLaidEggPerWeek;
    }

    public setNumberOfLaidEggPerWeek(numberOfLaidEggPerWeek: number): void {
        this.numberOfLaidEggPerWeek = numberOfLaidEggPerWeek;
    }

    public getIsMeat(): boolean {
        return this.isMeat;
    }

    public setIsMeat(isMeat: boolean): void {
        this.isMeat = isMeat;
    }
}