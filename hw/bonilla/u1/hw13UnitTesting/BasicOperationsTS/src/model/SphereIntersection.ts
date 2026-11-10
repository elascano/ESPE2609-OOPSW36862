/**
 * @author Angel Bonilla, @ESPE
 */
export class SphereIntersection {

    public computeVolume(radius1: number, radius2: number, distance: number): number {
        if (radius1 < 0 || radius2 < 0 || distance < 0) {
            throw new Error("Radii and distance cannot be negative");
        }

        if (radius1 === 0 || radius2 === 0 || distance >= radius1 + radius2) {
            return 0.0;
        }

        if (distance <= Math.abs(radius1 - radius2)) {
            const minRadius = Math.min(radius1, radius2);
            return (4.0 / 3.0) * Math.PI * Math.pow(minRadius, 3);
        }

        const part1 = Math.pow(radius1 + radius2 - distance, 2);
        const part2 =
            Math.pow(distance, 2) +
            2 * distance * radius1 -
            3 * Math.pow(radius1, 2) +
            2 * distance * radius2 +
            6 * radius1 * radius2 -
            3 * Math.pow(radius2, 2);

        return (Math.PI * part1 * part2) / (12.0 * distance);
    }
}