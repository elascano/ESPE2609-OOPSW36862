# Vehicle Parts (Python)

Creates and reads `VehiclePart` objects (mandatory id + name, brand, price)
using a list in memory and a JSON file.

Run (Python 3.9+), from this folder:

    python main.py

Structure:

    main.py                     entry point
    model/                      VehiclePart, VehiclePartCatalog (list)
    persistence/                VehiclePartFileManager (JSON)
    view/                       Menu (data input and output)
    data/vehicle_parts.json     JSON file
    docs/class_diagram.png      class diagram
