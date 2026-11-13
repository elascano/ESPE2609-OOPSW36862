/**
 * @author Leslye Cuichan, Byte Fabric & devs, @ESPE
 */
export class FarmAnimal {
    id: number;
    breed: string;
    bornOnDate: Date;

    constructor(id: number, breed: string, bornOnDate: Date) {
        this.id = id;
        this.breed = breed;
        this.bornOnDate = bornOnDate;
    }

    toString(): string {
        const dateStr = this.bornOnDate instanceof Date ? this.bornOnDate.toLocaleDateString() : String(this.bornOnDate);
        return `id=${this.id}, breed=${this.breed}, bornOnDate=${dateStr}`;
    }
}