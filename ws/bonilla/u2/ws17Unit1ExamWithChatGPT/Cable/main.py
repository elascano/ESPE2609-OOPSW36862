from model.cable import Cable, CableManager
from view.cli import CableView

def main():
    manager = CableManager("cables.json")
    view = CableView()

    while True:
        opcion = view.mostrar_menu()

        if opcion == "1":
            cables = manager.obtener_todos()
            view.mostrar_cables(cables)

        elif opcion == "2":
            cable_id, tipo, longitud, estado, ubicacion = view.solicitar_datos_cable()
            if not cable_id or not tipo:
                view.mostrar_mensaje("El ID y el tipo son obligatorios.", error=True)
                continue
            if manager.buscar_por_id(cable_id):
                view.mostrar_mensaje(f"Ya existe un cable con el ID '{cable_id}'.", error=True)
            else:
                nuevo_cable = Cable(cable_id, tipo, longitud, estado, ubicacion)
                manager.agregar_cable(nuevo_cable)
                view.mostrar_mensaje("Cable agregado y guardado con éxito.")

        elif opcion == "3":
            cable_id = view.solicitar_id("buscar")
            cable = manager.buscar_por_id(cable_id)
            if cable:
                view.mostrar_cables([cable])
            else:
                view.mostrar_mensaje(f"No se encontró ningún cable con ID '{cable_id}'.", error=True)

        elif opcion == "4":
            tipo = view.solicitar_tipo()
            cables = manager.buscar_por_tipo(tipo)
            view.mostrar_cables(cables)

        elif opcion == "5":
            cable_id = view.solicitar_id("actualizar")
            cable = manager.buscar_por_id(cable_id)
            if not cable:
                view.mostrar_mensaje(f"No se encontró ningún cable con ID '{cable_id}'.", error=True)
                continue
            
            print(f"\nActualizando cable '{cable_id}'. Deje en blanco para conservar el valor actual.")
            tipo = input(f"Tipo [{cable.tipo}]: ").strip() or cable.tipo
            longitud = input(f"Longitud [{cable.longitud}]: ").strip() or cable.longitud
            estado = input(f"Estado [{cable.estado}]: ").strip() or cable.estado
            ubicacion = input(f"Ubicación [{cable.ubicacion}]: ").strip() or cable.ubicacion

            exito = manager.actualizar_cable(cable_id, tipo, longitud, estado, ubicacion)
            if exito:
                view.mostrar_mensaje("Cable actualizado exitosamente.")
            else:
                view.mostrar_mensaje("No se pudo actualizar el cable.", error=True)

        elif opcion == "6":
            print("\n¡Gracias por usar CableManager! Saliendo...")
            break
        else:
            view.mostrar_mensaje("Opción no válida. Por favor, seleccione entre 1 y 6.", error=True)

if __name__ == "__main__":
    main()
