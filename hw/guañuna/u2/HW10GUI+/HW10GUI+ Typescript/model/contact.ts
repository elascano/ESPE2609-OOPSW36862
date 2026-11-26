export class Contact {
    constructor(
        public id: number,
        public fullName: string,
        public email: string,
        public cellphone: string,
        public instagram: string,
        public facebook: string,
        public isKnown: boolean,
        public typeContact: string,
        public gender: string,
        public hobbies: string[],
        public birthDate: Date,
        public comments: string
    ) { }

    toString(): string {
        const fechaFormateada = this.birthDate.toLocaleDateString('es-EC');
        return `\n--- CONTACTO GUARDADO ---\n` +
            `ID: ${this.id}\n` +
            `Nombre: ${this.fullName}\n` +
            `Email: ${this.email}\n` +
            `Celular: ${this.cellphone}\n` +
            `Instagram: ${this.instagram}\n` +
            `Facebook: ${this.facebook}\n` +
            `Conocido: ${this.isKnown ? 'Sí' : 'No'}\n` +
            `Tipo: ${this.typeContact}\n` +
            `Género: ${this.gender}\n` +
            `Hobbies: ${this.hobbies.join(', ')}\n` +
            `Fecha Nacimiento: ${fechaFormateada}\n` +
            `Comentarios: ${this.comments}\n` +
            `-------------------------`;
    }
}