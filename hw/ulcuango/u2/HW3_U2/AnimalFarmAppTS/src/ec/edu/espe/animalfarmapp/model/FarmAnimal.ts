export class FarmAnimal {
    private id: number;
    private breed: string;
    private bornOnDate: Date;

    constructor(id: number, breed: string, bornOnDate: Date) {
        this.id = id;
        this.breed = breed;
        this.bornOnDate = bornOnDate;
    }

    public toString(): string {
        const day = this.bornOnDate.getDate();
        const month = this.bornOnDate.getMonth() + 1; 
        const year = this.bornOnDate.getFullYear();
        return `id=${this.id}, breed=${this.breed}, bornOnDate=${day}/${month}/${year}, `;
    }

    public getAgeInMonths(): number {
        // TODO compute the age in months
        return 0;
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