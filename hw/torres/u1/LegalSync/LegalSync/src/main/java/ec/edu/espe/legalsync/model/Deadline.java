/*
 * Click nbfs://nbhost/SystemFileSystem/Templates/Licenses/license-default.txt to change this license
 * Click nbfs://nbhost/SystemFileSystem/Templates/Classes/Class.java to edit this template
 */
package ec.edu.espe.legalsync.model;

import java.time.LocalDate;
import java.time.temporal.ChronoUnit;

/**
 *
 * @author CodeLex, @ESPE
 */
public class Deadline {

    private int deadlineId;
    private int caseId;
    private String description;
    private String type;
    private LocalDate targetDate;
    private int alertDaysBefore;
    private boolean completed;

    public Deadline(int deadlineId, int caseId, String description, String type,
            LocalDate targetDate, int alertDaysBefore) {
        this.deadlineId = deadlineId;
        this.caseId = caseId;
        this.description = description;
        this.type = type;
        this.targetDate = targetDate;
        this.alertDaysBefore = alertDaysBefore;
        this.completed = false;
    }
    
    public int calculateRemainingDays() {
        return (int) ChronoUnit.DAYS.between(LocalDate.now(), targetDate);
    }

    public boolean isApproaching() {
        if (completed) {
            return false;
        }
        int remainingDays = calculateRemainingDays();
        return remainingDays >= 0 && remainingDays <= alertDaysBefore;
    }

    public boolean needsAlert() {
        if (completed) {
            return false;
        }
        return calculateRemainingDays() <= alertDaysBefore;
    }

    public String generateAlertMessage() {
        int remainingDays = calculateRemainingDays();
        String message;
        if (remainingDays < 0) {
            message = "OVERDUE: " + description + " (" + type + ") was due on " + targetDate
                    + ", " + (-remainingDays) + " day(s) ago";
        } else if (remainingDays == 0) {
            message = "TODAY: " + description + " (" + type + ") is due today " + targetDate;
        } else {
            message = "UPCOMING: " + description + " (" + type + ") is due on " + targetDate
                    + ", " + remainingDays + " day(s) remaining";
        }
        return message;
    }

    public void markCompleted() {
        completed = true;
    }

    @Override
    public String toString() {
        return "Deadline{" + "deadlineId=" + deadlineId + ", description=" + description + ", type=" + type
                + ", targetDate=" + targetDate + ", completed=" + completed + '}';
    }

    /**
     * @return the deadlineId
     */
    public int getDeadlineId() {
        return deadlineId;
    }

    /**
     * @param deadlineId the deadlineId to set
     */
    public void setDeadlineId(int deadlineId) {
        this.deadlineId = deadlineId;
    }

    /**
     * @return the caseId
     */
    public int getCaseId() {
        return caseId;
    }

    /**
     * @param caseId the caseId to set
     */
    public void setCaseId(int caseId) {
        this.caseId = caseId;
    }

    /**
     * @return the description
     */
    public String getDescription() {
        return description;
    }

    /**
     * @param description the description to set
     */
    public void setDescription(String description) {
        this.description = description;
    }

    /**
     * @return the type
     */
    public String getType() {
        return type;
    }

    /**
     * @param type the type to set
     */
    public void setType(String type) {
        this.type = type;
    }

    /**
     * @return the targetDate
     */
    public LocalDate getTargetDate() {
        return targetDate;
    }

    /**
     * @param targetDate the targetDate to set
     */
    public void setTargetDate(LocalDate targetDate) {
        this.targetDate = targetDate;
    }

    /**
     * @return the alertDaysBefore
     */
    public int getAlertDaysBefore() {
        return alertDaysBefore;
    }

    /**
     * @param alertDaysBefore the alertDaysBefore to set
     */
    public void setAlertDaysBefore(int alertDaysBefore) {
        this.alertDaysBefore = alertDaysBefore;
    }

    /**
     * @return the completed
     */
    public boolean isCompleted() {
        return completed;
    }
}
