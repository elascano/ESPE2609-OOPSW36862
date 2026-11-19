class Location:
    def __init__(self, id: int, description: str):
        self.id = id
        self.description = description

    def __str__(self):
        return f"Location{{id={self.id}, description={self.description}}}"