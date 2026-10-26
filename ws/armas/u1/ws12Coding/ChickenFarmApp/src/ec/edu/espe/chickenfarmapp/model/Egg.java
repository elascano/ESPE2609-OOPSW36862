package ec.edu.espe.chickenfarmapp.model;

/**
 *
 * @author Demian Armas, Logical Lords, @ESPE
 */
public class Egg {

    private int id;
    private char size;

    @Override
    public String toString() {
        return "Egg{" + "id=" + id + ", size=" + size + '}';
    }

    
    
    
    public Egg(int id, char size) {
        this.id = id;
        this.size = size;
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
     * @return the size
     */
    public char getSize() {
        return size;
    }

    /**
     * @param size the size to set
     */
    public void setSize(char size) {
        this.size = size;
    }
    
    
    
    
}