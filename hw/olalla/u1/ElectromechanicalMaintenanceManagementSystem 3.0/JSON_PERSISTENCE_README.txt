Electromechanical Maintenance Management System - corrected build

HOW TO BUILD AND RUN (Apache NetBeans / Java Ant)
1. Open this folder as an existing Java Ant project in NetBeans.
2. Run Build Project, then Run Project.
3. In a terminal opened at the project root, the main source can also be compiled with:
   javac -encoding UTF-8 -d out @sources.txt
   (Create sources.txt first with: dir /s /b src\*.java > sources.txt on Windows.)
4. Run from the project root with:
   java -cp out ec.edu.espe.electromechanicalmaintenancemanagementsystem.view.ElectromechanicalMaintenanceManagementSystem

IMPLEMENTED / FIXED
- Console menu has 15 options; option 14 now actually generates maintenance_report.csv.
- Option 15 exits cleanly.
- CSV export uses UTF-8 with BOM for Excel, quotes CSV cells, and exports the in-memory records from all current record lists, including tasks, task parts, and task safety regulations.
- Client data is saved in clients.json and loaded on startup.
- Work-order reports are saved in reports.json and loaded on startup.
- Equipment removal checks that the equipment is actually associated with the selected client. It removes the association only; it does not delete the equipment record from the whole system.
- Uses only standard Java APIs; no JSON or CSV external library is required.

IMPORTANT LIMITATIONS TO KNOW
- At present, JSON persistence is implemented for client details and generated reports only. Equipment, technicians, schedules, work orders, tasks, parts, regulations, histories, alerts and QR codes are kept in memory and are lost when the program exits. Client-equipment associations are not serialized either. If the rubric requires persistence for every type of record, more persistence work is still needed.
- CSV records are exported in two columns (Record Type and Record Data). The data column contains each object's readable toString() representation rather than one spreadsheet column per field.
- The supplied JUnit test source files refer to JUnit libraries and testdata CSV fixtures that are not included in this project ZIP. Main application source compilation and console workflow smoke tests were performed, but the existing JUnit suite cannot run as packaged until those dependencies/fixtures are restored.
