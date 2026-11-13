export abstract class FarmAnimal {
    private id: number;
    private breed: string;
    private bornOnDate: Date;
    private isMeat: boolean;

    constructor(id: number, breed: string, bornOnDate: Date, isMeat: boolean) {
        this.id = id;
        this.breed = breed;
        this.bornOnDate = bornOnDate;
        this.isMeat = isMeat;
    }

    public toString(): string {
        return `FarmAnimal{id=${this.getId()}, breed=${this.getBreed()}, is meat=${this.isIsMeat()}, bornOnDate=${this.bornOnDate.getDate()} / ${this.bornOnDate.getMonth() + 1} / ${this.bornOnDate.getFullYear()}}, `;
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

    public isIsMeat(): boolean {
        return this.isMeat;
    }

    public setIsMeat(isMeat: boolean): void {
        this.isMeat = isMeat;
    }
}
