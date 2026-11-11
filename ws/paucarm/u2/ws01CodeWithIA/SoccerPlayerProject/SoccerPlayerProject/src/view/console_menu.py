"""View layer: console menu to interact with the user."""

from src.model.soccer_player import SoccerPlayer
from src.service.soccer_player_service import SoccerPlayerService


class ConsoleMenu:
    """Text-based user interface."""

    def __init__(self, service: SoccerPlayerService):
        self.service = service

    def run(self) -> None:
        while True:
            self._show_menu()
            option = input("Choose an option: ").strip()
            if option == "1":
                self._create_player()
            elif option == "2":
                self._read_players()
            elif option == "3":
                print("Goodbye!")
                break
            else:
                print("Invalid option, try again.")

    @staticmethod
    def _show_menu() -> None:
        print("\n========== SOCCER PLAYER MANAGER ==========")
        print("1. Create soccer player")
        print("2. Read soccer players from JSON file")
        print("3. Exit")
        print("===========================================")

    # ---------- Input helpers ----------
    @staticmethod
    def _read_int(message: str) -> int:
        while True:
            try:
                return int(input(message))
            except ValueError:
                print("Please enter a valid integer.")

    @staticmethod
    def _read_position() -> str:
        positions = SoccerPlayer.VALID_POSITIONS
        for index, position in enumerate(positions, start=1):
            print(f"   {index}. {position}")
        while True:
            choice = input("Select the position (1-4): ").strip()
            if choice.isdigit() and 1 <= int(choice) <= len(positions):
                return positions[int(choice) - 1]
            print("Invalid position, try again.")

    # ---------- Actions ----------
    def _create_player(self) -> None:
        print("\n--- New Soccer Player ---")
        try:
            player_id = self._read_int("Id: ")
            name = input("Name: ")
            position = self._read_position()
            shirt_number = self._read_int("Shirt number (1-99): ")
            player = self.service.create_player(player_id, name,
                                                position, shirt_number)
            print(f"Player saved successfully -> {player}")
        except ValueError as error:
            print(f"Error: {error}")

    def _read_players(self) -> None:
        players = self.service.read_players()
        print("\n--- Soccer Players in JSON file ---")
        if not players:
            print("There are no players registered.")
            return
        for player in players:
            print(player)
        print(f"Total players: {len(players)}")
