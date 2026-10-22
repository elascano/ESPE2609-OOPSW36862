"use strict";
Object.defineProperty(exports, "__esModule", { value: true });
exports.Chicken = void 0;
const Egg_1 = require("./Egg");
const Poop_1 = require("./Poop");
class Chicken {
    constructor(id, name, color, bornOnDate, molting) {
        this.id = id;
        this.name = name;
        this.color = color;
        this.bornOnDate = bornOnDate;
        this.ageInYears = 0;
        this.molting = molting;
    }
    doStuff() {
        this.cluck();
        this.wander();
        this.eat();
        this.drink(1.3);
        console.log("poop ----" + this.poop());
        console.log("egg----" + this.layAnEgg());
    }
    cluck() {
        console.log("The chicken " + this.name + " is clucking");
    }
    wander() {
        console.log("The chicken " + this.name + " is wandering");
    }
    eat() {
        console.log("The chicken " + this.name + " is eating");
    }
    drink(amountOfWater) {
        console.log("The chicken " + this.name + " is drinking " + amountOfWater + " ml of water");
    }
    poop() {
        return new Poop_1.Poop(2, "green", "M");
    }
    layAnEgg() {
        return new Egg_1.Egg(2, "S");
    }
    computeAgeInYears() {
        const today = new Date();
        let age = today.getFullYear() - this.bornOnDate.getFullYear();
        const currentMonth = today.getMonth();
        const birthMonth = this.bornOnDate.getMonth();
        if (currentMonth < birthMonth || (currentMonth === birthMonth && today.getDate() < this.bornOnDate.getDate())) {
            age--;
        }
        this.ageInYears = age;
        return this.ageInYears;
    }
    toString() {
        return "Chicken{" + "id=" + this.id + ", name=" + this.name + ", color=" + this.color + ", bornOnDate=" + this.bornOnDate.toISOString().split("T")[0] + ", age=" + this.computeAgeInYears() + ", molting=" + this.molting + '}';
    }
}
exports.Chicken = Chicken;
//# sourceMappingURL=Chicken.js.map