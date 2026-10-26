/**
 * @author Leslye Cuichan, Infinite loops, @eESPE
 */

export class DistanceCalculation {
    public calculateDistance(speed: number, time: number): number {
        if (speed < 0 || time < 0) {
            return 0.0;
        }
        
        let distance: number;
        distance = speed * time;
        distance = Math.round(distance * 100.0) / 100.0;
        
        return distance;
    }
}