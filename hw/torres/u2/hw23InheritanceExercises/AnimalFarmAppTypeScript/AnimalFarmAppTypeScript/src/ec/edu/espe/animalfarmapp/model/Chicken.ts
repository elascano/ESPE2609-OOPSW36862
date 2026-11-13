import { FarmAnimal } from "./FarmAnimal";

/**
 *
 * @author Ostin Torres, CodeLex, @ESPE
 */
export class Chicken extends FarmAnimal {

    private isMolting: boolean;
    private numberOfLaidEggsPerWeek: number;

    constructor(isMolting: boolean, numberOfLaidEggsPerWeek: number, id: number, breed: string, bornOnDate: Date) {
        super(id, breed, bornOnDate, isMolting);
        this.isMolting = isMolting;
        this.numberOfLaidEggsPerWeek = numberOfLaidEggsPerWeek;
    }

    public override toString(): string {
        return "Chicken{" + super.toString() + "isMolting=" + this.isMolting + ", numberOfLaidEggsPerWeek=" + this.numberOfLaidEggsPerWeek + "}";
    }

    /**
     * @return the isMolting
     */
    public isIsMolting(): boolean {
        return this.isMolting;
    }

    /**
     * @param isMolting the isMolting to set
     */
    public setIsMolting(isMolting: boolean): void {
        this.isMolting = isMolting;
    }

    /**
     * @return the numberOfLaidEggsPerWeek
     */
    public getNumberOfLaidEggsPerWeek(): number {
        return this.numberOfLaidEggsPerWeek;
    }

    /**
     * @param numberOfLaidEggsPerWeek the numberOfLaidEggsPerWeek to set
     */
    public setNumberOfLaidEggsPerWeek(numberOfLaidEggsPerWeek: number): void {
        this.numberOfLaidEggsPerWeek = numberOfLaidEggsPerWeek;
    }
}
