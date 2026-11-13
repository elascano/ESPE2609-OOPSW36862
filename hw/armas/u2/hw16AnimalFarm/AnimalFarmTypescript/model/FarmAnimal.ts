/**
 * Demian Armas, Logical Lords, @ESPE
 */
export class FarmAnimal {
    private id: number;
    private breed: string;
    private bornOnDate: Date;

    constructor(id: number, breed: string, bornOnDate: Date) {
        this.id = id;
        this.breed = breed;
        this.bornOnDate = bornOnDate;
    }

    public getAgeInMonths(): number {
        return 0;
    }

    public toString(): string {
        const formattedDate = `${this.bornOnDate.getDate()}/${this.bornOnDate.getMonth() + 1}/${this.bornOnDate.getFullYear()}`;
        return `id=${this.id}, breed=${this.breed}, born On Date=${formattedDate},`;
    }

    public getId(): number {
        return this.id;
    }

    public setId(id: number): void {
        this.id = id;
    }

    public getBreed(): string {
        return this.breed;
    }

    public setBreed(breed: string): void {
        this.breed = breed;
    }

    public getBornOnDate(): Date {
        return this.bornOnDate;
    }

    public setBornOnDate(bornOnDate: Date): void {
        this.bornOnDate = bornOnDate;
    }
}