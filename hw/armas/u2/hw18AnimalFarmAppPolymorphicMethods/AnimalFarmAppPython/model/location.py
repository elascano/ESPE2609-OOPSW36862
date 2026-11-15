class Location:
    def __init__(self, location_id: int, description: str):
        self.location_id = location_id
        self.description = description

    def __str__(self) -> str:
        return self.description
