import { Contact } from '../models/Contact.js';

class FrmContacts {
    private txtId = document.getElementById('txtId') as HTMLInputElement;
    private txtFullName = document.getElementById('txtFullName') as HTMLInputElement;
    private txtEmail = document.getElementById('txtEmail') as HTMLInputElement;
    private txtCellPhoneNumber = document.getElementById('txtCellPhoneNumber') as HTMLInputElement;
    private txtInstagram = document.getElementById('txtInstagram') as HTMLInputElement;
    private txtFacebook = document.getElementById('txtFacebook') as HTMLInputElement;
    private chkKnown = document.getElementById('chkKnown') as HTMLInputElement;
    private cmbTypeOfContact = document.getElementById('cmbTypeOfContact') as HTMLSelectElement;
    private cmbGender = document.getElementById('cmbGender') as HTMLSelectElement;
    private lstHobbies = document.getElementById('lstHobbies') as HTMLSelectElement;
    private txtBirthDate = document.getElementById('txtBirthDate') as HTMLInputElement;
    private txaComments = document.getElementById('txaComments') as HTMLTextAreaElement;
    private btnSave = document.getElementById('btnSave') as HTMLButtonElement;
    private btnCancel = document.getElementById('btnCancel') as HTMLButtonElement;

    constructor() {
        this.setupInputRestrictions();
        this.btnSave.addEventListener('click', (e) => {
            e.preventDefault();
            this.btnSaveActionPerformed();
        });
        this.btnCancel.addEventListener('click', () => this.clearForm());
    }

    private setupInputRestrictions(): void {
        const numbersOnly = (e: Event) => {
            const input = e.target as HTMLInputElement;
            input.value = input.value.replace(/\D/g, '');
        };
        this.txtId.addEventListener('input', numbersOnly);
        this.txtCellPhoneNumber.addEventListener('input', numbersOnly);

        this.txtFullName.addEventListener('input', () => {
            this.txtFullName.value = this.txtFullName.value.replace(/[^a-zA-ZáéíóúÁÉÍÓÚñÑ\s]/g, '');
        });
    }

    private clearForm(): void {
        this.txtId.value = '';
        this.txtFullName.value = '';
        this.txtEmail.value = '';
        this.txtCellPhoneNumber.value = '';
        this.txtInstagram.value = '';
        this.txtFacebook.value = '';
        this.chkKnown.checked = false;
        this.cmbTypeOfContact.selectedIndex = 0;
        this.cmbGender.selectedIndex = 0;
        Array.from(this.lstHobbies.options).forEach(opt => opt.selected = false);
        this.txtBirthDate.value = '';
        this.txaComments.value = '';
        this.txtId.focus();
    }

    private parseStrictDate(dateStr: string): Date | null {
        const match = /^(\d{2})\/(\d{2})\/(\d{4})$/.exec(dateStr);
        if (!match) return null;

        const day = Number(match[1]);
        const month = Number(match[2]) - 1;
        const year = Number(match[3]);

        const date = new Date(year, month, day);
        if (date.getFullYear() !== year || date.getMonth() !== month || date.getDate() !== day) {
            return null;
        }
        return date;
    }

    private btnSaveActionPerformed(): void {
        const idText = this.txtId.value.trim();
        if (!idText || !/^\d+$/.test(idText)) {
            alert('ID is required and must contain only positive numbers.');
            this.txtId.focus();
            return;
        }
        const id = parseInt(idText, 10);

        const fullName = this.txtFullName.value.trim();
        if (!/^[a-zA-ZáéíóúÁÉÍÓÚñÑ\s]{2,}$/.test(fullName)) {
            alert('Full Name is required and must contain only letters (at least 2 characters).');
            this.txtFullName.focus();
            return;
        }

        const email = this.txtEmail.value.trim();
        if (!/^[a-zA-Z0-9._%+-]+@[a-zA-Z0-9.-]+\.[a-zA-Z]{2,}$/.test(email)) {
            alert('Please enter a valid email address (e.g., user@domain.com).');
            this.txtEmail.focus();
            return;
        }

        const cellPhoneNumber = this.txtCellPhoneNumber.value.trim();
        if (!/^\d{10}$/.test(cellPhoneNumber)) {
            alert('Cell Phone Number must be exactly 10 digits.');
            this.txtCellPhoneNumber.focus();
            return;
        }

        const instagram = this.txtInstagram.value.trim();
        if (instagram && instagram.includes(' ')) {
            alert('Instagram username cannot contain spaces.');
            this.txtInstagram.focus();
            return;
        }

        const facebook = this.txtFacebook.value.trim();
        if (facebook && facebook.includes(' ')) {
            alert('Facebook username cannot contain spaces.');
            this.txtFacebook.focus();
            return;
        }

        const hobbies = Array.from(this.lstHobbies.selectedOptions).map(opt => opt.value);
        if (hobbies.length === 0) {
            alert('Please select at least one hobby.');
            this.lstHobbies.focus();
            return;
        }

        const dateText = this.txtBirthDate.value.trim();
        const parsedDate = this.parseStrictDate(dateText);
        if (!parsedDate) {
            alert('Invalid date. Please use the exact format dd/MM/yyyy (e.g., 25/12/2000).');
            this.txtBirthDate.focus();
            return;
        }
        if (parsedDate > new Date()) {
            alert('Birth Date cannot be in the future.');
            this.txtBirthDate.focus();
            return;
        }

        const contact = new Contact(
            id,
            fullName,
            email,
            cellPhoneNumber,
            instagram,
            facebook,
            this.chkKnown.checked,
            this.cmbTypeOfContact.value,
            this.cmbGender.value,
            hobbies,
            parsedDate,
            this.txaComments.value.trim()
        );

        alert(`Contact Saved Successfully:\n\n${contact.toString()}`);
        this.clearForm();
    }
}

window.addEventListener('DOMContentLoaded', () => new FrmContacts());