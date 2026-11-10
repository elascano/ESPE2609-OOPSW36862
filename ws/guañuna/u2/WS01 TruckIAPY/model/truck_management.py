from model.truck import Truck

class TruckManagement:
    def __init__(self):
        self.id = "Gestor-01"
        self.trucks = Truck.LoadInformation()

    def add_truck(self, truck: Truck):
        self.trucks.append(truck)
        truck.saveInformation()

    def get_all_trucks(self):
        return self.trucks