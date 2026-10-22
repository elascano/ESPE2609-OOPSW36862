import { Chicken } from './Chicken';

export class ChickenCoop {
  private chickenCoopId: number;
  private chickens: (Chicken | null)[] = new Array(10).fill(null);
  private currentIndex: number;

  constructor(chickenCoopId: number) {
    this.chickenCoopId = chickenCoopId;
    this.currentIndex = 0;
  }

  public add(chicken: Chicken): void {
    for (const existing of this.chickens) {
      if (existing !== null && existing.getId() === chicken.getId()) {
        console.log(`There is already a chicken with id ${chicken.getId()}.`);
        return;
      }
    }
    for (let i = 0; i < this.chickens.length; i++) {
      if (this.chickens[i] === null) {
        this.chickens[i] = chicken;
        console.log(`Chicken added: ${chicken.getName()}`);
        return;
      }
    }
    console.log(`The coop is full (maximum ${this.chickens.length}).`);
  }

  public remove(chickenId: number): void {
    for (let i = 0; i < this.chickens.length; i++) {
      const current = this.chickens[i];
      if (current !== null && current.getId() === chickenId) {
        const name = current.getName();
        for (let j = i; j < this.chickens.length - 1; j++) {
          this.chickens[j] = this.chickens[j + 1];
        }
        this.chickens[this.chickens.length - 1] = null;
        console.log(`Chicken removed: ${name}`);
        return;
      }
    }
    console.log(`No chicken found with id ${chickenId}.`);
  }

  public resetIteration(): void {
    this.currentIndex = 0;
    console.log('Iteration reset.');
  }

  public next(): void {
    if (this.currentIndex < this.chickens.length && this.chickens[this.currentIndex] !== null) {
      console.log(String(this.chickens[this.currentIndex]));
      this.currentIndex++;
    } else {
      console.log('No more chickens. Reset the iteration to start over.');
    }
  }

  public toString(): string {
    const list = this.chickens.map((c) => (c === null ? 'null' : c.toString())).join(', ');
    return `ChickenCoop{chickenCoopId=${this.chickenCoopId}, chickens=[${list}], currentIndex=${this.currentIndex}}`;
  }

  public getChickenCoopId(): number {
    return this.chickenCoopId;
  }

  public setChickenCoopId(chickenCoopId: number): void {
    this.chickenCoopId = chickenCoopId;
  }

  public getChickens(): (Chicken | null)[] {
    return this.chickens;
  }

  public setChickens(chickens: (Chicken | null)[]): void {
    this.chickens = chickens;
  }

  public getCurrentIndex(): number {
    return this.currentIndex;
  }

  public setCurrentIndex(currentIndex: number): void {
    this.currentIndex = currentIndex;
  }
}