"""Service layer: business rules for SoccerPlayer."""

from src.model.soccer_player import SoccerPlayer
from src.repository.json_repository import SoccerPlayerJsonRepository


class SoccerPlayerService:
    """Manages the list of players and keeps it synchronized with the JSON file."""

    def __init__(self, repository: SoccerPlayerJsonRepository):
        self.repository = repository
        self.players: list[SoccerPlayer] = self.repository.load_all()

    def id_exists(self, player_id: int) -> bool:
        return any(p.id == player_id for p in self.players)

    def create_player(self, player_id: int, name: str,
                      position: str, shirt_number: int) -> SoccerPlayer:
        """Creates a new player, adds it to the list and saves it in the JSON file."""
        if self.id_exists(player_id):
            raise ValueError(f"A player with id {player_id} already exists.")
        player = SoccerPlayer(player_id, name, position, shirt_number)
        self.players.append(player)
        self.repository.save_all(self.players)
        return player

    def read_players(self) -> list[SoccerPlayer]:
        """Reloads the players from the JSON file and returns them."""
        self.players = self.repository.load_all()
        return self.players
