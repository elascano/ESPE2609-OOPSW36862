import json
import os

ARCHIVO_JSON = "chickencoop.json"

def guardar(coop):
    try:
        with open(ARCHIVO_JSON, 'w', encoding='utf-8') as writer:
            # Usamos indent=4 para simular el setPrettyPrinting() de Gson
            json.dump(coop.to_dict(), writer, indent=4)
        print(f">> Datos guardados exitosamente en {ARCHIVO_JSON}")
    except Exception as e:
        print(f">> Error al guardar los datos: {e}")

def cargar():
    if not os.path.exists(ARCHIVO_JSON):
        print(">> No se encontró archivo previo o hubo un error. Creando un corral nuevo.")
        return None
        
    try:
        with open(ARCHIVO_JSON, 'r', encoding='utf-8') as reader:
            data = json.load(reader)
            from chickencoop import ChickenCoop
            coop = ChickenCoop.from_dict(data)
            print(f">> Datos cargados exitosamente desde {ARCHIVO_JSON}")
            return coop
    except Exception as e:
        print(f">> No se encontró archivo previo o hubo un error. Creando un corral nuevo. (Detalle: {e})")
        return None