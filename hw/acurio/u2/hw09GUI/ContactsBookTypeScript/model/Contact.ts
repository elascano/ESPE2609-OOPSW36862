export class Contact {
    id: number;
    fullName: string;
    email: string;
    cellPhoneNumber: string;
    instagram: string;
    facebook: string;
    isKnown: boolean;
    type: string;
    gender: string;
    hobbies: string[];
    birthDate: Date;
    comments: string;

    constructor(id: number, fullName: string, email: string,
                cellPhoneNumber: string, instagram: string, facebook: string,
                isKnown: boolean, type: string, gender: string,
                hobbies: string[], birthDate: Date, comments: string) {
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

    toString(): string {
        return `ID: ${this.id}\nFull Name: ${this.fullName}\nEmail: ${this.email}\n` +
               `Cell Phone Number: ${this.cellPhoneNumber}\nInstagram: ${this.instagram}\n` +
               `Facebook: ${this.facebook}\nKnown: ${this.isKnown}\nType: ${this.type}\n` +
               `Gender: ${this.gender}\nHobbies: ${this.hobbies.join(", ")}\n` +
               `Birth Date: ${this.birthDate.toLocaleDateString()}\nComments: ${this.comments}`;
    }
}
