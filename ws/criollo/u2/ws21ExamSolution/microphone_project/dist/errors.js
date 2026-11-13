"use strict";
Object.defineProperty(exports, "__esModule", { value: true });
exports.StorageError = exports.DuplicateMicrophoneIdError = exports.InvalidMicrophoneDataError = exports.MicrophoneError = void 0;
class MicrophoneError extends Error {
    constructor(message) {
        super(message);
        this.name = new.target.name;
        Object.setPrototypeOf(this, new.target.prototype);
    }
}
exports.MicrophoneError = MicrophoneError;
class InvalidMicrophoneDataError extends MicrophoneError {
}
exports.InvalidMicrophoneDataError = InvalidMicrophoneDataError;
class DuplicateMicrophoneIdError extends MicrophoneError {
}
exports.DuplicateMicrophoneIdError = DuplicateMicrophoneIdError;
class StorageError extends MicrophoneError {
}
exports.StorageError = StorageError;
