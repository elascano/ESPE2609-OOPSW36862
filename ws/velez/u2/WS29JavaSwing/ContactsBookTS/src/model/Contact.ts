class Contact {
    id: number;
    fullName: string;
    email: string;
    cellPhoneNumber: string;
    instagram: string;
    Facebook: string;
    isKNown: boolean;
    type: string;
    gender: string;
    hobbies: string[];
    birtDate: Date;
    comments: string;

    constructor(
        ide: number,
        fullName: string,
        email: string,
        cellPhoneNumber: string,
        instagram: string,
        Facebook: string,
        isKNown: boolean,
        type: string,
        gender: string,
        hobbies: string[],
        birtDate: Date,
        comments: string
    ) {
        this.id = ide;
        this.fullName = fullName;
        this.email = email;
        this.cellPhoneNumber = cellPhoneNumber;
        this.instagram = instagram;
        this.Facebook = Facebook;
        this.isKNown = isKNown;
        this.type = type;
        this.gender = gender;
        this.hobbies = hobbies;
        this.birtDate = birtDate;
        this.comments = comments;
    }

    toString(): string {
        return (
            `Contact{\n` +
            `id=${this.id}\n` +
            `fullName=${this.fullName}\n` +
            `email=${this.email}\n` +
            `cellPhoneNumber=${this.cellPhoneNumber}\n` +
            `instagram=${this.instagram}\n` +
            `Facebook=${this.Facebook}\n` +
            `isKNown=${this.isKNown}\n` +
            `type=${this.type}\n` +
            `gender=${this.gender}\n` +
            `hobbies=[${this.hobbies.join(", ")}]\n` +
            `birtDate=${this.birtDate}\n` +
            `comments=${this.comments}\n}`
        );
    }
}
