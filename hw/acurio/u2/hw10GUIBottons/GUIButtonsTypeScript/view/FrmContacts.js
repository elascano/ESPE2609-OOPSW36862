"use strict";
function getValue(id) {
    return document.getElementById(id).value.trim();
}
function showError(id, message) {
    alert(message);
    document.getElementById(id)?.focus();
}
function saveContact() {
    const idText = getValue("id");
    const fullName = getValue("fullName");
    const email = getValue("email");
    const phone = getValue("phone");
    const birthDateText = getValue("birthDate");
    if (!/^\d+$/.test(idText)) {
        showError("id", "ID must contain only numbers.");
        return;
    }
    const id = Number(idText);
    if (!Number.isSafeInteger(id) || id <= 0) {
        showError("id", "ID must be a valid number greater than zero.");
        return;
    }
    if (fullName.length < 2 || !/^[\p{L} ]+$/u.test(fullName)) {
        showError("fullName", "Enter a valid full name without numbers.");
        return;
    }
    if (!/^[A-Za-z0-9._%+-]+@[A-Za-z0-9.-]+\.[A-Za-z]{2,}$/.test(email)) {
        showError("email", "Enter a valid email, for example: ana@gmail.com");
        return;
    }
    if (!/^09\d{8}$/.test(phone)) {
        showError("phone", "Phone number must have 10 digits and start with 09.");
        return;
    }
    if (!/^\d{2}\/\d{2}\/\d{4}$/.test(birthDateText)) {
        showError("birthDate", "Invalid birth date. Use dd/MM/yyyy.");
        return;
    }
    const parts = birthDateText.split("/");
    const day = Number(parts[0]);
    const month = Number(parts[1]);
    const year = Number(parts[2]);
    const birthDate = new Date(year, month - 1, day);
    if (year < 100 || birthDate.getDate() !== day ||
        birthDate.getMonth() !== month - 1 || birthDate.getFullYear() !== year) {
        showError("birthDate", "Invalid birth date. Use dd/MM/yyyy.");
        return;
    }
    if (birthDate > new Date()) {
        showError("birthDate", "Birth date cannot be in the future.");
        return;
    }
    const hobbiesList = document.getElementById("hobbies");
    const hobbies = Array.from(hobbiesList.selectedOptions).map(option => option.value);
    const known = document.getElementById("known").checked;
    const contact = new Contact(id, fullName, email, phone, getValue("instagram"), getValue("facebook"), known, getValue("contactType"), getValue("gender"), hobbies, birthDate, getValue("comments"));
    alert("Contact details\n\n" + contact.toString());
}
document.getElementById("saveButton")
    .addEventListener("click", saveContact);
