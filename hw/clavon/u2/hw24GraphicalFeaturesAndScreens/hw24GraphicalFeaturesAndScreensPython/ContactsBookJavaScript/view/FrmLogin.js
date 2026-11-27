import { FrmContactsBook } from "./FrmContactsBook.js";

export class FrmLogin {
    constructor(container) {
        this.container = container;
    }

    render() {
        this.container.innerHTML = `
            <div class="window login-window">
                <div class="title-bar">FrmLogin</div>
                <div class="login-header">LOGIN TO CONTACTS</div>
                <div class="login-form">
                    <label for="txtUserName">User Name:</label>
                    <input type="text" id="txtUserName">

                    <label for="pwPassword">Password:</label>
                    <input type="password" id="pwPassword">

                    <label for="cmbUserType">Type:</label>
                    <select id="cmbUserType">
                        <option value="Manager">Manager</option>
                        <option value="Friend">Friend</option>
                    </select>
                </div>
                <div class="login-buttons">
                    <button id="btnLogin">Login</button>
                    <button id="btnCancel">Cancel</button>
                </div>
            </div>
        `;

        const txtUserName = document.getElementById("txtUserName");
        const pwPassword = document.getElementById("pwPassword");

        const validateUserAndPassword = () => {
            const user = "David";
            const password = "Clavon";

            if (txtUserName.value === user && pwPassword.value === password) {
                const frmContactsBook = new FrmContactsBook(this.container);
                frmContactsBook.render();
            } else {
                alert("User or password are wrong");
            }
        };

        document.getElementById("btnLogin").addEventListener("click", validateUserAndPassword);
        pwPassword.addEventListener("keydown", (event) => {
            if (event.key === "Enter") {
                validateUserAndPassword();
            }
        });
    }
}