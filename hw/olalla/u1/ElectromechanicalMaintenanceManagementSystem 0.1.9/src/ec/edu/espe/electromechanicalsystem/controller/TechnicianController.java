package ec.edu.espe.electromechanicalsystem.controller;

import ec.edu.espe.electromechanicalmaintenancemanagementsystem.model.Technician;
import ec.edu.espe.electromechanicalmaintenancemanagementsystem.model.WorkOrder;
import java.util.List;

/** Business rules for technicians. */
public class TechnicianController {
    private final SystemState state;

    public TechnicianController(SystemState state) {
        this.state = state;
    }

    public List<Technician> findAll() {
        return state.technicians;
    }

    public Technician findById(int id) {
        for (Technician technician : state.technicians) {
            if (technician.getTechnicianId() == id) {
                return technician;
            }
        }
        return null;
    }

    public Technician register(String name, String expertise) {
        int id = IdGenerator.nextId(state.technicians, Technician::getTechnicianId);
        Technician technician = new Technician(id, name, expertise, true);
        state.technicians.add(technician);
        return technician;
    }

    public boolean isAssignedToOpenOrder(Technician technician) {
        for (WorkOrder order : state.orders) {
            Technician assigned = order.getTechnician();
            if (assigned != null && assigned.getTechnicianId() == technician.getTechnicianId()
                    && !"Closed".equals(order.getStatus())) {
                return true;
            }
        }
        return false;
    }

    /** Deletes the technician unless he or she is working on an open work order. */
    public boolean delete(Technician technician) {
        if (isAssignedToOpenOrder(technician)) {
            return false;
        }
        state.technicians.remove(technician);
        return true;
    }
}
