export class Distance {
    static floatMax = 3.4028235e+38;
    static floatMin = 1.4e-45;
    computeDistance(speed, time) {
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
