package ec.edu.espe.electromechanicalsystem.controller;

import ec.edu.espe.electromechanicalmaintenancemanagementsystem.model.Client;
import ec.edu.espe.electromechanicalmaintenancemanagementsystem.model.Report;
import ec.edu.espe.electromechanicalmaintenancemanagementsystem.model.Technician;
import java.nio.file.Path;

/**
 * Single entry point for persistence.
 * - maintenance_data.ser keeps the complete object graph (relations included).
 * - clients.json, technicians.json and reports.json keep readable, editable copies.
 * On startup the JSON records are merged into the loaded state; on every save all files are rewritten.
 */
public class DataRepository {
    private static final String STATE_FILE = "maintenance_data.ser";

    private final Path directory;
    private final SystemState state;
    private final JsonFileManager clientStore;
    private final TechnicianJsonFileManager technicianStore;
    private final ReportJsonFileManager reportStore;

    public DataRepository() {
        this(".");
    }

    public DataRepository(String directory) {
        this.directory = Path.of(directory);
        this.clientStore = new JsonFileManager(file("clients.json"));
        this.technicianStore = new TechnicianJsonFileManager(file("technicians.json"));
        this.reportStore = new ReportJsonFileManager(file("reports.json"));
        this.state = SystemState.load(file(STATE_FILE));
        mergeClients();
        mergeTechnicians();
        mergeReports();
    }

    public SystemState getState() {
        return state;
    }

    public void saveAll() {
        state.save(file(STATE_FILE));
        clientStore.saveClients(state.clients);
        technicianStore.saveTechnicians(state.technicians);
        reportStore.saveReports(state.reports);
    }

    private String file(String name) {
        return directory.resolve(name).toString();
    }

    private void mergeClients() {
        for (Client stored : clientStore.loadClients()) {
            Client existing = state.clients.stream()
                    .filter(client -> client.getClientId() == stored.getClientId())
                    .findFirst().orElse(null);
            if (existing == null) {
                state.clients.add(stored);
            } else {
                existing.setName(stored.getName());
                existing.setContactInfo(stored.getContactInfo());
                existing.setAddress(stored.getAddress());
            }
        }
    }

    private void mergeTechnicians() {
        for (Technician stored : technicianStore.loadTechnicians()) {
            Technician existing = state.technicians.stream()
                    .filter(technician -> technician.getTechnicianId() == stored.getTechnicianId())
                    .findFirst().orElse(null);
            if (existing == null) {
                state.technicians.add(stored);
            } else {
                existing.setName(stored.getName());
                existing.setExpertise(stored.getExpertise());
                existing.setAvailabilityStatus(stored.getAvailabilityStatus());
            }
        }
    }

    private void mergeReports() {
        for (Report stored : reportStore.loadReports()) {
            boolean exists = state.reports.stream()
                    .anyMatch(report -> report.getReportId() == stored.getReportId());
            if (!exists) {
                state.reports.add(stored);
            }
        }
    }
}
