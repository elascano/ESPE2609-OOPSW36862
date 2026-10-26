package ec.edu.espe.basicoperations.model;

import ec.edu.espe.basicoperations.model.Operation;

/**
 *
 * @author David Clavón, Infinite Loops, @ESPE
 */
public class conversionOfDegreesToRadians{
    
    public double conversionAngle(double angleInDegrees) {
        System.out.println("Angulos de primera vuelta");
        
        double normalizedAngle = angleInDegrees % 360.0;
        
        if (normalizedAngle < 0) {
            normalizedAngle += 360.0;
        }
        
        double conversion = (normalizedAngle * Math.PI) / 180.0;
        return conversion;
    }
}