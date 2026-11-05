package ec.edu.espe.electromechanicalsystem.controller;

import ec.edu.espe.electromechanicalmaintenancemanagementsystem.model.Technician;
import java.util.List;

/** Persists technicians in a JSON file (technicians.json). */
public class TechnicianJsonFileManager extends JsonFileStore<Technician> {

    public TechnicianJsonFileManager(String fileName) {
        super(fileName);
    }

    public List<Technician> loadTechnicians() {
        return load();
    }

    public void saveTechnicians(List<Technician> technicians) {
        save(technicians);
    }

    @Override
    protected String toJson(Technician technician) {
        return JsonUtil.object(
                JsonUtil.number("technicianId", technician.getTechnicianId()),
                JsonUtil.string("name", technician.getName()),
                JsonUtil.string("expertise", technician.getExpertise()),
                JsonUtil.bool("availabilityStatus", technician.getAvailabilityStatus()));
    }

    @Override
    protected Technician fromJson(String object) {
        Integer id = JsonUtil.readInt(object, "technicianId");
        String name = JsonUtil.readString(object, "name");
        String expertise = JsonUtil.readString(object, "expertise");
        Boolean available = JsonUtil.readBoolean(object, "availabilityStatus");
        if (id == null || name == null || expertise == null || available == null) {
            return null;
        }
        return new Technician(id, name, expertise, available);
    }
}
