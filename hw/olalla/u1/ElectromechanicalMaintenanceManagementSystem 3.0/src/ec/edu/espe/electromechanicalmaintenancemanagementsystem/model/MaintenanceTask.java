package ec.edu.espe.electromechanicalmaintenancemanagementsystem.model;

import java.util.ArrayList;
import java.util.List;

public class MaintenanceTask implements java.io.Serializable {
    private int taskId;
    private String taskDescription;
    private String taskType;
    private float estimatedDuration;
    private boolean isCompleted;

    private List<Part> parts = new ArrayList<>();
    private List<SafetyRegulation> regulations = new ArrayList<>();

    public MaintenanceTask(int taskId, String taskDescription, String taskType, float estimatedDuration, boolean isCompleted) {
        this.taskId = taskId;
        this.taskDescription = taskDescription;
        this.taskType = taskType;
        this.estimatedDuration = estimatedDuration;
        this.isCompleted = isCompleted;
    }

    public int getTaskId() {
        return taskId;
    }
    public void setTaskId(int taskId) {
        this.taskId = taskId;
    }

    public String getTaskDescription() {
        return taskDescription;
    }
    public void setTaskDescription(String taskDescription) {
        this.taskDescription = taskDescription;
    }

    public String getTaskType() {
        return taskType;
    }
    public void setTaskType(String taskType) {
        this.taskType = taskType;
    }

    public float getEstimatedDuration() {
        return estimatedDuration;
    }
    public void setEstimatedDuration(float estimatedDuration) {
        this.estimatedDuration = estimatedDuration;
    }

    public boolean getIsCompleted() {
        return isCompleted;
    }
    public void setIsCompleted(boolean isCompleted) {
        this.isCompleted = isCompleted;
    }

    public List<Part> getParts() {
        return new ArrayList<>(parts);
    }

    public List<SafetyRegulation> getRegulations() {
        return new ArrayList<>(regulations);
    }

    public void executeTask() {
        for (SafetyRegulation regulation : regulations) {
            if (regulation == null) return;
        }
        isCompleted = true;
    }

    public void recordPart(Part part) {
        if (part != null && !parts.contains(part)) parts.add(part);
    }

    public void verifySafety(SafetyRegulation regulation) {
        if (regulation != null && !regulations.contains(regulation)) regulations.add(regulation);
    }

    @Override
    public String toString() {
        return "MaintenanceTask{" + "taskId=" + taskId + ", " + "taskDescription=" + taskDescription + ", " + "taskType=" + taskType + ", " + "estimatedDuration=" + estimatedDuration + ", " + "isCompleted=" + isCompleted + "}";
    }
}
