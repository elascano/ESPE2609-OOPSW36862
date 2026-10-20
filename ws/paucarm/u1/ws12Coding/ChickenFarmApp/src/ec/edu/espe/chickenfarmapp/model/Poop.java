package ec.edu.espe.chickenfarmapp.model;

/**
 *
 * @author Paucar Matias, ByteWorks, @ESPE
 */
public class Poop {
    private int id;
    private String color;
    private char consistency;

    public Poop(int id, String color, char consistency) {
        this.id = id;
        this.color = color;
        this.consistency = consistency;
    }

    @Override
    public String toString() {
        return "Poop{" + "id=" + id + ", color=" + color + ", consistency=" + consistency + '}';
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
     * @return the color
     */
    public String getColor() {
        return color;
    }

    /**
     * @param color the color to set
     */
    public void setColor(String color) {
        this.color = color;
    }

    /**
     * @return the consistency
     */
    public char getConsistency() {
        return consistency;
    }

    /**
     * @param consistency the consistency to set
     */
    public void setConsistency(char consistency) {
        this.consistency = consistency;
    }
    
    
}
