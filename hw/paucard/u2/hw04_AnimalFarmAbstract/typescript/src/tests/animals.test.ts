import { test } from "node:test";
import assert from "node:assert/strict";
import { Chicken, Cow, FarmAnimal, Pig, Sheep } from "../model";

const d = (y: number, m: number, day: number) => new Date(y, m - 1, day);

test("las subclases son FarmAnimal", () => {
  const animals: FarmAnimal[] = [
    new Chicken(false, 10, 1, "a", d(2022, 10, 10)),
    new Cow(true, 1.5, 2, "b", d(2022, 10, 10), false),
    new Pig(true, 1.5, 3, "c", d(2022, 10, 10), false),
    new Sheep(true, d(2025, 1, 1), 4, "d", d(2022, 10, 10), true),
  ];
  for (const a of animals) assert.ok(a instanceof FarmAnimal);
});

test("toString de Cow", () => {
  const cow = new Cow(true, 23.5, 3, "Holstein", d(2024, 12, 23), false);
  assert.equal(
    cow.toString(),
    "Cow{FarmAnimal{id=3, breed=Holstein, is meat=false, bornOnDate=23 / 12 / 2024}, " +
      "isProducingMilk=true, litersOfMilkPerWeek=23.5}"
  );
});

test("Chicken envía isMolting como isMeat (igual que el original)", () => {
  assert.equal(new Chicken(true, 5, 1, "x", d(2022, 10, 10)).isMeat, true);
});

test("getters y setters", () => {
  const pig = new Pig(true, 1.0, 1, "x", d(2022, 1, 1), false);
  pig.weightGainPerWeek = 2.5;
  pig.breed = "y";
  assert.equal(pig.weightGainPerWeek, 2.5);
  assert.equal(pig.breed, "y");
});
