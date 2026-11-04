import json
from pathlib import Path
from goalkeeper import Goalkeeper

ARCHIVO = Path(__file__).with_name("goalkeepers.json")


def leer():
    arqueros = []
    if ARCHIVO.exists():
        with open(ARCHIVO, "r", encoding="utf-8") as archivo:
            datos = json.load(archivo)
        for dato in datos:
            arquero = Goalkeeper(dato["id"], dato["nombre"],
                                 dato["edad"], dato["equipo"])
            arqueros.append(arquero)
    return arqueros


def guardar(arqueros):
    datos = []
    for arquero in arqueros:
        datos.append({"id": arquero.id, "nombre": arquero.nombre,
                      "edad": arquero.edad, "equipo": arquero.equipo})
    with open(ARCHIVO, "w", encoding="utf-8") as archivo:
        json.dump(datos, archivo, indent=4, ensure_ascii=False)


def crear(arqueros):
    id_arquero = input("ID: ").strip()
    nombre = input("Nombre: ").strip()
    edad = input("Edad: ").strip()
    equipo = input("Equipo: ").strip()

    if not id_arquero or not nombre or not edad or not equipo:
        print("Todos los campos son obligatorios.")
        return
    if not edad.isdecimal() or int(edad) <= 0:
        print("La edad debe ser un entero mayor que cero.")
        return
    for arquero in arqueros:
        if arquero.id == id_arquero:
            print("Ese ID ya existe.")
            return

    nuevo = Goalkeeper(id_arquero, nombre, int(edad), equipo)
    arqueros.append(nuevo)
    try:
        guardar(arqueros)
    except OSError:
        arqueros.pop()
        raise
    print("Arquero guardado.")


def main():
    # list es el equivalente de ArrayList en Python.
    while True:
        print("\n1. Crear arquero")
        print("2. Leer y mostrar arqueros del JSON")
        print("3. Salir")
        opcion = input("Opcion: ").strip()

        if opcion == "3":
            print("Hasta luego.")
            break

        try:
            if opcion == "1":
                arqueros = leer()
                crear(arqueros)
            elif opcion == "2":
                arqueros = leer()
                if not arqueros:
                    print("No hay arqueros guardados.")
                for arquero in arqueros:
                    arquero.mostrar()
            else:
                print("Opcion incorrecta.")
        except (OSError, ValueError, KeyError, TypeError) as error:
            print("No se pudo procesar el archivo JSON:", error)


if __name__ == "__main__":
    main()
