import { Truck } from './truck';

export class TruckManagement {
    private id: string;
    private trucks: Truck[];

    constructor() {
        this.id = "Gestor-01";
        this.trucks = Truck.LoadInformation();
    }

    public add_truck(truck: Truck): void {
        this.trucks.push(truck);
        truck.saveInformation();
    }

    public get_all_trucks(): Truck[] {
        return this.trucks;
    }
}