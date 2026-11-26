"use strict";
Object.defineProperty(exports, "__esModule", { value: true });
exports.Contact = void 0;
class Contact {
    constructor(id, fullName, email, cellPhoneNumber, instagram, facebook, isKnown, type, gender, hobbies, birthDate, comments) {
        this.id = id;
        this.fullName = fullName;
        this.email = email;
        this.cellPhoneNumber = cellPhoneNumber;
        this.instagram = instagram;
        this.facebook = facebook;
        this.isKnown = isKnown;
        this.type = type;
        this.gender = gender;
        this.hobbies = hobbies;
        this.birthDate = birthDate;
        this.comments = comments;
    }
    toString() {
        return `ID: ${this.id}\nFull Name: ${this.fullName}\nEmail: ${this.email}\n` +
            `Cell Phone Number: ${this.cellPhoneNumber}\nInstagram: ${this.instagram}\n` +
            `Facebook: ${this.facebook}\nKnown: ${this.isKnown}\nType: ${this.type}\n` +
            `Gender: ${this.gender}\nHobbies: ${this.hobbies.join(", ")}\n` +
            `Birth Date: ${this.birthDate.toLocaleDateString()}\nComments: ${this.comments}`;
    }
}
exports.Contact = Contact;
