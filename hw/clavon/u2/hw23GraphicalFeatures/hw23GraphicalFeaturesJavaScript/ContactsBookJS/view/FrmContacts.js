import { Contact } from "../model/Contact.js";

export class FrmContacts {
    constructor() {
        this.txtId = document.getElementById("txtId");
        this.txtFullName = document.getElementById("txtFullName");
        this.txtEmail = document.getElementById("txtEmail");
        this.txtCellPhoneNumber = document.getElementById("txtCellPhoneNumber");
        this.txtInstagram = document.getElementById("txtInstagram");
        this.txtFacebook = document.getElementById("txtFacebook");
        this.chkisKnown = document.getElementById("chkisKnown");
        this.cmbType = document.getElementById("cmbType");
        this.cmbGender = document.getElementById("cmbGender");
        this.listHobbies = document.getElementById("listHobbies");
        this.txtBirthDate = document.getElementById("txtBirthDate");
        this.txtComments = document.getElementById("txtComments");
        this.btnSave = document.getElementById("btnSave");
        this.btnCancel = document.getElementById("btnCancel");

        this.initEvents();
    }

    initEvents() {
        this.btnSave.addEventListener("click", () => this.btnSaveActionPerformed());
    }

    btnSaveActionPerformed() {
        const id = parseInt(this.txtId.value, 10);
        const fullName = this.txtFullName.value;
        const email = this.txtEmail.value;
        const cellPhoneNumber = this.txtCellPhoneNumber.value;
        const instagram = this.txtInstagram.value;
        const facebook = this.txtFacebook.value;
        const isKnown = this.chkisKnown.checked;
        const type = this.cmbType.value;
        const gender = this.cmbGender.value.charAt(0);
        const hobbies = Array.from(this.listHobbies.selectedOptions).map(opt => opt.value);
        const birthDate = new Date();
        const comments = this.txtComments.value;

        const contact = new Contact(
            id,
            fullName,
            email,
            cellPhoneNumber,
            instagram,
            facebook,
            isKnown,
            type,
            gender,
            hobbies,
            birthDate,
            comments
        );

        alert(contact.toString());
    }
}   