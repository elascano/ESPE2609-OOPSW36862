"use strict";
Object.defineProperty(exports, "__esModule", { value: true });
exports.ChickenCoop = void 0;
/**
 * @author Josselyn Ulcuango, Codelex, @ESPE
 */
class ChickenCoop {
    constructor(id) {
        this.id = id;
        this.chickens = [];
    }
    addChicken(chicken) {
        if (chicken !== null && chicken !== undefined) {
            this.chickens.push(chicken);
            console.log(`Chicken '${chicken.getName()}' added to coop ${this.id}`);
        }
    }
    removeChicken(chickenId) {
        const index = this.chickens.findIndex(c => c.getId() === chickenId);
        if (index !== -1) {
            const removed = this.chickens.splice(index, 1)[0];
            console.log(`Chicken '${removed.getName()}' removed from coop ${this.id}`);
        }
        else {
            console.log(`Chicken with ID ${chickenId} was not found in coop ${this.id}`);
        }
    }
    reset() {
        this.chickens = [];
        console.log(`Coop ${this.id} has been reset.`);
    }
    countChickens() {
        return this.chickens.length;
    }
    toString() {
        let chickensStr = this.chickens.map(c => `  ${c.toString()}`).join("\n");
        return `ChickenCoop{id=${this.id}, chickens=[\n${chickensStr}\n]}`;
    }
    getId() { return this.id; }
    setId(id) { this.id = id; }
    getChickens() { return this.chickens; }
    setChickens(chickens) { this.chickens = chickens; }
}
exports.ChickenCoop = ChickenCoop;
