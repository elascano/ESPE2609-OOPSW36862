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
        id: number,
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
        this.id = id;
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

    getFormattedDate(): string {
        const d = String(this.birtDate.getDate()).padStart(2, "0");
        const m = String(this.birtDate.getMonth() + 1).padStart(2, "0");
        const y = this.birtDate.getFullYear();
        return `${d}/${m}/${y}`;
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
            `birtDate=${this.getFormattedDate()}\n` +
            `comments=${this.comments}\n}`
        );
    }
}
