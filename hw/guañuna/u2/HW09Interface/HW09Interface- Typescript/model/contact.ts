export class Contact {
    public id: number;
    public fullName: string;
    public email: string;
    public cellPhoneNumber: string;
    public instagram: string;
    public facebook: string;
    public isKnown: boolean;
    public type: string;
    public gender: string;
    public hobbies: string[];
    public birthDate: Date | null;
    public comments: string;

    constructor(
        id: number,
        fullName: string,
        email: string,
        cellPhoneNumber: string,
        instagram: string,
        facebook: string,
        isKnown: boolean,
        type: string,
        gender: string,
        hobbies: string[],
        birthDate: Date | null,
        comments: string
    ) {
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

    public toString(): string {
        return `id=${this.id}\n fullName=${this.fullName}\n email=${this.email}\n ` +
            `cellPhoneNumber=${this.cellPhoneNumber}\n instagram=${this.instagram}\n ` +
            `facebook=${this.facebook}\n isKnown=${this.isKnown}\n type=${this.type}\n ` +
            `gender=${this.gender}\n hobbies=${this.hobbies.join(", ")}\n ` +
            `birthDate=${this.birthDate ? this.birthDate.toISOString().split('T')[0] : "null"}\n ` +
            `comments=${this.comments}`;
    }
}