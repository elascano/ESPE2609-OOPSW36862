import { Router } from "express";
import * as customerService from "../services/customerService";
const router = Router();
router.get("/customers", async (req, res) => {
    try {
        const customers = await customerService.findAllCustomers();
        res.json(customers);
    }
    catch (err) {
        res.status(500).json({ message: err.message });
    }
});
router.get("/customers/analytics", async (req, res) => {
    try {
        const analytics = await customerService.getSpendingAnalytics();
        res.json(analytics);
    }
    catch (err) {
        res.status(500).json({ message: err.message });
    }
});
router.get("/customers/:id", async (req, res) => {
    try {
        const foundCustomer = await customerService.findCustomerById(Number(req.params.id));
        if (!foundCustomer) {
            return res.status(404).json({ message: "Customer not found" });
        }
        res.json(foundCustomer);
    }
    catch (err) {
        res.status(500).json({ message: err.message });
    }
});
router.post("/customers", async (req, res) => {
    try {
        const savedCustomer = await customerService.createCustomer(req.body);
        res.status(201).json(savedCustomer);
    }
    catch (err) {
        res.status(400).json({ message: err.message });
    }
});
router.put("/customers/:id", async (req, res) => {
    try {
        const updatedCustomer = await customerService.updateCustomerById(Number(req.params.id), req.body);
        if (!updatedCustomer) {
            return res.status(404).json({ message: "Customer not found" });
        }
        res.json(updatedCustomer);
    }
    catch (err) {
        res.status(400).json({ message: err.message });
    }
});
router.delete("/customers/:id", async (req, res) => {
    try {
        const deletedCustomer = await customerService.deleteCustomerById(Number(req.params.id));
        if (!deletedCustomer) {
            return res.status(404).json({ message: "Customer not found" });
        }
        res.json({ message: "Customer deleted" });
    }
    catch (err) {
        res.status(500).json({ message: err.message });
    }
});
export default router;
