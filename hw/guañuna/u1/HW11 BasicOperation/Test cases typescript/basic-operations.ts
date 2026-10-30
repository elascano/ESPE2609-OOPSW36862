import assert from 'node:assert';

class Operation {
    public static calculateSphereArea(radius: number): number {
        // Validar NaN (Double.NaN en Java)
        if (Number.isNaN(radius)) return 0;
        if (radius < 0) return 0;

        let area = 4 * Math.PI * Math.pow(radius, 2);
        area = Math.round(area * 1000.0) / 1000.0;
        return area;
    }

    public add(a: number, b: number): number {
        let sum = a + b;
        return Math.round(sum * 100.0) / 100.0;
    }

    public substract(a: number, b: number): number {
        return a - b;
    }

    public multiply(a: number, b: number): number {
        return a * b;
    }

    public divide(dividend: number, divisor: number): number {
        if (divisor === 0) throw new Error("No se puede dividir para cero");
        return dividend / divisor;
    }
}

function runTests() {
    console.log("🚀 Iniciando pruebas...\n");

    try {
        // Test 1: Número entero
        assert.strictEqual(Operation.calculateSphereArea(2), 50.265);
        console.log("✅ testCalculateSphereAreaNumber PASÓ");

        // Test 2: Decimal
        assert.strictEqual(Operation.calculateSphereArea(4.5), 254.469);
        console.log("✅ testCalculateSphereAreaDecimal PASÓ");

        // Test 3: Negativo
        assert.strictEqual(Operation.calculateSphereArea(-7), 0);
        console.log("✅ testCalculateSphereAreaNegative PASÓ");

        // Test 4: Número grande
        assert.strictEqual(Operation.calculateSphereArea(800), 8042477.193);
        console.log("✅ testCalculateSphereAreaLongNumber PASÓ");

        // Test 5: Decimal largo
        assert.strictEqual(Operation.calculateSphereArea(5.36982), 362.351);
        console.log("✅ testCalculateSphereAreaLongDecimal PASÓ");

        // Test 6: Letra 'r' (ASCII 114)
        assert.strictEqual(Operation.calculateSphereArea(114), 163312.553);
        console.log("✅ testCalculateSphereAreaLetter PASÓ");

        // Test 7: Cero
        assert.strictEqual(Operation.calculateSphereArea(0.0), 0.0);
        console.log("✅ testCalculateSphereAreaNumber0 PASÓ");

        // Test 8: Potencia
        assert.strictEqual(Operation.calculateSphereArea(Math.pow(1, 3)), 12.566);
        console.log("✅ testCalculateSphereAreaNumberPowered PASÓ");

        // Test 9: Pi (Unicode 960)
        assert.strictEqual(Operation.calculateSphereArea(960), 11581167.158);
        console.log("✅ testCalculateSphereAreaPi PASÓ");

        // Test 10: NaN
        assert.strictEqual(Operation.calculateSphereArea(NaN), 0);
        console.log("✅ testCalculateSphereAreaNull PASÓ");

        console.log("\n🎉 ¡TODAS LAS PRUEBAS PASARON CON ÉXITO! (10/10)");

    } catch (error) {
        console.error("\n❌ ALGUNA PRUEBA FALLÓ:");
        console.error(error);
    }
}

// Ejecutar las pruebas
runTests();