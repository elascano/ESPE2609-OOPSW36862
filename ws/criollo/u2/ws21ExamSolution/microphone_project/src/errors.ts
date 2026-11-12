export class MicrophoneError extends Error {
  constructor(message: string) {
    super(message);
    this.name = new.target.name;
    Object.setPrototypeOf(this, new.target.prototype);
  }
}

export class InvalidMicrophoneDataError extends MicrophoneError {}

export class DuplicateMicrophoneIdError extends MicrophoneError {}

export class StorageError extends MicrophoneError {}
