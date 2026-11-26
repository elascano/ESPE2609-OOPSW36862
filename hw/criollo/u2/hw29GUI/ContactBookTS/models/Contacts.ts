
export class Contact {
    private id: number;
    private fullName: string;
    private email: string;
    private cellPhoneNumber: string;
    private instagram: string;
    private facebook: string;
    private isKnown: boolean;
    private type: string;
    private gender: string;
    private hobbies: string[];
    private birthDate: Date;
    private comments: string;

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
        birthDate: Date,
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

    public getId(): number {
        return this.id;
    }

    public getFullName(): string {
        return this.fullName;
    }

    public getEmail(): string {
        return this.email;
    }

    public getCellPhoneNumber(): string {
        return this.cellPhoneNumber;
    }

    public getInstagram(): string {
        return this.instagram;
    }

    public getFacebook(): string {
        return this.facebook;
    }

    public getIsKnown(): boolean {
        return this.isKnown;
    }

    public getType(): string {
        return this.type;
    }

    public getGender(): string {
        return this.gender;
    }

    public getHobbies(): string[] {
        return this.hobbies;
    }

    public getBirthDate(): Date {
        return this.birthDate;
    }

    public getComments(): string {
        return this.comments;
    }

    public toString(): string {
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
