/**
 * @author Mateo, Group5, @ESPE
 */
import { Chicken } from '../model/Chicken';
import { Cow } from '../model/Cow';
import { FarmAnimal } from '../model/FarmAnimal';
import { Pig } from '../model/Pig';
import { Sheep } from '../model/Sheep';

function main(): void {
  console.log("Hello This is Mateo's Animal Farm!");
  console.log('==================================');

  let farmAnimal: FarmAnimal;
  farmAnimal = new FarmAnimal(1, 'holstein', new Date());

  console.log('my farm animal --> ' + farmAnimal);

  const chicken = new Chicken(true, 10, true, 2, 'Paleesh', new Date());
  console.log('chicken --> ' + chicken);

  const cow = new Cow(true, 100.5, 3, 'holstein', new Date());
  console.log('cow --> ' + cow);

  // En TypeScript (igual que en Java) el mes empieza en 0: 11 = diciembre
  const pig = new Pig(true, 30.23, 4, 'Mangalitsa', new Date(2019, 11, 30));
  console.log('pig --> ' + pig);

  const sheep = new Sheep(true, new Date(2026, 10, 20), 5, 'Merino', new Date(2014, 11, 25));
  console.log('sheep --> ' + sheep);
}

main();
