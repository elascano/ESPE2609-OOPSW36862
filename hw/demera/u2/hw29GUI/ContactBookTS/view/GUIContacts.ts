import express, { Request, Response } from "express";
import { Contact } from "../models/Contacts";

const contactTypes: string[] = [
    "Friend",
    "Family",
    "Job Mate",
    "Team Mate",
    "High School",
    "Gym"
];

const genderOptions: string[] = [
    "Male",
    "Female"
];

const hobbyOptions: string[] = [
    "Play the Guitar",
    "Watch movies",
    "Play Soccer",
    "Play Basketball",
    "Cooking",
    "Reading",
    "Running",
    "Swimming"
];

const contactsList: Contact[] = [];
const app = express();
const port = 3009;

app.use(express.json());

function renderHtml(): string {
    const typeOptionsHtml = contactTypes
        .map((type) => `<option value="${type}">${type}</option>`)
        .join("");

    const genderRadiosHtml = genderOptions
        .map(
            (gender, index) => `
            <label style="margin-right: 14px;">
                <input type="radio" name="gender" value="${gender}" ${index === 0 ? "checked" : ""}> ${gender}
            </label>`
        )
        .join("");

    const hobbiesCheckboxesHtml = hobbyOptions
        .map(
            (hobby) => `
            <label style="display: block; margin: 3px 0;">
                <input type="checkbox" name="hobbies" value="${hobby}"> ${hobby}
            </label>`
        )
        .join("");

    return `<!DOCTYPE html>
<html lang="en">
<head>
    <meta charset="UTF-8">
    <title>Contacts Book</title>
    <style>
        body { font-family: 'Segoe UI', sans-serif; background: #f3f4f6; display: flex; justify-content: center; padding: 24px; }
        .card { background: white; padding: 24px 30px; border-radius: 10px; box-shadow: 0 4px 12px rgba(0,0,0,0.1); width: 460px; }
        h2 { text-align: center; margin-top: 0; color: #1f2937; }
        .row { margin-bottom: 12px; }
        label.title { display: block; font-weight: 600; margin-bottom: 4px; color: #374151; font-size: 14px; }
        input[type="text"], input[type="number"], input[type="email"], input[type="date"], select, textarea {
            width: 100%; padding: 8px; border: 1px solid #d1d5db; border-radius: 6px; box-sizing: border-box;
        }
        .hobbies-box { border: 1px solid #d1d5db; border-radius: 6px; padding: 8px; max-height: 120px; overflow-y: auto; }
        .buttons { display: flex; gap: 10px; justify-content: center; margin-top: 18px; }
        button { padding: 9px 20px; border: none; border-radius: 6px; cursor: pointer; font-weight: 600; }
        .btn-save { background: #2563eb; color: white; }
        .btn-clear { background: #e5e7eb; color: #374151; }
        .result { margin-top: 16px; padding: 12px; background: #ecfdf5; border: 1px solid #10b981; border-radius: 6px; font-size: 13px; white-space: pre-wrap; display: none; }
        .error { margin-top: 16px; padding: 12px; background: #fef2f2; border: 1px solid #ef4444; color: #b91c1c; border-radius: 6px; font-size: 13px; display: none; }
    </style>
</head>
<body>
    <div class="card">
        <h2>CONTACTS</h2>
        <form id="contactForm">
            <div class="row">
                <label class="title">ID:</label>
                <input type="number" id="id" min="1" required>
            </div>
            <div class="row">
                <label class="title">Full Name:</label>
                <input type="text" id="fullName" required>
            </div>
            <div class="row">
                <label class="title">E-mail:</label>
                <input type="email" id="email">
            </div>
            <div class="row">
                <label class="title">Cell Phone Number:</label>
                <input type="text" id="cellPhoneNumber">
            </div>
            <div class="row">
                <label class="title">Instagram:</label>
                <input type="text" id="instagram">
            </div>
            <div class="row">
                <label class="title">Facebook:</label>
                <input type="text" id="facebook">
            </div>
            <div class="row">
                <label><input type="checkbox" id="isKnown"> <strong>Is this person known?</strong></label>
            </div>
            <div class="row">
                <label class="title">Type:</label>
                <select id="type">${typeOptionsHtml}</select>
            </div>
            <div class="row">
                <label class="title">Gender:</label>
                ${genderRadiosHtml}
            </div>
            <div class="row">
                <label class="title">Hobbies:</label>
                <div class="hobbies-box">${hobbiesCheckboxesHtml}</div>
            </div>
            <div class="row">
                <label class="title">Birth Date:</label>
                <input type="date" id="birthDate" required>
            </div>
            <div class="row">
                <label class="title">Comments:</label>
                <textarea id="comments" rows="3"></textarea>
            </div>
            <div class="buttons">
                <button type="submit" class="btn-save">Save</button>
                <button type="button" class="btn-clear" onclick="clearForm()">Clear</button>
            </div>
        </form>
        <div id="errorBox" class="error"></div>
        <div id="resultBox" class="result"></div>
    </div>

    <script>
        const form = document.getElementById("contactForm");
        const errorBox = document.getElementById("errorBox");
        const resultBox = document.getElementById("resultBox");

        form.addEventListener("submit", async (event) => {
            event.preventDefault();
            errorBox.style.display = "none";
            resultBox.style.display = "none";

            const selectedHobbies = Array.from(document.querySelectorAll('input[name="hobbies"]:checked'))
                .map((cb) => cb.value);

            const selectedGender = document.querySelector('input[name="gender"]:checked').value;

            const payload = {
                id: Number(document.getElementById("id").value),
                fullName: document.getElementById("fullName").value.trim(),
                email: document.getElementById("email").value.trim(),
                cellPhoneNumber: document.getElementById("cellPhoneNumber").value.trim(),
                instagram: document.getElementById("instagram").value.trim(),
                facebook: document.getElementById("facebook").value.trim(),
                isKnown: document.getElementById("isKnown").checked,
                type: document.getElementById("type").value,
                gender: selectedGender,
                hobbies: selectedHobbies,
                birthDate: document.getElementById("birthDate").value,
                comments: document.getElementById("comments").value.trim()
            };

            const response = await fetch("/contacts", {
                method: "POST",
                headers: { "Content-Type": "application/json" },
                body: JSON.stringify(payload)
            });

            const data = await response.json();
            if (!response.ok) {
                errorBox.textContent = data.message;
                errorBox.style.display = "block";
                return;
            }

            resultBox.textContent = "Contact Created Successfully:\\n\\n" + data.contactText;
            resultBox.style.display = "block";
            clearForm(false);
        });

        function clearForm(hideMessages = true) {
            form.reset();
            if (hideMessages) {
                errorBox.style.display = "none";
                resultBox.style.display = "none";
            }
        }
    </script>
</body>
</html>`;
}

app.get("/", (req: Request, res: Response) => {
    res.send(renderHtml());
});

app.post("/contacts", (req: Request, res: Response) => {
    const body = req.body;

    if (!Number.isInteger(body.id) || body.id <= 0) {
        return res.status(400).json({ message: "Please enter a valid positive integer for ID." });
    }

    if (!body.fullName) {
        return res.status(400).json({ message: "Full name is required." });
    }

    const dateParts = String(body.birthDate || "").match(/^(\d{4})-(\d{2})-(\d{2})$/);
    if (!dateParts) {
        return res.status(400).json({ message: "Please enter a valid birth date." });
    }

    const year = Number(dateParts[1]);
    const month = Number(dateParts[2]);
    const day = Number(dateParts[3]);
    const birthDate = new Date(year, month - 1, day);

    if (
        birthDate.getFullYear() !== year ||
        birthDate.getMonth() !== month - 1 ||
        birthDate.getDate() !== day
    ) {
        return res.status(400).json({ message: "Please enter a valid calendar date." });
    }

    const contact = new Contact(
        body.id,
        body.fullName,
        body.email,
        body.cellPhoneNumber,
        body.instagram,
        body.facebook,
        Boolean(body.isKnown),
        body.type,
        body.gender,
        body.hobbies || [],
        birthDate,
        body.comments
    );

    contactsList.push(contact);

    res.status(201).json({
        contactText: contact.toString()
    });
});

app.listen(port, () => {
    console.log(`Contacts Book UI running at http://localhost:${port}`);
});