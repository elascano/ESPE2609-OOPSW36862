import * as readlineSync from 'readline-sync';
import { Glasses } from '../model/Glasses';
import { GlassesController } from '../controller/GlassesController';

export class GlassesView {
    private controller: GlassesController;

    constructor() {
        this.controller = new GlassesController();
    }

    public start(): void {
        let option: number = 0;

        do {
            console.log("\n--- GLASSES MANAGEMENT SYSTEM ---");
            console.log("1. Create and Save Glasses");
            console.log("2. Read All Glasses from JSON");
            console.log("3. Exit");

            option = readlineSync.questionInt("Select an option: ");

            switch (option) {
                case 1:
                    const id = readlineSync.question("Enter ID: ");
                    const brand = readlineSync.question("Enter Brand: ");
                    const frameType = readlineSync.question("Enter Frame Type (e.g., Aviator, Wayfarer): ");
                    const price = readlineSync.questionFloat("Enter Price: ");

                    const newGlasses = new Glasses(id, brand, frameType, price);
                    this.controller.createGlasses(newGlasses);
                    console.log("Glasses successfully saved to JSON file.");
                    break;

                case 2:
                    const glassesList = this.controller.readGlasses();
                    console.log("\n--- REGISTERED GLASSES ---");
                    if (glassesList.length === 0) {
                        console.log("No records found.");
                    } else {
                        glassesList.forEach((g, index) => {
                            console.log(`[${index + 1}] ID: ${g.getId()} | Brand: ${g.getBrand()} | Frame: ${g.getFrameType()} | Price: $${g.getPrice()}`);
                        });
                    }
                    break;

                case 3:
                    console.log("Exiting program...");
                    break;

                default:
                    console.log("Invalid option, try again.");
            }
        } while (option !== 3);
    }
}

const app = new GlassesView();
app.start();