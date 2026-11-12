import { Chicken } from '../model/Chicken';
import { Cow } from '../model/Cow';
import { FarmAnimal } from '../model/FarmAnimal';

function main(): void {
  console.log("Hello This is Mateo's Animal Farm!");

  const farmAnimal: FarmAnimal = new FarmAnimal(1, 'holstein', new Date());
  console.log('my farm animal --> ' + farmAnimal);

  const chicken = new Chicken(true, 10, true, 2, 'Paleesh', new Date());
  console.log('chicken --> ' + chicken);

  const cow: FarmAnimal = new Cow(true, 100.5, 3, 'holstein', new Date());
  console.log('cow --> ' + cow);
}

main();
