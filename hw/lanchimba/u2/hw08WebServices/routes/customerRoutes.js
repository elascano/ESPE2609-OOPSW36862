const express = require("express");
const Customer = require("../models/Customer");

const router = express.Router();

/**
 * Helper to validate numeric path parameter :id
 */
function parseNumericId(rawId) {
  const numericId = Number(rawId);
  if (!Number.isInteger(numericId)) {
    return null;
  }
  return numericId;
}

/**
 * GET /api/customers
 * Returns all customers ordered by id ascending.
 */
router.get("/", async (_req, res) => {
  try {
    const customers = await Customer.find().sort({ id: 1 });
    return res.status(200).json(customers);
  } catch (error) {
    return res.status(500).json({
      message: "Error retrieving customers",
      error: error.message,
    });
  }
});

/**
 * GET /api/customers/:id
 * Finds and returns a single customer by numeric id.
 */
router.get("/:id", async (req, res) => {
  const customerId = parseNumericId(req.params.id);
  if (customerId === null) {
    return res.status(400).json({ message: "Invalid customer id. Expected an integer." });
  }

  try {
    const customer = await Customer.findOne({ id: customerId });
    if (!customer) {
      return res.status(404).json({ message: `Customer with id=${customerId} not found.` });
    }
    return res.status(200).json(customer);
  } catch (error) {
    return res.status(500).json({
      message: "Error retrieving customer",
      error: error.message,
    });
  }
});

/**
 * POST /api/customers
 * Creates and persists a new customer document.
 */
router.post("/", async (req, res) => {
  try {
    const { id, name, email, cellphoneNumber } = req.body;
    const customer = new Customer({ id, name, email, cellphoneNumber });
    const savedCustomer = await customer.save();
    return res.status(201).json(savedCustomer);
  } catch (error) {
    if (error.code === 11000) {
      return res.status(400).json({
        message: `A customer with id=${req.body.id} already exists.`,
      });
    }
    if (error.name === "ValidationError" || error.name === "CastError") {
      return res.status(400).json({
        message: "Validation failed for customer payload",
        error: error.message,
      });
    }
    return res.status(500).json({
      message: "Error creating customer",
      error: error.message,
    });
  }
});

/**
 * PUT /api/customers/:id
 * Updates an existing customer by numeric id.
 */
router.put("/:id", async (req, res) => {
  const customerId = parseNumericId(req.params.id);
  if (customerId === null) {
    return res.status(400).json({ message: "Invalid customer id. Expected an integer." });
  }

  const allowedUpdates = {};
  if (req.body.name !== undefined) allowedUpdates.name = req.body.name;
  if (req.body.email !== undefined) allowedUpdates.email = req.body.email;
  if (req.body.cellphoneNumber !== undefined) {
    allowedUpdates.cellphoneNumber = req.body.cellphoneNumber;
  }

  if (Object.keys(allowedUpdates).length === 0) {
    return res.status(400).json({
      message: "No valid updatable fields provided (name, email, cellphoneNumber).",
    });
  }

  try {
    const updatedCustomer = await Customer.findOneAndUpdate(
      { id: customerId },
      { $set: allowedUpdates },
      { new: true, runValidators: true }
    );

    if (!updatedCustomer) {
      return res.status(404).json({ message: `Customer with id=${customerId} not found.` });
    }
    return res.status(200).json(updatedCustomer);
  } catch (error) {
    if (error.name === "ValidationError" || error.name === "CastError") {
      return res.status(400).json({
        message: "Validation failed for update payload",
        error: error.message,
      });
    }
    return res.status(500).json({
      message: "Error updating customer",
      error: error.message,
    });
  }
});

/**
 * DELETE /api/customers/:id
 * Deletes an existing customer by numeric id.
 */
router.delete("/:id", async (req, res) => {
  const customerId = parseNumericId(req.params.id);
  if (customerId === null) {
    return res.status(400).json({ message: "Invalid customer id. Expected an integer." });
  }

  try {
    const deletedCustomer = await Customer.findOneAndDelete({ id: customerId });
    if (!deletedCustomer) {
      return res.status(404).json({ message: `Customer with id=${customerId} not found.` });
    }
    return res.status(200).json({
      message: `Customer with id=${customerId} deleted successfully.`,
      deleted: deletedCustomer,
    });
  } catch (error) {
    return res.status(500).json({
      message: "Error deleting customer",
      error: error.message,
    });
  }
});

module.exports = router;
