export class CompactDisc {
    private id: string;
    private title: string;
    private artist: string;
    private year: number;

    constructor(id: string, title: string, artist: string, year: number) {
        this.id = id;
        this.title = title;
        this.artist = artist;
        this.year = year;
    }

    public getId(): string {
        return this.id;
    }

    public setId(id: string): void {
        this.id = id;
    }

    public getTitle(): string {
        return this.title;
    }

    public setTitle(title: string): void {
        this.title = title;
    }

    public getArtist(): string {
        return this.artist;
    }

    public setArtist(artist: string): void {
        this.artist = artist;
    }

    public getYear(): number {
        return this.year;
    }

    public setYear(year: number): void {
        this.year = year;
    }

    public toJSON() {
        return {
            id: this.id,
            title: this.title,
            artist: this.artist,
            year: this.year
        };
    }
}