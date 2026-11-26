const express = require("express");
const Costumer = require("../models/costumer");
const router = express.Router();

router.get("/costumers", async (req, res) => {
    try {
        const costumers = await Costumer.find();

        if (costumers.length === 0) {
            return res.json({
                totalCustomers: 0,
                totalExpenses: 0,
                averageExpenses: 0,
                averageAge: 0,
                youngestCustomer: null,
                oldestCustomer: null,
                data: []
            });
        }

        let totalExpenses = 0;
        let totalAge = 0;
        let youngestCustomer = costumers[0];
        let oldestCustomer = costumers[0];

        costumers.forEach(costumer => {
            totalExpenses += costumer.moneyspend || 0;
            totalAge += costumer.age || 0;

            if (costumer.age < youngestCustomer.age) {
                youngestCustomer = costumer;
            }

            if (costumer.age > oldestCustomer.age) {
                oldestCustomer = costumer;
            }
        });

        const averageExpenses = totalExpenses / costumers.length;
        const averageAge = totalAge / costumers.length;

        res.json({
            totalCustomers: costumers.length,
            totalExpenses: parseFloat(totalExpenses.toFixed(2)),
            averageExpenses: parseFloat(averageExpenses.toFixed(2)),
            averageAge: parseFloat(averageAge.toFixed(2)),
            youngestCustomer: youngestCustomer.name,
            oldestCustomer: oldestCustomer.name,
            data: costumers
        });

    } catch (err) {
        res.status(500).json({ message: err.message });
    }
});

module.exports = router;