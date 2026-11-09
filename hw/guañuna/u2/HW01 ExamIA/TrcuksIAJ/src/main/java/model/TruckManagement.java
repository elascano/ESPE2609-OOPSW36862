package model;

import java.util.List;

public class TruckManagement {
    private String id;
    private List<Truck> trucks;

    public TruckManagement() {
        this.id = "Gestor-01";
        this.trucks = Truck.LoadInformation();
    }

    public void add_truck(Truck truck) {
        this.trucks.add(truck);
        truck.saveInformation();
    }

    public List<Truck> get_all_trucks() {
        return this.trucks;
    }
}