package ec.edu.espe.chickenfarmapp.model;

/**
 *
 * @author Daniel Sangoquiza, Saborcode, @ESPE
 */
public class Egg {
    private int id;
    private char size;
    
    public Egg(int id, char size){
        this.id = id;
        this.size = size;
    }

    @Override
    public String toString() {
        return "Egg{" + "id=" + getId() + ", size=" + getSize() + '}';
    }

    public int getId() {
        return id;
    }

    public void setId(int id) {
        this.id = id;
    }

    public char getSize() {
        return size;
    }

    public void setSize(char size) {
        this.size = size;
    }
}