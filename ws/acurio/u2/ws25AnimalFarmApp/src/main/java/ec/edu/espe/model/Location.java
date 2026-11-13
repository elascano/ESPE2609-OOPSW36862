package ec.edu.espe.model;

/**
 *
 * @author Isaac Acurio, Logical Lords, @ESPE
 */
public class Location {
    private int id;
    private String description;

    @Override
    public String toString() {
        return "Location{" + "id=" + id + ", description=" + description + '}';
    }

    public Location(int id, String description) {
        this.id = id;
        this.description = description;
    }

    /**
     * @return the id
     */
    public int getId() {
        return id;
    }

    /**
     * @param id the id to set
     */
    public void setId(int id) {
        this.id = id;
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
