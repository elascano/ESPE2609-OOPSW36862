export class Contact {
    constructor(
        id,
        fullName,
        email,
        cellPhoneNumber,
        instagram,
        facebook,
        isKnown,
        type,
        gender,
        hobbies,
        birthDate,
        comments
    ) {
        this._id = id;
        this._fullName = fullName;
        this._email = email;
        this._cellPhoneNumber = cellPhoneNumber;
        this._instagram = instagram;
        this._facebook = facebook;
        this._isKnown = isKnown;
        this._type = type;
        this._gender = gender;
        this._hobbies = hobbies;
        this._birthDate = birthDate;
        this._comments = comments;
    }

    toString() {
        return (
            `\n id=${this._id}\n fullName=${this._fullName}` +
            `\n email=${this._email}\n cellPhoneNumber=${this._cellPhoneNumber}` +
            `\n instagram=${this._instagram},\n facebook=${this._facebook}` +
            `\n isKnown=${this._isKnown}\n type=${this._type}` +
            `\n gender=${this._gender}\n hobbies=${this._hobbies}` +
            `\n birthDate=${this._birthDate} comments=${this._comments}`
        );
    }

    get id() {
        return this._id;
    }

    set id(id) {
        this._id = id;
    }

    get fullName() {
        return this._fullName;
    }

    set fullName(fullName) {
        this._fullName = fullName;
    }

    get email() {
        return this._email;
    }

    set email(email) {
        this._email = email;
    }

    get cellPhoneNumber() {
        return this._cellPhoneNumber;
    }

    set cellPhoneNumber(cellPhoneNumber) {
        this._cellPhoneNumber = cellPhoneNumber;
    }

    get instagram() {
        return this._instagram;
    }

    set instagram(instagram) {
        this._instagram = instagram;
    }

    get facebook() {
        return this._facebook;
    }

    set facebook(facebook) {
        this._facebook = facebook;
    }

    get isKnown() {
        return this._isKnown;
    }

    set isKnown(isKnown) {
        this._isKnown = isKnown;
    }

    get type() {
        return this._type;
    }

    set type(type) {
        this._type = type;
    }

    get gender() {
        return this._gender;
    }

    set gender(gender) {
        this._gender = gender;
    }

    get hobbies() {
        return this._hobbies;
    }

    set hobbies(hobbies) {
        this._hobbies = hobbies;
    }

    get birthDate() {
        return this._birthDate;
    }

    set birthDate(birthDate) {
        this._birthDate = birthDate;
    }

    get comments() {
        return this._comments;
    }

    set comments(comments) {
        this._comments = comments;
    }
}