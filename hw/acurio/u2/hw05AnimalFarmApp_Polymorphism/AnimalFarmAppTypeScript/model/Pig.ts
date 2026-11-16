
import { FarmAnimal } from "./FarmAnimal";
import { Food } from "./Food";
import { SellAnimal } from "./SellAnimal";

export class Pig extends FarmAnimal implements SellAnimal {
    private isWallowing: boolean;
    private weightGainPerWeek: number;

    constructor(isWallowing: boolean, weightGain: number, id: number,
                breed: string, bornOnDate: Date, isMeat: boolean) {
        super(id, breed, bornOnDate, isMeat);
        this.isWallowing = isWallowing;
        this.weightGainPerWeek = weightGain;
    }

    showPigMeatCuts(): void {
        const cuts = ["head", "cheek", "picnic", "clear plate",
                      "boston shoulder", "back fat", "loin", "ribs",
                      "bacon", "leg", "ham", "hock"];

        console.log("Avalaible cuts for the pig[" + cuts.join(", ") + "]");
    }

    feed(food: Food, amount: number): void {
        console.log(`feeding the pig with ${amount} kg of ${food}`);
    }

    sellAnimal(price: number): void {
        console.log(`sold the pig in ${price} dollars.`);
    }

    getWeightGainPerWeek(weightGain: number): void {
        console.log(`the pig has gained ${weightGain} kg of weight`);
    }

    toString(): string {
        return `Pig{${super.toString()}isWallowing=${this.isWallowing}, weightGainPerWeek=${this.weightGainPerWeek}}`;
    }

    isIsWallowing(): boolean {
        return this.isWallowing;
    }

    setIsWallowing(value: boolean): void {
        this.isWallowing = value;
    }

    setWeightGainPerWeek(value: number): void {
        this.weightGainPerWeek = value;
    }
}
