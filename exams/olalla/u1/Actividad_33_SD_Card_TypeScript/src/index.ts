import * as path from "path";
import { SDCard } from "./SDCard";
import { SDCardManager } from "./SDCardManager";

const jsonPath = path.resolve(__dirname, "../data/sd_cards.json");
const manager = new SDCardManager(jsonPath);

function main(): void {
  console.log("=== ACTIVIDAD 33: TARJETAS SD ===");

  // Entrada de datos: se crean objetos SDCard y se guardan en el arreglo dinámico.
  manager.addCard(new SDCard(1, 32, "SanDisk", "Class 10"));
  manager.addCard(new SDCard(2, 64, "Kingston", "UHS-I U1"));
  manager.addCard(new SDCard(3, 128, "Samsung", "UHS-I U3"));

  // Persistencia: escritura del arreglo en un único archivo JSON.
  manager.saveToJson();
  console.log("\nTarjetas guardadas en data/sd_cards.json:");

  // Lectura: se recuperan los objetos desde el archivo JSON.
  const loadedCards = manager.readFromJson();
  console.log("\nTarjetas leídas desde el archivo JSON:");
  loadedCards.forEach(card => {
    console.log(
      `ID: ${card.id} | Capacidad: ${card.capacityGB} GB | ` +
      `Marca: ${card.brand} | Velocidad: ${card.speedClass}`
    );
  });

  console.log(`\nTotal de tarjetas leídas: ${loadedCards.length}`);
}

try {
  main();
} catch (error) {
  console.error("Error durante la ejecución:", error);
  process.exitCode = 1;
}
