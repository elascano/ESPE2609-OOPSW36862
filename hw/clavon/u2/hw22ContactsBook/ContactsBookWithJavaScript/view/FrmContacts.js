export class FrmContacts {
    constructor(containerId) {
        this.container = document.getElementById(containerId);
        this.initComponents();
    }

    initComponents() {
        this.container.innerHTML = `
            <div class="window">
                <div id="pnlTitle" class="pnl-title">
                    <label id="jLabel1">CONTACT</label>
                </div>

                <div id="cmbTypeOfContact" class="pnl-form">
                    <div class="col-left">
                        <div class="form-row">
                            <label id="jLabel2" for="txtId">id:</label>
                            <input type="text" id="txtId">
                        </div>
                        <div class="form-row">
                            <label id="jLabel3" for="txtFullName">Full Name:</label>
                            <input type="text" id="txtFullName">
                        </div>
                        <div class="form-row">
                            <label id="jLabel4" for="txtEmail">Email:</label>
                            <input type="text" id="txtEmail">
                        </div>
                        <div class="form-row">
                            <label id="jLabel5" for="txtCellPhoneNumber">Cell Phone Number:</label>
                            <input type="text" id="txtCellPhoneNumber">
                        </div>
                        <div class="form-row">
                            <label id="jLabel6" for="txtInstagram">Instagram:</label>
                            <input type="text" id="txtInstagram">
                        </div>
                        <div class="form-row">
                            <label id="jLabel7" for="txtFacebook">Facebook:</label>
                            <input type="text" id="txtFacebook">
                        </div>
                        <div class="form-row">
                            <label id="jLabel8" for="chkisKnown">Know:</label>
                            <div class="checkbox-wrapper">
                                <input type="checkbox" id="chkisKnown">
                                <span>Yes</span>
                            </div>
                        </div>
                    </div>

                    <div class="col-right">
                        <div class="form-row">
                            <label id="jLabel9" for="cmbType">Type:</label>
                            <select id="cmbType">
                                <option value="Friend">Friend</option>
                                <option value="Family">Family</option>
                                <option value="Job Mate">Job Mate</option>
                                <option value="Team Mate">Team Mate</option>
                                <option value="High School">High School</option>
                            </select>
                        </div>
                        <div class="form-row">
                            <label id="jLabel10" for="cmbGender">Gender:</label>
                            <select id="cmbGender">
                                <option value="Mate">Mate</option>
                                <option value="Formate">Formate</option>
                            </select>
                        </div>
                        <div class="form-row align-top">
                            <label id="jLabel11" for="listHobbies">Hobbies:</label>
                            <select id="listHobbies" multiple size="7">
                                <option value="Play the guitar">Play the guitar</option>
                                <option value="Watch movies">Watch movies</option>
                                <option value="Play soccer">Play soccer</option>
                                <option value="Play Basketball">Play Basketball</option>
                                <option value="Reading">Reading</option>
                                <option value="Cooking">Cooking</option>
                                <option value="Swiming">Swiming</option>
                            </select>
                        </div>
                        <div class="form-row">
                            <label id="jLabel12" for="txtBirthDate">Birth Date:</label>
                            <input type="text" id="txtBirthDate">
                        </div>
                        <div class="form-row align-top">
                            <label id="jLabel13" for="txtComments">Comments:</label>
                            <textarea id="txtComments" rows="3"></textarea>
                        </div>
                    </div>
                </div>

                <div id="pnlButtons" class="pnl-buttons"></div>
            </div>
        `;

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
        this.pnlButtons = document.getElementById("pnlButtons");
    }
}