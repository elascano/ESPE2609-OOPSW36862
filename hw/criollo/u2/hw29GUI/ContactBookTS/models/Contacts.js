export class Contact {
    id;
    fullName;
    email;
    cellPhoneNumber;
    instagram;
    facebook;
    isKnown;
    type;
    gender;
    hobbies;
    birthDate;
    comments;
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
    getId() {
        return this.id;
    }
    getFullName() {
        return this.fullName;
    }
    getEmail() {
        return this.email;
    }
    getCellPhoneNumber() {
        return this.cellPhoneNumber;
    }
    getInstagram() {
        return this.instagram;
    }
    getFacebook() {
        return this.facebook;
    }
    getIsKnown() {
        return this.isKnown;
    }
    getType() {
        return this.type;
    }
    getGender() {
        return this.gender;
    }
    getHobbies() {
        return this.hobbies;
    }
    getBirthDate() {
        return this.birthDate;
    }
    getComments() {
        return this.comments;
    }
    toString() {
        return [
            `id=${this.id}`,
            `fullName=${this.fullName}`,
            `email=${this.email}`,
            `cellPhoneNumber=${this.cellPhoneNumber}`,
            `instagram=${this.instagram}`,
            `facebook=${this.facebook}`,
            `isKnown=${this.isKnown}`,
            `type=${this.type}`,
            `gender=${this.gender}`,
            `hobbies=${this.hobbies.join(", ")}`,
            `birthDate=${this.birthDate.toDateString()}`,
            `comments=${this.comments}`
        ].join("\n");
    }
}
