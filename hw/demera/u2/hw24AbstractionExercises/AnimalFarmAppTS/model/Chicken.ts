import {FarmAnimal} from "./FarmAnimal";

export class Chicken extends FarmAnimal{
    constructor(
        id: number,
        breed: string,
        bornOnDate: Date,
        isMeat: boolean,       
        public readonly isMolting: boolean,
        public readonly numberOfLaidEggsPerWeek: number
    ){super(id, breed, bornOnDate, isMeat);}
 
    public override toString(): string{
        return super.toString() + ", Chicken(isMolting=" + this.isMolting + ", numberOfEggsLaidPerWeek= " + this.numberOfLaidEggsPerWeek + ")";
    }
    public layAnEgg(): void{
      console.log("Chicken is laying an Egg");
    }
}
