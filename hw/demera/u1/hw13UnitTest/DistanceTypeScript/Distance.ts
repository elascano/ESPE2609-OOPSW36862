export class Distance {
    private static readonly floatMax = 3.4028235e+38;
    private static readonly floatMin = 1.4e-45;

    public computeDistance(speed: number, time: number): number {
        if (Number.isNaN(speed) || Number.isNaN(time)) {
            return 0.0;
        }

        if (!Number.isFinite(speed)) {
            if (speed > 0 && time > 0) {
                return time;
            }
            return 0.0;
        }

        if (!Number.isFinite(time)) {
            return 0.0;
        }

        if (speed < 0 || time < 0) {
            return 0.0;
        }

        const distanceValue = speed * time;

        if (distanceValue > Distance.floatMax) {
            return Distance.floatMax;
        }

        if (distanceValue < -Distance.floatMax) {
            return -Distance.floatMax;
        }

        if (distanceValue > 0 && distanceValue < Distance.floatMin) {
            return Distance.floatMin;
        }

        return distanceValue;
    }
}