class Contact {
    constructor(
        public id: number,
        public fullName: string,
        public email: string,
        public cellPhoneNumber: string,
        public instagram: string,
        public facebook: string,
        public isKnown: boolean,
        public type: string,
        public gender: string,
        public hobbies: string[],
        public birthDate: Date,
        public comments: string
    ) {}

    toString(): string {
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
