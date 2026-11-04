"use strict";
Object.defineProperty(exports, "__esModule", { value: true });
const Pencil_1 = require("./Pencil");
const PencilManager_1 = require("./PencilManager");
const path = require('node:path');
const readline = require('node:readline/promises');
const process = require('node:process');
const filePath = path.join(__dirname, 'pencils.json');
const terminal = readline.createInterface({ input: process.stdin, output: process.stdout });
const manager = new PencilManager_1.PencilManager(filePath);
async function main() {
    manager.read();
    console.log('PENCIL MANAGER');
    console.log('JSON file: pencils.json (in this project folder)');
    while (true) {
        console.log('\n1. Create a pencil\n2. Read pencils from JSON\n0. Exit');
        const option = (await terminal.question('Choose an option: ')).trim();
        if (option === '0') {
            console.log('Goodbye!');
            return;
        }
        try {
            if (option === '1') {
                const id = Number(await terminal.question('ID: '));
                const brand = await terminal.question('Brand: ');
                const color = await terminal.question('Color: ');
                const hardness = await terminal.question('Hardness: ');
                const pencil = new Pencil_1.Pencil(id, brand, color, hardness);
                manager.add(pencil);
                console.log('Pencil saved in pencils.json.');
            }
            else if (option === '2') {
                const pencils = manager.read();
                console.log(`Read ${pencils.length} pencil(s) from pencils.json.`);
                console.table(pencils);
            }
            else {
                console.log('Invalid option.');
            }
        }
        catch (error) {
            console.log(`Error: ${error.message}`);
        }
    }
}
main().catch(error => {
    console.error(`Cannot read pencils.json: ${error.message}`);
    process.exitCode = 1;
}).finally(() => terminal.close());
