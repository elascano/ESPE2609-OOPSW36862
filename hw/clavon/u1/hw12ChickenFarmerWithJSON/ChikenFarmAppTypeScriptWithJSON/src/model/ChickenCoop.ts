import { Chicken } from './Chicken';

export class ChickenCoop {
  private chickenCoopId: number;
  private chickens: (Chicken | null)[] = new Array(10).fill(null);
  private count: number = 0;
  private currentIndex: number = 0;

  constructor(chickenCoopId: number, initialChickens?: Chicken[]) {
    this.chickenCoopId = chickenCoopId;
    if (initialChickens) {
      for (const chicken of initialChickens) {
        if (chicken) {
          this.add(chicken);
        }
      }
    }
  }

  public add(chicken: Chicken | null): boolean {
    if (!chicken || this.count >= this.chickens.length) {
      return false;
    }
    this.chickens[this.count] = chicken;
    this.count++;
    return true;
  }

  public remove(chickenId: number): boolean {
    let indexFound = -1;

    for (let i = 0; i < this.count; i++) {
      if (this.chickens[i]?.getId() === chickenId) {
        indexFound = i;
        break;
      }
    }

    if (indexFound !== -1) {
      for (let i = indexFound; i < this.count - 1; i++) {
        this.chickens[i] = this.chickens[i + 1];
      }
      this.chickens[this.count - 1] = null;
      this.count--;
      return true;
    }
    return false;
  }

  public resetIteration(): void {
    this.currentIndex = 0;
  }

  public next(): Chicken | null {
    if (this.currentIndex < this.count) {
      const currentChicken = this.chickens[this.currentIndex];
      this.currentIndex++;
      return currentChicken;
    }
    return null;
  }

  public getCurrentPosition(): number {
    return this.currentIndex;
  }

  public getCurrentChicken(): Chicken | null {
    if (this.currentIndex > 0 && this.currentIndex <= this.count) {
      return this.chickens[this.currentIndex - 1];
    }
    return null;
  }

  public getChickenCoopId(): number { return this.chickenCoopId; }
  public setChickenCoopId(id: number): void { this.chickenCoopId = id; }

  public getChickens(): (Chicken | null)[] { return this.chickens; }
  public getCount(): number { return this.count; }

  public toString(): string {
    const activeChickens = this.chickens
      .slice(0, this.count)
      .map((c) => c?.toString())
      .join(', ');
    return `ChickenCoop{chickenCoopId=${this.chickenCoopId}, totalChickens=${this.count}, chickens=[${activeChickens}]}`;
  }
}