/**
 * @author Andrés Pachacama, ByteWorks, @ESPE
 */
export class Location {
    private _description: string;

    public toString(): string {
        return "Location{" + "description=" + this._description + "}";
    }

    constructor(description: string) {
        this._description = description;
    }

    public get description(): string {
        return this._description;
    }

    public set description(description: string) {
        this._description = description;
    }
}
