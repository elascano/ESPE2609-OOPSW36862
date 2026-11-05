export class RectanglePerimeter {
    calculatePerimeter(base: number, height: number): number {
        if (base <= 0 || height <= 0) {
            throw new RangeError("Invalid dimensions");
        }
        const perimeter = 2 * (base + height);
        return perimeter;
    }
}
