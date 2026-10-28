/*
 * Click nbfs://nbhost/SystemFileSystem/Templates/Licenses/license-default.txt to change this license
 * Click nbfs://nbhost/SystemFileSystem/Templates/Classes/Class.java to edit this template
 */
package ec.edu.espe.legalsync.model;

import ec.edu.espe.legalsync.utils.FileManager;
import java.time.LocalDateTime;
import java.time.format.DateTimeFormatter;
import java.util.ArrayList;
import java.util.List;

/**
 *
 * @author CodeLex, @ESPE
 */
public class AuditLog {

    public static final String DEFAULT_LOG_FILE = "data/log.txt";

    private String logFile;
    private List<String> entries;

    public AuditLog(String logFile) {
        this.logFile = logFile;
        this.entries = new ArrayList<>();
    }


    public void registerEntry(User user, String action, CaseFile caseFile) {
        String docketNumber = "-";
        if (caseFile != null) {
            docketNumber = caseFile.getDocketNumber();
        }
        DateTimeFormatter format = DateTimeFormatter.ofPattern("yyyy-MM-dd HH:mm:ss");
        String dateTime = LocalDateTime.now().format(format);
        //example: 2026-10-25 21:54:03 | atorres | UPDATE_CASE | 17230-2026-00101
        String entry = dateTime + " | " + user.getUsername() + " | " + action + " | " + docketNumber;
        entries.add(entry);
        writeToFile();
    }

   
    public boolean writeToFile() {
        if (entries.isEmpty()) {
            return false;
        }
        String lastEntry = entries.get(entries.size() - 1);
        return FileManager.appendLine(logFile, lastEntry);
    }

    public List<String> getHistory() {
        return FileManager.readLines(logFile);
    }

    
    public String getLogFile() {
        return logFile;
    }

    
    public List<String> getEntries() {
        return entries;
    }
}
