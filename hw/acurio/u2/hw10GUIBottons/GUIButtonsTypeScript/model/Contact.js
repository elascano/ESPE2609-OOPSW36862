"use strict";
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
        const day = String(this.birthDate.getDate()).padStart(2, "0");
        const month = String(this.birthDate.getMonth() + 1).padStart(2, "0");
        const year = this.birthDate.getFullYear();
        return `ID: ${this.id}\n` +
            `Full Name: ${this.fullName}\n` +
            `E-mail: ${this.email}\n` +
            `Cell Phone Number: ${this.cellPhoneNumber}\n` +
            `Instagram: ${this.instagram}\n` +
            `Facebook: ${this.facebook}\n` +
            `Known: ${this.isKnown}\n` +
            `Type: ${this.type}\n` +
            `Gender: ${this.gender}\n` +
            `Hobbies: ${this.hobbies.join(", ")}\n` +
            `Birth Date: ${day}/${month}/${year}\n` +
            `Comments: ${this.comments}`;
    }
}
