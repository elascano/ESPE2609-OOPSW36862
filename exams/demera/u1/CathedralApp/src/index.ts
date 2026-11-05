import * as path from "path";
import { CathedralRepository } from "./repository/CathedralRepository";
import { JsonFileStorage } from "./storage/JsonFileStorage";
import { CathedralMenu } from "./view/CathedralMenu";
import { ConsoleInput } from "./view/ConsoleInput";

const CATHEDRALS_FILE_PATH = path.join(__dirname, "..", "data", "cathedrals.json");

const storage = new JsonFileStorage(CATHEDRALS_FILE_PATH);
const repository = new CathedralRepository(storage);
repository.loadFromFile();
const menu = new CathedralMenu(repository, new ConsoleInput());

menu.run();
