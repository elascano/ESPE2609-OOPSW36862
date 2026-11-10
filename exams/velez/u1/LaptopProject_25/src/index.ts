import * as readlineSync from 'readline-sync';
import { Laptop } from './models/Laptop';
import { LaptopManager } from './services/LaptopManager';

function main(): void {
    const manager = new LaptopManager();
    let exit = false;

    while (!exit) {
   
        console.log('     LAPTOP MANAGEMENT SYSTEM (JSON)    ');
        console.log('1. Create and Save Laptop to JSON');
        console.log('2. Read and Display Laptops from JSON');
        console.log('3. Exit');
        console.log('----------------------------------------');

        const option = readlineSync.question('Select an option (1-3): ');

        switch (option) {
            case '1':
                console.log('\n--- Enter New Laptop Data ---');
                const id = readlineSync.questionInt('Enter Laptop ID (number): ');
                const brand = readlineSync.question('Enter Laptop Brand: ');
                const model = readlineSync.question('Enter Laptop Model: ');
                const price = readlineSync.questionFloat('Enter Laptop Price: ');

                const newLaptop = new Laptop(id, brand, model, price);
                manager.addLaptop(newLaptop);
                break;

            case '2':
                manager.displayLaptops();
                break;

            case '3':
                console.log('\nExiting program...');
                exit = true;
                break;

            default:
                console.log('\n[ERROR] Invalid option. Please choose 1, 2, or 3.\n');
                break;
        }
    }
}

main();