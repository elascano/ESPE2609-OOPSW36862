/**
 * @author Andres Pachacama, Byte Works, ESPE
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

    toString(): string {
        return "id=" + this.id + ", breed=" + this.breed +
            ", bornOnDate=" + this.bornOnDate.getDate() +
            "/" + (this.bornOnDate.getMonth() + 1) + "/" + this.bornOnDate.getFullYear() + ",";
    }

    getAgeInMonths(): number {
        //Todo Compute the age
        return 0;
    }

    /**
     * @return the id
     */
    getId(): number {
        return this.id;
    }

    /**
     * @param id the id to set
     */
    setId(id: number): void {
        this.id = id;
    }

    /**
     * @return the breed
     */
    getBreed(): string {
        return this.breed;
    }

    /**
     * @param breed the breed to set
     */
    setBreed(breed: string): void {
        this.breed = breed;
    }

    /**
     * @return the bornOnDate
     */
    getBornOnDate(): Date {
        return this.bornOnDate;
    }

    /**
     * @param bornOnDate the bornOnDate to set
     */
    setBornOnDate(bornOnDate: Date): void {
        this.bornOnDate = bornOnDate;
    }
}
