from model.video_projector import VideoProjector
from model.video_projector_repository import VideoProjectorRepository


class InvalidDataError(Exception):
    pass


class DuplicateIdError(Exception):
    pass


class VideoProjectorService:
    def __init__(self, repository: VideoProjectorRepository):
        self.repository = repository

    def register(self, projector_id: str, brand: str, resolution: str, lumens: str) -> VideoProjector:
        projector = VideoProjector(
            projector_id=self._require_text(projector_id),
            brand=self._require_text(brand),
            resolution=self._require_text(resolution),
            lumens=self._parse_lumens(lumens),
        )
        if self.repository.exists(projector.projector_id):
            raise DuplicateIdError(projector.projector_id)
        self.repository.save(projector)
        return projector

    def list_all(self) -> list[VideoProjector]:
        return self.repository.find_all()

    def search(self, projector_id: str) -> VideoProjector | None:
        return self.repository.find_by_id(projector_id.strip())

    def remove(self, projector_id: str) -> bool:
        return self.repository.delete(projector_id.strip())

    @staticmethod
    def _require_text(value: str) -> str:
        cleaned = value.strip()
        if not cleaned:
            raise InvalidDataError("empty")
        return cleaned

    @staticmethod
    def _parse_lumens(value: str) -> int:
        if not value.strip().isdigit() or int(value) <= 0:
            raise InvalidDataError("lumens")
        return int(value)
