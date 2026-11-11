# Soccer Player - JSON CRUD (Create & Read) - Python

**Object:** Soccer Player (roster #18)
**Language:** Python 3.10+

## Attributes
| Attribute     | Type | Description                                   |
|---------------|------|-----------------------------------------------|
| id            | int  | Unique identifier (mandatory)                 |
| name          | str  | Full name of the player                       |
| position      | str  | Goalkeeper / Defender / Midfielder / Forward  |
| shirt_number  | int  | Shirt number (1 - 99)                         |

## Project structure
```
SoccerPlayerProject/
├── main.py                         # Entry point
├── data/
│   └── soccer_players.json         # JSON file (persistence)
├── docs/
│   ├── class_diagram.png           # UML class diagram
│   └── class_diagram.dot           # Diagram source (Graphviz)
└── src/
    ├── model/soccer_player.py      # SoccerPlayer entity (validation, to_dict / from_dict)
    ├── repository/json_repository.py  # Reads / writes the JSON file
    ├── service/soccer_player_service.py  # Business logic, list (ArrayList) of players
    └── view/console_menu.py        # Console menu (user input)
```

## How to run
From the project folder:
```
python main.py
```
Menu options:
1. Create soccer player (saved in `data/soccer_players.json`)
2. Read soccer players from the JSON file
3. Exit
