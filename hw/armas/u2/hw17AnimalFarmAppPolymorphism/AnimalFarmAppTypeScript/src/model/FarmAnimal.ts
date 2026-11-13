//Demian Armas, Logical Lords, @ESPE

export class FarmAnimal {
    id: number;
    breed: string;
    bornOnDate: Date;

    constructor(id: number, breed: string, bornOnDate: Date) {
        this.id = id;
        this.breed = breed;
        this.bornOnDate = bornOnDate;
    }

    getAgeInMonths(): number {
        const today = new Date();
        let months = (today.getFullYear() - this.bornOnDate.getFullYear()) * 12;
        months += today.getMonth() - this.bornOnDate.getMonth();
        if (today.getDate() < this.bornOnDate.getDate()) {
            months--;
        }
        return months;
    }

    toString(): string {
        const day = this.bornOnDate.getDate();
        const month = this.bornOnDate.getMonth() + 1;
        const year = this.bornOnDate.getFullYear();
        return `id=${this.id}, breed=${this.breed}, bornOnDate=${day}/${month}/${year}`;
    }
}
