class Person {
    private id: number;
    private name: string;
    private salary: number;

    constructor(id: number, name: string, salary: number) {
        this.id = id;
        this.name = name;
        this.salary = salary;
    }

    public toString(): string {
        return `id=${this.id}, name=${this.name}, \tsalary=${this.salary}`;
    }

    public getId(): number {
        return this.id;
    }

    public setId(id: number): void {
        this.id = id;
    }

    public getName(): string {
        return this.name;
    }

    public setName(name: string): void {
        this.name = name;
    }

    public getSalary(): number {
        return this.salary;
    }

    public setSalary(salary: number): void {
        this.salary = salary;
    }
}

function main(): void {
    console.log("=========================");
    console.log("|  Daniel Sangoquiza's  |");
    console.log("|      Collections      |");
    console.log("=========================");

    // Equivalente a Collection things (permite cualquier tipo)
    let things: any[] = [];
    let person: Person = new Person(1, "Jorge", 2851.275);

    console.log("person --> " + person.toString());

    console.log("Size of my collections at the beginnig is --> " + things.length);

    things.push(19720);
    things.push("Hello OOP programmers from Daniel Sangoquiza ");
    things.push(4078.56);
    things.push(person);

    console.log("================");
    console.log("Size of my collections at this point --> " + things.length);
    console.log("things --> " + things);

    // Remover por valor numérico
    const indexToRemove = things.indexOf(4078.56);
    if (indexToRemove > -1) {
        things.splice(indexToRemove, 1);
    }
    
    console.log("================");
    console.log("Size of my collections at this point --> " + things.length);

    // Equivalente a Collection<Person> persons
    let persons: Person[] = [];
    console.log("Size of my collections of persons --> " + persons.length);
    
    console.log("================");
    
    persons.push(person);
    persons.push(new Person(2, "Leslie Cuichan", 1851.25));
    persons.push(new Person(3, "Dennis Criollo", 3500.37));
    console.log("Size of my collections of persons --> " + persons.length);
    
    console.log("persons --> " + persons);
    
    console.log("================");
    persons.forEach((p) => {
        console.log("person --> " + p.toString());
    });
    
    console.log("persons size --> " + persons.length);
    
    console.log("============== for ");
    for (let p of persons) {
        console.log("person --> " + p.toString());
    }
    
    for (let i = 4; i < 15; i++) {
        persons.push(new Person(i, "Daniel Sangoquiza " + i, 1000.0 + i));
    }
    
    console.log("============== for ");
    for (let p of persons) {
        console.log("person --> " + p.toString());
    }
}

main();