package ec.edu.espe.chickenfarmapp.model;

import java.util.Date;

/**
 *
 * @author Yandry Velez, Codelex, @ESPE
 */
public class Chicken {
    private int ide;
    private String name;
    private String color;
    private Date bornOnDate;
    private int age;
    private boolean molting;

    public Chicken(int ide, String name, String color, Date bornOnDate, int age, boolean molting) {
        this.ide = ide;
        this.name = name;
        this.color = color;
        this.bornOnDate = bornOnDate;
        this.age = age;
        this.molting = molting;
    }

    @Override
    public String toString() {
        return "Chicken{" + "ide=" + ide + ", name=" + name + ", color=" + color + ", bornOnDate=" + bornOnDate + ", age=" + age + ", molting=" + molting + '}';
    }
    

    /**
     * @return the ide
     */
    public int getIde() {
        return ide;
    }

    /**
     * @param ide the ide to set
     */
    public void setIde(int ide) {
        this.ide = ide;
    }

    /**
     * @return the name
     */
    public String getName() {
        return name;
    }

    /**
     * @param name the name to set
     */
    public void setName(String name) {
        this.name = name;
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
     * @return the bornOnDate
     */
    public Date getBornOnDate() {
        return bornOnDate;
    }

    /**
     * @param bornOnDate the bornOnDate to set
     */
    public void setBornOnDate(Date bornOnDate) {
        this.bornOnDate = bornOnDate;
    }

    /**
     * @return the age
     */
    public int getAge() {
        return age;
    }

    /**
     * @param age the age to set
     */
    public void setAge(int age) {
        this.age = age;
    }

    /**
     * @return the molting
     */
    public boolean isMolting() {
        return molting;
    }

    /**
     * @param molting the molting to set
     */
    public void setMolting(boolean molting) {
        this.molting = molting;
    }
    
}
