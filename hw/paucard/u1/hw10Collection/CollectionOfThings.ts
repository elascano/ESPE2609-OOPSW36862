export class Person {
    private id: number;
    private name: string;
    private salary: number;

    constructor(id: number, name: string, salary: number) {
        this.id = id;
        this.name = name;
        this.salary = salary;
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

    public toString(): string {
        return `Person{id=${this.id}, name=${this.name}, salary=${this.salary}}`;
    }
}

class MyThings {
    public static main(): void {
        console.log("===================");
        console.log("Denis Paucar");
        console.log("Collection");
        console.log("===================");


        let things: any[] = [];
        
        let person = new Person(1, "Jorge", 2851.27);
        
        console.log("person----" + things.length);
        console.log("Size of my collections at the begining is-----" + things.length);
        
        things.push(19720);
        things.push("Hello OOP programmers from Anghelo");
        things.push(1978.56);
        things.push(person);

        console.log("===================");
        console.log("Size of my collections at this point --" + things.length);
        
        let persons: Person[] = [];
        console.log("Size of my collections at this point --" + things.length);
        
        console.log("===================");
        
        persons.push(person);
        persons.push(new Person(2, "Leslie Cuichan", 1851.25));
        persons.push(new Person(3, "Dennis Criollo", 3500.37));
        
        console.log("Size of my collections of persons --> " + persons.length);

        console.log("persons --> [" + persons.map(p => p.toString()).join(", ") + "]");

        console.log("============ for each del array list");
        persons.forEach((p) => {
            console.log("person --> " + p.toString());
        });

        console.log("persons size --> " + persons.length);

        console.log("============ for ");
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
}

MyThings.main();