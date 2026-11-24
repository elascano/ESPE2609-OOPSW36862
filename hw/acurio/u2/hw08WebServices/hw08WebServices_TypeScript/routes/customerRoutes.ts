import { Router } from "express";
import { Customer } from "../models/customer";

const router = Router();

router.get("/customers", async (req, res) => {
    const customers = await Customer.find();
    res.json(customers);
});

router.get("/customer/:id", async (req, res) => {
    const id = Number(req.params.id);

    const customer = await Customer.findOne({ id: id });

    if (customer) {
        res.json(customer);
    } else {
        res.status(404).json({ message: "Customer not found" });
    }
});

export default router;
