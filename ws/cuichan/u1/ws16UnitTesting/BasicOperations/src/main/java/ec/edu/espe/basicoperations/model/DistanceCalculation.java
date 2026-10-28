package ec.edu.espe.basicoperations.model;

/**
 *
 * @author Leslye Cuichan, Infinite loops, @eESPE
 */
public class DistanceCalculation {

    public float calculateDistance(float speed, float time) {
       if (speed < 0 || time < 0) {
            return 0.0F;
        }
       float distance; 
       distance = speed * time;
       distance = Math.round(distance * 100.0f) / 100.0f;
        return distance;            
    }
}