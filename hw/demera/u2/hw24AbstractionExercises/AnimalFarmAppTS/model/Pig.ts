import {FarmAnimal} from "./FarmAnimal";

export class Pig extends FarmAnimal{
    constructor(
        id: number,
        breed: string,
        bornOnDate: Date,
        isMeat: boolean,       
        public readonly isWallowing: boolean,
        public readonly weightGainPerWeek: number
    ){super(id, breed, bornOnDate, isMeat);}
 
    public override toString(): string{
        return super.toString() + ", Pig(isWallowing= " + this.isWallowing + ", weightGainPerWeek= " + this.weightGainPerWeek + ")";
    }

    public showPigMeatCuts(): void{
        let meatCuts:string[] = ["head", "cheek", "picnic", "clear plate", "boston shoulder", "back fat", "loin", "ribs", "bacon", "leg", "ham", "hock"];
        console.log("Avalaible Cuts for Pig: " + meatCuts);
    }
}