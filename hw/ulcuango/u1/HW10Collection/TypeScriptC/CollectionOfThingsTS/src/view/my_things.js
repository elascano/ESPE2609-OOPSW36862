"use strict";
Object.defineProperty(exports, "__esModule", { value: true });
exports.MyThings = void 0;
const person_1 = require("../model/person");
class MyThings {
    static main() {
        console.log("===========================");
        console.log("|    Josselyn ULcuango    |");
        console.log("|        Collections      |");
        console.log("===========================");
        const things = [];
        const person = new person_1.Person(1, "Jorge", 2851.27);
        console.log("person -->", person.toString());
        console.log("Size of my collections at the beginning is -->", things.length);
        things.push(19720);
        things.push("Hello OOP programmers from Josselyn");
        things.push(4878.56);
        things.push(person);
        console.log("=================================");
        console.log("Size of my collections at this point-->", things.length);
        console.log("things -->", things);
        const index = things.indexOf(4078.56);
        if (index > -1) {
            things.splice(index, 1);
        }
        console.log("===================");
        console.log("Size of my collections at this point-->", things.length);
        const persons = [];
        console.log("Size of my collections of persons -->", persons.length);
        console.log("==============");
        persons.push(person);
        persons.push(new person_1.Person(2, "Leslie Cuichan", 1851.25));
        persons.push(new person_1.Person(3, "Dennis Criollo", 3500.37));
        console.log("Size of my collections of persons -->", persons.length);
        console.log("persons -->", persons);
        console.log("==============");
        persons.forEach((p) => {
            console.log("person -->", p.toString());
        });
        console.log("persons size-->", persons.length);
        console.log("============== for");
        for (const p of persons) {
            console.log("person -->", p.toString());
        }
        for (let i = 4; i < 15; i++) {
            persons.push(new person_1.Person(i, `Josselyn${i}`, 100.0 + i));
            console.log("============== for ");
            for (const p of persons) {
                console.log("person -->", p.toString());
            }
        }
    }
}
exports.MyThings = MyThings;
MyThings.main();
