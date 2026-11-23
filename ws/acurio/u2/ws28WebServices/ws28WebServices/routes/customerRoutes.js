const express = require("express");
const customer = require("../models/customer");
const router = express.Router();

router.get("/customers", async (req, res) => {
  try {
    const customers = await customer.find();
    res.json(customers);
  } catch (err) {
    res.status(500).json({ message: err.message });
  }
});

router.get("/customer/:id", async (req, res) => {
  try {
    const customerObject = await customer.findOne({ id: Number(req.params.id) });
    if (customerObject == null) {
      return res.status(404).json({ message: "Customer not found" });
    }
    res.json(customerObject);
  } catch (err) {
    res.status(500).json({ message: err.message });
  }
});

module.exports = router;