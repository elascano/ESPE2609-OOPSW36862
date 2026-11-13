import * as path from "path";
import { JsonMicrophoneRepository } from "./repositories/JsonMicrophoneRepository";
import { MicrophoneService } from "./services/MicrophoneService";
import { ConsoleMenu } from "./ui/ConsoleMenu";

const DATA_FILE = path.join(__dirname, "..", "data", "microphones.json");

async function main(): Promise<void> {
  const repository = new JsonMicrophoneRepository(DATA_FILE);
  const service = new MicrophoneService(repository);
  await new ConsoleMenu(service).run();
}

main();
