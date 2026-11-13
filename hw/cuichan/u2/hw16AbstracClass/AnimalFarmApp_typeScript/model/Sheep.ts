import { FarmAnimal } from './FarmAnimal';

export class Sheep extends FarmAnimal {
    private lastSheering: Date;
    private isProducingWool: boolean;
    private isMeat: boolean;

    constructor(lastSheering: Date, isProducingWool: boolean, isMeat: boolean, id: number, breed: string, bornOnDate: Date) {
        super(id, breed, bornOnDate);
        this.lastSheering = lastSheering;
        this.isProducingWool = isProducingWool;
        this.isMeat = isMeat;
    }

    public feed(): void {
        console.log(`Sheep (ID: ${this.id}) is grazing.`);
    }

    public toString(): string {
        const day = this.lastSheering.getDate();
        const month = this.lastSheering.getMonth() + 1;
        const year = this.lastSheering.getFullYear();

        const dayStr = day < 10 ? '0' + day : '' + day;
        const monthStr = month < 10 ? '0' + month : '' + month;

        return `Sheep{${super.toString()}lastSheering=${dayStr}/${monthStr}/${year}, isProducingWool=${this.isProducingWool}, isMeat=${this.isMeat}}`;
    }
}