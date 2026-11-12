import { FarmAnimal } from "./FarmAnimal.ts";

/**
 * @author Andres Pachacama, Byte Works, ESPE
 */
export class Chicken extends FarmAnimal {
    private isMolting: boolean;
    private numberOfLaidEggsPerWeek: number;
    private isMeat: boolean;

    constructor(isMolting: boolean, numberOfLaidEggsPerWeek: number, isMeat: boolean, id: number, breed: string, bornOnDate: Date) {
        super(id, breed, bornOnDate);
        this.isMolting = isMolting;
        this.numberOfLaidEggsPerWeek = numberOfLaidEggsPerWeek;
        this.isMeat = isMeat;
    }

    override toString(): string {
        return "Chicken{" + super.toString() + " isMolting=" + this.isIsMolting()
            + ", numberOfLaidEggsPerWeek=" + this.getNumberOfLaidEggsPerWeek()
            + ", isMeat=" + this.isIsMeat() + "}";
    }

    /**
     * @return the isMolting
     */
    isIsMolting(): boolean {
        return this.isMolting;
    }

    /**
     * @param isMolting the isMolting to set
     */
    setIsMolting(isMolting: boolean): void {
        this.isMolting = isMolting;
    }

    /**
     * @return the numberOfLaidEggsPerWeek
     */
    getNumberOfLaidEggsPerWeek(): number {
        return this.numberOfLaidEggsPerWeek;
    }

    /**
     * @param numberOfLaidEggsPerWeek the numberOfLaidEggsPerWeek to set
     */
    setNumberOfLaidEggsPerWeek(numberOfLaidEggsPerWeek: number): void {
        this.numberOfLaidEggsPerWeek = numberOfLaidEggsPerWeek;
    }

    /**
     * @return the isMeat
     */
    isIsMeat(): boolean {
        return this.isMeat;
    }

    /**
     * @param isMeat the isMeat to set
     */
    setIsMeat(isMeat: boolean): void {
        this.isMeat = isMeat;
    }
}
