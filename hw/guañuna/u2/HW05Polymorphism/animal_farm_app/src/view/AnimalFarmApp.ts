import { Chicken } from '../model/Chicken';
import { Cow } from '../model/Cow';
import { Pig } from '../model/Pig';
import { Sheep } from '../model/Sheep';
import { Food } from '../model/Food';
import { Location } from '../model/Location';

function main(): void {
    console.log("Hello. This is Cristopher Animal Farm\n");

    const bornOnDate = new Date(2020, 9, 10); // Octubre es mes 9 en JS
    const corn = new Food("corn");
    const grass = new Food("grass");
    const northPasture = new Location(1, "North Pasture");

    // 1. CHICKEN
    const chicken = new Chicken(true, 10, true, 2, "Paleesh", bornOnDate);
    console.log(`Chicken --> ${chicken.toString()}`);
    chicken.feed(corn, 2);
    chicken.senToGraze(northPasture, new Date());
    chicken.layAnEgg();
    chicken.layAnEgg(5);
    console.log();

    // 2. COW
    const cow = new Cow(true, 100.5, 3, "holstein", bornOnDate);
    console.log(`Cow --> ${cow.toString()}`);
    cow.feed(grass, 5);
    cow.senToGraze(northPasture, new Date());

    cow.milk();
    cow.milk(3.2);
    cow.milk(100);
    cow.milk(2.5, new Date());
    console.log(`Total liters of milk per week: ${cow.getLitersOfMilkPerWeek()}`);
    console.log();

    // 3. PIG
    const pig = new Pig(85.5, true, 4, "Duroc", bornOnDate);
    console.log(`Pig --> ${pig.toString()}`);
    pig.feed(corn, 10);
    pig.senToGraze(northPasture, new Date());
    pig.eat();
    pig.eat(5.5);
    console.log();

    // 4. SHEEP
    const sheep = new Sheep(bornOnDate, 5.5, 5, "Merino", bornOnDate);
    console.log(`Sheep --> ${sheep.toString()}`);
    sheep.feed(grass, 3);
    sheep.senToGraze(northPasture, new Date());
    sheep.shear();
    sheep.shear(2.5);
    console.log();
}

main();