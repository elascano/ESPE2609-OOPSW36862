/*
 * Click nbfs://nbhost/SystemFileSystem/Templates/Licenses/license-default.txt to change this license
 * Click nbfs://nbhost/SystemFileSystem/Templates/Classes/Class.java to edit this template
 */
package ec.edu.espe.legalsync.model;

import java.util.ArrayList;
import java.util.List;

/**
 *
 * @author CodeLex, @ESPE
 */
public class SearchEngine {

    private List<CaseFile> caseFiles;
    private String lastQuery;

    public SearchEngine() {
        this.caseFiles = new ArrayList<>();
        this.lastQuery = "";
    }

    public void loadData(List<CaseFile> caseFiles) {
        this.caseFiles = caseFiles;
    }

    public List<CaseFile> searchByClientName(String clientName) {
        List<CaseFile> results = new ArrayList<>();
        lastQuery = clientName.trim();
        String query = lastQuery.toLowerCase();

        for (CaseFile caseFile : caseFiles) {
            String name = caseFile.getClient().getName().toLowerCase();
            if (name.contains(query)) {
                results.add(caseFile);
            }
        }
        return results;
    }

    public List<CaseFile> getResults() {
        return searchByClientName(lastQuery);
    }

    public CaseFile findByDocketNumber(String docketNumber) {
        for (CaseFile caseFile : caseFiles) {
            if (caseFile.getDocketNumber().equals(docketNumber)) {
                return caseFile;
            }
        }
        return null;
    }

    /**
     * @return the caseFiles
     */
    public List<CaseFile> getCaseFiles() {
        return caseFiles;
    }

    /**
     * @return the lastQuery
     */
    public String getLastQuery() {
        return lastQuery;
    }
}
