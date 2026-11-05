import os

from model.soccer_player import SoccerPlayer
from service.soccer_player_manager import SoccerPlayerManager

BASE_DIRECTORY = os.path.dirname(os.path.abspath(__file__))
JSON_FILE = os.path.join(BASE_DIRECTORY, "data", "soccer_players.json")


def read_integer(message):
    while True:
        value = input(message).strip()
        if value.isdigit():
            return int(value)
        print("Error: you must enter a positive integer.")


def read_text(message):
    while True:
        value = input(message).strip()
        if value != "":
            return value
        print("Error: this field cannot be empty.")


def create_player(manager):
    print("\n--- CREATE SOCCER PLAYER ---")
    id = read_integer("Id: ")
    if manager.exists_id(id):
        print("Error: a soccer player with id " + str(id) + " already exists.")
        return
    name = read_text("Name: ")
    position = read_text("Position: ")
    shirt_number = read_integer("Shirt number: ")

    player = SoccerPlayer(id, name, position, shirt_number)
    manager.add_player(player)
    manager.save_to_json()
    print("Soccer player saved in " + JSON_FILE)


def show_players(manager):
    print("\n--- SOCCER PLAYERS READ FROM JSON FILE ---")
    manager.read_from_json()
    players = manager.get_players()
    if len(players) == 0:
        print("There are no soccer players registered.")
        return
    for player in players:
        print(player)
    print("Total: " + str(len(players)) + " soccer player(s)")


def main():
    manager = SoccerPlayerManager(JSON_FILE)
    manager.read_from_json()

    option = 0
    while option != 3:
        print("\n===== SOCCER PLAYER MENU =====")
        print("1. Create soccer player")
        print("2. Read soccer players from JSON")
        print("3. Exit")
        option = read_integer("Choose an option: ")

        if option == 1:
            create_player(manager)
        elif option == 2:
            show_players(manager)
        elif option == 3:
            print("Goodbye!")
        else:
            print("Invalid option.")


if __name__ == "__main__":
    main()
