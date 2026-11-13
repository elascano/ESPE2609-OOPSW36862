class VideoProjectorView:
    def show_menu(self) -> str:
        print("\n===== PROYECTORES DE VIDEO =====")
        print("1. Registrar proyector")
        print("2. Ver todos los proyectores")
        print("3. Buscar proyector por ID")
        print("4. Eliminar proyector")
        print("5. Salir")
        return input("Elija una opción: ").strip()

    def ask(self, label: str) -> str:
        return input(f"{label}: ")

    def show_projector(self, projector) -> None:
        print(f"ID: {projector.projector_id} | Marca: {projector.brand} | "
              f"Resolución: {projector.resolution} | Lúmenes: {projector.lumens}")

    def show_message(self, message: str) -> None:
        print(message)
