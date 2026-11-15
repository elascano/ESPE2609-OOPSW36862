import { FarmAnimal } from "./FarmAnimal";
import { Food } from "./Food";
import { SellAnimal } from "./SellAnimal";

export class Pig extends FarmAnimal implements SellAnimal {
    constructor(
        private isWallowing: boolean,
        private weightGainPerWeek: number,
        id: number,
        breed: string,
        bornOnDate: Date,
        isMeat: boolean
    ) {
        super(id, breed, bornOnDate, isMeat);
    }

    public feed(food: Food, amount: number): void {
        console.log(`feeding the pig with ${amount} kg of ${food}`);
    }

    public sellAnimal(price: number): void {
        console.log(`sold the pig in ${price} dollars.`);
    }

    public showPigMeatCuts(): void {
        const meatCuts = ["head", "cheek", "picnic", "clear plate", "boston shoulder",
            "back fat", "loin", "ribs", "bacon", "leg", "ham", "hock"];
        console.log(`Avalaible cuts for the pig[${meatCuts.join(", ")}]`);
    }

    public getWeightGainPerWeek(weightGain: number): void {
        console.log(`the pig has gained ${weightGain} kg of weight`);
    }

    public override toString(): string {
        return `Pig{${super.toString()}isWallowing=${this.isWallowing}, weightGainPerWeek=${this.weightGainPerWeek}}`;
    }
}
