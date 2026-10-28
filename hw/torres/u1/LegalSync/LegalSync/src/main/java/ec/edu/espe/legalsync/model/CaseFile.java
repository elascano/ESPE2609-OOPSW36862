/*
 * Click nbfs://nbhost/SystemFileSystem/Templates/Licenses/license-default.txt to change this license
 * Click nbfs://nbhost/SystemFileSystem/Templates/Classes/Class.java to edit this template
 */
package ec.edu.espe.legalsync.model;

import ec.edu.espe.legalsync.utils.Validator;
import java.time.LocalDate;
import java.util.ArrayList;
import java.util.List;

/**
 *
 * @author CodeLex, @ESPE
 */
public class CaseFile {

    private int caseId;
    private String docketNumber;
    private String title;
    private CaseStatus status;
    private LocalDate openingDate;
    private Client client;
    private Defendant defendant;
    private Lawyer assignedLawyer;
    private List<Document> documents;
    private List<Evidence> evidences;
    private List<Deadline> deadlines;

    public CaseFile(int caseId, String docketNumber, String title, Client client,
            Defendant defendant, Lawyer assignedLawyer) {
        this.caseId = caseId;
        this.docketNumber = docketNumber;
        this.title = title;
        this.client = client;
        this.defendant = defendant;
        this.assignedLawyer = assignedLawyer;
        this.status = CaseStatus.OPEN;
        this.openingDate = LocalDate.now();
        this.documents = new ArrayList<>();
        this.evidences = new ArrayList<>();
        this.deadlines = new ArrayList<>();
    }


    public boolean register() {
        if (!validateMandatoryFields()) {
            return false;
        }
        if (status == null) {
            status = CaseStatus.OPEN;
        }
        if (openingDate == null) {
            openingDate = LocalDate.now();
        }
        return true;
    }

    public boolean update() {
        if (status == CaseStatus.CLOSED) {
            System.out.println("The case " + docketNumber + " is closed and can not be modified");
            return false;
        }
        return validateMandatoryFields();
    }

    public boolean validateMandatoryFields() {
        if (Validator.isEmpty(docketNumber)) {
            return false;
        }
        if (Validator.isEmpty(title)) {
            return false;
        }
        if (client == null || !client.register()) {
            return false;
        }
        if (defendant == null || !defendant.register()) {
            return false;
        }
        if (assignedLawyer == null) {
            return false;
        }
        if (openingDate != null && openingDate.isAfter(LocalDate.now())) {
            return false;
        }
        return true;
    }

    public void addDocument(Document document) {
        if (document != null && document.register()) {
            documents.add(document);
        } else {
            System.out.println("The document is not valid (only .pdf, .doc, .docx or .txt)");
        }
    }

    public void addEvidence(Evidence evidence) {
        if (evidence != null && evidence.register()) {
            evidences.add(evidence);
        } else {
            System.out.println("The evidence is not valid");
        }
    }

    public void addDeadline(Deadline deadline) {
        if (deadline != null && deadline.getTargetDate() != null) {
            deadline.setCaseId(caseId);
            deadlines.add(deadline);
        } else {
            System.out.println("The deadline is not valid");
        }
    }

    public Document generateDocument(String template) {
        DocumentGenerator generator = new DocumentGenerator(DocumentGenerator.DEFAULT_TEMPLATE_PATH,
                DocumentGenerator.DEFAULT_OUTPUT_FOLDER);
        Document document = generator.generate(this, template);
        if (document != null) {
            documents.add(document);
        }
        return document;
    }

    public static List<CaseFile> importFromCsv(String filePath, List<Lawyer> lawyers) {
        CsvImporter importer = new CsvImporter(filePath, lawyers);
        return importer.importCases(filePath);
    }

    public String getDetails() {
        String details = "Case #" + caseId + " - Docket: " + docketNumber + "\n";
        details = details + "  Title: " + title + "\n";
        details = details + "  Status: " + status + " | Opened: " + openingDate + "\n";
        details = details + "  " + client.getDetails() + "\n";
        details = details + "  " + defendant.getDetails() + "\n";
        details = details + "  Lawyer: " + assignedLawyer.getName() + "\n";

        details = details + "  Documents: " + documents.size();
        for (Document document : documents) {
            details = details + "\n    - " + document.getName() + " -> " + document.getFilePath();
        }
        details = details + "\n  Evidences: " + evidences.size();
        for (Evidence evidence : evidences) {
            details = details + "\n    - " + evidence.getDetails();
        }
        details = details + "\n  Deadlines: " + deadlines.size();
        for (Deadline deadline : deadlines) {
            details = details + "\n    - " + deadline.getDescription() + " on " + deadline.getTargetDate();
            if (deadline.isCompleted()) {
                details = details + " (completed)";
            } else {
                details = details + " (" + deadline.calculateRemainingDays() + " days)";
            }
        }
        return details;
    }

    @Override
    public String toString() {
        return "CaseFile{" + "caseId=" + caseId + ", docketNumber=" + docketNumber + ", title=" + title
                + ", status=" + status + ", client=" + client.getName() + '}';
    }

    
    public int getCaseId() {
        return caseId;
    }

    
    public void setCaseId(int caseId) {
        this.caseId = caseId;
    }

    
    public String getDocketNumber() {
        return docketNumber;
    }

    public void setDocketNumber(String docketNumber) {
        this.docketNumber = docketNumber;
    }

    
    public String getTitle() {
        return title;
    }

    
    public void setTitle(String title) {
        this.title = title;
    }

    
    public CaseStatus getStatus() {
        return status;
    }

    
    public void setStatus(CaseStatus status) {
        this.status = status;
    }

    
    public LocalDate getOpeningDate() {
        return openingDate;
    }

    
    public void setOpeningDate(LocalDate openingDate) {
        this.openingDate = openingDate;
    }

    
    public Client getClient() {
        return client;
    }

    
    public void setClient(Client client) {
        this.client = client;
    }

    
    public Defendant getDefendant() {
        return defendant;
    }

    
    public void setDefendant(Defendant defendant) {
        this.defendant = defendant;
    }

    
    public Lawyer getAssignedLawyer() {
        return assignedLawyer;
    }

    
    public void setAssignedLawyer(Lawyer assignedLawyer) {
        this.assignedLawyer = assignedLawyer;
    }

    /**
     * @return the documents
     */
    public List<Document> getDocuments() {
        return documents;
    }

    /**
     * @return the evidences
     */
    public List<Evidence> getEvidences() {
        return evidences;
    }

    /**
     * @return the deadlines
     */
    public List<Deadline> getDeadlines() {
        return deadlines;
    }
}
