export class Circle {
    public calculateArea(radius: number): number {
        if (radius < 0) {
            throw new Error("Radius cannot be negative");
        }
        return Math.PI * Math.pow(radius, 2);
    }

    public calculatePerimeter(radius: number): number {
        if (radius < 0) {
            throw new Error("Radius cannot be negative");
        }
        return 2 * Math.PI * radius;
    }
}