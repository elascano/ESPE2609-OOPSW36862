import * as fs from "fs";
import { Chicken } from "../model/Chicken";
import { ChickenCoop } from "../model/ChickenCoop";

/**
 * Persistence: saves the chickens of the coop in a text file and reads them again.
 * Every line of the file is one chicken, with its data separated by ";".
 *
 * @author Paucar Matias, ByteWorks, @ESPE
 */
export class FileManager {

    public static readonly FILE_NAME = "chickens.csv";
    private static readonly SEPARATOR = ";";
    private static readonly HEADER = "id;name;color;yearOfBirth;monthOfBirth;dayOfBirth;molting";

    /**
     * Writes ALL the chickens of the coop in the file.
     * The file is created again every time, so it always has the current chickens:
     * the ones that were already there, the new ones, and without the removed ones.
     */
    public static save(coop: ChickenCoop): void {
        const lines: string[] = [FileManager.HEADER];

        coop.resetIteration();
        while (coop.hasNext()) {
            const chicken = coop.next()!;
            const bornOnDate = chicken.getBornOnDate();

            // we save the date as year, month and day (the same numbers the customer wrote)
            // getUTCMonth() starts in 0, so we add 1
            lines.push(chicken.getId() + FileManager.SEPARATOR
                + FileManager.clean(chicken.getName()) + FileManager.SEPARATOR
                + FileManager.clean(chicken.getColor()) + FileManager.SEPARATOR
                + bornOnDate.getUTCFullYear() + FileManager.SEPARATOR
                + (bornOnDate.getUTCMonth() + 1) + FileManager.SEPARATOR
                + bornOnDate.getUTCDate() + FileManager.SEPARATOR
                + chicken.isMolting());
        }

        try {
            fs.writeFileSync(FileManager.FILE_NAME, lines.join("\n") + "\n", "utf-8");
        } catch (error) {
            console.log("Error: the file " + FileManager.FILE_NAME + " could not be saved: " + error);
        }
    }

    /**
     * Reads the file and returns a coop with the chickens that are saved there.
     * If the file does not exist yet, it returns an empty coop.
     */
    public static load(chickenCoopId: number): ChickenCoop {
        const coop = new ChickenCoop(chickenCoopId);

        if (!fs.existsSync(FileManager.FILE_NAME)) {
            return coop;
        }

        let content: string;
        try {
            content = fs.readFileSync(FileManager.FILE_NAME, "utf-8");
        } catch (error) {
            console.log("Error: the file " + FileManager.FILE_NAME + " could not be read: " + error);
            return coop;
        }

        const lines = content.split(/\r?\n/);
        for (let i = 0; i < lines.length; i++) {
            const line = lines[i].trim();
            const lineNumber = i + 1;

            // we skip the header and the empty lines
            if (lineNumber === 1 || line === "") {
                continue;
            }

            const chicken = FileManager.toChicken(line);
            if (chicken === null) {
                console.log("Warning: line " + lineNumber + " of " + FileManager.FILE_NAME
                    + " is not valid and was skipped");
            } else if (!coop.add(chicken)) {
                console.log("Warning: the coop is full, the other chickens of the file were not loaded");
                break;
            }
        }

        return coop;
    }

    /**
     * Converts one line of the file in a Chicken. Returns null if the line has an error.
     */
    private static toChicken(line: string): Chicken | null {
        const data = line.split(FileManager.SEPARATOR);
        if (data.length !== 7) {
            return null;
        }

        const id = Number(data[0]);
        const year = Number(data[3]);
        const month = Number(data[4]);
        const day = Number(data[5]);
        if (![id, year, month, day].every(Number.isInteger)) {
            return null;
        }

        // month - 1 because in JavaScript the months start in 0
        const bornOnDate = new Date(Date.UTC(year, month - 1, day));
        const molting = data[6].trim().toLowerCase() === "true";
        return new Chicken(id, data[1], data[2], bornOnDate, molting);
    }

    /**
     * The ";" separates the data in the file, so it can not be inside a name or a color.
     */
    private static clean(text: string): string {
        return text.split(FileManager.SEPARATOR).join(",");
    }
}
