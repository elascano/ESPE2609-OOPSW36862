package ec.edu.espe.chickenfarmapp.model;

import java.util.Arrays;

/**
 *
 * @author Paucar Matias, ByteWorks, @ESPE
 */
public class ChickenCoop {
    private int chickenCoopId;
    private Chicken chickens[] = new Chicken[10];
    private int currentIndex;
    private int numberOfChickens;

    //we need to know the maximum, in this case is 10
    //we need to know the index

    /**
     * @return true if the chicken was added, false if the coop is full
     */
    public boolean add(Chicken chicken){
        //if we have less than 10 chickens, we can add one more
        //else we can not add any more
        if (getNumberOfChickens() < getChickens().length) {
            getChickens()[getNumberOfChickens()] = chicken;
            setNumberOfChickens(getNumberOfChickens() + 1);
            return true;
        }
        return false;
    }

    /**
     * @return true if the chicken was removed, false if there is no chicken with that id
     */
    public boolean remove(int chickenId){
        int position = -1;

        //we look for the position of the chicken with that id
        for (int i = 0; i < getNumberOfChickens(); i++) {
            if (getChickens()[i].getId() == chickenId) {
                position = i;
                break;
            }
        }

        if (position == -1) {
            return false;
        }

        //we move the next chickens one position to the left
        for (int i = position; i < getNumberOfChickens() - 1; i++) {
            getChickens()[i] = getChickens()[i + 1];
        }

        getChickens()[getNumberOfChickens() - 1] = null;
        setNumberOfChickens(getNumberOfChickens() - 1);
        return true;
    }

    public Chicken findById(int chickenId){
        for (int i = 0; i < getNumberOfChickens(); i++) {
            if (getChickens()[i].getId() == chickenId) {
                return getChickens()[i];
            }
        }
        return null;
    }

    public void resetIteration(){
        setCurrentIndex(0);
    }

    public boolean hasNext(){
        return getCurrentIndex() < getNumberOfChickens();
    }

    public Chicken next(){
        if (hasNext()) {
            Chicken chicken = getChickens()[getCurrentIndex()];
            setCurrentIndex(getCurrentIndex() + 1);
            return chicken;
        }
        return null;
    }

    public ChickenCoop(int chickenCoopId) {
        this.chickenCoopId = chickenCoopId;
        this.chickens = new Chicken[10];
        this.numberOfChickens = 0;
        this.currentIndex = 0;
    }

    public ChickenCoop(int chickenCoopId, Chicken[] chickens) {
        this.chickenCoopId = chickenCoopId;
        this.chickens = chickens;

        //we count how many chickens already come in the array
        this.numberOfChickens = 0;
        for (int i = 0; i < chickens.length; i++) {
            if (chickens[i] != null) {
                this.numberOfChickens++;
            }
        }
    }

    @Override
    public String toString() {
        return "ChickenCoop{" + "chickenCoopId=" + getChickenCoopId() + ", numberOfChickens=" + getNumberOfChickens() + ", chickens=" + Arrays.toString(getChickens()) + '}';
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
     * @return the currentIndex
     */
    public int getCurrentIndex() {
        return currentIndex;
    }

    /**
     * @param currentIndex the currentIndex to set
     */
    public void setCurrentIndex(int currentIndex) {
        this.currentIndex = currentIndex;
    }

    /**
     * @return the numberOfChickens
     */
    public int getNumberOfChickens() {
        return numberOfChickens;
    }

    /**
     * @param numberOfChickens the numberOfChickens to set
     */
    public void setNumberOfChickens(int numberOfChickens) {
        this.numberOfChickens = numberOfChickens;
    }
}