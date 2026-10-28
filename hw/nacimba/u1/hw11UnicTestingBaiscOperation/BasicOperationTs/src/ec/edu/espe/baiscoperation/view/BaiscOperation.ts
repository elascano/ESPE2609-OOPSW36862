import { Operation } from "../model/Operation";
import { formatFloat } from "../../../../../utils/javaFloat";

/** @author Nacimba Mateo, Group5, @ESPE */
function main(): void {
  console.log("operation");
  const operation = new Operation();
  console.log("the addition of 2 andd 3 is -->" + formatFloat(operation.add(1.2, 2.4)));
  console.log("the addition of 2 andd 3 is -->" + formatFloat(operation.add(1.2, 2.4)));
  console.log("the addition of 2 andd 3 is -->" + formatFloat(operation.add(1.2, 2.4)));

  console.log("divide -->" + formatFloat(operation.divide(1, 0)));
}

main();
