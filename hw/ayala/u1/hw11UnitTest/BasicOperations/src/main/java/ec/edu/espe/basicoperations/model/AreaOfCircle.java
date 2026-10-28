package ec.edu.espe.basicoperations.model;

/**
 *
 * @author Demian Armas, Logical Lords, @ESPE
 */
public class AreaOfCircle {
    public float calculatetAreaOfCircle(float radio){
        float area;
        area = (float) (Math.PI*Math.pow(radio,2));
        return area;
        
    }
}
