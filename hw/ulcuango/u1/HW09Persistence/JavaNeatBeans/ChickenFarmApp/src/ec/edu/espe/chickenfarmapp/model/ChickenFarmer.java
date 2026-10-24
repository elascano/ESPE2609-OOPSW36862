package ec.edu.espe.chickenfarmapp.model;

import java.util.ArrayList;

/**
 * @author Josselyn Ulcuango, Codelex, @ESPE
 */
public class ChickenFarmer {
    private String name;
    private ArrayList<ChickenCoop> coops;

    public ChickenFarmer() {
        this.coops = new ArrayList<>();
    }

    public ChickenFarmer(String name) {
        this.name = name;
        this.coops = new ArrayList<>();
    }

    public void addCoop(ChickenCoop coop) {
        if (coop != null) {
            coops.add(coop);
        }
    }

    public void removeCoop(int coopId) {
        coops.removeIf(coop -> coop.getId() == coopId);
    }

    @Override
    public String toString() {
        return "ChickenFarmer{name='" + name + "', coops=" + coops.size() + "}";
    }

    public String getName() {
        return name;
    }

    public void setName(String name) {
        this.name = name;
    }

    public ArrayList<ChickenCoop> getCoops() {
        return coops;
    }

    public void setCoops(ArrayList<ChickenCoop> coops) {
        this.coops = coops;
    }
}