package ec.edu.espe.chickenfarmapp.model;

import java.util.ArrayList;

/**
 * @author Josselyn Ulcuango, Codelex, @ESPE
 */
public class ChickenCoop {
    private int id;
    private ArrayList<Chicken> chickens;

    public ChickenCoop(int id) {
        this.id = id;
        this.chickens = new ArrayList<>();
    }

    public void addChicken(Chicken chicken) {
        if (chicken != null) {
            chickens.add(chicken);
            System.out.println("Chicken '" + chicken.getName() + "' added to coop " + id);
        }
    }

    public void removeChicken(int chickenId) {
        Chicken chickenToRemove = null;
        for (Chicken chicken : chickens) {
            if (chicken.getId() == chickenId) {
                chickenToRemove = chicken;
                break;
            }
        }

        if (chickenToRemove != null) {
            chickens.remove(chickenToRemove);
            System.out.println("Chicken '" + chickenToRemove.getName() + "' removed from coop " + id);
        } else {
            System.out.println("Chicken with ID " + chickenId + " was not found in coop " + id);
        }
    }

    public void reset() {
        chickens.clear();
        System.out.println("Coop " + id + " has been reset.");
    }

    public int countChickens() {
        return chickens.size();
    }

    @Override
    public String toString() {
        StringBuilder sb = new StringBuilder();
        sb.append("ChickenCoop{id=").append(id).append(", chickens=[\n");
        for (Chicken chicken : chickens) {
            sb.append("  ").append(chicken.toString()).append("\n");
        }
        sb.append("]}");
        return sb.toString();
    }

    public int getId() {
        return id;
    }

    public void setId(int id) {
        this.id = id;
    }

    public ArrayList<Chicken> getChickens() {
        return chickens;
    }

    public void setChickens(ArrayList<Chicken> chickens) {
        this.chickens = chickens;
    }
}