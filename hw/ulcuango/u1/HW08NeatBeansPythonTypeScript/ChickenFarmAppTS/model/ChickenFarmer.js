"use strict";
Object.defineProperty(exports, "__esModule", { value: true });
exports.ChickenFarmer = void 0;
/**
 * @author Josselyn Ulcuango, Codelex, @ESPE
 */
class ChickenFarmer {
    constructor(id, name, chickenCoop) {
        this.id = id;
        this.name = name;
        this.chickenCoop = chickenCoop;
    }
    feedChickens() {
        console.log(`Farmer ${this.name} is feeding the chickens in coop ${this.chickenCoop.getId()}`);
    }
    collectEggs() {
        console.log(`Farmer ${this.name} is collecting eggs from coop ${this.chickenCoop.getId()}`);
    }
    toString() {
        return `ChickenFarmer{id=${this.id}, name=${this.name}, chickenCoopId=${this.chickenCoop.getId()}}`;
    }
    getId() { return this.id; }
    setId(id) { this.id = id; }
    getName() { return this.name; }
    setName(name) { this.name = name; }
    getChickenCoop() { return this.chickenCoop; }
    setChickenCoop(chickenCoop) { this.chickenCoop = chickenCoop; }
}
exports.ChickenFarmer = ChickenFarmer;
