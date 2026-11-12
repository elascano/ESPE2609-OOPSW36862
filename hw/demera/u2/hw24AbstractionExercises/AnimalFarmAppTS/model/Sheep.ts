import {FarmAnimal} from "./FarmAnimal";

export class Sheep extends FarmAnimal{
    constructor(
        id: number,
        breed: string,
        bornOnDate: Date,
        isMeat: boolean,       
        public readonly isSheared: boolean,
        public readonly lastSheering: Date
    ){super(id, breed, bornOnDate, isMeat);}
 
    public override toString(): string{
        return super.toString() + ", Sheep(isSheared= " + this.isSheared + ", lastSheering= " + this.lastSheering + ")";
    }
    public sheerSheep():void{
        console.log("Sheep is sheared");
    }
}