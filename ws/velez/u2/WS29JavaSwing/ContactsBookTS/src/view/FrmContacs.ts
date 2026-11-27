function emptyFields(): void {
    (document.getElementById("txtId") as HTMLInputElement).value = "";
    (document.getElementById("txtFullName") as HTMLInputElement).value = "";
    (document.getElementById("txtEmail") as HTMLInputElement).value = "";
    (document.getElementById("txtCellphoneNumber") as HTMLInputElement).value = "";
    (document.getElementById("txtInstagram") as HTMLInputElement).value = "";
    (document.getElementById("txtFacebook") as HTMLInputElement).value = "";
    (document.getElementById("chkIsKnown") as HTMLInputElement).checked = false;
    (document.getElementById("cmbTypeOfContact") as HTMLSelectElement).selectedIndex = 0;
    (document.getElementById("cmbGender") as HTMLSelectElement).selectedIndex = 0;

    const lstHobbies = document.getElementById("lstHobbies") as HTMLSelectElement;
    for (let i = 0; i < lstHobbies.options.length; i++) {
        lstHobbies.options[i].selected = false;
    }
    (document.getElementById("jTextField4") as HTMLInputElement).value = "";
    (document.getElementById("txaComments") as HTMLTextAreaElement).value = "";
}

function btnSaveActionPerformed(): void {
    const idText = (document.getElementById("txtId") as HTMLInputElement).value.trim();
    const id = parseInt(idText, 10);
    if (!idText || isNaN(id)) {
        alert("Please enter a valid numeric id.");
        return;
    }

    const fullName = (document.getElementById("txtFullName") as HTMLInputElement).value;
    const email = (document.getElementById("txtEmail") as HTMLInputElement).value;
    const cellPhoneNumber = (document.getElementById("txtCellphoneNumber") as HTMLInputElement).value;
    const instagram = (document.getElementById("txtInstagram") as HTMLInputElement).value;
    const Facebook = (document.getElementById("txtFacebook") as HTMLInputElement).value;
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

    const birthDate = new Date();
    const comments = (document.getElementById("txaComments") as HTMLTextAreaElement).value;

    const contact = new Contact(
        id,
        fullName,
        email,
        cellPhoneNumber,
        instagram,
        Facebook,
        isKnown,
        type,
        gender,
        hobbies,
        birthDate,
        comments
    );

    alert(contact.toString());
}

window.addEventListener("DOMContentLoaded", () => {
    document.getElementById("BTNCancel")?.addEventListener("click", () => emptyFields());
    document.getElementById("btnSave")?.addEventListener("click", () => btnSaveActionPerformed());
});
