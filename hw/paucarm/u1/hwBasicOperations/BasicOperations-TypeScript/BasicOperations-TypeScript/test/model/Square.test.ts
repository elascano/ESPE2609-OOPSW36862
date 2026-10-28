/**
 * @author Paucar Matias, ByteWorks, @ESPE
 */
import { describe, test, expect, beforeEach } from "vitest";
import { Square } from "../../src/model/Square";

// Igual que assertEquals(expected, actual, delta) de JUnit
function assertEquals(expected: number, actual: number, delta: number): void {
    expect(Math.abs(expected - actual)).toBeLessThanOrEqual(delta);
}

describe("SquareTest", () => {
    let instance: Square;

    beforeEach(() => {
        instance = new Square();
    });

    test("testComputeAreaGood1", () => {
        console.log("computeArea - Good 1");
        assertEquals(4.0, instance.computeArea(2.0), 0);
    });

    test("testComputeAreaGood2", () => {
        console.log("computeArea - Good 2");
        assertEquals(25.0, instance.computeArea(5.0), 0);
    });

    test("testComputeAreaGood3", () => {
        console.log("computeArea - Good 3");
        assertEquals(144.0, instance.computeArea(12.0), 0);
    });

    test("testComputeAreaStrong1", () => {
        console.log("computeArea - Strong 1");
        assertEquals(0.0, instance.computeArea(0.0), 0);
    });

    test("testComputeAreaStrong2", () => {
        console.log("computeArea - Strong 2");
        assertEquals(6.25, instance.computeArea(2.5), 0.0001);
    });

    test("testComputeAreaStrong3", () => {
        console.log("computeArea - Strong 3");
        assertEquals(0.25, instance.computeArea(0.5), 0.0001);
    });

    test("testComputeAreaStrong4", () => {
        console.log("computeArea - Strong 4");
        assertEquals(98.01, instance.computeArea(9.9), 0.01);
    });

    test("testComputeAreaBad1", () => {
        console.log("computeArea - Bad 1");
        assertEquals(12.25, instance.computeArea(3.5), 0);
    });

    test("testComputeAreaBad2", () => {
        console.log("computeArea - Bad 2");
        assertEquals(2.251, instance.computeArea(1.5), 0);
    });

    test("testComputeAreaBad3", () => {
        console.log("computeArea - Bad 3");
        assertEquals(64.0, instance.computeArea(8.0), 0);
    });
});
