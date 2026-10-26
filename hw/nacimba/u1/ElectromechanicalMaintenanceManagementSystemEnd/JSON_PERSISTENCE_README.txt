JSON persistence added to this Java Ant project

How to use
1. Open the project folder in Apache NetBeans.
2. Run the project normally (Java Ant; no Maven conversion is needed).
3. Register a client using option 1. The program creates/updates clients.json in the project's working directory.
4. Close and run the program again. Previously saved clients are loaded automatically.

Important scope
- This version persists CLIENT records (ID, name, contact information, address) in JSON.
- Other records (equipment, technicians, orders, tasks, etc.) are still kept in memory and are not yet persisted.
- The implementation uses standard Java classes and does not require Gson or any extra JAR.
- The JsonFileManager package is ec.edu.espe.electromechanicalsystem.controller, as requested.
