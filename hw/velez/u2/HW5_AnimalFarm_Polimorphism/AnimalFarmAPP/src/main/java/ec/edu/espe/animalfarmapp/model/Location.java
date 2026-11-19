package ec.edu.espe.animalfarmapp.model;

/**
 *
 * @author Yandry Velez, Codelex, @ESPE
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

    /**
     * @return the description
     */
    public String getDescription() {
        return description;
    }

    /**
     * @param description the description to set
     */
    public void setDescription(String description) {
        this.description = description;
    }

}

