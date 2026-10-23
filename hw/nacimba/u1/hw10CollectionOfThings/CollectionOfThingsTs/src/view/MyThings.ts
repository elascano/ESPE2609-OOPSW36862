import { Person } from "../model/Person";

console.log("======================");
console.log("|   Mateo Nacimba´s  |");
console.log("|     Collections    |");
console.log("======================");

const things: (number | string | Person)[] = [];
const person = new Person(1, "jirge", 2851.57);

console.log("person --->" + person);
console.log("Size of my collections at the begining is ---> " + things.length);

things.push(19720);
things.push("Hello OPP programmers from Mateo Nacimba");
things.push(1078.56);
things.push(person);

console.log("================");
console.log("size of my collections at this point-->" + things.length);

const persons: Person[] = [];
console.log("Size of my collections of person -->" + persons.length);

persons.push(person);
persons.push(new Person(2, "Leslie Cuichan", 1851.23));
persons.push(new Person(3, "Dennis criollo", 3500.23));

console.log("Size of my collection of persons -->" + persons.length);
console.log("persons -->" + persons.map((p) => p.toString()).join(", "));
console.log("================");

persons.forEach((p) => {
  console.log("person -->" + p);
});

console.log("persons size -->" + persons.length);
console.log("================ for ");
for (const p of persons) {
  console.log("person -->" + p);
}

for (let i = 4; i < 15; i++) {
  const newPerson = new Person(i, "Mateo" + i, 1880.0 + i);
  persons.push(newPerson);
  console.log("person--> " + newPerson);
}
