import { FrmLogin } from "./FrmLogin.js";

export class ContactsSplashScreen {
    constructor(container) {
        this.container = container;
    }

    render() {
        this.container.innerHTML = `
            <div class="window splash-window">
                <div class="title-bar">ContactsSplashScreen</div>
                <div class="splash-body">
                    <div>David's Contact Book</div>
                    <div>Infinite Loops Developers for fun</div>
                    <div class="splash-meta">
                        <span>(C)2026</span>
                        <span>0.9.0</span>
                    </div>
                    <button id="btnContinue">Continue</button>
                </div>
            </div>
        `;

        document.getElementById("btnContinue").addEventListener("click", () => {
            const frmLogin = new FrmLogin(this.container);
            frmLogin.render();
        });
    }
}