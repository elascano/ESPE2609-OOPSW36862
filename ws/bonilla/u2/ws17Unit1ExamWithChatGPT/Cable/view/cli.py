class CableView:
    @staticmethod
    def mostrar_menu():
        print("\n===============================")
        print("   SISTEMA CABLEMANAGER (MVC)  ")
        print("===============================")
        print("1. Listar todos los cables")
        print("2. Agregar nuevo cable")
        print("3. Buscar cable por ID")
        print("4. Buscar cables por tipo")
        print("5. Actualizar cable")
        print("6. Salir")
        return input("Seleccione una opción (1-6): ").strip()

    @staticmethod
    def mostrar_cables(cables):
        if not cables:
            print("\n[!] No hay cables registrados en el inventario.")
            return
        print("\n--- LISTADO DE CABLES ---")
        for c in cables:
            print(f"ID: {c.id} | Tipo: {c.tipo} | Longitud: {c.longitud} | Estado: {c.estado} | Ubicación: {c.ubicacion}")

    @staticmethod
    def solicitar_datos_cable():
        print("\n--- NUEVO CABLE ---")
        cable_id = input("ID único (ej. C001): ").strip()
        tipo = input("Tipo (ej. HDMI, Ethernet, USB-C): ").strip()
        longitud = input("Longitud (ej. 2m): ").strip()
        estado = input("Estado [Disponible / En uso / Dañado]: ").strip()
        ubicacion = input("Ubicación (ej. Estante A-1): ").strip()
        return cable_id, tipo, longitud, estado or "Disponible", ubicacion

    @staticmethod
    def solicitar_id(accion="buscar"):
        return input(f"\nIngrese el ID del cable a {accion}: ").strip()

    @staticmethod
    def solicitar_tipo():
        return input("\nIngrese el tipo de cable a buscar: ").strip()

    @staticmethod
    def mostrar_mensaje(mensaje, error=False):
        prefijo = "[ERROR]" if error else "[OK]"
        print(f"\n{prefijo} {mensaje}")
