const express = require('express');
const customer = require('../models/customer');
const router = express.Router();

router.get("/customers", async (req, res) => {
    try {
        const customers = await customer.find();
        res.json(customers);
    } catch (err) {
        res.status(500).json({ message: err.message });
    }
});

router.get("/customers/stats", async (req, res) => {
    try {
        const customers = await customer.find();

        if (customers.length === 0) {
            return res.status(404).json({ message: "No hay clientes registrados para calcular estadísticas." });
        }

        const totalSpent = customers.reduce((sum, c) => sum + (c.moneySpent || 0), 0);
        const averageSpent = totalSpent / customers.length;

        const topSpender = customers.reduce((max, c) => 
            (c.moneySpent || 0) > (max.moneySpent || 0) ? c : max
        , customers[0]);

        const totalAge = customers.reduce((sum, c) => sum + (c.age || 0), 0);
        const averageAge = totalAge / customers.length;

        const oldestCustomer = customers.reduce((oldest, c) => 
            (c.age || 0) > (oldest.age || 0) ? c : oldest
        , customers[0]);

        const youngestCustomer = customers.reduce((youngest, c) => 
            (c.age || 0) < (youngest.age || 0) ? c : youngest
        , customers[0]);

        res.json({
            totalCustomers: customers.length,
            totalSpent: Number(totalSpent.toFixed(2)),
            averageSpent: Number(averageSpent.toFixed(2)),
            topSpender: {
                id: topSpender.id,
                name: topSpender.name,
                moneySpent: topSpender.moneySpent
            },
            averageAge: Number(averageAge.toFixed(2)),
            oldestCustomer: {
                id: oldestCustomer.id,
                name: oldestCustomer.name,
                age: oldestCustomer.age
            },
            youngestCustomer: {
                id: youngestCustomer.id,
                name: youngestCustomer.name,
                age: youngestCustomer.age
            }
        });

    } catch (err) {
        res.status(500).json({ message: err.message });
    }
});

module.exports = router;