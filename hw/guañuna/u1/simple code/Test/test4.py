import json
import os
import csv # NUEVO: Importamos la librería para manejar CSV

class Function2:
    def __init__(self, nombre="", edad=0):
        self.nombre = nombre
        self.edad = edad

    def __str__(self):
        return f"Function2{{nombre={self.nombre}, edad={self.edad}}}"

    def get_nombre(self): return self.nombre
    def get_edad(self): return self.edad

RUTA_JSON = "datos.json"
RUTA_CSV = "datos_exportados.csv" # NUEVA RUTA

# ==========================================
# PERSISTENCIA JSON (Se mantiene igual)
# ==========================================
def guardar_en_json(lista_objetos):
    lista_diccionarios = [{"nombre": obj.nombre, "edad": obj.edad} for obj in lista_objetos]
    with open(RUTA_JSON, "w", encoding="utf-8") as archivo:
        json.dump(lista_diccionarios, archivo, indent=4)
    print(f"Datos guardados en {RUTA_JSON}")

def cargar_desde_json():
    lista_objetos = []
    if os.path.exists(RUTA_JSON):
        with open(RUTA_JSON, "r", encoding="utf-8") as archivo:
            datos = json.load(archivo)
            for d in datos:
                lista_objetos.append(Function2(d["nombre"], d["edad"]))
        print(f"Datos cargados desde {RUTA_JSON}")
    return lista_objetos

# ==========================================
# NUEVO: EXPORTAR A CSV
# ==========================================
def exportar_a_csv(lista_objetos):
    if not lista_objetos:
        print("No hay datos para exportar.")
        return

    # 'newline=""' es necesario en Windows para evitar líneas en blanco extra
    with open(RUTA_CSV, "w", newline="", encoding="utf-8") as archivo:
        escritor = csv.writer(archivo)
        
        # 1. Escribimos los encabezados
        escritor.writerow(["Nombre", "Edad"])
        
        # 2. Escribimos cada objeto
        for obj in lista_objetos:
            escritor.writerow([obj.get_nombre(), obj.get_edad()])
            
    print(f"¡Datos exportados exitosamente a {RUTA_CSV}!")

# ==========================================
# MAIN
# ==========================================
def main():
    lista_objetos = cargar_desde_json()

    while True:
        print("\n=== MENÚ PYTHON ===")
        print("1. Crear Objeto")
        print("2. Mostrar Todos")
        print("3. Guardar en JSON")
        print("4. Exportar a CSV") # NUEVA OPCIÓN
        print("5. Salir (Guarda automáticamente)")
        
        opcion = input("Seleccione: ")

        if opcion == "1":
            nombre = input("Ingrese nombre: ")
            try:
                edad = int(input("Ingrese edad: "))
                lista_objetos.append(Function2(nombre, edad))
                print("¡Objeto agregado!")
            except ValueError:
                print("Error: Edad inválida.")
        elif opcion == "2":
            if not lista_objetos:
                print("Lista vacía.")
            else:
                for obj in lista_objetos:
                    print(obj)
        elif opcion == "3":
            guardar_en_json(lista_objetos)
        elif opcion == "4":
            exportar_a_csv(lista_objetos)
        elif opcion == "5":
            guardar_en_json(lista_objetos)
            print("Saliendo...")
            break
        else:
            print("Opción inválida.")

if __name__ == "__main__":
    main()