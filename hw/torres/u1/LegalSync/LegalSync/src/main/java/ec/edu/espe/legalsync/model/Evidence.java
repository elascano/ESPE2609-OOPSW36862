/*
 * Click nbfs://nbhost/SystemFileSystem/Templates/Licenses/license-default.txt to change this license
 * Click nbfs://nbhost/SystemFileSystem/Templates/Classes/Class.java to edit this template
 */
package ec.edu.espe.legalsync.model;

import ec.edu.espe.legalsync.utils.Validator;
import java.time.LocalDate;

/**
 *
 * @author CodeLex, @ESPE
 */
public class Evidence {

    private int evidenceId;
    private String description;
    private String type;
    private String filePath;
    private LocalDate collectionDate;

    public Evidence(int evidenceId, String description, String type, String filePath, LocalDate collectionDate) {
        this.evidenceId = evidenceId;
        this.description = description;
        this.type = type;
        this.filePath = filePath;
        this.collectionDate = collectionDate;
    }

    public boolean register() {
        if (Validator.isEmpty(description)) {
            return false;
        }
        if (collectionDate == null || collectionDate.isAfter(LocalDate.now())) {
            return false;
        }
        return true;
    }

    public String getFilePath() {
        return filePath;
    }

    public String getDetails() {
        return "Evidence #" + evidenceId + ": " + description + " | Type: " + type
                + " | File: " + filePath + " | Collected: " + collectionDate;
    }

    @Override
    public String toString() {
        return "Evidence{" + "evidenceId=" + evidenceId + ", description=" + description + ", type=" + type + '}';
    }

    /**
     * @return the evidenceId
     */
    public int getEvidenceId() {
        return evidenceId;
    }

    /**
     * @param evidenceId the evidenceId to set
     */
    public void setEvidenceId(int evidenceId) {
        this.evidenceId = evidenceId;
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
     * @param filePath the filePath to set
     */
    public void setFilePath(String filePath) {
        this.filePath = filePath;
    }

    /**
     * @return the collectionDate
     */
    public LocalDate getCollectionDate() {
        return collectionDate;
    }

    /**
     * @param collectionDate the collectionDate to set
     */
    public void setCollectionDate(LocalDate collectionDate) {
        this.collectionDate = collectionDate;
    }
}
