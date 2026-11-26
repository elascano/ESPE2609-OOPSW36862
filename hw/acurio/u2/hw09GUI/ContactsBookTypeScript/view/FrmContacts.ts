class FrmContacts {
    showForm(): void {
        const app = document.getElementById("app");

        if (app) {
            app.innerHTML = `
                <h2>CONTACT</h2>
                <div class="columns">
                    <div>
                        <div class="field"><label for="id">ID:</label><input id="id" type="text"></div>
                        <div class="field"><label for="fullName">Full Name:</label><input id="fullName" type="text"></div>
                        <div class="field"><label for="email">E-mail:</label><input id="email" type="text"></div>
                        <div class="field"><label for="phone">Cell Phone Number:</label><input id="phone" type="text"></div>
                        <div class="field"><label for="instagram">Instagram:</label><input id="instagram" type="text"></div>
                        <div class="field"><label for="facebook">Facebook:</label><input id="facebook" type="text"></div>
                        <div class="field"><label for="known">Known:</label><div><input id="known" type="checkbox"> Yes</div></div>
                    </div>
                    <div>
                        <div class="field">
                            <label for="type">Type:</label>
                            <select id="type">
                                <option>Friend</option>
                                <option>Family</option>
                                <option>Job Mate</option>
                                <option>Team Mate</option>
                                <option>High School</option>
                                <option>Gym</option>
                            </select>
                        </div>
                        <div class="field">
                            <label for="gender">Gender:</label>
                            <select id="gender">
                                <option>Male</option>
                                <option>Female</option>
                            </select>
                        </div>
                        <div class="field">
                            <label for="hobbies">Hobbies:</label>
                            <select id="hobbies" multiple size="8">
                                <option>Play the Guitar</option>
                                <option>Watch movies</option>
                                <option>Play Soccer</option>
                                <option>Play Basketball</option>
                                <option>Cooking</option>
                                <option>Reading</option>
                                <option>Running</option>
                                <option>Swimming</option>
                            </select>
                        </div>
                        <div class="field"><label for="birthDate">BirthDate:</label><input id="birthDate" type="text"></div>
                        <div class="field"><label for="comments">Comments:</label><textarea id="comments" rows="4"></textarea></div>
                    </div>
                </div>
            `;
        }
    }
}

const form = new FrmContacts();
form.showForm();
