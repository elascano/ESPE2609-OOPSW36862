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
        this.ageInYears = 0;
        this.molting = molting;
    }

    public computeAgeInYears(): number {
        const today = new Date();
        let age = today.getFullYear() - this.bornOnDate.getFullYear();

        // Misma lógica exacta que en Java y Python
        if (today.getMonth() < this.bornOnDate.getMonth()) {
            age--;
        } else if (today.getMonth() === this.bornOnDate.getMonth()) {
            if (today.getDate() < this.bornOnDate.getDate()) {
                age--;
            } else if (today.getDate() === this.bornOnDate.getDate()) {
                console.log("Today is this chicken's birthday!");
            }
        }
        return age;
    }

    // Getters y Setters
    public getId(): number { return this.id; }
    public getName(): string { return this.name; }
    public getBornOnDate(): Date { return this.bornOnDate; }
    public isMolting(): boolean { return this.molting; }

    // Método toString (similar al de Java)
    public toString(): string {
        return `Chicken{id=${this.id}, name=${this.name}, color=${this.color}, bornOnDate=${this.bornOnDate.toISOString().split('T')[0]}, age=${this.computeAgeInYears()}, molting=${this.molting}}`;
    }
}