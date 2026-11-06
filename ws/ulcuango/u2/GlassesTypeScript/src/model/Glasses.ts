export class Glasses {
    id: number;
    brand: string;
    price: number;
    isPolarized: boolean;

    constructor(id: number, brand: string, price: number, isPolarized: boolean) {
        this.id = id;
        this.brand = brand;
        this.price = price;
        this.isPolarized = isPolarized;
    }

    displayInfo(): void {
        const polarizedText = this.isPolarized ? 'Yes' : 'No';
        console.log(`ID: ${this.id} | Brand: ${this.brand} | Price: $${this.price} | Polarized: ${polarizedText}`);
    }
}