import { Chicken } from './Chicken';
import { Egg } from './Egg';
import { Poop } from './Poop';

export class ChickenCoop {
    constructor(
        public idCoop: number,
        public chickens: Chicken[] = [],
        public eggs: Egg[] = [],
        public poops: Poop[] = []
    ) {}

    public addChicken(chicken: Chicken): void {
        this.chickens.push(chicken);
    }

    public toString(): string {
        return `ChickenCoop(id=${this.idCoop}, chickens=${this.chickens.length}, eggs=${this.eggs.length}, poops=${this.poops.length})`;
    }
}
