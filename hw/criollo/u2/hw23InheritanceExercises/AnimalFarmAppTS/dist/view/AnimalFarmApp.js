"use strict";
Object.defineProperty(exports, "__esModule", { value: true });
const FarmAnimal_1 = require("../model/FarmAnimal");
const Chicken_1 = require("../model/Chicken");
const Cow_1 = require("../model/Cow");
const Pig_1 = require("../model/Pig");
const Sheep_1 = require("../model/Sheep");
function main() {
    const farmAnimal = new FarmAnimal_1.FarmAnimal(1, "Holstein", new Date("2024-12-29"), false);
    const chicken = new Chicken_1.Chicken(2, "Palish", new Date("2022-10-10"), false, false, 10);
    const cow = new Cow_1.Cow(3, "Holstein", new Date("2024-12-23"), false, true, 17.8);
    const pig = new Pig_1.Pig(4, "Mangalitsa", new Date("2019-11-30"), false, true, 18.75);
    const sheep = new Sheep_1.Sheep(5, "Manchega", new Date("2025-11-24"), false, true, new Date("2024-09-27"));
    console.log("Dennis's Animal Farm");
    console.log("=======================");
    console.log(farmAnimal);
    console.log(chicken);
    console.log(cow);
    console.log(pig);
    console.log(sheep);
}
main();
