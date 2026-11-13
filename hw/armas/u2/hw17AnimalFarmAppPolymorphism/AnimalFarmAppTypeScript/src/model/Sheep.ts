// Demian Armas, Logical Lords, @ESPE

import { FarmAnimal } from "./FarmAnimal.ts";

export class Sheep extends FarmAnimal {
    lastSheering: Date;
    isProducingWool: boolean;
    kgOfWoolForMonth: number;

    constructor(
        lastSheering: Date,
        isProducingWool: boolean,
        kgOfWoolForMonth: number,
        id: number,
        breed: string,
        bornOnDate: Date
    ) {
        super(id, breed, bornOnDate);
        this.lastSheering = lastSheering;
        this.isProducingWool = isProducingWool;
        this.kgOfWoolForMonth = kgOfWoolForMonth;
    }

    shear(): string {
        return "shearing the sheep";
    }

    override toString(): string {
        const day = this.lastSheering.getDate();
        const month = this.lastSheering.getMonth() + 1;
        const year = this.lastSheering.getFullYear();
        return `Sheep{${super.toString()}, lastSheering=${day}/${month}/${year}, ` +
            `isProducingWool=${this.isProducingWool}, kgOfWoolForMonth=${this.kgOfWoolForMonth}}`;
    }
}
