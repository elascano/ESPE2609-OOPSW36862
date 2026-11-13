export class Cathedral {
    private id: string;
    private name: string;
    private location: string;
    private yearBuilt: number;

    constructor(id: string, name: string, location: string, yearBuilt: number) {
        this.id = id;
        this.name = name;
        this.location = location;
        this.yearBuilt = yearBuilt;
    }

    public getId(): string {
        return this.id;
    }

    public getName(): string {
        return this.name;
    }

    public getLocation(): string {
        return this.location;
    }

    public getYearBuilt(): number {
        return this.yearBuilt;
    }

    public toJSON(): object {
        return {
            id: this.id,
            name: this.name,
            location: this.location,
            yearBuilt: this.yearBuilt
        };
    }

    public static fromJSON(obj: any): Cathedral {
        return new Cathedral(obj.id, obj.name, obj.location, obj.yearBuilt);
    }
}
