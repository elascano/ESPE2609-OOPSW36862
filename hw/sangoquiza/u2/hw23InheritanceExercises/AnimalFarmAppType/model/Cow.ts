import { FarmAnimal } from "./FarmAnimal";

/**
 * Clase Cow traducida de ec.edu.espe.animalfarmapp.model.Cow
 * @author Daniel Sangoquiza, SaborCode, @ESPE
 */
export class Cow extends FarmAnimal {
    private isProducingMilk: boolean;
    private litersOfMilkPerWeek: number;

    constructor(
        isProducingMilk: boolean,
        litersOfMilkPerWeek: number,
        id: number,
        breed: string,
        bornOnDate: Date,
        isMeat: boolean
    ) {
        super(id, breed, bornOnDate, isMeat);
        this.isProducingMilk = isProducingMilk;
        this.litersOfMilkPerWeek = litersOfMilkPerWeek;
    }

    public override toString(): string {
        return `Cow{${super.toString()}isProducingMilk=${this.isProducingMilk}, litersOfMilkPerWeek=${this.litersOfMilkPerWeek}}`;
    }

    // --- Getters y Setters ---
    public isIsProducingMilk(): boolean {
        return this.isProducingMilk;
    }

    public setIsProducingMilk(isProducingMilk: boolean): void {
        this.isProducingMilk = isProducingMilk;
    }

    public getLitersOfMilkPerWeek(): number {
        return this.litersOfMilkPerWeek;
    }

    public setLitersOfMilkPerWeek(litersOfMilkPerWeek: number): void {
        this.litersOfMilkPerWeek = litersOfMilkPerWeek;
    }
}