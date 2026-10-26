import { Chicken } from '../model/Chicken';
import { ChickenCoop } from '../model/ChickenCoop';
import { ChickenFarmer } from '../model/ChickenFarmer';
import { Egg } from '../model/Egg';
import { Poop } from '../model/Poop';

function main() {
    console.log("=== BIENVENIDO A CHICKEN FARM APP (TYPESCRIPT VERSION) ===");

    const farmer = new ChickenFarmer(1, "Juan Perez", "Senior");
    console.log(farmer.toString());

    const chicken1 = new Chicken(1, "Lucas", "Blanco", 1, false, 101, new Date('2025-05-10'));
    const chicken2 = new Chicken(2, "Maruja", "Rojo", 2, true, 101, new Date('2024-03-15'));

    const coop = new ChickenCoop(101);
    coop.addChicken(chicken1);
    coop.addChicken(chicken2);

    coop.eggs.push(new Egg(1, "Blanco"));
    coop.poops.push(new Poop(1, 45.5));

    console.log("\n--- Estado del Gallinero ---");
    console.log(coop.toString());
    coop.chickens.forEach(c => {
        console.log(` - ${c.toString()}`);
    });
}

main();
