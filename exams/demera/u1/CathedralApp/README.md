# Cathedral App (TypeScript)

Creates and reads `Cathedral` objects using an in-memory array (ArrayList equivalent) and a JSON file.

## Run
```
npm install
npm start
```
Data is stored in `data/cathedrals.json`.

## Structure
- `model/` Cathedral entity
- `repository/` in-memory array + persistence
- `storage/` JSON file read/write
- `view/` console menu and input
