import { FrmContacts } from "./FrmContacts.js";

export class FrmContactsBook {
    constructor(container) {
        this.container = container;
    }

    render() {
        this.container.innerHTML = `
            <div class="window book-window">
                <div class="title-bar">FrmContactsBook</div>
                <div class="menu-bar">
                    <div class="menu-item">
                        Contacts Book
                        <div class="dropdown">
                            <div class="dropdown-option" id="itmAbout">About</div>
                            <div class="dropdown-option" id="itmExit">Exit</div>
                        </div>
                    </div>
                    <div class="menu-item">
                        Contact
                        <div class="dropdown">
                            <div class="dropdown-option" id="itmAddContact">Add</div>
                            <div class="dropdown-option" id="itmSearch">Search</div>
                            <div class="dropdown-option">
                                Reports &#9656;
                                <div class="submenu">
                                    <div class="dropdown-option">Print out</div>
                                    <div class="dropdown-option">Save as PDF</div>
                                </div>
                            </div>
                        </div>
                    </div>
                    <div class="menu-item">
                        Help
                        <div class="dropdown">
                            <div class="dropdown-option">
                                Use Guide &#9656;
                                <div class="submenu">
                                    <div class="dropdown-option">On Line</div>
                                    <div class="dropdown-option">Print out</div>
                                </div>
                            </div>
                        </div>
                    </div>
                </div>
                <div class="book-content"></div>
            </div>
        `;

        document.getElementById("itmAddContact").addEventListener("click", () => {
            const frmContacts = new FrmContacts(this.container);
            frmContacts.render();
        });

        document.getElementById("itmExit").addEventListener("click", () => {
            window.close();
            this.container.innerHTML = "";
        });
    }
}