from model.video_projector_service import DuplicateIdError, InvalidDataError, VideoProjectorService
from view.video_projector_view import VideoProjectorView


class VideoProjectorController:
    def __init__(self, service: VideoProjectorService, view: VideoProjectorView):
        self.service = service
        self.view = view
        self.actions = {
            "1": self.register,
            "2": self.list_all,
            "3": self.search,
            "4": self.remove,
        }

    def run(self) -> None:
        while (option := self.view.show_menu()) != "5":
            action = self.actions.get(option)
            if action:
                action()
            else:
                self.view.show_message("Opción no válida.")
        self.view.show_message("¡Hasta luego!")

    def register(self) -> None:
        try:
            self.service.register(
                self.view.ask("ID"),
                self.view.ask("Marca"),
                self.view.ask("Resolución (ej. 1920x1080)"),
                self.view.ask("Lúmenes"),
            )
            self.view.show_message("Proyector registrado y guardado en JSON.")
        except DuplicateIdError:
            self.view.show_message("Ya existe un proyector con ese ID.")
        except InvalidDataError:
            self.view.show_message("Datos inválidos: no deje campos vacíos y use un número entero positivo en lúmenes.")

    def list_all(self) -> None:
        projectors = self.service.list_all()
        if not projectors:
            self.view.show_message("No hay proyectores registrados.")
        for projector in projectors:
            self.view.show_projector(projector)

    def search(self) -> None:
        projector = self.service.search(self.view.ask("ID a buscar"))
        if projector:
            self.view.show_projector(projector)
        else:
            self.view.show_message("Proyector no encontrado.")

    def remove(self) -> None:
        if self.service.remove(self.view.ask("ID a eliminar")):
            self.view.show_message("Proyector eliminado.")
        else:
            self.view.show_message("Proyector no encontrado.")
