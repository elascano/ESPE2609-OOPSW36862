import { createInterface } from "readline";
import { Contact } from "../models/Contacts.js";
const contactTypes = [
    "Friend",
    "Family",
    "Job Mate",
    "Team Mate",
    "High School",
    "Gym"
];
const genderOptions = [
    "Male",
    "Female"
];
const hobbyOptions = [
    "Play the Guitar",
    "Watch movies",
    "Play Soccer",
    "Play Basketball",
    "Cooking",
    "Reading",
    "Running",
    "Swimming"
];
const input = createInterface({
    input: process.stdin,
    output: process.stdout
});
function ask(question) {
    return new Promise((resolve) => {
        input.question(question, resolve);
    });
}
async function askForNumber(question) {
    while (true) {
        const answer = (await ask(question)).trim();
        const number = Number(answer);
        if (Number.isInteger(number) && number > 0) {
            return number;
        }
        console.log("Please enter a valid positive integer.");
    }
}
async function askForChoice(question, options) {
    console.log(question);
    options.forEach((option, index) => {
        console.log(`${index + 1}. ${option}`);
    });
    while (true) {
        const selection = Number((await ask("Select an option: ")).trim());
        if (Number.isInteger(selection) &&
            selection >= 1 &&
            selection <= options.length) {
            return options[selection - 1];
        }
        console.log("Invalid selection. Try again.");
    }
}
async function askForHobbies() {
    console.log("Select hobbies separated by commas:");
    hobbyOptions.forEach((hobby, index) => {
        console.log(`${index + 1}. ${hobby}`);
    });
    while (true) {
        const answer = (await ask("Hobby numbers (example: 1,3,6): ")).trim();
        if (answer.length === 0) {
            return [];
        }
        const selections = answer
            .split(",")
            .map((item) => Number(item.trim()));
        const areValid = selections.every((selection) => Number.isInteger(selection) &&
            selection >= 1 &&
            selection <= hobbyOptions.length);
        if (areValid) {
            return [...new Set(selections)].map((selection) => hobbyOptions[selection - 1]);
        }
        console.log("One or more hobby numbers are invalid.");
    }
}
async function askForBirthDate() {
    while (true) {
        const answer = (await ask("Birth date (YYYY-MM-DD): ")).trim();
        const dateParts = answer.match(/^(\d{4})-(\d{2})-(\d{2})$/);
        if (dateParts) {
            const year = Number(dateParts[1]);
            const month = Number(dateParts[2]);
            const day = Number(dateParts[3]);
            const birthDate = new Date(year, month - 1, day);
            if (birthDate.getFullYear() === year &&
                birthDate.getMonth() === month - 1 &&
                birthDate.getDate() === day) {
                return birthDate;
            }
        }
        console.log("Please enter a valid date.");
    }
}
async function createContact() {
    console.log("\n--- Contact Information ---");
    const id = await askForNumber("ID: ");
    const fullName = (await ask("Full name: ")).trim();
    const email = (await ask("E-mail: ")).trim();
    const cellPhoneNumber = (await ask("Cell phone number: ")).trim();
    const instagram = (await ask("Instagram: ")).trim();
    const facebook = (await ask("Facebook: ")).trim();
    const isKnown = (await ask("Is this person known? (yes/no): ")).trim().toLowerCase() === "yes";
    const type = await askForChoice("Contact type:", contactTypes);
    const gender = await askForChoice("Gender:", genderOptions);
    const hobbies = await askForHobbies();
    const birthDate = await askForBirthDate();
    const comments = (await ask("Comments: ")).trim();
    return new Contact(id, fullName, email, cellPhoneNumber, instagram, facebook, isKnown, type, gender, hobbies, birthDate, comments);
}
async function main() {
    try {
        const contact = await createContact();
        console.log("\n--- Contact Created Successfully ---");
        console.log(contact.toString());
    }
    finally {
        input.close();
    }
}
main().catch((error) => {
    console.error("An unexpected error occurred:", error);
    process.exitCode = 1;
});
