import json
import os

class Cable:
    def __init__(self, cable_id, tipo, longitud, estado, ubicacion):
        self.id = cable_id
        self.tipo = tipo
        self.longitud = longitud
        self.estado = estado
        self.ubicacion = ubicacion

    def to_dict(self):
        return {
            "id": self.id,
            "tipo": self.tipo,
            "longitud": self.longitud,
            "estado": self.estado,
            "ubicacion": self.ubicacion
        }

    @classmethod
    def from_dict(cls, data):
        return cls(
            cable_id=data["id"],
            tipo=data["tipo"],
            longitud=data["longitud"],
            estado=data["estado"],
            ubicacion=data["ubicacion"]
        )

class CableManager:
    def __init__(self, filepath="cables.json"):
        self.filepath = filepath
        self.cables = []
        self.cargar_datos()

    def cargar_datos(self):
        if os.path.exists(self.filepath):
            try:
                with open(self.filepath, "r", encoding="utf-8") as f:
                    data = json.load(f)
                    self.cables = [Cable.from_dict(item) for item in data]
            except json.JSONDecodeError:
                self.cables = []
        else:
            self.cables = []

    def guardar_datos(self):
        with open(self.filepath, "w", encoding="utf-8") as f:
            json.dump([cable.to_dict() for cable in self.cables], f, indent=4, ensure_ascii=False)

    def agregar_cable(self, cable):
        self.cables.append(cable)
        self.guardar_datos()

    def obtener_todos(self):
        return self.cables

    def buscar_por_id(self, cable_id):
        for cable in self.cables:
            if cable.id.lower() == cable_id.lower():
                return cable
        return None

    def buscar_por_tipo(self, tipo):
        return [c for c in self.cables if tipo.lower() in c.tipo.lower()]

    def actualizar_cable(self, cable_id, nuevo_tipo, nueva_longitud, nuevo_estado, nueva_ubicacion):
        cable = self.buscar_por_id(cable_id)
        if cable:
            cable.tipo = nuevo_tipo
            cable.longitud = nueva_longitud
            cable.estado = nuevo_estado
            cable.ubicacion = nueva_ubicacion
            self.guardar_datos()
            return True
        return False
