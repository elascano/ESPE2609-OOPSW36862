/**
 * Clase base FarmAnimal traducida de ec.edu.espe.animalfarmapp.model.FarmAnimal
 * @author Daniel Sangoquiza, SaborCode, @ESPE
 */
export class FarmAnimal {
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
        const day = this.bornOnDate.getDate();
        const month = this.bornOnDate.getMonth() + 1;
        const year = this.bornOnDate.getFullYear();

        return `FarmAnimal{id=${this.id}, breed=${this.breed}, is meat=${this.isMeat}, bornOnDate=${day} / ${month} / ${year}}, `;
    }

    // --- Getters y Setters ---
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