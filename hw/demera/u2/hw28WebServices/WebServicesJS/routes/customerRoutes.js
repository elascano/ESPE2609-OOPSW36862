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
router.get("/customers/analytics", async (req, res) => {
  try {
    const customers = await customer.find();
    res.json({
      totalSpent: calculateTotalSpent(customers),
      averageSpent: calculateAverageSpent(customers),
      highestSpender: findHighestSpender(customers),
    });
  } catch (err) {
    res.status(500).json({ message: err.message });
  }
});
router.get("/customers/:id", async (req, res) => {
  try {
    const foundCustomer = await customer.findOne({ id: Number(req.params.id) });
    if (!foundCustomer) {
      return res.status(404).json({ message: "Customer not found" });
    }
    res.json(foundCustomer);
  } catch (err) {
    res.status(500).json({ message: err.message });
  }
});

router.post("/customers", async (req, res) => {
  const newCustomer = new customer({
    id: req.body.id,
    name: req.body.name,
    age: req.body.age,
    moneySpent: req.body.moneySpent,
  });
  try {
    const savedCustomer = await newCustomer.save();
    res.status(201).json(savedCustomer);
  } catch (err) {
    res.status(400).json({ message: err.message });
  }
});

router.put("/customers/:id", async (req, res) => {
  try {
    const updatedCustomer = await customer.findOneAndUpdate(
      { id: Number(req.params.id) },
      req.body,
      { new: true },
    );
    if (!updatedCustomer) {
      return res.status(404).json({ message: "Customer not found" });
    }
    res.json(updatedCustomer);
  } catch (err) {
    res.status(400).json({ message: err.message });
  }
});

router.delete("/customers/:id", async (req, res) => {
  try {
    const deletedCustomer = await customer.findOneAndDelete({
      id: Number(req.params.id),
    });
    if (!deletedCustomer) {
      return res.status(404).json({ message: "Customer not found" });
    }
    res.json({ message: "Customer deleted" });
  } catch (err) {
    res.status(500).json({ message: err.message });
  }
});

const calculateTotalSpent = (customers) => {
  return customers.reduce(
    (total, currentCustomer) => total + (currentCustomer.moneySpent || 0),
    0,
  );
};

const calculateAverageSpent = (customers) => {
  if (customers.length === 0) return 0;
  return calculateTotalSpent(customers) / customers.length;
};

const findHighestSpender = (customers) => {
  if (customers.length === 0) return null;
  return customers.reduce((highest, current) =>
    (current.moneySpent || 0) > (highest.moneySpent || 0) ? current : highest,
  );
};

module.exports = router;
