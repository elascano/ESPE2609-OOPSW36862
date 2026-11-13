
package ec.edu.espe.model;

/**
 *
 * @author Leslye Cuichan, Byte Fabric & devs, @ESPE
 */
public class Food {
    private String description;

    @Override
    public String toString() {
        return  description ;
    }

    public Food(String description) {
        this.description = description;
    }

    public String getDescription() {
        return description;
    }

    public void setDescription(String description) {
        this.description = description;
    }
    
    
}
