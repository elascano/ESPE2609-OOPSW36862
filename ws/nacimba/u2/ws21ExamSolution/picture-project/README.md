# Picture Manager (Python)
Object: Picture (id, title, author, year). Pictures live in a list (ArrayList equivalent) and are saved/read to/from `data/pictures.json`.

## Run (Python 3.9+), from this folder
    python -m src.main

Menu: 1 Add picture, 2 List, 3 Save to JSON, 4 Read from JSON, 5 Exit.

## Structure
- src/models/picture.py - entity
- src/services/picture_repository.py - list + JSON read/write
- src/main.py - console menu
- data/pictures.json - JSON file
