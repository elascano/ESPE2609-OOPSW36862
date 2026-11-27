function showView(viewId: string): void {
    const views = ["viewMenu", "viewAdd", "viewList", "viewAbout"];
    for (const id of views) {
        const el = document.getElementById(id);
        if (el) {
            el.style.display = id === viewId ? "block" : "none";
        }
    }
    if (viewId === "viewList") {
        renderContactsTable();
    }
}

function initBirthDateSelects(): void {
    const cmbDay = document.getElementById("cmbDay") as HTMLSelectElement;
    const cmbMonth = document.getElementById("cmbMonth") as HTMLSelectElement;
    const cmbYear = document.getElementById("cmbYear") as HTMLSelectElement;

    for (let d = 1; d <= 31; d++) {
        cmbDay.add(new Option(String(d), String(d)));
    }
    for (let m = 1; m <= 12; m++) {
        cmbMonth.add(new Option(String(m), String(m)));
    }
    const currentYear = new Date().getFullYear();
    for (let y = currentYear; y >= 1940; y--) {
        cmbYear.add(new Option(String(y), String(y)));
    }
    cmbYear.value = "2005";
}

function setupInputFilters(): void {
    const txtId = document.getElementById("txtId") as HTMLInputElement;
    const txtFullName = document.getElementById("txtFullName") as HTMLInputElement;
    const txtCellPhone = document.getElementById("txtCellphoneNumber") as HTMLInputElement;

    txtId.addEventListener("input", () => {
        txtId.value = txtId.value.replace(/\D/g, "");
    });

    txtFullName.addEventListener("input", () => {
        txtFullName.value = txtFullName.value.replace(/[^a-zA-ZáéíóúÁÉÍÓÚñÑ ]/g, "");
    });

    txtCellPhone.addEventListener("input", () => {
        txtCellPhone.value = txtCellPhone.value.replace(/\D/g, "").slice(0, 10);
    });
}

function clearForm(): void {
    (document.getElementById("txtId") as HTMLInputElement).value = "";
    (document.getElementById("txtFullName") as HTMLInputElement).value = "";
    (document.getElementById("txtEmail") as HTMLInputElement).value = "";
    (document.getElementById("txtCellphoneNumber") as HTMLInputElement).value = "";
    (document.getElementById("txtInstagram") as HTMLInputElement).value = "";
    (document.getElementById("txtFacebook") as HTMLInputElement).value = "";
    (document.getElementById("chkIsKnown") as HTMLInputElement).checked = false;
    (document.getElementById("cmbTypeOfContact") as HTMLSelectElement).selectedIndex = 0;
    (document.getElementById("cmbGender") as HTMLSelectElement).selectedIndex = 0;
    (document.getElementById("cmbDay") as HTMLSelectElement).selectedIndex = 0;
    (document.getElementById("cmbMonth") as HTMLSelectElement).selectedIndex = 0;
    (document.getElementById("cmbYear") as HTMLSelectElement).value = "2005";

    const lstHobbies = document.getElementById("lstHobbies") as HTMLSelectElement;
    for (let i = 0; i < lstHobbies.options.length; i++) {
        lstHobbies.options[i].selected = false;
    }
    (document.getElementById("txaComments") as HTMLTextAreaElement).value = "";
}

function saveContact(): void {
    const idText = (document.getElementById("txtId") as HTMLInputElement).value.trim();
    const fullName = (document.getElementById("txtFullName") as HTMLInputElement).value.trim();
    const email = (document.getElementById("txtEmail") as HTMLInputElement).value.trim();
    const phone = (document.getElementById("txtCellphoneNumber") as HTMLInputElement).value.trim();
    const instagram = (document.getElementById("txtInstagram") as HTMLInputElement).value.trim();
    const facebook = (document.getElementById("txtFacebook") as HTMLInputElement).value.trim();

    if (!/^\d+$/.test(idText) || parseInt(idText, 10) <= 0) {
        alert("Please enter a valid numeric ID greater than 0.");
        return;
    }

    if (!fullName || !/^[a-zA-ZáéíóúÁÉÍÓÚñÑ ]+$/.test(fullName)) {
        alert("Please enter a valid Full Name (letters only).");
        return;
    }

    if (!email || !/^[\w.-]+@[\w.-]+\.[a-zA-Z]{2,}$/.test(email)) {
        alert("Please enter a valid Email (example: user@mail.com).");
        return;
    }

    if (!/^\d{10}$/.test(phone)) {
        alert("Cell Phone Number must have 10 digits.");
        return;
    }

    const day = parseInt((document.getElementById("cmbDay") as HTMLSelectElement).value, 10);
    const month = parseInt((document.getElementById("cmbMonth") as HTMLSelectElement).value, 10);
    const year = parseInt((document.getElementById("cmbYear") as HTMLSelectElement).value, 10);

    const candidateDate = new Date(year, month - 1, day);
    if (
        candidateDate.getFullYear() !== year ||
        candidateDate.getMonth() !== month - 1 ||
        candidateDate.getDate() !== day
    ) {
        alert("Invalid Birth Date selected (check day and month).");
        return;
    }

    const today = new Date();
    today.setHours(23, 59, 59, 999);
    if (candidateDate > today) {
        alert("Birth Date cannot be in the future.");
        return;
    }

    const isKnown = (document.getElementById("chkIsKnown") as HTMLInputElement).checked;
    const type = (document.getElementById("cmbTypeOfContact") as HTMLSelectElement).value;
    const gender = (document.getElementById("cmbGender") as HTMLSelectElement).value.charAt(0);

    const lstHobbies = document.getElementById("lstHobbies") as HTMLSelectElement;
    const hobbies: string[] = [];
    for (let i = 0; i < lstHobbies.options.length; i++) {
        if (lstHobbies.options[i].selected) {
            hobbies.push(lstHobbies.options[i].value);
        }
    }

    const comments = (document.getElementById("txaComments") as HTMLTextAreaElement).value;

    const contact = new Contact(
        parseInt(idText, 10),
        fullName,
        email,
        phone,
        instagram,
        facebook,
        isKnown,
        type,
        gender,
        hobbies,
        candidateDate,
        comments
    );

    ContactController.addContact(contact);
    alert("Contact saved!\n\n" + contact.toString());
    clearForm();
}

function renderContactsTable(): void {
    const tbody = document.getElementById("tblContactsBody") as HTMLTableSectionElement;
    tbody.innerHTML = "";

    for (const c of ContactController.getContacts()) {
        const tr = document.createElement("tr");
        const cells = [
            String(c.id),
            c.fullName,
            c.email,
            c.cellPhoneNumber,
            c.type,
            c.gender,
            c.getFormattedDate()
        ];
        for (const val of cells) {
            const td = document.createElement("td");
            td.textContent = val;
            tr.appendChild(td);
        }
        tbody.appendChild(tr);
    }
}

window.addEventListener("DOMContentLoaded", () => {
    initBirthDateSelects();
    setupInputFilters();

    document.getElementById("mnuHome")?.addEventListener("click", () => showView("viewMenu"));
    document.getElementById("mnuAbout")?.addEventListener("click", () => showView("viewAbout"));
    document.getElementById("mnuAdd")?.addEventListener("click", () => showView("viewAdd"));
    document.getElementById("mnuView")?.addEventListener("click", () => showView("viewList"));

    document.getElementById("btnMenuAdd")?.addEventListener("click", () => showView("viewAdd"));
    document.getElementById("btnMenuView")?.addEventListener("click", () => showView("viewList"));

    document.getElementById("btnBackFromAdd")?.addEventListener("click", () => showView("viewMenu"));
    document.getElementById("btnCancel")?.addEventListener("click", () => clearForm());
    document.getElementById("btnSave")?.addEventListener("click", () => saveContact());

    document.getElementById("btnBackFromList")?.addEventListener("click", () => showView("viewMenu"));
    document.getElementById("btnAddFromList")?.addEventListener("click", () => showView("viewAdd"));
    document.getElementById("btnBackFromAbout")?.addEventListener("click", () => showView("viewMenu"));
});
