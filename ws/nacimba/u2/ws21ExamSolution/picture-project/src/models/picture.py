class Picture:
    """Picture entity: id (mandatory) + three more attributes."""

    def __init__(self, id: int, title: str, author: str, year: int):
        self.id = id
        self.title = title
        self.author = author
        self.year = year

    def to_dict(self) -> dict:
        return {"id": self.id, "title": self.title, "author": self.author, "year": self.year}

    @staticmethod
    def from_dict(data: dict) -> "Picture":
        return Picture(int(data["id"]), str(data["title"]), str(data["author"]), int(data["year"]))

    def __str__(self) -> str:
        return f"Picture #{self.id} | {self.title} | {self.author} | {self.year}"
