export class FarmAnimal {
    private id: number;
    private bred: string;
    private bornOnDate: Date;

    constructor(id: number, bred: string, bornOnDate: Date) {
        this.id = id;
        this.bred = bred;
        this.bornOnDate = bornOnDate;
    }

    public getAgeInMonths(): number {
        return 0;
    }

    public toString(): string {
        return `FarmAnimal{id=${this.id}, bred=${this.bred}, bornOnDate=${this.bornOnDate}}`;
    }

    public getId(): number {
        return this.id;
    }

    public setId(id: number): void {
        this.id = id;
    }

    public getBred(): string {
        return this.bred;
    }

    public setBred(bred: string): void {
        this.bred = bred;
    }

    public getBornOnDate(): Date {
        return this.bornOnDate;
    }

    public setBornOnDate(bornOnDate: Date): void {
        this.bornOnDate = bornOnDate;
    }
}