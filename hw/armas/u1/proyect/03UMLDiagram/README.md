ESPE2609-OOPSW36862

ESPE 26 09 - Object Oriented Programming SW 36862

\## Class Diagram

The UML class diagram defines the static structure of the "Liquor Store" system. 

\* It shows core entities such as `Sale`, `Product`, `Customer`, `PurchaseOrder`, `Supplier`, and `DailyCashRecord` along with their respective attributes and methods (e.g., `calculateTotal()` in sales or `increaseStock()` in products). 

\* It illustrates multiplicity relationships, composition, and structural dependencies using stereotypes like `<<use>>` and `<<create>>` to connect sales, inventory, and financial operations.



\### Use Case Diagram

The use case diagram models the system's functions based on the roles of three main actors: Owner, Cashier, and Administrator.

\* \*\*Owner:\*\* Interacts with control functions such as receiving low stock alerts, reviewing credit payment statuses, generating best-selling products reports, reviewing sales by employee, generating daily closure reports, and configuring product profit margins.

\* \*\*Cashier:\*\* Is responsible for processing sales, an action that internally includes applying active promotions and discounts, as well as displaying an age verification reminder.

\* \*\*Administrator:\*\* Manages suppliers and pending orders, recording the receipt of supplier deliveries which, in turn, automatically updates the inventory.

