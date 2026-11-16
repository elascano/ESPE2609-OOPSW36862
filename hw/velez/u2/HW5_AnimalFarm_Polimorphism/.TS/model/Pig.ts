import { FarmAnimal } from "./FarmAnimal";
import { SellAnimal } from "./SellAnimal";
import { Food } from "./Food";

export class Pig extends FarmAnimal implements SellAnimal {
    private isWallowing: boolean;
    private weightGainPerWeek: number;

    constructor(isWallowing: boolean, weightGainPerWeek: number, id: number, breed: string, bornOnDate: Date, isMeat: boolean) {
        super(id, breed, bornOnDate, isMeat);
        this.isWallowing = isWallowing;
        this.weightGainPerWeek = weightGainPerWeek;
    }

    public showPigMeatCuts(): void {
        const meatCuts: string[] = ["head", "cheek", "picnic", "clear plate", "boston shoulder", "back fat", "loin", "ribs", "bacon", "leg", "ham", "hock"];
        console.log(`Available cuts for the pig: ${meatCuts.join(", ")}`);
    }

    public feed(food: Food, amount: number): void {
        console.log(`feeding the pig with ${amount} kg of ${food.toString()}`);
    }

    public sellAnimal(price: number): void {
        console.log(`sold the pig in ${price} dollars.`);
    }

    public getWeightGainPerWeek(weightGain: number): void {
        console.log(`the pig has gained ${weightGain} kg of weight`);
    }

    public toString(): string {
        return `Pig{${super.toString()}, isWallowing=${this.isWallowing}, weightGain=${this.weightGainPerWeek}}`;
    }
}