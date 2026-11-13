import { FarmAnimal } from "./FarmAnimal";

/**
 *
 * @author Ostin Torres, CodeLex, @ESPE
 */
export class Cow extends FarmAnimal {

    private isProducingMilk: boolean;
    private litersOfMilkPerWeek: number;

    public milk(): void {
        console.log("cow is producing milk");
    }

    constructor(isProducingMilk: boolean, litersOfMilkPerWeek: number, id: number, breed: string, bornOnDate: Date, isMeat: boolean) {
        super(id, breed, bornOnDate, isMeat);
        this.isProducingMilk = isProducingMilk;
        this.litersOfMilkPerWeek = litersOfMilkPerWeek;
    }

    public override toString(): string {
        return "Cow{" + super.toString() + "isProducingMilk=" + this.isProducingMilk + ", litersOfMilkPerWeek=" + this.litersOfMilkPerWeek + "}";
    }

    /**
     * @return the isProducingMilk
     */
    public isIsProducingMilk(): boolean {
        return this.isProducingMilk;
    }

    /**
     * @param isProducingMilk the isProducingMilk to set
     */
    public setIsProducingMilk(isProducingMilk: boolean): void {
        this.isProducingMilk = isProducingMilk;
    }

    /**
     * @return the litersOfMilkPerWeek
     */
    public getLitersOfMilkPerWeek(): number {
        return this.litersOfMilkPerWeek;
    }

    /**
     * @param litersOfMilkPerWeek the litersOfMilkPerWeek to set
     */
    public setLitersOfMilkPerWeek(litersOfMilkPerWeek: number): void {
        this.litersOfMilkPerWeek = litersOfMilkPerWeek;
    }
}
