/*
 * Click nbfs://nbhost/SystemFileSystem/Templates/Licenses/license-default.txt to change this license
 * Click nbfs://nbhost/SystemFileSystem/Templates/Classes/Class.java to edit this template
 */
package ec.edu.espe.legalsync.model;

import ec.edu.espe.legalsync.utils.FileManager;
import java.time.LocalDate;
import java.util.List;

/**
 *
 * @author CodeLex, @ESPE
 */
public class DocumentGenerator {

    public static final String DEFAULT_TEMPLATE_PATH = "data/templates";
    public static final String DEFAULT_OUTPUT_FOLDER = "data/output";

    private String templatePath;
    private String outputFolder;
    private String outputFile;

    public DocumentGenerator(String templatePath, String outputFolder) {
        this.templatePath = templatePath;
        this.outputFolder = outputFolder;
        this.outputFile = "";
    }

    public Document generate(CaseFile caseFile, String template) {
        template = template.toUpperCase();
        String content = loadTemplate(template);

        content = content.replace("{DATE}", LocalDate.now().toString());
        content = content.replace("{DOCKET}", caseFile.getDocketNumber());
        content = content.replace("{TITLE}", caseFile.getTitle());
        content = content.replace("{STATUS}", caseFile.getStatus().toString());
        content = content.replace("{OPENING_DATE}", caseFile.getOpeningDate().toString());
        content = content.replace("{CLIENT}", caseFile.getClient().getName());
        content = content.replace("{CLIENT_ID}", caseFile.getClient().getIdNumber());
        content = content.replace("{DEFENDANT}", caseFile.getDefendant().getName());
        content = content.replace("{DEFENDANT_ID}", caseFile.getDefendant().getIdNumber());
        content = content.replace("{LAWYER}", caseFile.getAssignedLawyer().getName());
        content = content.replace("{LICENSE}", caseFile.getAssignedLawyer().getLicenseNumber());

        outputFile = outputFolder + "/" + caseFile.getDocketNumber() + "_" + template + ".txt";
        if (!saveAsTxt(content)) {
            return null;
        }
        String name = template + " - " + caseFile.getDocketNumber();
        return new Document(caseFile.getDocuments().size() + 1, name, "TXT", outputFile);
    }

    public boolean saveAsTxt(String content) {
        return FileManager.writeText(outputFile, content);
    }

    private String loadTemplate(String template) {
        String fileName = templatePath + "/" + template + ".txt";
        if (FileManager.exists(fileName)) {
            List<String> lines = FileManager.readLines(fileName);
            String text = "";
            for (String line : lines) {
                text = text + line + "\n";
            }
            return text;
        }
        return "LEGALSYNC - " + template + "\n"
                + "Quito, {DATE}\n\n"
                + "Docket number: {DOCKET}\n"
                + "Case: {TITLE}\n"
                + "Status: {STATUS} (opened on {OPENING_DATE})\n\n"
                + "Client: {CLIENT}, ID {CLIENT_ID}\n"
                + "Defendant: {DEFENDANT}, ID {DEFENDANT_ID}\n\n"
                + "____________________________\n"
                + "{LAWYER}\n"
                + "License {LICENSE}\n";
    }

    /**
     * @return the templatePath
     */
    public String getTemplatePath() {
        return templatePath;
    }

    /**
     * @return the outputFolder
     */
    public String getOutputFolder() {
        return outputFolder;
    }
}
