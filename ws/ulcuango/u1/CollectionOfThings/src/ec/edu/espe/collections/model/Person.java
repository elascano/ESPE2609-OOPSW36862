package ec.edu.espe.collections.model;

/**
 *
 * @author Josselyn Ulcuango, CodeLex, @ESPE
 */
public class Person {
    private int id;
    private String name;
    private float salary;

    public Person(int id, String name, float salary) {
        this.id = id;
        this.name = name;
        this.salary = salary;
    }
    
    
    @Override
    public String toString() {
        return "Person{" + "id=" + getId() + ", name=" + getName() + ", salary=" + getSalary() + '}';
    }

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

    public float getSalary() {
        return salary;
    }

    public void setSalary(float salary) {
        this.salary = salary;
    }
    
    
}
