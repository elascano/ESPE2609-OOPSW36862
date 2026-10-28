/**
 * @author Enrique Ayala, Byte and LogicalLords, @ESPE
 */

import { Person } from "./person.js";

function main(): void {
  console.log("==================");
  console.log("Enrique Ayala's");
  console.log("    Collection    ");
  console.log("==================");

  const things: any[] = [];
  const person = new Person(1, "Jorge Lascano", 2851.27);

  console.log(`size of my collection at the beggining is -------${things.length}`);

  things.push(1920);
  things.push("Hello OOP programmers from Edison");
  things.push(4078.56);
  things.push(person);

  console.log(`Size of my collections at the begginning is-->${things.length}`);

  const formattedThings = things.map((item) => String(item));
  console.log(`things-->[${formattedThings.join(", ")}]`);

  const index = things.indexOf(4078.56);
  if (index !== -1) {
    things.splice(index, 1);
  }

  console.log("========");
  console.log(`Size of my collections at this point--->${things.length}`);

  const persons: Person[] = [];
  console.log(`Size of my collections of persons-->${persons.length}`);

  persons.push(person);
  persons.push(new Person(2, "Leslie Cuicahan", 1851.25));
  persons.push(new Person(3, "Dennis Criollo", 3050.37));

  const formattedPersons = persons.map((p) => String(p));
  console.log(`persons-->[${formattedPersons.join(", ")}]`);

  console.log("=============");
  for (const p of persons) {
    console.log(`person -->${p}`);
  }

  console.log(`persons size -->${persons.length}`);

  console.log("=========== for");
  for (const p of persons) {
    console.log(`person -->${p}`);
  }

  for (let i = 0; i < 15; i++) {
    persons.push(new Person(i, `Edison${i}`, 1000.0 + i));
  }

  console.log("=========== for");
  for (const p of persons) {
    console.log(`person -->${p}`);
  }
}

   main();