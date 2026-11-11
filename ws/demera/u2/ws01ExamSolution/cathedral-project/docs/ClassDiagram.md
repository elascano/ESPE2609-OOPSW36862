# Cathedral Class Diagram

```
+-------------------------------------------------------+
|                       Cathedral                       |
+-------------------------------------------------------+
| - id: string                                          |
| - name: string                                        |
| - location: string                                    |
| - yearBuilt: number                                   |
+-------------------------------------------------------+
| + constructor(id: string, name: string,               |
|               location: string, yearBuilt: number)    |
| + getId(): string                                     |
| + getName(): string                                   |
| + getLocation(): string                               |
| + getYearBuilt(): number                              |
| + toJSON(): object                                    |
| + static fromJSON(obj: any): Cathedral                |
+-------------------------------------------------------+
```
