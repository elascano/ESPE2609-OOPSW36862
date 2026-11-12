# Microphone Manager (TypeScript)

Creates and reads `Microphone` objects (id, brand, type, connection)
using an Array (the ArrayList equivalent) and one JSON file.

Requirements: Node.js 18+

Run:
    npm install
    npm start

Structure:
- `src/models` - Microphone class (validation, encapsulation)
- `src/repositories` - JSON read/write
- `src/services` - business rules (in-memory array, duplicate ids)
- `src/ui` - console menu
- `data/microphones.json` - stored data
