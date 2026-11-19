import { FarmAnimal } from "./FarmAnimal";
import { GraceAnimalI } from "./GraceAnimalI";
import { Food } from "./Food";
import { Location } from "./Location";

/**
 * Clase Cow traducida de ec.edu.espe.model.Cow
 * Extiende de la clase abstracta FarmAnimal e implementa la interfaz GraceAnimalI.
 * @author Daniel Sangoquiza, SaborCode
 */
export class Cow extends FarmAnimal implements GraceAnimalI {
    private _isProducingMilk: boolean;
    private litersOfMilkPerWeek: number;
    private food?: string;

    constructor(
        isProducingMilk: boolean,
        litersOfMilkPerWeek: number,
        id: number,
        breed: string,
        bornOnDate: Date
    ) {
        super(id, breed, bornOnDate);
        this._isProducingMilk = isProducingMilk;
        this.litersOfMilkPerWeek = litersOfMilkPerWeek;
    }

    public graze(location: Location): void {
        console.log(`Feeding a cow in the stable with ---> ${this.food}`);
    }

    // --- Sobrecarga de métodos milk() en TypeScript ---
    public milk(): void;
    public milk(amountOfLiters: number): void;
    public milk(amountOfLiters: number, hour: Date): void;
    public milk(amountOfLiters?: number, hour?: Date): void {
        if (amountOfLiters === undefined) {
            console.log("Milking the cow as I need");
            return;
        }

        this.litersOfMilkPerWeek += amountOfLiters;

        if (hour !== undefined) {
            const hours = String(hour.getHours()).padStart(2, "0");
            const minutes = String(hour.getMinutes()).padStart(2, "0");
            console.log(`Milking the cow for ${amountOfLiters} liters, at ${hours}:${minutes}`);
        } else {
            console.log(`Milking the cow: ${amountOfLiters} liters`);
        }
    }

    public override feed(food: Food, amountOfFood: number): void {
        console.log(`Feeding a cow with ---> ${food.toString()}`);
    }

    public override toString(): string {
        return `Cow{${super.toString()}, isProducingMilk=${this.isProducingMilk()}, litersOfMilkPerWeek=${this.getLitersOfMilkPerWeek()}}`;
    }

    // --- Getters y Setters ---
    public isProducingMilk(): boolean {
        return this._isProducingMilk;
    }

    public setProducingMilk(producingMilk: boolean): void {
        this._isProducingMilk = producingMilk;
    }

    public setProducingMilk(producingMilk: boolean): void {
        this.isProducingMilk = producingMilk;
    }

    public getLitersOfMilkPerWeek(): number {
        return this.litersOfMilkPerWeek;
    }

    public setLitersOfMilkPerWeek(litersOfMilkPerWeek: number): void {
        this.litersOfMilkPerWeek = litersOfMilkPerWeek;
    }
}