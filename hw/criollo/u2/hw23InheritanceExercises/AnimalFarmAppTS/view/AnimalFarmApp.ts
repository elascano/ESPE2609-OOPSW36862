import {FarmAnimal} from "../model/FarmAnimal";
import {Chicken} from "../model/Chicken";
import {Cow} from "../model/Cow";
import {Pig} from "../model/Pig";
import {Sheep} from "../model/Sheep";

function main() : void{
    const farmAnimal = new FarmAnimal(1, "Holstein", new Date("2024-12-29"), false);
    const chicken = new Chicken(2, "Palish", new Date("2022-10-10"), false, false, 10);
    const cow = new Cow(3, "Holstein", new Date("2024-12-23"), false, true, 17.8);
    const pig = new Pig(4, "Mangalitsa", new Date("2019-11-30"), false, true, 18.75);
    const sheep = new Sheep(5,"Manchega", new Date("2025-11-24"), false, true, new Date("2024-09-27"));
    
    console.log("Dennis's Animal Farm");
    console.log("=======================");
    console.log(farmAnimal);
    console.log(chicken);
    console.log(cow);
    console.log(pig);
    console.log(sheep);
} 
main();