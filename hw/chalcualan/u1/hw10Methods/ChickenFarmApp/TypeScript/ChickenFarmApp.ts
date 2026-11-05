import { Chicken, Egg, Poop } from "./Models";
import { ChickenCoop } from "./ChickenCoop";
 
function main(): void {
    console.log("Hello, Isaac Acurio Chicken Farm App");
    console.log("------------------------------------------");
 
    // Nota: en JS/TS los meses empiezan en 0 (0 = enero, 1 = febrero...)
    const chicken = new Chicken(1, "Lucy", "brown and white", new Date(2020, 1, 28), true);
    const egg = new Egg(1, "M");
    const poop = new Poop(1, "brown", "S");
    const chicken1 = new Chicken(2, "Arturita", "white", new Date(2007, 3, 28), true);
    const chickens: (Chicken | null)[] = new Array<Chicken | null>(10).fill(null);
 
    console.log("chicken ---" + chicken);
    console.log("egg ---" + egg);
    console.log("poop---" + poop);
    console.log("chicken 2 ---" + chicken1);
 
    const localAge = chicken.computeAgeInYears();
    console.log("the age of the chicken " + chicken.name + " is " + localAge);
    chicken.doStuff();
 
    chickens[0] = new Chicken(3, "Pepita", "Gray", new Date(2020, 1, 2), true);
    chickens[1] = chicken;
    chickens[2] = chicken1;
 
    const chickenCoop = new ChickenCoop(1, chickens);
 
    for (let i = 0; i < chickens.length; i++) {
        if (i > 2) {
            chickens[i] = new Chicken(i + 1, "chicken", "black", new Date(2020 - i + 1, 3, 27), true);
        }
        console.log("chicken----" + chickens[i]);
    }
 
    // TESTING METHODS
    console.log("\n--- TESTING ITERATION WITH next() AND resetIteration() ---");
    chickenCoop.resetIteration();
 
    let current: Chicken | null;
    while ((current = chickenCoop.next()) !== null) {
        console.log(`Iterated chicken: ${current.name} (ID: ${current.id})`);
    }
 
    console.log("\n--- TESTING REMOVE (remove) ---");
    console.log("Removing chicken with ID 1 (Lucy)...");
    chickenCoop.remove(1);
 
    console.log("\n--- TESTING ADD (add) ---");
    const newChicken = new Chicken(99, "Little Chick", "Yellow", new Date(), true);
    console.log("Adding " + newChicken.name + " into available space...");
    chickenCoop.add(newChicken);
 
    console.log("\nFinal Chicken Coop status:");
    console.log(chickenCoop.toString());
}
 
main();
 