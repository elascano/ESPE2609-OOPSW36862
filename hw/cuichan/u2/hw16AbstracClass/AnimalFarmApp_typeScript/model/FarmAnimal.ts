export abstract class FarmAnimal {
    protected id: number;
    protected breed: string;
    protected bornOnDate: Date;

    constructor(id: number, breed: string, bornOnDate: Date) {
        this.id = id;
        this.breed = breed;
        this.bornOnDate = bornOnDate;
    }

    public abstract feed(): void;

    public toString(): string {
        const dateStr = this.bornOnDate.toLocaleDateString('es-EC', {
            day: '2-digit',
            month: '2-digit',
            year: 'numeric'
        });
        return `id=${this.id}, breed=${this.breed}, bornOnDate=${dateStr}`;
    }

    public getId(): number { return this.id; }
    public getBreed(): string { return this.breed; }
    public getBornOnDate(): Date { return this.bornOnDate; }
}