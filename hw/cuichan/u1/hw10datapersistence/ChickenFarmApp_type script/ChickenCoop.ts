import { Chicken } from './Chicken';

export class ChickenCoop {
  private _chickenCoopId: number;
  private _chickens: (Chicken | null)[];
  private _currentIndex: number;

  constructor(chickenCoopId: number, chickens?: (Chicken | null)[]) {
    this._chickenCoopId = chickenCoopId;
    this._chickens = chickens ? chickens : new Array(10).fill(null);
    this._currentIndex = 0;
  }

  public generateUniqueId(): number {
    let generatedId: number;
    let isDuplicate: boolean;

    do {
      generatedId = Math.floor(Math.random() * 100) + 1;
      isDuplicate = false;

      for (const c of this._chickens) {
        if (c !== null && c !== undefined && c.id === generatedId) {
          isDuplicate = true;
          break;
        }
      }
    } while (isDuplicate);

    return generatedId;
  }

  public add(chicken: Chicken): boolean {
    if (!chicken) {
      return false;
    }
    for (let i = 0; i < this._chickens.length; i++) {
      if (this._chickens[i] === null || this._chickens[i] === undefined) {
        this._chickens[i] = chicken;
        return true;
      }
    }
    return false;
  }

  public remove(chickenId: number): boolean {
    for (let i = 0; i < this._chickens.length; i++) {
      const c = this._chickens[i];
      if (c !== null && c !== undefined && c.id === chickenId) {
        this._chickens[i] = null;
        return true;
      }
    }
    return false;
  }

  public resetIteration(): void {
    this._currentIndex = 0;
  }

  public next(): Chicken | null {
    while (this._currentIndex < this._chickens.length) {
      const current = this._chickens[this._currentIndex];
      this._currentIndex++;
      if (current !== null && current !== undefined) {
        return current;
      }
    }
    return null;
  }

  public getCurrentPosition(): number {
    return this._currentIndex;
  }

  public getCount(): number {
    let count = 0;
    for (const c of this._chickens) {
      if (c !== null && c !== undefined) {
        count++;
      }
    }
    return count;
  }

  public get chickenCoopId(): number {
    return this._chickenCoopId;
  }

  public set chickenCoopId(chickenCoopId: number) {
    this._chickenCoopId = chickenCoopId;
  }

  public get chickens(): (Chicken | null)[] {
    return this._chickens;
  }

  public set chickens(chickens: (Chicken | null)[]) {
    this._chickens = chickens;
  }

  public toString(): string {
    return `ChickenCoop{chickenCoopId=${this._chickenCoopId}, chickens=${JSON.stringify(this._chickens)}}`;
  }
}