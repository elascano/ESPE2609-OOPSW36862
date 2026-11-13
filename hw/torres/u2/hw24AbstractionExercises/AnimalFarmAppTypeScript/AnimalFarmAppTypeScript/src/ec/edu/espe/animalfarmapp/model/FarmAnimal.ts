/**
 *
 * @author Ostin Torres, CodeLex, @ESPE
 */
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
        return "FarmAnimal{" + "id=" + this.getId() + ", breed=" + this.getBreed() + ", is meat=" + this.isIsMeat()
            + ", bornOnDate=" + this.getBornOnDate().getDate() + " / "
            + (this.getBornOnDate().getMonth() + 1) + " / " + this.getBornOnDate().getFullYear()
            + "}, ";
    }

    /**
     * @return the id
     */
    public getId(): number {
        return this.id;
    }

    /**
     * @param id the id to set
     */
    public setId(id: number): void {
        this.id = id;
    }

    /**
     * @return the breed
     */
    public getBreed(): string {
        return this.breed;
    }

    /**
     * @param breed the breed to set
     */
    public setBreed(breed: string): void {
        this.breed = breed;
    }

    /**
     * @return the bornOnDate
     */
    public getBornOnDate(): Date {
        return this.bornOnDate;
    }

    /**
     * @param bornOnDate the bornOnDate to set
     */
    public setBornOnDate(bornOnDate: Date): void {
        this.bornOnDate = bornOnDate;
    }

    /**
     * @return the isMeat
     */
    public isIsMeat(): boolean {
        return this.isMeat;
    }

    /**
     * @param isMeat the isMeat to set
     */
    public setIsMeat(isMeat: boolean): void {
        this.isMeat = isMeat;
    }
}
