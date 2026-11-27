export class Contact {
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
        return `\n id=${this.id}\n fullName=${this.fullName}`
            + `\n email=${this.email}\n cellPhoneNumber=${this.cellPhoneNumber}`
            + `\n instagram=${this.instagram},\n facebook=${this.facebook}`
            + `\n isKnown=${this.isKnown}\n type=${this.type}`
            + `\n gender=${this.gender}\n hobbies=[${this.hobbies.join(", ")}]`
            + `\n birthDate=${this.birthDate} comments=${this.comments}`;
    }
}