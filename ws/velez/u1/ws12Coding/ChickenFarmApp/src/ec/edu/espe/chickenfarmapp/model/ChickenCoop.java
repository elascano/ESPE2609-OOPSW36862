package ec.edu.espe.chickenfarmapp.model;

import java.util.Date;

/**
 *
 * @author Yandry Velez, Codelex, @ESPE
 */
public class ChickenCoop {
    
    private int chickenCoopId;
    private Chicken chickens[]= new Chicken[10];
    private int currentIndex;

    public void add(Chicken chicken){
        if(chickens.length < 10){
            
        }
    }
public int count(){
    int total = 0;
    for(Chicken chicken : chickens){
        if(chicken != null){
            total++;
        }
    }
    return total;
}

public Chicken find(int chickenId){
    for(Chicken chicken : chickens){
        if(chicken != null && chicken.getIde() == chickenId){
            return chicken;
        }
    }
    return null;
}

public boolean remove(int chickenId){
    for(int i = 0; i < chickens.length; i++){
        if(chickens[i] != null && chickens[i].getIde() == chickenId){
            for(int j = i; j < chickens.length - 1; j++){
                chickens[j] = chickens[j + 1];
            }
            chickens[chickens.length - 1] = null;
            return true;
        }
    }
    return false;
}
    public void resetIteration(){
       currentIndex = 0; 
    }
    public Chicken next(){
        return new Chicken (3,"luci", "White", new Date(2020,3,3),true);
    }
    @Override
    public String toString() {
        return "ChickenCoop{" + "chickenCoopId=" + chickenCoopId + ", chickens=" + chickens + '}';
    }

    public ChickenCoop(int chickenCoopId, Chicken[] chickens) {
        this.chickenCoopId = chickenCoopId;
        this.chickens = chickens;
    }

    /**
     * @return the chickenCoopId
     */
    public int getChickenCoopId() {
        return chickenCoopId;
    }

    /**
     * @param chickenCoopId the chickenCoopId to set
     */
    public void setChickenCoopId(int chickenCoopId) {
        this.chickenCoopId = chickenCoopId;
    }

    /**
     * @return the chickens
     */
    public Chicken[] getChickens() {
        return chickens;
    }

    /**
     * @param chickens the chickens to set
     */
    public void setChickens(Chicken[] chickens) {
        this.chickens = chickens;
    }
    
}
