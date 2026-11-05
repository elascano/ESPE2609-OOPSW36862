package ec.edu.espe.electromechanicalsystem.controller;

import ec.edu.espe.electromechanicalmaintenancemanagementsystem.model.Alert;
import ec.edu.espe.electromechanicalmaintenancemanagementsystem.model.Equipment;
import ec.edu.espe.electromechanicalmaintenancemanagementsystem.model.MaintenanceHistory;
import ec.edu.espe.electromechanicalmaintenancemanagementsystem.model.MaintenanceSchedule;
import java.time.LocalDate;
import java.util.ArrayList;
import java.util.List;

/** Business rules for maintenance schedules, histories and alerts. */
public class MaintenanceController {
    private static final int UPCOMING_DAYS = 30;

    private final SystemState state;

    public MaintenanceController(SystemState state) {
        this.state = state;
    }

    public MaintenanceSchedule schedule(Equipment equipment, String frequency, String date, boolean preventive) {
        int id = IdGenerator.nextId(state.schedules, MaintenanceSchedule::getScheduleId);
        MaintenanceSchedule schedule = new MaintenanceSchedule(id, frequency, date, preventive);
        state.schedules.add(schedule);
        equipment.assignSchedule(schedule);
        return schedule;
    }

    public MaintenanceHistory recordHistory(Equipment equipment, String type, String description) {
        int id = IdGenerator.nextId(state.histories, MaintenanceHistory::getHistoryId);
        MaintenanceHistory history = new MaintenanceHistory(id, LocalDate.now().toString(), type, description);
        state.histories.add(history);
        equipment.attachHistory(history);
        return history;
    }

    public void registerAlert(Alert alert) {
        alert.setAlertId(IdGenerator.nextId(state.alerts, Alert::getAlertId));
        state.alerts.add(alert);
    }

    /** Lines describing upcoming (next 30 days) and overdue schedules plus unresolved alerts. */
    public List<String> upcomingNotices(LocalDate today) {
        List<String> notices = new ArrayList<>();
        for (MaintenanceSchedule schedule : state.schedules) {
            try {
                LocalDate due = LocalDate.parse(schedule.getScheduleDate());
                if (due.isBefore(today)) {
                    notices.add("OVERDUE: " + schedule);
                } else if (!due.isAfter(today.plusDays(UPCOMING_DAYS))) {
                    notices.add("UPCOMING: " + schedule);
                }
            } catch (RuntimeException ignored) {
                // A schedule with an unreadable date is skipped.
            }
        }
        for (Alert alert : state.alerts) {
            if (!alert.getIsResolved()) {
                notices.add("PENDING ALERT: " + alert);
            }
        }
        return notices;
    }
}
