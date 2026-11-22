const express = require("express");
const costumer = require("../models/costumer");
const router = express.Router();

router.get("/costumers", async (req, res) => {
    try {
        const costumers = await costumer.find();
        res.json(costumers); // Enviamos el array, no el modelo
    } catch (err) {
        res.status(500).json({ message: err.message });
    }
});

module.exports = router;