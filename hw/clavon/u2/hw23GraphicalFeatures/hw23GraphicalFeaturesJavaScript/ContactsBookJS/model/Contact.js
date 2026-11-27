export class Contact {
    #id;
    #fullName;
    #email;
    #cellPhoneNumber;
    #instagram;
    #facebook;
    #isKnown;
    #type;
    #gender;
    #hobbies;
    #birthDate;
    #comments;

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
        this.#id = id;
        this.#fullName = fullName;
        this.#email = email;
        this.#cellPhoneNumber = cellPhoneNumber;
        this.#instagram = instagram;
        this.#facebook = facebook;
        this.#isKnown = isKnown;
        this.#type = type;
        this.#gender = gender;
        this.#hobbies = hobbies;
        this.#birthDate = birthDate;
        this.#comments = comments;
    }

    toString() {
        return (
            `\n id=${this.#id}` +
            `\n fullName=${this.#fullName}` +
            `\n email=${this.#email}` +
            `\n cellPhoneNumber=${this.#cellPhoneNumber}` +
            `\n instagram=${this.#instagram},` +
            `\n facebook=${this.#facebook}` +
            `\n isKnown=${this.#isKnown}` +
            `\n type=${this.#type}` +
            `\n gender=${this.#gender}` +
            `\n hobbies=[${this.#hobbies.join(", ")}]` +
            `\n birthDate=${this.#birthDate} comments=${this.#comments}`
        );
    }

    getId() { return this.#id; }
    setId(id) { this.#id = id; }

    getFullName() { return this.#fullName; }
    setFullName(fullName) { this.#fullName = fullName; }

    getEmail() { return this.#email; }
    setEmail(email) { this.#email = email; }

    getCellPhoneNumber() { return this.#cellPhoneNumber; }
    setCellPhoneNumber(cellPhoneNumber) { this.#cellPhoneNumber = cellPhoneNumber; }

    getInstagram() { return this.#instagram; }
    setInstagram(instagram) { this.#instagram = instagram; }

    getFacebook() { return this.#facebook; }
    setFacebook(facebook) { this.#facebook = facebook; }

    isIsKnown() { return this.#isKnown; }
    setIsKnown(isKnown) { this.#isKnown = isKnown; }

    getType() { return this.#type; }
    setType(type) { this.#type = type; }

    getGender() { return this.#gender; }
    setGender(gender) { this.#gender = gender; }

    getHobbies() { return this.#hobbies; }
    setHobbies(hobbies) { this.#hobbies = hobbies; }

    getBirthDate() { return this.#birthDate; }
    setBirthDate(birthDate) { this.#birthDate = birthDate; }

    getComments() { return this.#comments; }
    setComments(comments) { this.#comments = comments; }
}