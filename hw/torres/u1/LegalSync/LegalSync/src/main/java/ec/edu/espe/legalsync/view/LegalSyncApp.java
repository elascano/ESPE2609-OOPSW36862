/*
 * Click nbfs://nbhost/SystemFileSystem/Templates/Licenses/license-default.txt to change this license
 * Click nbfs://nbhost/SystemFileSystem/Templates/Classes/Main.java to edit this template
 */
package ec.edu.espe.legalsync.view;

import ec.edu.espe.legalsync.model.AuditLog;
import ec.edu.espe.legalsync.model.AuthenticationService;
import ec.edu.espe.legalsync.model.CaseFile;
import ec.edu.espe.legalsync.model.CaseStatus;
import ec.edu.espe.legalsync.model.Client;
import ec.edu.espe.legalsync.model.CsvImporter;
import ec.edu.espe.legalsync.model.Deadline;
import ec.edu.espe.legalsync.model.Defendant;
import ec.edu.espe.legalsync.model.Document;
import ec.edu.espe.legalsync.model.DocumentGenerator;
import ec.edu.espe.legalsync.model.Evidence;
import ec.edu.espe.legalsync.model.Lawyer;
import ec.edu.espe.legalsync.model.LegalAssistant;
import ec.edu.espe.legalsync.model.Role;
import ec.edu.espe.legalsync.model.SearchEngine;
import ec.edu.espe.legalsync.model.User;
import java.time.LocalDate;
import java.time.format.DateTimeParseException;
import java.util.ArrayList;
import java.util.List;
import java.util.Scanner;

/**
 *
 * @author CodeLex, @ESPE
 */
public class LegalSyncApp {

    static Scanner scanner = new Scanner(System.in);

    static AuthenticationService authService = new AuthenticationService(AuthenticationService.DEFAULT_CREDENTIALS_FILE, 3);
    static AuditLog auditLog = new AuditLog(AuditLog.DEFAULT_LOG_FILE);
    static SearchEngine searchEngine = new SearchEngine();
    static DocumentGenerator documentGenerator = new DocumentGenerator(DocumentGenerator.DEFAULT_TEMPLATE_PATH,
            DocumentGenerator.DEFAULT_OUTPUT_FOLDER);
    static List<CaseFile> caseFiles = new ArrayList<>();
    static User currentUser;

    //counters to generate the ids automatically
    static int nextDocumentId = 1;
    static int nextEvidenceId = 1;
    static int nextDeadlineId = 100;

    public static void main(String[] args) {
        System.out.println("CodeLex - LegalSync, Legal Case Management System");
        System.out.println("=================================================");

        if (!authService.loadCredentials()) {
            System.out.println("There are no users, the system can not start");
            return;
        }

        CsvImporter importer = new CsvImporter(CsvImporter.DEFAULT_CSV_FILE, authService.getLawyers());
        caseFiles.addAll(importer.importCases(CsvImporter.DEFAULT_CSV_FILE));
        searchEngine.loadData(caseFiles);
        System.out.println(importer.getRecordsImported() + " case(s) loaded from " + CsvImporter.DEFAULT_CSV_FILE);

        String answer = "y";
        while (answer.equalsIgnoreCase("y")) {
            currentUser = login();
            if (currentUser == null) {
                System.out.println("Too many failed attempts, the system will close");
                return;
            }
            showMyAlerts();
            showMenu();
            answer = readText("Do you want to log in with another user? (y/n): ");
        }
        System.out.println("Goodbye!");
    }

    public static User login() {
        for (int attempt = 1; attempt <= authService.getMaxAttempts(); attempt++) {
            System.out.println("\n----------- LOGIN (attempt " + attempt + " of " + authService.getMaxAttempts() + ") -----------");
            String username = readText("Username: ");
            String password = readText("Password: ");
            System.out.println("Password entered: " + authService.maskPassword(password));

            User user = authService.authenticate(username, password);
            if (user != null) {
                System.out.println("Welcome " + user.getName() + " (" + user.getRole() + ")");
                auditLog.registerEntry(user, "LOGIN", null);
                return user;
            }
            System.out.println("Invalid credentials or the user is inactive");
        }
        return null;
    }

    public static void showMenu() {
        int option;
        do {
            System.out.println("\n----------- MENU (" + currentUser.getUsername() + " - " + currentUser.getRole() + ") -----------");
            System.out.println(" 1. Register case");
            System.out.println(" 2. Update case");
            System.out.println(" 3. View case file details");
            System.out.println(" 4. Search case by client name");
            System.out.println(" 5. Link document to a case");
            System.out.println(" 6. Register evidence");
            System.out.println(" 7. Manage deadlines");
            System.out.println(" 8. View deadline alerts");
            System.out.println(" 9. Generate legal document (.txt)");
            System.out.println("10. Import cases from CSV");
            System.out.println("11. View audit log");
            System.out.println("12. Manage user accounts");
            System.out.println("13. Change my password");
            System.out.println(" 0. Log out");
            option = readInt("Choose an option: ");

            switch (option) {
                case 1:
                    if (allowed("REGISTER_CASE")) {
                        registerCase();
                    }
                    break;
                case 2:
                    if (allowed("UPDATE_CASE")) {
                        updateCase();
                    }
                    break;
                case 3:
                    viewCaseDetails();
                    break;
                case 4:
                    if (allowed("SEARCH_CASE")) {
                        searchCases();
                    }
                    break;
                case 5:
                    if (allowed("UPDATE_CASE")) {
                        linkDocument();
                    }
                    break;
                case 6:
                    if (allowed("REGISTER_EVIDENCE")) {
                        registerEvidence();
                    }
                    break;
                case 7:
                    if (allowed("MANAGE_DEADLINES")) {
                        manageDeadlines();
                    }
                    break;
                case 8:
                    if (allowed("VIEW_ALERTS")) {
                        showMyAlerts();
                    }
                    break;
                case 9:
                    if (allowed("GENERATE_DOCUMENT")) {
                        generateDocument();
                    }
                    break;
                case 10:
                    if (allowed("IMPORT_CSV")) {
                        importCases();
                    }
                    break;
                case 11:
                    if (allowed("VIEW_AUDIT_LOG")) {
                        viewAuditLog();
                    }
                    break;
                case 12:
                    if (allowed("MANAGE_USERS")) {
                        manageUsers();
                    }
                    break;
                case 13:
                    changePassword();
                    break;
                case 0:
                    auditLog.registerEntry(currentUser, "LOGOUT", null);
                    currentUser.logout();
                    break;
                default:
                    System.out.println("Invalid option, try again");
            }
        } while (option != 0);
    }

    public static boolean allowed(String permission) {
        if (currentUser.hasPermission(permission)) {
            return true;
        }
        System.out.println("Access denied: your role (" + currentUser.getRole() + ") can not do this operation");
        return false;
    }

    public static void showMyAlerts() {
        boolean seeAll = currentUser.getRole() == Role.PARTNER || currentUser.getRole() == Role.LEGAL_ASSISTANT;
        int shown = 0;

        System.out.println("\n========== DEADLINE ALERTS ==========");
        for (CaseFile caseFile : caseFiles) {
            Lawyer lawyer = caseFile.getAssignedLawyer();
            boolean isMyCase = lawyer.getUsername().equals(currentUser.getUsername());
            if (seeAll || isMyCase) {
                for (Deadline deadline : caseFile.getDeadlines()) {
                    if (deadline.needsAlert()) {
                        System.out.println("[!] [" + caseFile.getDocketNumber() + "] " + deadline.generateAlertMessage()
                                + "  -> for: " + lawyer.getName());
                        shown++;
                    }
                }
            }
        }
        if (shown == 0) {
            System.out.println("There are no upcoming deadlines for you");
        }
        System.out.println("=====================================");
    }

    public static void registerCase() {
        System.out.println("\n--- Register case ---");
        int caseId = caseFiles.size() + 1;
        System.out.println("Case id: " + caseId);
        String docketNumber = readText("Docket number (e.g. 17230-2026-00123): ");
        if (searchEngine.findByDocketNumber(docketNumber) != null) {
            System.out.println("There is already a case with that docket number");
            return;
        }
        String title = readText("Title: ");

        System.out.println("-- Client --");
        String clientName = readText("Name: ");
        String clientId = readText("ID number (10 or 13 digits): ");
        String phone = readText("Phone: ");
        String email = readText("Email: ");
        String address = readText("Address: ");
        Client client = new Client(caseId, clientName, clientId, phone, email, address);

        System.out.println("-- Defendant --");
        String defendantName = readText("Name: ");
        String defendantId = readText("ID number (optional): ");
        String contact = readText("Contact info: ");
        String defendantAddress = readText("Address: ");
        Defendant defendant = new Defendant(caseId, defendantName, defendantId, contact, defendantAddress);

        Lawyer lawyer = chooseLawyer();

        CaseFile caseFile = new CaseFile(caseId, docketNumber, title, client, defendant, lawyer);
        if (caseFile.register()) {
            caseFiles.add(caseFile);
            auditLog.registerEntry(currentUser, "REGISTER_CASE", caseFile);
            System.out.println("The case " + docketNumber + " was registered");
        } else {
            System.out.println("The case was NOT registered: check the mandatory fields "
                    + "(docket, title, client name and ID, defendant name)");
        }
    }

    public static void updateCase() {
        CaseFile caseFile = chooseCase();
        if (caseFile == null) {
            return;
        }
        System.out.println("Leave empty to keep the current value");
        String oldTitle = caseFile.getTitle();

        String title = readText("Title [" + caseFile.getTitle() + "]: ");
        if (!title.isEmpty()) {
            caseFile.setTitle(title);
        }
        CaseStatus newStatus = caseFile.getStatus();
        String status = readText("Status OPEN/IN_PROGRESS/SUSPENDED/CLOSED [" + caseFile.getStatus() + "]: ");
        if (!status.isEmpty()) {
            try {
                newStatus = CaseStatus.valueOf(status.toUpperCase());
            } catch (IllegalArgumentException e) {
                System.out.println("Invalid status, it was not changed");
            }
        }

        boolean updated;
        if (currentUser.getRole() == Role.LEGAL_ASSISTANT) {
            LegalAssistant assistant = authService.findAssistant(currentUser.getUsername());
            updated = assistant.updateCaseData(caseFile);
        } else {
            updated = caseFile.update();
        }

        if (updated) {
            caseFile.setStatus(newStatus);
            auditLog.registerEntry(currentUser, "UPDATE_CASE", caseFile);
            System.out.println("The case was updated");
        } else {
            caseFile.setTitle(oldTitle);
            System.out.println("The case was NOT updated");
        }
    }

    public static void viewCaseDetails() {
        CaseFile caseFile = chooseCase();
        if (caseFile != null) {
            System.out.println(caseFile.getDetails());
        }
    }

    public static void searchCases() {
        String clientName = readText("Client name (or part of it): ");
        List<CaseFile> results = searchEngine.searchByClientName(clientName);
        System.out.println(results.size() + " case(s) found for \"" + clientName + "\"");
        for (CaseFile caseFile : results) {
            System.out.println("  " + caseFile);
        }
    }

    public static void linkDocument() {
        CaseFile caseFile = chooseCase();
        if (caseFile == null) {
            return;
        }
        String name = readText("Document name: ");
        String path = readText("File path (e.g. C:\\cases\\case1.pdf): ");
        Document document = new Document(nextDocumentId, name, "", path);
        if (document.register()) {
            caseFile.addDocument(document);
            document.saveToFile();
            nextDocumentId++;
            auditLog.registerEntry(currentUser, "LINK_DOCUMENT", caseFile);
            System.out.println("The document was linked to the case");
        } else {
            System.out.println("The document is not valid (only .pdf, .doc, .docx or .txt)");
        }
    }

    public static void registerEvidence() {
        CaseFile caseFile = chooseCase();
        if (caseFile == null) {
            return;
        }
        String description = readText("Description: ");
        String type = readText("Type (photo, video, contract...): ");
        String path = readText("File path: ");
        LocalDate date = readDate("Collection date (yyyy-MM-dd): ");
        Evidence evidence = new Evidence(nextEvidenceId, description, type, path, date);
        if (evidence.register()) {
            caseFile.addEvidence(evidence);
            nextEvidenceId++;
            auditLog.registerEntry(currentUser, "REGISTER_EVIDENCE", caseFile);
            System.out.println("The evidence was registered");
        } else {
            System.out.println("The evidence is not valid (description empty or date in the future)");
        }
    }

    public static void manageDeadlines() {
        CaseFile caseFile = chooseCase();
        if (caseFile == null) {
            return;
        }
        System.out.println("1. Add deadline");
        System.out.println("2. Mark deadline as completed");
        int option = readInt("Choose an option: ");

        if (option == 1) {
            String description = readText("Description: ");
            String type = readText("Type (hearing, filing, appeal...): ");
            LocalDate date = readDate("Target date (yyyy-MM-dd): ");
            int alertDays = readInt("Alert how many days before?: ");
            Deadline deadline = new Deadline(nextDeadlineId, caseFile.getCaseId(), description, type, date, alertDays);
            caseFile.addDeadline(deadline);
            nextDeadlineId++;
            auditLog.registerEntry(currentUser, "ADD_DEADLINE", caseFile);
            System.out.println("Remaining days: " + deadline.calculateRemainingDays());
        } else if (option == 2) {
            List<Deadline> deadlines = caseFile.getDeadlines();
            if (deadlines.isEmpty()) {
                System.out.println("The case has no deadlines");
                return;
            }
            for (int i = 0; i < deadlines.size(); i++) {
                System.out.println((i + 1) + ". " + deadlines.get(i));
            }
            int number = readInt("Number of the deadline: ");
            if (number >= 1 && number <= deadlines.size()) {
                deadlines.get(number - 1).markCompleted();
                auditLog.registerEntry(currentUser, "COMPLETE_DEADLINE", caseFile);
                System.out.println("The deadline was marked as completed");
            } else {
                System.out.println("Invalid number");
            }
        }
    }

    public static void generateDocument() {
        CaseFile caseFile = chooseCase();
        if (caseFile == null) {
            return;
        }
        String template = readText("Template name (e.g. DEMAND): ");
        Document document = documentGenerator.generate(caseFile, template);
        if (document != null) {
            caseFile.getDocuments().add(document);
            auditLog.registerEntry(currentUser, "GENERATE_DOCUMENT", caseFile);
            System.out.println("The document was generated in " + document.getFilePath());
        } else {
            System.out.println("The document could not be generated");
        }
    }

    public static void importCases() {
        String path = readText("CSV file path (enter = " + CsvImporter.DEFAULT_CSV_FILE + "): ");
        if (path.isEmpty()) {
            path = CsvImporter.DEFAULT_CSV_FILE;
        }
        CsvImporter importer = new CsvImporter(path, authService.getLawyers());
        List<CaseFile> imported = importer.importCases(path);
        int added = 0;
        for (CaseFile caseFile : imported) {
            //the cases that already exist are not added again
            if (searchEngine.findByDocketNumber(caseFile.getDocketNumber()) == null) {
                caseFile.setCaseId(caseFiles.size() + 1);
                caseFiles.add(caseFile);
                auditLog.registerEntry(currentUser, "IMPORT_CSV", caseFile);
                added++;
            }
        }
        System.out.println(importer.getRecordsImported() + " record(s) read, " + added + " new case(s) added");
    }

    public static void viewAuditLog() {
        System.out.println("\n--- Audit log (" + auditLog.getLogFile() + ") ---");
        for (String line : auditLog.getHistory()) {
            System.out.println(line);
        }
    }

    public static void manageUsers() {
        System.out.println("\n--- Manage user accounts ---");
        System.out.println("1. List users");
        System.out.println("2. Create user");
        System.out.println("3. Restrict user access");
        System.out.println("4. Reactivate user");
        int option = readInt("Choose an option: ");

        if (option == 1) {
            for (Lawyer lawyer : authService.getLawyers()) {
                System.out.println("  " + lawyer.getUsername() + " - " + lawyer.getName() + " - " + lawyer.getRole()
                        + " - active: " + lawyer.isActive());
            }
            for (LegalAssistant assistant : authService.getAssistants()) {
                System.out.println("  " + assistant.getUsername() + " - " + assistant.getName() + " - " + assistant.getRole()
                        + " - active: " + assistant.isActive());
            }
        } else if (option == 2) {
            createUser();
        } else if (option == 3) {
            User user = authService.findUser(readText("Username to restrict: "));
            Lawyer partner = authService.findLawyer(currentUser.getUsername());
            partner.restrictUserAccess(user);
            if (user != null && !user.isActive()) {
                authService.saveCredentials();
                auditLog.registerEntry(currentUser, "RESTRICT_USER " + user.getUsername(), null);
            }
        } else if (option == 4) {
            User user = authService.findUser(readText("Username to reactivate: "));
            if (user != null) {
                user.setActive(true);
                authService.saveCredentials();
                auditLog.registerEntry(currentUser, "REACTIVATE_USER " + user.getUsername(), null);
                System.out.println("The user " + user.getUsername() + " is active again");
            } else {
                System.out.println("The user does not exist");
            }
        } else {
            System.out.println("Invalid option");
        }
    }

    public static void createUser() {
        int userId = authService.nextUserId();
        System.out.println("User id: " + userId);
        String name = readText("Full name: ");
        String username = readText("Username: ");
        if (authService.findUser(username) != null) {
            System.out.println("That username already exists");
            return;
        }
        String password = readText("Password (min 6 characters): ");
        if (password.length() < 6) {
            System.out.println("The password is too short");
            return;
        }
        System.out.println("1. Partner   2. Lawyer   3. Junior lawyer   4. Legal assistant");
        int type = readInt("Type of user: ");

        if (type == 4) {
            String area = readText("Area: ");
            LegalAssistant assistant = new LegalAssistant(userId, name, username, password, area);
            System.out.println("Choose the supervisor:");
            assistant.setSupervisor(chooseLawyer());
            authService.getAssistants().add(assistant);
        } else {
            Role role = Role.LAWYER;
            if (type == 1) {
                role = Role.PARTNER;
            } else if (type == 3) {
                role = Role.JUNIOR_LAWYER;
            }
            String position = readText("Position: ");
            String license = readText("License number: ");
            authService.getLawyers().add(new Lawyer(userId, name, username, password, role, position, license));
        }
        authService.saveCredentials();
        auditLog.registerEntry(currentUser, "CREATE_USER " + username, null);
        System.out.println("The user " + username + " was created");
    }

    public static void changePassword() {
        String oldPass = readText("Current password: ");
        String newPass = readText("New password (min 6 characters): ");
        if (currentUser.changePassword(oldPass, newPass)) {
            authService.saveCredentials();
            auditLog.registerEntry(currentUser, "CHANGE_PASSWORD", null);
            System.out.println("The password was changed");
        } else {
            System.out.println("The password was NOT changed");
        }
    }

    public static CaseFile chooseCase() {
        if (caseFiles.isEmpty()) {
            System.out.println("There are no cases registered");
            return null;
        }
        System.out.println("Cases:");
        for (CaseFile caseFile : caseFiles) {
            System.out.println("  " + caseFile.getDocketNumber() + " - " + caseFile.getTitle()
                    + " (" + caseFile.getClient().getName() + ")");
        }
        String docket = readText("Docket number: ");
        CaseFile caseFile = searchEngine.findByDocketNumber(docket);
        if (caseFile == null) {
            System.out.println("There is no case with docket number " + docket);
        }
        return caseFile;
    }

    public static Lawyer chooseLawyer() {
        List<Lawyer> lawyers = authService.getLawyers();
        for (int i = 0; i < lawyers.size(); i++) {
            System.out.println((i + 1) + ". " + lawyers.get(i).getName() + " (" + lawyers.get(i).getRole() + ")");
        }
        int number;
        do {
            number = readInt("Choose the lawyer: ");
        } while (number < 1 || number > lawyers.size());
        return lawyers.get(number - 1);
    }

    public static String readText(String message) {
        System.out.print(message);
        return scanner.nextLine().trim();
    }

    public static int readInt(String message) {
        while (true) {
            System.out.print(message);
            try {
                return Integer.parseInt(scanner.nextLine().trim());
            } catch (NumberFormatException e) {
                System.out.println("Please enter a valid number");
            }
        }
    }

    public static LocalDate readDate(String message) {
        while (true) {
            System.out.print(message);
            try {
                return LocalDate.parse(scanner.nextLine().trim());
            } catch (DateTimeParseException e) {
                System.out.println("Please enter a valid date with the format yyyy-MM-dd");
            }
        }
    }
}
