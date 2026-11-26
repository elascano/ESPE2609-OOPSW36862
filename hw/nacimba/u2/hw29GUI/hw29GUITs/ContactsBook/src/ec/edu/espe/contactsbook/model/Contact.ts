// Equivalente a ec.edu.espe.contactsbook.model.Contact
export interface Contact {
  id: number;
  fullName: string;
  email: string;
  cellPhoneNumber: string;
  instagram: string;
  facebook: string;
  isKnown: boolean;
  type: string;
  gender: "F" | "M"; // char en Java
  hobbies: string[];
  birthDate: Date;
  comments: string;
}

// Equivalente a toString()
export function contactToString(c: Contact): string {
  return (
    `id=${c.id}\n fullName=${c.fullName}` +
    `\n email=${c.email}\n cellPhoneNumber=${c.cellPhoneNumber}` +
    `\n instagram=${c.instagram}\n facebook=${c.facebook}` +
    `\n isKnown=${c.isKnown}\n type=${c.type}\n gender=${c.gender}` +
    `\n hobbies=${c.hobbies.join(", ")}` +
    `\n birthDate=${c.birthDate.toLocaleDateString("es-EC")}` +
    `\n comments=${c.comments}`
  );
}
