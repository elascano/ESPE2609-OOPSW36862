/**
 * @author Paucar Matias, ByteWorks, @ESPE
 */
import { Person } from "../model/Person";

// Imprime la colección igual que el toString() de ArrayList en Java
function toString(collection: unknown[]): string {
    return "[" + collection.map((item) => String(item)).join(", ") + "]";
}

// Equivalente a Collection.remove(obj): elimina la primera coincidencia
function remove(collection: unknown[], item: unknown): boolean {
    const index = collection.indexOf(item);
    if (index === -1) {
        return false;
    }
    collection.splice(index, 1);
    return true;
}

function main(): void {
    console.log("=======================");
    console.log("|   Matias Paucar's   |");
    console.log("|     Collections     |");
    console.log("=======================");

    let things: unknown[];
    let person: Person;
    things = [];
    person = new Person(1, "Matias Paucar", 2851.27);

    console.log("person --> " + person);

    console.log("Size of my collection at the beginning -->" + things.length);

    things.push(19720);
    things.push("Hello OOP programmers from Matias Paucar");
    things.push(4078.56);
    things.push(person);
    console.log("=============");
    console.log("Size of my collection at this point -->" + things.length);
    console.log("things -->" + toString(things));

    remove(things, 4078.56);
    console.log("=============");
    console.log("Size of my collection at this point -->" + things.length);

    let persons: Person[];
    persons = [];
    console.log("Size of my collection of persons -->" + persons.length);

    console.log("=============");

    persons.push(person);
    persons.push(new Person(2, "Leslie Cuichan", 1851.25));
    persons.push(new Person(3, "Dennis Criollo", 3500.37));
    console.log("Size of my collection of persons -->" + persons.length);

    console.log("persons ->" + toString(persons));

    console.log("======== for each of array List");
    persons.forEach((p) => {
        console.log("person --> " + p);
    });

    console.log("persons size -->" + persons.length);

    console.log("======== for");
    for (const p of persons) {
        console.log("person-->" + p);
    }

    for (let i = 4; i < 15; i++) {
        persons.push(new Person(i, "Matias " + i, 1000.0 + i));
    }
    console.log("======== for");
    for (const p of persons) {
        console.log("person-->" + p);
    }
}

main();
