class FarmAnimal {
    constructor(
        public id: number,
        public breed: string,
        public bornOnDate: Date
    ) {}

    getAgeInMonths(): number {
        return 0;
    }

    toString(): string {
        return `id=${this.id}, breed='${this.breed}', bornOnDate=${this.bornOnDate.toDateString()}`;
    }
}

class Chicken extends FarmAnimal {
    constructor(
        id: number,
        breed: string,
        bornOnDate: Date,
        public isMolting: boolean,
        public numberOfLaidEggPerWeek: number,
        public isMeat: boolean
    ) {
        super(id, breed, bornOnDate);
    }

    toString(): string {
        return `Chicken{${super.toString()}, isMolting=${this.isMolting}, numberOfLaidEggPerWeek=${this.numberOfLaidEggPerWeek}, isMeat=${this.isMeat}}`;
    }
}

class Cow extends FarmAnimal {
    constructor(
        id: number,
        breed: string,
        bornOnDate: Date,
        public isProducingMilk: boolean
    ) {
        super(id, breed, bornOnDate);
    }

    toString(): string {
        return `Cow{${super.toString()}, isProducingMilk=${this.isProducingMilk}}`;
    }
}

class Pig extends FarmAnimal {
    constructor(id: number, breed: string, bornOnDate: Date) {
        super(id, breed, bornOnDate);
    }

    toString(): string {
        return `Pig{${super.toString()}}`;
    }
}

class Sheep extends FarmAnimal {
    constructor(
        id: number,
        breed: string,
        bornOnDate: Date,
        public lastSheering: Date
    ) {
        super(id, breed, bornOnDate);
    }

    toString(): string {
        return `Sheep{${super.toString()}, lastSheering=${this.lastSheering.toDateString()}}`;
    }
}

console.log("Yandry Farm Animal");
console.log("==================");

const farmAnimal = new FarmAnimal(1, "Holstein", new Date());
console.log(`My farm Animal is --> FarmAnimal{${farmAnimal.toString()}}`);

const chicken = new Chicken(2, "Criolla", new Date(2022, 9, 10), false, 10, false);
console.log(`Chicken --> ${chicken.toString()}`);

const cow = new Cow(3, "Braman", new Date(2021, 4, 20), true);
console.log(`Cow --> ${cow.toString()}`);

const pig = new Pig(4, "Pietran", new Date(2020, 0, 15));
console.log(`Pig --> ${pig.toString()}`);

const sheep = new Sheep(5, "Lanuda", new Date(2021, 7, 12), new Date(2024, 9, 5));
console.log(`Sheep --> ${sheep.toString()}`);