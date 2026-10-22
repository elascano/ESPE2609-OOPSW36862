/**
 * @author Paucar Matias, ByteWorks, @ESPE
 */
export class Chicken {
    private id: number;
    private name: string;
    private color: string;
    private bornOnDate: Date;
    private ageInYears: number;
    private molting: boolean;

    constructor(id: number, name: string, color: string, bornOnDate: Date, molting: boolean) {
        this.id = id;
        this.name = name;
        this.color = color;
        this.bornOnDate = bornOnDate;
        this.molting = molting;
        this.ageInYears = this.computeAgeInYears();
    }

    // the age is based only on years
    public computeAgeInYears(): number {
        const today = new Date();
        // getUTCFullYear so the time zone does not change the year (for example 1 of January)
        const age = today.getFullYear() - this.bornOnDate.getUTCFullYear();
        this.ageInYears = age;
        return age;
    }

    public doStuff(): void {
        this.cluck();
        this.wander();
        this.eat();
        this.drink(1.3);
    }

    private cluck(): void {
        console.log("The chicken " + this.name + " is clucking ...");
    }

    private wander(): void {
        console.log("The chicken " + this.name + " is wandering ...");
    }

    private eat(): void {
        console.log("The chicken " + this.name + " is eating ...");
    }

    private drink(amountOfWater: number): void {
        console.log("The chicken " + this.name + " is drinking ..." + amountOfWater + "ml of water");
    }

    public toString(): string {
        const date = this.bornOnDate.toISOString().substring(0, 10);
        return "Chicken{id=" + this.id + ", name=" + this.name + ", color=" + this.color
            + ", bornOnDate=" + date + ", age=" + this.ageInYears + ", molting=" + this.molting + "}";
    }

    // getters and setters
    public getId(): number {
        return this.id;
    }

    public setId(id: number): void {
        this.id = id;
    }

    public getName(): string {
        return this.name;
    }

    public setName(name: string): void {
        this.name = name;
    }

    public getColor(): string {
        return this.color;
    }

    public setColor(color: string): void {
        this.color = color;
    }

    public getBornOnDate(): Date {
        return this.bornOnDate;
    }

    public setBornOnDate(bornOnDate: Date): void {
        this.bornOnDate = bornOnDate;
    }

    public getAgeInYears(): number {
        return this.ageInYears;
    }

    public setAgeInYears(): void {
        this.ageInYears = this.computeAgeInYears();
    }

    public isMolting(): boolean {
        return this.molting;
    }

    public setMolting(molting: boolean): void {
        this.molting = molting;
    }
}
