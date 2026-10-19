package ec.edu.espe.chickenfarmapp.model;

/**
 *
 * @author Usuario
 */
public class Egg {
    int id;
    char size;
    public Egg(int id, char size){
        this.size = size;
    }

    @Override
    public String toString() {
        return "Egg{" + "id=" + id + ", size=" + size + '}';
    }
    
}
