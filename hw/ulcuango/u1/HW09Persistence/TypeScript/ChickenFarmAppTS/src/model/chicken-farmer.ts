import { ChickenCoop } from './chicken-coop';

export class ChickenFarmer {
    private name: string;
    private coops: ChickenCoop[];

    constructor(name: string = '') {
        this.name = name;
        this.coops = [];
    }

    public addCoop(coop: ChickenCoop): void {
        if (coop !== null) {
            this.coops.push(coop);
        }
    }

    public removeCoop(coopId: number): void {
        this.coops = this.coops.filter(coop => coop.getId() !== coopId);
    }

    public toString(): string {
        return "ChickenFarmer{name='" + this.name + "', coops=" + this.coops.length + "}";
    }

    public getName(): string {
        return this.name;
    }

    public setName(name: string): void {
        this.name = name;
    }

    public getCoops(): ChickenCoop[] {
        return this.coops;
    }

    public setCoops(coops: ChickenCoop[]): void {
        this.coops = coops;
    }
}