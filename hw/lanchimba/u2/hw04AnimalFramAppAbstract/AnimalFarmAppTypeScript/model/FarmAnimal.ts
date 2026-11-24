/**
 * Abstract superclass representing a generic farm animal.
 * Cannot be instantiated directly; concrete subclasses must implement
 * the abstract methods.
 */
export abstract class FarmAnimal {
  private id: number;
  private breed: string;
  private bornOn: Date;

  constructor(id: number, breed: string, bornOn: Date) {
    this.id = id;
    this.breed = breed;
    this.bornOn = bornOn;
  }

  /**
   * Calculates the exact age of the animal in completed months
   * from its birth date to the current date.
   */
  public getAgeInMonths(): number {
    const today = new Date();
    const todayYear = today.getFullYear();
    const todayMonth = today.getMonth();
    const todayDay = today.getDate();

    const bornYear = this.bornOn.getFullYear();
    const bornMonth = this.bornOn.getMonth();
    const bornDay = this.bornOn.getDate();

    if (
      bornYear > todayYear ||
      (bornYear === todayYear && bornMonth > todayMonth) ||
      (bornYear === todayYear && bornMonth === todayMonth && bornDay > todayDay)
    ) {
      return 0;
    }

    let months = (todayYear - bornYear) * 12 + (todayMonth - bornMonth);
    if (todayDay < bornDay) {
      months -= 1;
    }
    return Math.max(0, months);
  }

  /**
   * Defines how this specific farm animal feeds.
   */
  public abstract feed(): string;

  /**
   * Defines the characteristic sound of this farm animal.
   */
  public abstract makeSound(): string;

  /**
   * Defines the specific farm output or production of this animal.
   */
  public abstract produce(): string;

  public getId(): number {
    return this.id;
  }

  public setId(id: number): void {
    this.id = id;
  }

  public getBreed(): string {
    return this.breed;
  }

  public setBreed(breed: string): void {
    this.breed = breed;
  }

  public getBornOn(): Date {
    return this.bornOn;
  }

  public setBornOn(bornOn: Date): void {
    this.bornOn = bornOn;
  }

  protected formatDate(date: Date): string {
    const year = date.getFullYear();
    const month = String(date.getMonth() + 1).padStart(2, "0");
    const day = String(date.getDate()).padStart(2, "0");
    return `${year}-${month}-${day}`;
  }

  public toString(): string {
    return `FarmAnimal{id=${this.id}, breed='${this.breed}', bornOn=${this.formatDate(this.bornOn)}}`;
  }
}
