"use strict";
Object.defineProperty(exports, "__esModule", { value: true });
exports.MicrophoneService = void 0;
const errors_1 = require("../errors");
class MicrophoneService {
    constructor(repository) {
        this.repository = repository;
        this.microphones = [];
    }
    loadFromFile() {
        this.microphones = this.repository.loadAll();
        return this.getAll();
    }
    addMicrophone(microphone) {
        if (this.idExists(microphone.id)) {
            throw new errors_1.DuplicateMicrophoneIdError(`Id ${microphone.id} already exists.`);
        }
        this.microphones.push(microphone);
        this.repository.saveAll(this.microphones);
    }
    getAll() {
        return [...this.microphones];
    }
    idExists(id) {
        return this.microphones.some((microphone) => microphone.id === id);
    }
}
exports.MicrophoneService = MicrophoneService;
