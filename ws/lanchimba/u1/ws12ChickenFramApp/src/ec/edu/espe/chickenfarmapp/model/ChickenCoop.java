package ec.edu.espe.chickenfarmapp.model;

/**
 *
 * @author Cristopher Lanchimba, Runtime Dynamic, @ESPE
 */
public class ChickenCoop {
    private int chickenCoopId;
    private Chicken[] chickens= new Chicken[100];

    public ChickenCoop(int chickenCoopId, Chicken[] chikens) {
        this.chickenCoopId = chickenCoopId;
        this.chickens = chikens;
    }

    public Chicken[] getChickens() {
        return chickens;
    }

    public void setChickens(Chicken[] chickens) {
        this.chickens = chickens;
    }

    public int getChickenCoopId() {
        return chickenCoopId;
    }

    public void setChickenCoopId(int chickenCoopId) {
        this.chickenCoopId = chickenCoopId;
    }
}
