from dataclasses import dataclass, asdict


@dataclass
class VideoProjector:
    projector_id: str
    brand: str
    resolution: str
    lumens: int

    def to_dict(self) -> dict:
        return asdict(self)

    @classmethod
    def from_dict(cls, data: dict) -> "VideoProjector":
        return cls(
            projector_id=str(data["projector_id"]),
            brand=str(data["brand"]),
            resolution=str(data["resolution"]),
            lumens=int(data["lumens"]),
        )
