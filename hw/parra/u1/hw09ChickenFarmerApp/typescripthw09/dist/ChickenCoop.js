"use strict";
Object.defineProperty(exports, "__esModule", { value: true });
exports.ChickenCoop = void 0;
const CAPACITY = 10;
class ChickenCoop {
    chickenCoopId;
    chickens = new Array(CAPACITY).fill(null);
    currentIndex = 0;
    constructor(chickenCoopId) {
        this.chickenCoopId = chickenCoopId;
    }
    add(chicken) {
        for (const existing of this.chickens) {
            if (existing !== null && existing.id === chicken.id) {
                console.log(`There is already a chicken with id ${chicken.id}.`);
                return;
            }
        }
        for (let i = 0; i < this.chickens.length; i++) {
            if (this.chickens[i] === null) {
                this.chickens[i] = chicken;
                console.log(`Chicken added: ${chicken.name}`);
                return;
            }
        }
        console.log(`The coop is full (maximum ${this.chickens.length}).`);
    }
    remove(chickenId) {
        for (let i = 0; i < this.chickens.length; i++) {
            const current = this.chickens[i];
            if (current !== null && current.id === chickenId) {
                const name = current.name;
                for (let j = i; j < this.chickens.length - 1; j++) {
                    this.chickens[j] = this.chickens[j + 1];
                }
                this.chickens[this.chickens.length - 1] = null;
                console.log(`Chicken removed: ${name}`);
                return;
            }
        }
        console.log(`No chicken found with id ${chickenId}.`);
    }
    resetIteration() {
        this.currentIndex = 0;
        console.log("Iteration reset.");
    }
    next() {
        if (this.currentIndex < this.chickens.length && this.chickens[this.currentIndex] !== null) {
            console.log(String(this.chickens[this.currentIndex]));
            this.currentIndex++;
        }
        else {
            console.log("No more chickens. Reset the iteration to start over.");
        }
    }
    toString() {
        return (`ChickenCoop{chickenCoopId=${this.chickenCoopId}, ` +
            `chickens=[${this.chickens.map(String).join(", ")}], currentIndex=${this.currentIndex}}`);
    }
}
exports.ChickenCoop = ChickenCoop;
