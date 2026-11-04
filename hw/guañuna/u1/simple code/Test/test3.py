import json # Módulo nativo de Python para JSON
import os

class Function2:
    def __init__(self, nombre="", edad=0):
        self.nombre = nombre
        self.edad = edad

    def __str__(self):
        return f"Function2{{nombre={self.nombre}, edad={self.edad}}}"

    def get_nombre(self): return self.nombre
    def get_edad(self): return self.edad

# ==========================================
# MÉTODOS DE PERSISTENCIA JSON
# ==========================================
RUTA_ARCHIVO = "datos.json"

def guardar_en_json(lista_objetos):
    # Convertimos la lista de objetos a una lista de diccionarios
    lista_diccionarios = []
    for obj in lista_objetos:
        lista_diccionarios.append({
            "nombre": obj.nombre,
            "edad": obj.edad
        })
    
    # json.dump escribe la lista en el archivo con formato JSON
    with open(RUTA_ARCHIVO, "w", encoding="utf-8") as archivo:
        json.dump(lista_diccionarios, archivo, indent=4) # indent=4 para que se vea bonito
    print(f"Datos guardados en {RUTA_ARCHIVO}")

def cargar_desde_json():
    lista_objetos = []
    if os.path.exists(RUTA_ARCHIVO):
        with open(RUTA_ARCHIVO, "r", encoding="utf-8") as archivo:
            datos = json.load(archivo) # Convierte el JSON de vuelta a diccionarios
            
            for d in datos:
                # Reconstruimos los objetos
                lista_objetos.append(Function2(d["nombre"], d["edad"]))
        print(f"Datos cargados desde {RUTA_ARCHIVO}")
    return lista_objetos


def main():
    # Cargar datos al iniciar
    lista_objetos = cargar_desde_json()

    while True:
        print("\n=== MENÚ PYTHON (JSON) ===")
        print("1. Crear Objeto")
        print("2. Mostrar Todos")
        print("3. Guardar en JSON")
        print("4. Salir (Guarda automáticamente)")
        
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
            guardar_en_json(lista_objetos)
            print("Saliendo...")
            break
        else:
            print("Opción inválida.")

if __name__ == "__main__":
    main()