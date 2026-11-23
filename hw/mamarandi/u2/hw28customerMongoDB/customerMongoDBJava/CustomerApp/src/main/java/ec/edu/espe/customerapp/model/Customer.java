package ec.edu.espe.customerapp.model;

/**
 *
 * @author Marcos Mamarandi, @ESPE
 */

public class Customer {
    private int id;
    private String name;
    private int age;
    private double moneySpent;

    public Customer() {
    }

    public Customer(int id, String name, int age, double moneySpent) {
        this.id = id;
        this.name = name;
        this.age = age;
        this.moneySpent = moneySpent;
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

    public int getAge() {
        return age;
    }

    public void setAge(int age) {
        this.age = age;
    }

    public double getMoneySpent() {
        return moneySpent;
    }

    public void setMoneySpent(double moneySpent) {
        this.moneySpent = moneySpent;
    }
}

