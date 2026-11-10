import * as readline from 'readline';
import { Glasses } from '../model/Glasses';
import { GlassesManager } from '../model/GlassesManager';

const rl = readline.createInterface({
    input: process.stdin,
    output: process.stdout
});

const manager = new GlassesManager();
manager.loadFromJson();

function showMenu(): void {
    console.log('\n==================================');
    console.log('       GLASSES STORE MENU         ');
    console.log('==================================');
    console.log('1. Add new Glasses');
    console.log('2. Display all Glasses');
    console.log('3. Save and Exit');
    console.log('==================================');
    
    rl.question('Select an option (1-3): ', (choice) => {
        switch (choice.trim()) {
            case '1':
                rl.question('Enter ID: ', (idInput) => {
                    rl.question('Enter Brand: ', (brandInput) => {
                        rl.question('Enter Price: ', (priceInput) => {
                            rl.question('Is it polarized? (y/n): ', (polInput) => {
                                const id = Number(idInput);
                                const price = Number(priceInput);
                                const isPolarized = polInput.trim().toLowerCase() === 'y';

                                const newGlasses = new Glasses(id, brandInput, price, isPolarized);
                                manager.addGlasses(newGlasses);
                                console.log('\n[+] Glasses added successfully!');
                                showMenu();
                            });
                        });
                    });
                });
                break;

            case '2':
                console.log('\n--- REGISTERED GLASSES LIST ---');
                if (manager.glassesList.length === 0) {
                    console.log('No glasses registered yet.');
                } else {
                    manager.glassesList.forEach((glasses) => glasses.displayInfo());
                }
                showMenu();
                break;

            case '3':
                manager.saveToJson();
                console.log('Exiting application. Good luck!\n');
                rl.close();
                break;

            default:
                console.log('\n[-] Invalid option. Please select 1, 2, or 3.');
                showMenu();
                break;
        }
    });
}

showMenu();