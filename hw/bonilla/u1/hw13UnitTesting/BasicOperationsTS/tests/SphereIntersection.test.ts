import { SphereIntersection } from "../src/model/SphereIntersection";

describe("SphereIntersectionTest", () => {
    const DELTA = 0.01;
    let instance: SphereIntersection;

    beforeEach(() => {
        instance = new SphereIntersection();
    });

    const assertClose = (expected: number, actual: number) => {
        expect(Math.abs(expected - actual)).toBeLessThanOrEqual(DELTA);
    };


    test("testComputeVolumeNormalCase", () => {
        assertClose(12.5664, instance.computeVolume(3, 2, 3));
    });

    test("testComputeVolumeEqualSpheres", () => {
        assertClose(35.3429, instance.computeVolume(3, 3, 3));
    });


    test("testComputeVolumeUnitValues", () => {
        assertClose(1.3090, instance.computeVolume(1, 1, 1));
    });


    test("testComputeVolumeDecimals", () => {
        assertClose(8.9012, instance.computeVolume(2.5, 1.5, 2.0));
    });


    test("testComputeVolumeLargeValues", () => {
        assertClose(1308.9969, instance.computeVolume(10, 10, 10));
    });


    test("testComputeVolumeSmallValues", () => {
        assertClose(0.1636, instance.computeVolume(0.5, 0.5, 0.5));
    });


    test("testComputeVolumeZeroRadius", () => {
        assertClose(0.0, instance.computeVolume(0, 5, 2));
    });

    test("testComputeVolumeAsymmetricSpheres", () => {
        assertClose(25.9181, instance.computeVolume(4, 2, 3));
    });


    test("testComputeVolumeIsCommutative", () => {
        assertClose(
            instance.computeVolume(4, 2, 3),
            instance.computeVolume(2, 4, 3)
        );
    });


    test("testComputeVolumeNegativeValue", () => {
        expect(() => {
            instance.computeVolume(-3, 2, 3);
        }).toThrow("Radii and distance cannot be negative");
    });
});