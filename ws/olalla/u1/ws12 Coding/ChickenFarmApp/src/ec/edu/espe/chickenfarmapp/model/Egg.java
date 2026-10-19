package ec.edu.espe.chickenfarmapp.model;

/**
 *
 * @author Renata Olalla, Binary Bosses, @ESPE
 */
public class Egg {
    int id;
    char size;
    
    public Egg(int id, char size ){
        this.id=id;
        this.size= size;
}

    @Override
    public String toString() {
        return "Egg{" + "id=" + id + ", size=" + size + '}';
    }

}