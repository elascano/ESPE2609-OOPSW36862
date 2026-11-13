import {FarmAnimal} from "./FarmAnimal";

export class Cow extends FarmAnimal{
    constructor(
        id: number,
        breed: string,
        bornOnDate: Date,
        isMeat: boolean,       
        public readonly isProducingMilk: boolean,
        public readonly litersOfMilkPerWeek: number
    ){super(id, breed, bornOnDate, isMeat);}
 
    public override toString(): string{
        return super.toString() + ", Cow(isProducingMilk= " + this.isProducingMilk + ", litersOfMilkPerWeek= " + this.litersOfMilkPerWeek + ")";
    }
    public milk(): void{
        console.log("Cow is Producing milk");
    }
}