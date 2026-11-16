"use strict";
Object.defineProperty(exports, "__esModule", { value: true });
exports.TruckManagement = void 0;
const truck_1 = require("./truck");
class TruckManagement {
    constructor() {
        this.id = "Gestor-01";
        this.trucks = truck_1.Truck.LoadInformation();
    }
    add_truck(truck) {
        this.trucks.push(truck);
        truck.saveInformation();
    }
    get_all_trucks() {
        return this.trucks;
    }
}
exports.TruckManagement = TruckManagement;
