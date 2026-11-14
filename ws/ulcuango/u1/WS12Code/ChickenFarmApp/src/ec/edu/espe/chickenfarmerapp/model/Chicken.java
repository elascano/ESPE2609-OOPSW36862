package ec.edu.espe.chickenfarmerapp.model;

import java.util.Date;

/**
 *
 * @author Josselyn Ulcuango, CodeLex, @ESPE
 */
public class Chicken {
    int id;
    String name;
    String color;
    Date bornDate;
    int age;
    boolean molting;

    public Chicken(int id, String name, String color, Date bornDate, int age, boolean molting) {
        this.id = id;
        this.name = name;
        this.color = color;
        this.bornDate = bornDate;
        this.age = age;
        this.molting = molting;
    }

    @Override
    public String toString() {
        return "Chicken{" + "id=" + id + ", name=" + name + ", color=" + color + ", bornDate=" + bornDate + ", age=" + age + ", molting=" + molting + '}';
    }
    
    
}
