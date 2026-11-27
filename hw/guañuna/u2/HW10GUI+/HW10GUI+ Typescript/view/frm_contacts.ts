import { Contact } from '../model/contact.js';

export class FrmContacts {
    private errorLog: string[] = [];

    constructor() {
        // Referencias a los elementos del HTML
        const btnSave = document.getElementById("btnSave") as HTMLButtonElement;
        const btnCancel = document.getElementById("btnCancel") as HTMLButtonElement;
        const menuNuevo = document.getElementById("menu-nuevo") as HTMLAnchorElement;
        const menuSalir = document.getElementById("menu-salir") as HTMLAnchorElement;
        const menuErrores = document.getElementById("menu-errores") as HTMLAnchorElement;
        const menuAcerca = document.getElementById("menu-acerca") as HTMLAnchorElement;

        // Eventos de los botones
        btnSave.addEventListener("click", () => this.guardarContacto(menuErrores));
        btnCancel.addEventListener("click", () => this.limpiarFormulario());
        menuNuevo.addEventListener("click", () => this.limpiarFormulario());

        menuSalir.addEventListener("click", () => {
            alert("Cerrando aplicación...");
        });

        menuErrores.addEventListener("click", () => {
            if (this.errorLog.length === 0) {
                alert("No hay errores registrados.");
            } else {
                alert("Historial de errores:\n\n" + this.errorLog.join("\n"));
            }
        });

        menuAcerca.addEventListener("click", () => {
            alert("ContactBook v1.0\nAutor: Cristopher Guañuna\nESPE - Runtime Dynamics");
        });
    }

    private registrarError(mensaje: string, menuErrores: HTMLAnchorElement): void {
        const hora = new Date().toLocaleTimeString();
        this.errorLog.push(`[${hora}] ${mensaje}`);
        menuErrores.innerText = `Errores (${this.errorLog.length})`;
        alert(`❌ Error de validación:\n${mensaje}`);
    }

    private limpiarFormulario(): void {
        (document.getElementById("txtId") as HTMLInputElement).value = "";
        (document.getElementById("txtFullName") as HTMLInputElement).value = "";
        (document.getElementById("txtEmail") as HTMLInputElement).value = "";
        (document.getElementById("txtCellphone") as HTMLInputElement).value = "";
        (document.getElementById("txtInstagram") as HTMLInputElement).value = "";
        (document.getElementById("txtFacebook") as HTMLInputElement).value = "";
        (document.getElementById("chkIsKnown") as HTMLInputElement).checked = false;
        (document.getElementById("cmbType") as HTMLSelectElement).selectedIndex = 0;
        (document.getElementById("cmbGender") as HTMLSelectElement).selectedIndex = 0;
        (document.getElementById("lstHobbies") as HTMLSelectElement).selectedIndex = -1;
        (document.getElementById("txtBirthDate") as HTMLInputElement).value = "";
        (document.getElementById("txtComments") as HTMLTextAreaElement).value = "";
    }

    private guardarContacto(menuErrores: HTMLAnchorElement): void {
        // 1. Validar ID
        const idString = (document.getElementById("txtId") as HTMLInputElement).value.trim();
        if (idString === "" || isNaN(parseInt(idString, 10))) {
            this.registrarError("El ID debe ser un número entero.", menuErrores);
            return;
        }
        const id = parseInt(idString, 10);

        // 2. Validar Nombre
        const fullName = (document.getElementById("txtFullName") as HTMLInputElement).value.trim();
        if (!/^[a-zA-ZáéíóúÁÉÍÓÚñÑ ]+$/.test(fullName)) {
            this.registrarError("El nombre solo debe contener letras y espacios.", menuErrores);
            return;
        }

        // 3. Validar Email
        const email = (document.getElementById("txtEmail") as HTMLInputElement).value.trim();
        if (!/^[\w\-\.]+@([\w\-]+\.)+[\w\-]{2,4}$/.test(email)) {
            this.registrarError("Ingrese un correo electrónico válido.", menuErrores);
            return;
        }

        // 4. Validar Celular
        const cellphone = (document.getElementById("txtCellphone") as HTMLInputElement).value.trim();
        if (!/^\d{10}$/.test(cellphone)) {
            this.registrarError("El número de celular debe tener 10 dígitos.", menuErrores);
            return;
        }

        // 5. Validar Fecha
        const fechaString = (document.getElementById("txtBirthDate") as HTMLInputElement).value;
        if (!fechaString) {
            this.registrarError("Debe seleccionar una fecha de nacimiento.", menuErrores);
            return;
        }
        const birthDate = new Date(fechaString);

        // 6. Obtener datos restantes
        const instagram = (document.getElementById("txtInstagram") as HTMLInputElement).value.trim();
        const facebook = (document.getElementById("txtFacebook") as HTMLInputElement).value.trim();
        const isKnown = (document.getElementById("chkIsKnown") as HTMLInputElement).checked;
        const typeContact = (document.getElementById("cmbType") as HTMLSelectElement).value;
        const gender = (document.getElementById("cmbGender") as HTMLSelectElement).value;
        const comments = (document.getElementById("txtComments") as HTMLTextAreaElement).value.trim();

        const lstHobbies = document.getElementById("lstHobbies") as HTMLSelectElement;
        const hobbies: string[] = [];
        for (let i = 0; i < lstHobbies.options.length; i++) {
            if (lstHobbies.options[i].selected) {
                hobbies.push(lstHobbies.options[i].value);
            }
        }

        // Crear el contacto y mostrarlo
        const contacto = new Contact(id, fullName, email, cellphone, instagram, facebook, isKnown, typeContact, gender, hobbies, birthDate, comments);
        alert("✅ Contacto Guardado:\n" + contacto.toString());
    }
}