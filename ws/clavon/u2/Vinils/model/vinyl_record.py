
from dataclasses import dataclass
from datetime import date


@dataclass
class VinylRecord:
    id: int
    title: str
    artist: str
    year: int

    def __post_init__(self):
        self.validate_data()

    def validate_data(self):
        if type(self.id) is not int or self.id <= 0:
            raise ValueError("ID must be a positive integer.")

        if not isinstance(self.title, str) or not self.title.strip():
            raise ValueError("Title cannot be empty.")

        if not isinstance(self.artist, str) or not self.artist.strip():
            raise ValueError("Artist cannot be empty.")

        if type(self.year) is not int or not 1 <= self.year <= date.today().year:
            raise ValueError("Invalid release year.")

        self.title = self.title.strip()
        self.artist = self.artist.strip()

    def to_dict(self):
        return {
            "id": self.id,
            "title": self.title,
            "artist": self.artist,
            "year": self.year
        }

    @classmethod
    def from_dict(cls, data):
        return cls(
            id=data["id"],
            title=data["title"],
            artist=data["artist"],
            year=data["year"]
        )
