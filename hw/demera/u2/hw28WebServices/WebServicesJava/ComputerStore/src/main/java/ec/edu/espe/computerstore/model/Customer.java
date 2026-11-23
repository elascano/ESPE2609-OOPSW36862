package ec.edu.espe.computerstore.model;

/**
 *
 * @author Francisco Demera, Bytes & Fabric Devs, @ESPE
 */
import org.springframework.data.annotation.Id;
import org.springframework.data.mongodb.core.mapping.Document;
import org.springframework.data.mongodb.core.mapping.Field;

@Document(collection = "Customer")
public class Customer {

    @Id
    private String mongoId;

    @Field("id")
    private int id;
    private String name;
    private int age;
    private float moneySpent;

    public int getId() {
        return id;
    }

    public void setId(int id) {
        this.id = id;
    }

    public String getName() {
        return name;
    }

    public void setName(String name) {
        this.name = name;
    }

    public int getAge() {
        return age;
    }

    public void setAge(int age) {
        this.age = age;
    }

    public float getMoneySpent() {
        return moneySpent;
    }

    public void setMoneySpent(float moneySpent) {
        this.moneySpent = moneySpent;
    }
}
