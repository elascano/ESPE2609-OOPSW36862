package ec.edu.espe.animalfarmapp.model;

/**
 * @author Andrés Pachacama, ByteWorks, @ESPE
 */
public class Location {
    private String description;

    @Override
    public String toString() {
        return "Location{" + "description=" + description + '}';
    }

    public Location(String description) {
        this.description = description;
    }

    public String getDescription() {
        return description;
    }

    public void setDescription(String description) {
        this.description = description;
    }
}
