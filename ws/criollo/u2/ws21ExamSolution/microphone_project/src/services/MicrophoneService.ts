import { DuplicateMicrophoneIdError } from "../errors";
import { Microphone } from "../models/Microphone";
import { JsonMicrophoneRepository } from "../repositories/JsonMicrophoneRepository";

export class MicrophoneService {
  private microphones: Microphone[] = [];

  constructor(private readonly repository: JsonMicrophoneRepository) {}

  loadFromFile(): Microphone[] {
    this.microphones = this.repository.loadAll();
    return this.getAll();
  }

  addMicrophone(microphone: Microphone): void {
    if (this.idExists(microphone.id)) {
      throw new DuplicateMicrophoneIdError(`Id ${microphone.id} already exists.`);
    }
    this.microphones.push(microphone);
    this.repository.saveAll(this.microphones);
  }

  getAll(): Microphone[] {
    return [...this.microphones];
  }

  private idExists(id: number): boolean {
    return this.microphones.some((microphone) => microphone.id === id);
  }
}
