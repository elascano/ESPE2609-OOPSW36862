import * as fs from 'fs';
import * as path from 'path';
import { CompactDisc } from '../model/CompactDisc';

class CompactDiscApp {
    private static filePath: string = path.join(__dirname, '../utils/compact_discs.json');

    public static getAllDiscs(): CompactDisc[] {
        try {
            if (!fs.existsSync(CompactDiscApp.filePath)) {
                return [];
            }
            const data = fs.readFileSync(CompactDiscApp.filePath, 'utf8');
            const jsonData = JSON.parse(data);
            return jsonData.map((item: any) => new CompactDisc(item.id, item.title, item.artist, item.year));
        } catch (error) {
            console.error('Error reading the JSON file:', error);
            return [];
        }
    }

    public static saveDisc(disc: CompactDisc): void {
        try {
            const discs = CompactDiscApp.getAllDiscs();
            discs.push(disc);
            const dataToSave = discs.map(d => d.toJSON());
            fs.writeFileSync(CompactDiscApp.filePath, JSON.stringify(dataToSave, null, 2), 'utf8');
            console.log('Compact Disc successfully saved to the JSON file.');
        } catch (error) {
            console.error('Error saving to the JSON file:', error);
        }
    }

    public static main(): void {
        console.log('=== INITIAL LIST OF COMPACT DISCS (From JSON) ===');
        const existingDiscs = CompactDiscApp.getAllDiscs();
        existingDiscs.forEach(disc => {
            console.log(`ID: ${disc.getId()} | Title: ${disc.getTitle()} | Artist: ${disc.getArtist()} | Year: ${disc.getYear()}`);
        });

        console.log('\n=== CREATING AND SAVING A NEW COMPACT DISC ===');
        const newDisc = new CompactDisc('3', 'Dark Side of the Moon', 'Pink Floyd', 1973);
        CompactDiscApp.saveDisc(newDisc);

        console.log('\n=== UPDATED LIST OF COMPACT DISCS ===');
        const updatedDiscs = CompactDiscApp.getAllDiscs();
        updatedDiscs.forEach(disc => {
            console.log(`ID: ${disc.getId()} | Title: ${disc.getTitle()} | Artist: ${disc.getArtist()} | Year: ${disc.getYear()}`);
        });
    }
}

CompactDiscApp.main();