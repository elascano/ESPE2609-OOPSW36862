"""Entry point of the Soccer Player program."""

import os

from src.repository.json_repository import SoccerPlayerJsonRepository
from src.service.soccer_player_service import SoccerPlayerService
from src.view.console_menu import ConsoleMenu

JSON_PATH = os.path.join(os.path.dirname(os.path.abspath(__file__)),
                         "data", "soccer_players.json")


def main() -> None:
    repository = SoccerPlayerJsonRepository(JSON_PATH)
    service = SoccerPlayerService(repository)
    ConsoleMenu(service).run()


if __name__ == "__main__":
    main()
