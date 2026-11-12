export abstract class FarmAnimal {
  constructor(
    public readonly id: number,
    public readonly breed: string,
    public readonly bornOnDate: Date,
    public readonly isMeat: boolean,
  ) {}
  public toString() : string{
    return "FarmAnimal(id=" + this.id + ", breed=" + this.breed + ", bornOnDate " + this.bornOnDate + ", isMeat= " + this.isMeat + ")";
  }
}
