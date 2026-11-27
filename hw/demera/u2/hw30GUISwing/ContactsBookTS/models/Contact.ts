export class Contact {
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

    public toString(): string {
        const day = String(this.birthDate.getDate()).padStart(2, '0');
        const month = String(this.birthDate.getMonth() + 1).padStart(2, '0');
        const year = this.birthDate.getFullYear();
        const formattedDate = `${day}/${month}/${year}`;

        return `id=${this.id}\n` +
               ` fullName=${this.fullName}\n` +
               ` email=${this.email}\n` +
               ` cellPhoneNumber=${this.cellPhoneNumber}\n` +
               ` instagram=${this.instagram}\n` +
               ` facebook=${this.facebook}\n` +
               ` isKnown=${this.isKnown}\n` +
               ` type=${this.type}\n` +
               ` gender=${this.gender}\n` +
               ` hobbies=[${this.hobbies.join(', ')}]\n` +
               ` birthDate=${formattedDate}\n` +
               ` comments=${this.comments}}`;
    }
}