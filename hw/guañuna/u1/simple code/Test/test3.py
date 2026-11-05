import json  # Módulo nativo de Python para JSON
import os

class Truck:
    def __init__(self, id="", marca="", capacidad_tons=0.0, año=0):
        self.id = id                          # atributo OBLIGATORIO
        self.marca = marca                    # extra 1
        self.capacidad_tons = capacidad_tons  # extra 2
        self.año = año                        # extra 3

    def __str__(self):
        return (f"Truck{{id={self.id}, marca={self.marca}, "
                f"capacidad_tons={self.capacidad_tons}, año={self.año}}}")

    def get_id(self): return self.id
    def get_marca(self): return self.marca
    def get_capacidad_tons(self): return self.capacidad_tons
    def get_año(self): return self.año


# ==========================================
# MÉTODOS DE PERSISTENCIA JSON
# ==========================================
RUTA_ARCHIVO = "trucks.json"


def guardar_en_json(lista_objetos):
    # Convertimos la lista de objetos a una lista de diccionarios
    lista_diccionarios = []
    for obj in lista_objetos:
        lista_diccionarios.append({
            "id": obj.id,
            "marca": obj.marca,
            "capacidad_tons": obj.capacidad_tons,
            "año": obj.año
        })

    # json.dump escribe la lista en el archivo con formato JSON
    with open(RUTA_ARCHIVO, "w", encoding="utf-8") as archivo:
        json.dump(lista_diccionarios, archivo, indent=4, ensure_ascii=False)
    print(f"Datos guardados en {RUTA_ARCHIVO}")


def cargar_desde_json():
    lista_objetos = []
    if os.path.exists(RUTA_ARCHIVO):
        with open(RUTA_ARCHIVO, "r", encoding="utf-8") as archivo:
            datos = json.load(archivo)  # Convierte el JSON de vuelta a diccionarios

            for d in datos:
                # Reconstruimos los objetos
                lista_objetos.append(
                    Truck(d["id"], d["marca"], d["capacidad_tons"], d["año"])
                )
        print(f"Datos cargados desde {RUTA_ARCHIVO}")
    return lista_objetos


def main():
    # Cargar datos al iniciar
    lista_objetos = cargar_desde_json()

    while True:
        print("\n=== MENÚ CAMIONES (JSON) ===")
        print("1. Crear Camión")
        print("2. Mostrar Todos")
        print("3. Guardar en JSON")
        print("4. Salir (Guarda automáticamente)")

        opcion = input("Seleccione: ")

        if opcion == "1":
            id_camion = input("Ingrese ID (obligatorio): ").strip()
            if not id_camion:
                print("Error: el ID es obligatorio.")
                continue
            marca = input("Ingrese marca: ")
            try:
                capacidad = float(input("Ingrese capacidad (toneladas): "))
                año = int(input("Ingrese año: "))
                lista_objetos.append(Truck(id_camion, marca, capacidad, año))
                print("¡Camión agregado!")
            except ValueError:
                print("Error: capacidad o año inválido.")

        elif opcion == "2":
            if not lista_objetos:
                print("Lista vacía.")
            else:
                for obj in lista_objetos:
                    print(obj)

        elif opcion == "3":
            guardar_en_json(lista_objetos)

        elif opcion == "4":
            guardar_en_json(lista_objetos)
            print("Saliendo...")
            break
        else:
            print("Opción inválida.")


if __name__ == "__main__":
    main()