/*
 * Click nbfs://nbhost/SystemFileSystem/Templates/Licenses/license-default.txt to change this license
 * Click nbfs://nbhost/SystemFileSystem/Templates/Classes/Class.java to edit this template
 */
package ec.edu.espe.legalsync.model;

import ec.edu.espe.legalsync.utils.FileManager;
import java.util.ArrayList;
import java.util.List;

/**
 *
 * @author CodeLex, @ESPE
 */
public class AuthenticationService {

    public static final String DEFAULT_CREDENTIALS_FILE = "data/credentials.txt";

    private String credentialsFile;
    private int maxAttempts;
    private List<Lawyer> lawyers;
    private List<LegalAssistant> assistants;

    public AuthenticationService(String credentialsFile, int maxAttempts) {
        this.credentialsFile = credentialsFile;
        this.maxAttempts = maxAttempts;
        this.lawyers = new ArrayList<>();
        this.assistants = new ArrayList<>();
    }

    
    public User authenticate(String username, String password) {
        for (Lawyer lawyer : lawyers) {
            if (lawyer.login(username, password)) {
                return lawyer;
            }
        }
        for (LegalAssistant assistant : assistants) {
            if (assistant.login(username, password)) {
                return assistant;
            }
        }
        return null;
    }

    
    public String maskPassword(String input) {
        String masked = "";
        for (int i = 0; i < input.length(); i++) {
            masked = masked + "*";
        }
        return masked;
    }

    
    public boolean loadCredentials() {
        if (!FileManager.exists(credentialsFile)) {
            createDefaultCredentials();
        }
        lawyers.clear();
        assistants.clear();
        List<String> lines = FileManager.readLines(credentialsFile);
        List<String> supervisors = new ArrayList<>();

        for (String line : lines) {
            if (line.trim().isEmpty() || line.startsWith("#")) {
                continue;
            }
            String[] data = line.split(";");
            int userId = Integer.parseInt(data[0]);
            Role role = Role.valueOf(data[4]);
            boolean active = Boolean.parseBoolean(data[5]);

            if (role == Role.LEGAL_ASSISTANT) {
                LegalAssistant assistant = new LegalAssistant(userId, data[1], data[2], data[3], data[6]);
                assistant.setActive(active);
                assistants.add(assistant);
                supervisors.add(data[7]);
            } else {
                Lawyer lawyer = new Lawyer(userId, data[1], data[2], data[3], role, data[6], data[7]);
                lawyer.setActive(active);
                lawyers.add(lawyer);
            }
        }

        
        for (int i = 0; i < assistants.size(); i++) {
            assistants.get(i).setSupervisor(findLawyer(supervisors.get(i)));
        }
        return lawyers.size() + assistants.size() > 0;
    }

    public boolean saveCredentials() {
        String text = "# userId;name;username;password;role;active;position/area;license/supervisor\n";
        for (Lawyer lawyer : lawyers) {
            text = text + lawyer.getUserId() + ";" + lawyer.getName() + ";" + lawyer.getUsername() + ";"
                    + lawyer.getPassword() + ";" + lawyer.getRole() + ";" + lawyer.isActive() + ";"
                    + lawyer.getPosition() + ";" + lawyer.getLicenseNumber() + "\n";
        }
        for (LegalAssistant assistant : assistants) {
            String supervisor = "-";
            if (assistant.getSupervisor() != null) {
                supervisor = assistant.getSupervisor().getUsername();
            }
            text = text + assistant.getUserId() + ";" + assistant.getName() + ";" + assistant.getUsername() + ";"
                    + assistant.getPassword() + ";" + assistant.getRole() + ";" + assistant.isActive() + ";"
                    + assistant.getArea() + ";" + supervisor + "\n";
        }
        return FileManager.writeText(credentialsFile, text);
    }

    public Lawyer findLawyer(String username) {
        for (Lawyer lawyer : lawyers) {
            if (lawyer.getUsername().equals(username)) {
                return lawyer;
            }
        }
        return null;
    }

    public LegalAssistant findAssistant(String username) {
        for (LegalAssistant assistant : assistants) {
            if (assistant.getUsername().equals(username)) {
                return assistant;
            }
        }
        return null;
    }

    
    public User findUser(String username) {
        User user = findLawyer(username);
        if (user == null) {
            user = findAssistant(username);
        }
        return user;
    }

    public int nextUserId() {
        return lawyers.size() + assistants.size() + 1;
    }

    private void createDefaultCredentials() {
        String text = "# userId;name;username;password;role;active;position/area;license/supervisor\n"
                + "1;Ana Torres;atorres;partner123;PARTNER;true;Senior Partner;LIC-0001\n"
                + "2;Carlos Mena;cmena;lawyer123;LAWYER;true;Litigation Lawyer;LIC-0102\n"
                + "3;Daniela Ruiz;druiz;junior123;JUNIOR_LAWYER;true;Junior Lawyer;LIC-0230\n"
                + "4;Luis Paredes;lparedes;assist123;LEGAL_ASSISTANT;true;Civil;cmena\n";
        FileManager.writeText(credentialsFile, text);
    }

    /**
     * @return the credentialsFile
     */
    public String getCredentialsFile() {
        return credentialsFile;
    }

    /**
     * @return the maxAttempts
     */
    public int getMaxAttempts() {
        return maxAttempts;
    }

    /**
     * @return the lawyers
     */
    public List<Lawyer> getLawyers() {
        return lawyers;
    }

    /**
     * @return the assistants
     */
    public List<LegalAssistant> getAssistants() {
        return assistants;
    }
}
