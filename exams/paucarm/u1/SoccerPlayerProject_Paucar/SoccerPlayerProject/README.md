# Soccer Player - Python (JSON + list)

## Estructura del proyecto
```
SoccerPlayerProject/
├── main.py                      -> Menú de consola (ingreso de datos y lectura)
├── model/
│   └── soccer_player.py         -> Clase SoccerPlayer (id, name, position, shirt_number)
├── service/
│   └── soccer_player_manager.py -> Lista de jugadores + guardar/leer el JSON
├── data/
│   └── soccer_players.json      -> Archivo JSON con los jugadores
```

## Cómo ejecutarlo en VS Code
1. Descomprime el .zip y abre la carpeta `SoccerPlayerProject` en VS Code (File > Open Folder).
2. Abre la terminal (Terminal > New Terminal).
3. Ejecuta:
   ```
   python main.py
   ```
4. Opción 1: crear un jugador (se guarda en `data/soccer_players.json`).
   Opción 2: leer y mostrar los jugadores desde el JSON.
   Opción 3: salir.

No usa librerías externas, solo `json` y `os`, que vienen incluidas en Python.
