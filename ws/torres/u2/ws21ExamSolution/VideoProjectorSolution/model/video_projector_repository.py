import json
from pathlib import Path

from model.video_projector import VideoProjector


class VideoProjectorRepository:
    def __init__(self, file_path: Path):
        self.file_path = file_path
        self.file_path.parent.mkdir(parents=True, exist_ok=True)
        if not self.file_path.exists():
            self._write_all([])

    def find_all(self) -> list[VideoProjector]:
        with self.file_path.open("r", encoding="utf-8") as file:
            return [VideoProjector.from_dict(item) for item in json.load(file)]

    def find_by_id(self, projector_id: str) -> VideoProjector | None:
        return next(
            (projector for projector in self.find_all() if projector.projector_id == projector_id),
            None,
        )

    def exists(self, projector_id: str) -> bool:
        return self.find_by_id(projector_id) is not None

    def save(self, projector: VideoProjector) -> None:
        projectors = self.find_all()
        projectors.append(projector)
        self._write_all(projectors)

    def delete(self, projector_id: str) -> bool:
        projectors = self.find_all()
        remaining = [projector for projector in projectors if projector.projector_id != projector_id]
        if len(remaining) == len(projectors):
            return False
        self._write_all(remaining)
        return True

    def _write_all(self, projectors: list[VideoProjector]) -> None:
        with self.file_path.open("w", encoding="utf-8") as file:
            json.dump([projector.to_dict() for projector in projectors], file, indent=4, ensure_ascii=False)
