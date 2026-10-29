import { test } from "node:test";
import { strict as assert } from "node:assert";
import { RectanglePerimeter } from "./RectanglePerimeter";

test("testCalculatePerimeter1", () => {
    const base = 4;
    const height = 3;
    const instance = new RectanglePerimeter();
    const expResult = 14;
    const result = instance.calculatePerimeter(base, height);
    assert.equal(result, expResult);
});

test("testCalculatePerimeter2", () => {
    const base = 5;
    const height = 2;
    const instance = new RectanglePerimeter();
    const expResult = 14;
    const result = instance.calculatePerimeter(base, height);
    assert.equal(result, expResult);
});

test("testCalculatePerimeter3", () => {
    const base = 6;
    const height = 4;
    const instance = new RectanglePerimeter();
    const expResult = 20;
    const result = instance.calculatePerimeter(base, height);
    assert.equal(result, expResult);
});

test("testCalculatePerimeter4", () => {
    const base = 8;
    const height = 3;
    const instance = new RectanglePerimeter();
    const expResult = 22;
    const result = instance.calculatePerimeter(base, height);
    assert.equal(result, expResult);
});

test("testCalculatePerimeter5", () => {
    const base = 1;
    const height = 1;
    const instance = new RectanglePerimeter();
    const expResult = 4;
    const result = instance.calculatePerimeter(base, height);
    assert.equal(result, expResult);
});

test("testCalculatePerimeter6", () => {
    const base = 0.5;
    const height = 0.5;
    const instance = new RectanglePerimeter();
    const expResult = 2;
    const result = instance.calculatePerimeter(base, height);
    assert.equal(result, expResult);
});

test("testCalculatePerimeter7", () => {
    const base = 1000;
    const height = 2000;
    const instance = new RectanglePerimeter();
    const expResult = 6000;
    const result = instance.calculatePerimeter(base, height);
    assert.equal(result, expResult);
});

test("testCalculatePerimeter8", () => {
    const base = 1;
    const height = 100;
    const instance = new RectanglePerimeter();
    const expResult = 202;
    const result = instance.calculatePerimeter(base, height);
    assert.equal(result, expResult);
});

test("testCalculatePerimeter9", () => {
    const base = 7;
    const height = 3;
    const instance = new RectanglePerimeter();
    const expResult = 20;
    const result = instance.calculatePerimeter(base, height);
    assert.equal(result, expResult);
});

test("testCalculatePerimeter10", () => {
    const base = 9;
    const height = 2;
    const instance = new RectanglePerimeter();
    const expResult = 22;
    const result = instance.calculatePerimeter(base, height);
    assert.equal(result, expResult);
});

