import json
import os

class Truck:
    def __init__(self, id: str, brand: str, capacity_tons: int, year: int):
        self.id = id
        self.brand = brand
        self.capacity_tons = capacity_tons
        self.year = year

    def _get_file_path(self):
        model_dir = os.path.dirname(os.path.abspath(__file__))
        root_dir = os.path.dirname(model_dir)
        return os.path.join(root_dir, "data", "trucks.json")

    def saveInformation(self):
        file_path = self._get_file_path()
        
        if not os.path.exists(os.path.dirname(file_path)):
            os.makedirs(os.path.dirname(file_path))

        trucks_data = []
        if os.path.exists(file_path):
            try:
                with open(file_path, 'r', encoding='utf-8') as file:
                    trucks_data = json.load(file)
            except json.JSONDecodeError:
                trucks_data = []

        trucks_data.append({
            "id": self.id,
            "brand": self.brand,
            "capacity_tons": self.capacity_tons,
            "year": self.year
        })

        with open(file_path, 'w', encoding='utf-8') as file:
            json.dump(trucks_data, file, indent=4)

    @staticmethod
    def LoadInformation():
        model_dir = os.path.dirname(os.path.abspath(__file__))
        root_dir = os.path.dirname(model_dir)
        file_path = os.path.join(root_dir, "data", "trucks.json")
        
        trucks_list = []
        if os.path.exists(file_path):
            try:
                with open(file_path, 'r', encoding='utf-8') as file:
                    data = json.load(file)
                    for item in data:
                        trucks_list.append(Truck(item['id'], item['brand'], item['capacity_tons'], item['year']))
            except json.JSONDecodeError:
                pass
        return trucks_list

    def __str__(self):
        return f"ID: {self.id} | Marca: {self.brand} | Capacidad: {self.capacity_tons} ton | Año: {self.year}"