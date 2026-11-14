export class Cellphone {
    private id: string;
    private brand: string;
    private model: string;
    private price: number;

    constructor(id: string, brand: string, model: string, price: number) {
        this.id = id;
        this.brand = brand;
        this.model = model;
        this.price = price;
    }

    public getId(): string {
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
}