package ec.edu.espe.animalfarmapp.model;

/**
 * @author Andrés Pachacama, ByteWorks, @ESPE
 */
public class Food {
    private String description;

    @Override
    public String toString() {
        return description;
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
