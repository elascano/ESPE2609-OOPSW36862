/*
 * Click nbfs://nbhost/SystemFileSystem/Templates/Licenses/license-default.txt to change this license
 * Click nbfs://nbhost/SystemFileSystem/Templates/Classes/Class.java to edit this template
 */
package ec.edu.espe.legalsync.model;

import ec.edu.espe.legalsync.utils.FileManager;
import ec.edu.espe.legalsync.utils.Validator;
import java.time.LocalDate;

/**
 *
 * @author CodeLex, @ESPE
 */
public class Document {

    public static final String REGISTRY_FILE = "data/documents.txt";

    private int documentId;
    private String name;
    private String type;
    private String filePath;
    private LocalDate uploadDate;

    public Document(int documentId, String name, String type, String filePath) {
        this.documentId = documentId;
        this.name = name;
        this.type = type;
        this.filePath = filePath;
        this.uploadDate = LocalDate.now();
    }

    public boolean register() {
        if (Validator.isEmpty(name) || !Validator.isValidDocumentPath(filePath)) {
            return false;
        }
        if (Validator.isEmpty(type)) {
            
            int dotPosition = filePath.lastIndexOf('.');
            type = filePath.substring(dotPosition + 1).toUpperCase();
        }
        uploadDate = LocalDate.now();
        return true;
    }

    public String getFilePath() {
        return filePath;
    }

    public boolean saveToFile() {
        String line = documentId + ";" + name + ";" + type + ";" + filePath + ";" + uploadDate;
        return FileManager.appendLine(REGISTRY_FILE, line);
    }

    @Override
    public String toString() {
        return "Document{" + "documentId=" + documentId + ", name=" + name + ", type=" + type
                + ", filePath=" + filePath + ", uploadDate=" + uploadDate + '}';
    }

    /**
     * @return the documentId
     */
    public int getDocumentId() {
        return documentId;
    }

    /**
     * @param documentId the documentId to set
     */
    public void setDocumentId(int documentId) {
        this.documentId = documentId;
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
     * @return the uploadDate
     */
    public LocalDate getUploadDate() {
        return uploadDate;
    }

    /**
     * @param uploadDate the uploadDate to set
     */
    public void setUploadDate(LocalDate uploadDate) {
        this.uploadDate = uploadDate;
    }
}
