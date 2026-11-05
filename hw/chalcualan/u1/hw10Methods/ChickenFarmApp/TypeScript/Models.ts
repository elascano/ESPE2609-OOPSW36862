export class Chicken {
    constructor(
        public id: number,
        public name: string,
        public color: string,
        public dateOfBirth: Date,
        public isLayingEggs: boolean
    ) {}

    computeAgeInYears(): number {
        const today = new Date();
        let age = today.getFullYear() - this.dateOfBirth.getFullYear();
        const hadBirthday =
            today.getMonth() > this.dateOfBirth.getMonth() ||
            (today.getMonth() === this.dateOfBirth.getMonth() &&
                today.getDate() >= this.dateOfBirth.getDate());
        if (!hadBirthday) age--;
        return age;
    }

    doStuff(): void {
        console.log(`${this.name} is clucking and scratching the ground...`);
    }

    toString(): string {
        return `Chicken{id=${this.id}, name=${this.name}, color=${this.color}, dateOfBirth=${this.dateOfBirth.toISOString().slice(0, 10)}, isLayingEggs=${this.isLayingEggs}}`;
    }
}

export class Egg {
    constructor(public id: number, public size: string) {}

    toString(): string {
        return `Egg{id=${this.id}, size=${this.size}}`;
    }
}

export class Poop {
    constructor(public id: number, public color: string, public size: string) {}

    toString(): string {
        return `Poop{id=${this.id}, color=${this.color}, size=${this.size}}`;
    }
}