import { FarmAnimal } from "./FarmAnimal.ts";

/**
 * @author Andres Pachacama, Byte Works, ESPE
 */
export class Cow extends FarmAnimal {
    private litersOfMilkPerDay: number;
    private isPregnant: boolean;
    private weightInKg: number;
    private isMilking: boolean;

    constructor(litersOfMilkPerDay: number, isPregnant: boolean, weightInKg: number, isMilking: boolean, id: number, breed: string, bornOnDate: Date) {
        super(id, breed, bornOnDate);
        this.litersOfMilkPerDay = litersOfMilkPerDay;
        this.isPregnant = isPregnant;
        this.weightInKg = weightInKg;
        this.isMilking = isMilking;
    }

    override toString(): string {
        return "Cow{" + super.toString() + " litersOfMilkPerDay=" + this.getLitersOfMilkPerDay()
            + ", isPregnant=" + this.isIsPregnant() + ", weightInKg=" + this.getWeightInKg()
            + ", isMilking=" + this.isIsMilking() + "}";
    }

    /**
     * @return the litersOfMilkPerDay
     */
    getLitersOfMilkPerDay(): number {
        return this.litersOfMilkPerDay;
    }

    /**
     * @param litersOfMilkPerDay the litersOfMilkPerDay to set
     */
    setLitersOfMilkPerDay(litersOfMilkPerDay: number): void {
        this.litersOfMilkPerDay = litersOfMilkPerDay;
    }

    /**
     * @return the isPregnant
     */
    isIsPregnant(): boolean {
        return this.isPregnant;
    }

    /**
     * @param isPregnant the isPregnant to set
     */
    setIsPregnant(isPregnant: boolean): void {
        this.isPregnant = isPregnant;
    }

    /**
     * @return the weightInKg
     */
    getWeightInKg(): number {
        return this.weightInKg;
    }

    /**
     * @param weightInKg the weightInKg to set
     */
    setWeightInKg(weightInKg: number): void {
        this.weightInKg = weightInKg;
    }

    /**
     * @return the isMilking
     */
    isIsMilking(): boolean {
        return this.isMilking;
    }

    /**
     * @param isMilking the isMilking to set
     */
    setIsMilking(isMilking: boolean): void {
        this.isMilking = isMilking;
    }
}
