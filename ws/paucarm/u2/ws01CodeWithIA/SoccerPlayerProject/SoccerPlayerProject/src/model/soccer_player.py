"""Model layer: SoccerPlayer entity."""


class SoccerPlayer:
    """Represents a soccer player.

    Attributes:
        id (int): Unique identifier of the player (mandatory).
        name (str): Full name of the player.
        position (str): Position on the field (Goalkeeper, Defender,
            Midfielder, Forward).
        shirt_number (int): Number on the player's shirt (1 - 99).
    """

    VALID_POSITIONS = ("Goalkeeper", "Defender", "Midfielder", "Forward")

    def __init__(self, id: int, name: str, position: str, shirt_number: int):
        self.id = id
        self.name = name
        self.position = position
        self.shirt_number = shirt_number

    # ---------- Properties with validation ----------
    @property
    def id(self) -> int:
        return self._id

    @id.setter
    def id(self, value: int) -> None:
        if not isinstance(value, int) or value <= 0:
            raise ValueError("The id must be a positive integer.")
        self._id = value

    @property
    def name(self) -> str:
        return self._name

    @name.setter
    def name(self, value: str) -> None:
        if not isinstance(value, str) or not value.strip():
            raise ValueError("The name cannot be empty.")
        self._name = value.strip()

    @property
    def position(self) -> str:
        return self._position

    @position.setter
    def position(self, value: str) -> None:
        if value not in self.VALID_POSITIONS:
            raise ValueError(
                f"Invalid position. Options: {', '.join(self.VALID_POSITIONS)}."
            )
        self._position = value

    @property
    def shirt_number(self) -> int:
        return self._shirt_number

    @shirt_number.setter
    def shirt_number(self, value: int) -> None:
        if not isinstance(value, int) or not 1 <= value <= 99:
            raise ValueError("The shirt number must be between 1 and 99.")
        self._shirt_number = value

    # ---------- JSON conversion ----------
    def to_dict(self) -> dict:
        """Converts the object into a dictionary ready to be saved as JSON."""
        return {
            "id": self.id,
            "name": self.name,
            "position": self.position,
            "shirtNumber": self.shirt_number,
        }

    @classmethod
    def from_dict(cls, data: dict) -> "SoccerPlayer":
        """Creates a SoccerPlayer object from a dictionary read from JSON."""
        return cls(
            id=data["id"],
            name=data["name"],
            position=data["position"],
            shirt_number=data["shirtNumber"],
        )

    def __str__(self) -> str:
        return (
            f"ID: {self.id:<4} | Name: {self.name:<22} | "
            f"Position: {self.position:<10} | Shirt #: {self.shirt_number}"
        )
