# Diagrama de clases – SD Card

```mermaid
classDiagram
    class SDCard {
        +number id
        +number capacityGB
        +string brand
        +string speedClass
        +constructor(id, capacityGB, brand, speedClass)
    }
    class SDCardManager {
        -SDCard[] cards
        -string jsonFilePath
        +addCard(card: SDCard) void
        +getAllCards() SDCard[]
        +saveToJson() void
        +readFromJson() SDCard[]
    }
    SDCardManager "1" o-- "0..*" SDCard : administra
```

## Descripción
- **SDCard:** modelo de una tarjeta SD. `id` es obligatorio; `capacityGB`, `brand` y `speedClass` son los tres atributos adicionales.
- **SDCardManager:** mantiene la colección dinámica `SDCard[]`, evita IDs duplicados y escribe/lee los objetos en el archivo JSON.
