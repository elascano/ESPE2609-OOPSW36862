from pathlib import Path

from controller.video_projector_controller import VideoProjectorController
from model.video_projector_repository import VideoProjectorRepository
from model.video_projector_service import VideoProjectorService
from view.video_projector_view import VideoProjectorView

DATA_FILE = Path(__file__).parent / "data" / "video_projectors.json"


def main() -> None:
    repository = VideoProjectorRepository(DATA_FILE)
    service = VideoProjectorService(repository)
    controller = VideoProjectorController(service, VideoProjectorView())
    controller.run()


if __name__ == "__main__":
    main()
