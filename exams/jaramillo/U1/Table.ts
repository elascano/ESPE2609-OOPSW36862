import { readFileSync, writeFileSync, existsSync } from "fs";
import { createInterface } from "readline";

class Mesa {
  constructor(
    public id: number,
    public material: string,
    public forma: string,
    public capacidad: number
  ) {}
}

const entrada = createInterface({
  input: process.stdin,
  output: process.stdout
});

function preguntar(texto: string): Promise<string> {
  return new Promise((resolver) => {
    entrada.question(texto, resolver);
  });
}

async function iniciar() {
  const mesas: Mesa[] = existsSync("mesas.json")
    ? JSON.parse(readFileSync("mesas.json", "utf-8"))
    : [];

  const id = Number(await preguntar("Escribe el ID: "));
  const material = await preguntar("Escribe el material: ");
  const forma = await preguntar("Escribe la forma: ");
  const capacidad = Number(await preguntar("Escribe la capacidad: "));

  const mesa = new Mesa(id, material, forma, capacidad);
  mesas.push(mesa);

  writeFileSync("mesas.json", JSON.stringify(mesas, null, 2));

  console.log("\nMesa guardada en mesas.json:");
  console.log(mesa);

  console.log("\nMesas registradas:");
  console.log(JSON.parse(readFileSync("mesas.json", "utf-8")));

  entrada.close();
}

iniciar();