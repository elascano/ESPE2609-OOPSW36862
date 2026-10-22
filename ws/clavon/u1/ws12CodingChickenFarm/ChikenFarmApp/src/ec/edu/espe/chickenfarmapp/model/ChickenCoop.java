package ec.edu.espe.chickenfarmapp.model;

public class ChickenCoop {

    private int chickenCoopId;
    private Chicken chickens[] = new Chicken[10];
    private int count = 0;
    private int currentIndex = 0;

    public ChickenCoop(int chickenCoopId) {
        this.chickenCoopId = chickenCoopId;
    }

    public ChickenCoop(int chickenCoopId, Chicken[] chickens) {
        this.chickenCoopId = chickenCoopId;
        if (chickens != null) {
            for (Chicken chicken : chickens) {
                if (chicken != null) {
                    this.add(chicken);
                }
            }
        }
    }

    public boolean add(Chicken chicken) {
        if (chicken == null || count >= chickens.length) {
            return false;
        }
        chickens[count] = chicken;
        count++;
        return true;
    }

    public boolean remove(int chickenId) {
        int indexFound = -1;

        for (int i = 0; i < count; i++) {
            if (chickens[i] != null && chickens[i].getId() == chickenId) {
                indexFound = i;
                break;
            }
        }

        if (indexFound != -1) {
            for (int i = indexFound; i < count - 1; i++) {
                chickens[i] = chickens[i + 1];
            }
            chickens[count - 1] = null;
            count--;
            return true;
        }
        return false;
    }

    public void resetIteration() {
        this.currentIndex = 0;
    }

    public Chicken next() {
        if (currentIndex < count) {
            Chicken currentChicken = chickens[currentIndex];
            currentIndex++;
            return currentChicken;
        }
        return null;
    }

    public int getCurrentPosition() {
        return currentIndex;
    }

    public Chicken getCurrentChicken() {
        if (currentIndex > 0 && currentIndex <= count) {
            return chickens[currentIndex - 1];
        }
        return null;
    }

    @Override
    public String toString() {
        StringBuilder sb = new StringBuilder();
        sb.append("ChickenCoop{chickenCoopId=").append(chickenCoopId);
        sb.append(", totalChickens=").append(count);
        sb.append(", chickens=[");
        for (int i = 0; i < count; i++) {
            sb.append(chickens[i]);
            if (i < count - 1) sb.append(", ");
        }
        sb.append("]}");
        return sb.toString();
    }

    public int getChickenCoopId() {
        return chickenCoopId;
    }

    public void setChickenCoopId(int chickenCoopId) {
        this.chickenCoopId = chickenCoopId;
    }

    public Chicken[] getChickens() {
        return chickens;
    }

    public void setChickens(Chicken[] chickens) {
        this.chickens = chickens;
    }

    public int getCount() {
        return count;
    }
}