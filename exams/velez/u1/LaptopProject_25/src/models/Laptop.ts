export class Laptop {
    private id: number;
    private brand: string;
    private model: string;
    private price: number;

    constructor(id: number, brand: string, model: string, price: number) {
        this.id = id;
        this.brand = brand;
        this.model = model;
        this.price = price;
    }

    public getId(): number {
        return this.id;
    }

    public getBrand(): string {
        return this.brand;
    }

    public getModel(): string {
        return this.model;
    }

    public getPrice(): number {
        return this.price;
    }

    public toString(): string {
        return `ID: ${this.id} | Brand: ${this.brand} | Model: ${this.model} | Price: $${this.price.toFixed(2)}`;
    }
}