import { ContactsSplashScreen } from "./view/ContactsSplashScreen.js";

const appContainer = document.getElementById("app");
const splashScreen = new ContactsSplashScreen(appContainer);
splashScreen.render();