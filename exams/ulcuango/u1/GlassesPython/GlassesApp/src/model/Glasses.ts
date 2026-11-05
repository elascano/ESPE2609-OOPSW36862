export class Glasses {
    private id: string;
    private brand: string;
    private frameType: string;
    private price: number;

    constructor(id: string, brand: string, frameType: string, price: number) {
        this.id = id;
        this.brand = brand;
        this.frameType = frameType;
        this.price = price;
    }

    public getId(): string {
        return this.id;
    }

    public setId(id: string): void {
        this.id = id;
    }

    public getBrand(): string {
        return this.brand;
    }

    public setBrand(brand: string): void {
        this.brand = brand;
    }

    public getFrameType(): string {
        return this.frameType;
    }

    public setFrameType(frameType: string): void {
        this.frameType = frameType;
    }

    public getPrice(): number {
        return this.price;
    }

    public setPrice(price: number): void {
        this.price = price;
    }
}