import { CompactDisc } from './CompactDisc';

export class CDManager {
    private cds: CompactDisc[];
    private filePath: string;

    constructor(filePath: string) {
        this.cds = [];
        this.filePath = filePath;
    }

    public addCD(id: string, title: string, artist: string, year: number): void {
        const newCD = new CompactDisc(id, title, artist, year);
        this.cds.push(newCD);
    }

    public saveToJson(): void {
    }

    public readFromJson(): CompactDisc {
        throw new Error("Method not implemented.");
    }

    public getAllCDs(): CompactDisc {
        throw new Error("Method not implemented.");
    }
}