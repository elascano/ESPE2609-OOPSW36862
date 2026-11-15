class Location:
    def __init__(self, description: str):
        self.description = description

    def __str__(self) -> str:
        return f"Location{{description={self.description}}}"
