/** Tests sencillos sin librerías de pruebas externas. */
import { strict as assert } from "node:assert";
import { Chicken, parseISODate } from "./model/Chicken";
import { ChickenCoop } from "./model/ChickenCoop";

function makeChicken(id: number): Chicken {
    return new Chicken(id, `Chicken ${id}`, "white", parseISODate("2020-01-01"));
}

const coop = new ChickenCoop(1);
assert.equal(coop.add(makeChicken(1)), true);
assert.equal(coop.add(makeChicken(1)), false);
for (let i = 2; i <= 10; i++) assert.equal(coop.add(makeChicken(i)), true);
assert.equal(coop.add(makeChicken(11)), false);
assert.equal(coop.remove(2), true);
assert.equal(coop.getChickens()[1]?.getId(), 3);
assert.equal(coop.getChickens()[9], null);

const chicken = makeChicken(15);
chicken.setMolting(true);
const restored = Chicken.fromJSON(chicken.toJSON());
assert.equal(restored.getId(), 15);
assert.equal(restored.isMolting(), true);

console.log("All TypeScript tests passed.");
