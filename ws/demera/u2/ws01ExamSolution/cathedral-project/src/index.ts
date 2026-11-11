import * as readline from 'readline';
import { Cathedral } from './Cathedral';
import { CathedralManager } from './CathedralManager';

const rl = readline.createInterface({
    input: process.stdin,
    output: process.stdout
});

const manager = new CathedralManager('cathedrals.json');

// Helper to wrap readline prompt in a Promise
function prompt(query: string): Promise<string> {
    return new Promise(resolve => rl.question(query, resolve));
}

async function handleAddCathedral() {
    console.log('\n--- ADD NEW CATHEDRAL ---');

    let id = '';
    while (!id) {
        id = (await prompt('Enter ID (e.g., CATH-004): ')).trim();
        if (!id) {
            console.log('ID cannot be empty.');
        } else if (manager.exists(id)) {
            console.log(`An entry with ID "${id}" already exists. Please use a unique ID.`);
            id = '';
        }
    }

    let name = '';
    while (!name) {
        name = (await prompt('Enter Cathedral Name: ')).trim();
        if (!name) console.log('Name cannot be empty.');
    }

    let location = '';
    while (!location) {
        location = (await prompt('Enter Location (City, Country): ')).trim();
        if (!location) console.log('Location cannot be empty.');
    }

    let yearBuilt = NaN;
    while (isNaN(yearBuilt) || yearBuilt <= 0) {
        const inputYear = await prompt('Enter Year Built (e.g., 1892): ');
        yearBuilt = parseInt(inputYear, 10);
        if (isNaN(yearBuilt) || yearBuilt <= 0) {
            console.log('Please enter a valid positive year number.');
        }
    }

    // Instantiate object and add via manager
    const newCathedral = new Cathedral(id, name, location, yearBuilt);
    manager.addCathedral(newCathedral);
}

function handleListCathedrals() {
    console.log('\n--- CATHEDRAL RECORDS (READ FROM JSON) ---');
    const list = manager.loadFromFile();

    if (list.length === 0) {
        console.log('No cathedrals registered yet.');
        return;
    }

    list.forEach((cathedral, index) => {
        console.log(`[${index + 1}] ID: ${cathedral.getId()}`);
        console.log(`    Name:       ${cathedral.getName()}`);
        console.log(`    Location:   ${cathedral.getLocation()}`);
        console.log(`    Year Built: ${cathedral.getYearBuilt()}`);
        console.log('----------------------------------------------------');
    });
}

async function showMenu() {
    let running = true;

    while (running) {
        console.log('\n================ CATHEDRAL SYSTEM MENU ================');
        console.log('1. Display all cathedrals (Read from JSON)');
        console.log('2. Add a new cathedral (Write to JSON)');
        console.log('3. Exit');
        console.log('=======================================================');

        const option = (await prompt('Select an option (1-3): ')).trim();

        switch (option) {
            case '1':
                handleListCathedrals();
                break;
            case '2':
                await handleAddCathedral();
                break;
            case '3':
                console.log('\nExiting program. Goodbye!');
                running = false;
                rl.close();
                break;
            default:
                console.log('\nInvalid option. Please enter 1, 2, or 3.');
                break;
        }
    }
}

showMenu();