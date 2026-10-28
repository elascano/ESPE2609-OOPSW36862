/*
 * Click nbfs://nbhost/SystemFileSystem/Templates/Licenses/license-default.txt to change this license
 * Click nbfs://nbhost/SystemFileSystem/Templates/Classes/Class.java to edit this template
 */
package ec.edu.espe.legalsync.model;

import ec.edu.espe.legalsync.utils.FileManager;
import java.time.LocalDate;
import java.time.format.DateTimeParseException;
import java.util.ArrayList;
import java.util.List;

/**
 *
 * @author CodeLex, @ESPE
 */
public class CsvImporter {

    public static final String DEFAULT_CSV_FILE = "data/cases.csv";

    private String sourceFile;
    private int recordsImported;
    private List<Lawyer> lawyers;

    public CsvImporter(String sourceFile, List<Lawyer> lawyers) {
        this.sourceFile = sourceFile;
        this.lawyers = lawyers;
        this.recordsImported = 0;
    }

    public List<CaseFile> importCases(String filePath) {
        sourceFile = filePath;
        recordsImported = 0;
        List<CaseFile> caseFiles = new ArrayList<>();
        List<String> lines = FileManager.readLines(filePath);

        for (int i = 1; i < lines.size(); i++) {
            CaseFile caseFile = parseLine(lines.get(i));
            if (caseFile != null && caseFile.register()) {
                caseFiles.add(caseFile);
                recordsImported++;
            } else {
                System.out.println("Line " + (i + 1) + " of the CSV was rejected (invalid data)");
            }
        }
        return caseFiles;
    }

    public CaseFile parseLine(String line) {
        String[] data = line.split(",", -1);
        if (data.length < 15) {
            return null;
        }
        try {
            int caseId = Integer.parseInt(data[0]);
            Client client = new Client(caseId, data[5], data[6], data[7], data[8], "");
            Defendant defendant = new Defendant(caseId, data[9], data[10], "", "");
            Lawyer lawyer = findLawyer(data[11]);

            CaseFile caseFile = new CaseFile(caseId, data[1], data[2], client, defendant, lawyer);
            caseFile.setStatus(CaseStatus.valueOf(data[3]));
            caseFile.setOpeningDate(LocalDate.parse(data[4]));

            if (!data[14].isEmpty()) {
                Deadline deadline = new Deadline(caseId, caseId, data[12], data[13], LocalDate.parse(data[14]), 7);
                caseFile.addDeadline(deadline);
            }
            return caseFile;
        } catch (NumberFormatException e) {
            System.out.println("Invalid number in the line: " + e.getMessage());
            return null;
        } catch (DateTimeParseException e) {
            System.out.println("Invalid date in the line: " + e.getParsedString());
            return null;
        } catch (IllegalArgumentException e) {
            System.out.println("Invalid case status in the line");
            return null;
        }
    }

    private Lawyer findLawyer(String username) {
        for (Lawyer lawyer : lawyers) {
            if (lawyer.getUsername().equals(username)) {
                return lawyer;
            }
        }
        return null;
    }

    /**
     * @return the sourceFile
     */
    public String getSourceFile() {
        return sourceFile;
    }

    /**
     * @return the recordsImported
     */
    public int getRecordsImported() {
        return recordsImported;
    }
}
