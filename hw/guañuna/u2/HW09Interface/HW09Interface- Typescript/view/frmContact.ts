import { Contact } from "../model/contact";

export class FrmContacts {
    private txtId: HTMLInputElement;
    private txtFullName: HTMLInputElement;
    private txtEmail: HTMLInputElement;
    private txtCellphone: HTMLInputElement;
    private txtInstagram: HTMLInputElement;
    private txtFacebook: HTMLInputElement;
    private chkIsKnown: HTMLInputElement;
    private cmbType: HTMLSelectElement;
    private cmbGender: HTMLSelectElement;
    private lstHobbies: HTMLSelectElement;
    private txtBirthDate: HTMLInputElement;
    private txaComments: HTMLTextAreaElement;

    constructor() {
        this.txtId = document.getElementById("txtId") as HTMLInputElement;
        this.txtFullName = document.getElementById("txtFullName") as HTMLInputElement;
        this.txtEmail = document.getElementById("txtEmail") as HTMLInputElement;
        this.txtCellphone = document.getElementById("txtCellphone") as HTMLInputElement;
        this.txtInstagram = document.getElementById("txtInstagram") as HTMLInputElement;
        this.txtFacebook = document.getElementById("txtFacebook") as HTMLInputElement;
        this.chkIsKnown = document.getElementById("chkIsKnown") as HTMLInputElement;
        this.cmbType = document.getElementById("cmbType") as HTMLSelectElement;
        this.cmbGender = document.getElementById("cmbGender") as HTMLSelectElement;
        this.lstHobbies = document.getElementById("lstHobbies") as HTMLSelectElement;
        this.txtBirthDate = document.getElementById("txtBirthDate") as HTMLInputElement;
        this.txaComments = document.getElementById("txaComments") as HTMLTextAreaElement;

        this.setupEventListeners();
    }

    private setupEventListeners(): void {
        document.getElementById("btnGuardar")?.addEventListener("click", () => this.saveContact());
        document.getElementById("btnLimpiar")?.addEventListener("click", () => this.clearForm());
    }

    private saveContact(): void {
        try {
            const hobbies: string[] = Array.from(this.lstHobbies.selectedOptions).map(opt => opt.value);
            const birthDate = this.txtBirthDate.value ? new Date(this.txtBirthDate.value) : null;

            const contact = new Contact(
                parseInt(this.txtId.value),
                this.txtFullName.value,
                this.txtEmail.value,
                this.txtCellphone.value,
                this.txtInstagram.value,
                this.txtFacebook.value,
                this.chkIsKnown.checked,
                this.cmbType.value,
                this.cmbGender.value,
                hobbies,
                birthDate,
                this.txaComments.value
            );

            alert("Contacto guardado:\n\n" + contact.toString());
        } catch (error) {
            alert("Error: El id debe ser numérico y la fecha debe ser válida.");
        }
    }

    private clearForm(): void {
        this.txtId.value = "";
        this.txtFullName.value = "";
        this.txtEmail.value = "";
        this.txtCellphone.value = "";
        this.txtInstagram.value = "";
        this.txtFacebook.value = "";
        this.chkIsKnown.checked = false;
        this.cmbType.selectedIndex = 0;
        this.cmbGender.selectedIndex = 0;
        this.lstHobbies.selectedIndex = -1;
        this.txtBirthDate.value = "";
        this.txaComments.value = "";
    }
}

// Inicializar la vista
new FrmContacts();