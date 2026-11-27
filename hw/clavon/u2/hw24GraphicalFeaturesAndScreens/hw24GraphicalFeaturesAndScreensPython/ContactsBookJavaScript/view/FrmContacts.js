import { Contact } from "../model/Contact.js";
import { FrmContactsBook } from "./FrmContactsBook.js";

export class FrmContacts {
    constructor(container) {
        this.container = container;
    }

    render() {
        this.container.innerHTML = `
            <div class="window contacts-window">
                <div class="title-bar">FrmContacts</div>
                <div class="contacts-header">ADD CONTACT</div>
                <div class="contacts-grid">
                    <div class="form-col">
                        <label for="txtId">id:</label>
                        <input type="text" id="txtId">

                        <label for="txtFullName">Full Name:</label>
                        <input type="text" id="txtFullName">

                        <label for="txtEmail">Email:</label>
                        <input type="text" id="txtEmail">

                        <label for="txtCellPhoneNumber">Cell Phone Number:</label>
                        <input type="text" id="txtCellPhoneNumber">

                        <label for="txtInstagram">Instagram:</label>
                        <input type="text" id="txtInstagram">

                        <label for="txtFacebook">Facebook:</label>
                        <input type="text" id="txtFacebook">

                        <label for="chkisKnown">Know:</label>
                        <div>
                            <input type="checkbox" id="chkisKnown">
                            <label for="chkisKnown" style="text-align:left;">Yes</label>
                        </div>
                    </div>

                    <div class="form-col">
                        <label for="cmbType">Type:</label>
                        <select id="cmbType">
                            <option value="Friend">Friend</option>
                            <option value="Family">Family</option>
                            <option value="Job Mate">Job Mate</option>
                            <option value="Team Mate">Team Mate</option>
                            <option value="High School">High School</option>
                            <option value=" "> </option>
                        </select>

                        <label for="cmbGender">Gender:</label>
                        <select id="cmbGender">
                            <option value="M">M</option>
                            <option value="F">F</option>
                        </select>

                        <label for="listHobbies">Hobbies:</label>
                        <select id="listHobbies" multiple>
                            <option value="Play the guitar">Play the guitar</option>
                            <option value="Watch movies">Watch movies</option>
                            <option value="Play soccer">Play soccer</option>
                            <option value="Play Basketball">Play Basketball</option>
                            <option value="Reading">Reading</option>
                            <option value="Cooking">Cooking</option>
                            <option value="Swiming">Swiming</option>
                            <option value=" "> </option>
                        </select>

                        <label for="txtBirthDate">Birth Date:</label>
                        <input type="text" id="txtBirthDate">

                        <label for="txtComments">Comments:</label>
                        <textarea id="txtComments"></textarea>
                    </div>
                </div>
                <div class="contacts-buttons">
                    <button id="btnSave">Save</button>
                    <button id="btnCancel">Cancel</button>
                    <button id="btmSystem">System</button>
                </div>
            </div>
        `;

        document.getElementById("btnSave").addEventListener("click", () => {
            const id = parseInt(document.getElementById("txtId").value, 10);
            const fullName = document.getElementById("txtFullName").value;
            const email = document.getElementById("txtEmail").value;
            const cellPhoneNumber = document.getElementById("txtCellPhoneNumber").value;
            const instagram = document.getElementById("txtInstagram").value;
            const facebook = document.getElementById("txtFacebook").value;
            const isKnown = document.getElementById("chkisKnown").checked;
            const type = document.getElementById("cmbType").value;
            const gender = document.getElementById("cmbGender").value.charAt(0);
            const listHobbies = document.getElementById("listHobbies");
            const hobbies = Array.from(listHobbies.selectedOptions).map(opt => opt.value);
            const birthDate = new Date().toString();
            const comments = document.getElementById("txtComments").value;

            const contact = new Contact(
                id, fullName, email, cellPhoneNumber,
                instagram, facebook, isKnown, type,
                gender, hobbies, birthDate, comments
            );

            alert(contact.toString());
        });

        document.getElementById("btmSystem").addEventListener("click", () => {
            const frmContactsBook = new FrmContactsBook(this.container);
            frmContactsBook.render();
        });
    }
}