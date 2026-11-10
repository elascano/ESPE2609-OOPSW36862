package ec.edu.espe.electromechanicalsystem.controller;

import ec.edu.espe.electromechanicalmaintenancemanagementsystem.model.Client;
import ec.edu.espe.electromechanicalmaintenancemanagementsystem.model.Equipment;
import ec.edu.espe.electromechanicalmaintenancemanagementsystem.model.QrCode;
import java.util.ArrayList;
import java.util.List;
import java.util.Locale;

/** Business rules for equipment and its QR codes. */
public class EquipmentController {
    private final SystemState state;

    public EquipmentController(SystemState state) {
        this.state = state;
    }

    public List<Equipment> findAll() {
        return state.equipments;
    }

    public Equipment findById(int id) {
        for (Equipment equipment : state.equipments) {
            if (equipment.getEquipmentId() == id) {
                return equipment;
            }
        }
        return null;
    }

    /** Registers equipment for a client and generates its QR code. */
    public Equipment register(Client owner, String name, String serialNumber, String location) {
        int id = IdGenerator.nextId(state.equipments, Equipment::getEquipmentId);
        Equipment equipment = new Equipment(id, name, serialNumber, location, "Available");
        state.equipments.add(equipment);
        owner.addEquipment(equipment);
        ensureQrCode(equipment);
        return equipment;
    }

    public List<Equipment> search(String term) {
        String text = term.toLowerCase(Locale.ROOT);
        List<Equipment> matches = new ArrayList<>();
        for (Equipment equipment : state.equipments) {
            if (String.valueOf(equipment.getEquipmentId()).equals(text)
                    || equipment.getName().toLowerCase(Locale.ROOT).contains(text)
                    || equipment.getSerialNumber().toLowerCase(Locale.ROOT).contains(text)) {
                matches.add(equipment);
            }
        }
        return matches;
    }

    /** Finds equipment by QR ID, QR payload, QR URL or serial number. */
    public Equipment findByQrOrSerial(String code) {
        for (Equipment equipment : state.equipments) {
            QrCode qr = ensureQrCode(equipment);
            if (code.equalsIgnoreCase(String.valueOf(qr.getQrId()))
                    || code.equalsIgnoreCase(qr.getCodeData())
                    || code.equalsIgnoreCase(qr.getGenerateUrl())
                    || code.equalsIgnoreCase(equipment.getSerialNumber())) {
                return equipment;
            }
        }
        return null;
    }

    public QrCode ensureQrCode(Equipment equipment) {
        if (equipment.getQrCode() == null) {
            state.qrCodes.add(equipment.createQrCode());
        }
        return equipment.getQrCode();
    }
}
