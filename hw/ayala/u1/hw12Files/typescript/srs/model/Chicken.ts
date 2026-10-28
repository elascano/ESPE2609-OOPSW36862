export class Chicken {
    constructor(
        public id: number,
        public name: string,
        public color: string,
        public bornOnDate: string,
        public ageInYears: number = 0,
        public molting: boolean = false
    ) {
        this.setAgeInYears();
    }

    computeAgeInYears(): number {
        const born = new Date(`${this.bornOnDate}T00:00:00`);
        const today = new Date();
        let years = today.getFullYear() - born.getFullYear();
        const monthDifference = today.getMonth() - born.getMonth();
        if (monthDifference < 0 ||
            (monthDifference === 0 && today.getDate() < born.getDate())) {
            years--;
        }
        return years;
    }

    setAgeInYears(): void {
        this.ageInYears = this.computeAgeInYears();
    }

    doStuff(): void {
        console.log(`The chicken ${this.name} is clucking`);
        console.log(`The chicken ${this.name} is wandering`);
        console.log(`The chicken ${this.name} is drinking --> 1.3 ml of water`);
        console.log("poop--> Poop{id=2, color=green, consistency=M}");
        console.log("egg--> Egg{id=2, size=S}");
    }

    toString(): string {
        this.setAgeInYears();
        return `Chicken{id=${this.id}, name=${this.name}, color=${this.color}, bornOnDate=${this.bornOnDate}, age=${this.ageInYears}, molting=${this.molting}}`;
    }
}