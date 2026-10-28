package ec.edu.espe.basicoperations.model;

/**
 *
 * @author Mateo Unda, CodeLex, @ESPE
 */
public class Calculation {
    public float time(float distance, float speed){
        float travelTime;
        travelTime = distance / speed;
        return travelTime;
    }
}
