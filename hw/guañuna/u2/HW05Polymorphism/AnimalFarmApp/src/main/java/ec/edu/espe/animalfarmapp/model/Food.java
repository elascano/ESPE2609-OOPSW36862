package ec.edu.espe.animalfarmapp.model;

/**
 *
 * @author Cristopher Guañuna, Runtime Dynamic @ESPE
 */
public class Food {
    private String description;

    public Food(String description) {
        this.description = description;
    }

    @Override
    public String toString() {
        return description;
    }

    public String getDescription() { return description; }
    public void setDescription(String description) { this.description = description; }
}