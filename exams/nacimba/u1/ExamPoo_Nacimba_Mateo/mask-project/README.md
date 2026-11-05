# Mask Manager (TypeScript)
Object: Mask (id, name, material, price). Masks live in an array (ArrayList equivalent) and are saved/read to/from `data/masks.json`.

## Run
    npm install
    npm start

Menu: 1 Add mask, 2 List, 3 Save to JSON, 4 Read from JSON, 5 Exit.

## Structure
- src/models/Mask.ts – entity
- src/services/MaskRepository.ts – array + JSON read/write
- src/main.ts – console menu
- data/masks.json – JSON file
