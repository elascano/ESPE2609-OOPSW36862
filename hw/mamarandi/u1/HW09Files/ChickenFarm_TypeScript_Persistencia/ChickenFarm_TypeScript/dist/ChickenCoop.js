"use strict";
Object.defineProperty(exports, "__esModule", { value: true });
exports.ChickenCoop = void 0;
class ChickenCoop {
    chickenCoopId;
    chickens;
    currentIndex = 0;
    constructor(chickenCoopId, chickens) {
        this.chickenCoopId = chickenCoopId;
        this.chickens = chickens;
    }
    add(chicken) {
        for (let i = 0; i < this.chickens.length; i++) {
            if (this.chickens[i] === null) {
                this.chickens[i] = chicken;
                console.log("Chicken added successfully");
                return;
            }
        }
        console.log("The chicken coop is full");
    }
    remove(chickenId) {
        for (let i = 0; i < this.chickens.length; i++) {
            if (this.chickens[i] !== null && this.chickens[i].getId() === chickenId) {
                for (let j = i; j < this.chickens.length - 1; j++) {
                    this.chickens[j] = this.chickens[j + 1];
                }
                this.chickens[this.chickens.length - 1] = null;
                console.log("Chicken removed successfully");
                return;
            }
        }
        console.log("Chicken not found");
    }
    resetIteration() { this.currentIndex = 0; }
    next() {
        while (this.currentIndex < this.chickens.length) {
            const chicken = this.chickens[this.currentIndex++];
            if (chicken !== null)
                return chicken;
        }
        return null;
    }
    getChickenCoopId() { return this.chickenCoopId; }
    setChickenCoopId(id) { this.chickenCoopId = id; }
    getChickens() { return this.chickens; }
    setChickens(chickens) {
        this.chickens = chickens;
        this.resetIteration();
    }
    toString() {
        return `ChickenCoop{chickenCoopId=${this.chickenCoopId}, chickens=${this.chickens}}`;
    }
}
exports.ChickenCoop = ChickenCoop;
