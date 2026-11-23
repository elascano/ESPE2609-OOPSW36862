import { Router, Request, Response } from "express";
import * as customerService from "../services/customerService";

const router = Router();

router.get("/customers", async (req: Request, res: Response) => {
  try {
    const customers = await customerService.findAllCustomers();
    res.json(customers);
  } catch (err: any) {
    res.status(500).json({ message: err.message });
  }
});

router.get("/customers/analytics", async (req: Request, res: Response) => {
  try {
    const analytics = await customerService.getSpendingAnalytics();
    res.json(analytics);
  } catch (err: any) {
    res.status(500).json({ message: err.message });
  }
});

router.get("/customers/:id", async (req: Request, res: Response) => {
  try {
    const foundCustomer = await customerService.findCustomerById(
      Number(req.params.id),
    );
    if (!foundCustomer) {
      return res.status(404).json({ message: "Customer not found" });
    }
    res.json(foundCustomer);
  } catch (err: any) {
    res.status(500).json({ message: err.message });
  }
});

router.post("/customers", async (req: Request, res: Response) => {
  try {
    const savedCustomer = await customerService.createCustomer(req.body);
    res.status(201).json(savedCustomer);
  } catch (err: any) {
    res.status(400).json({ message: err.message });
  }
});

router.put("/customers/:id", async (req: Request, res: Response) => {
  try {
    const updatedCustomer = await customerService.updateCustomerById(
      Number(req.params.id),
      req.body,
    );
    if (!updatedCustomer) {
      return res.status(404).json({ message: "Customer not found" });
    }
    res.json(updatedCustomer);
  } catch (err: any) {
    res.status(400).json({ message: err.message });
  }
});

router.delete("/customers/:id", async (req: Request, res: Response) => {
  try {
    const deletedCustomer = await customerService.deleteCustomerById(
      Number(req.params.id),
    );
    if (!deletedCustomer) {
      return res.status(404).json({ message: "Customer not found" });
    }
    res.json({ message: "Customer deleted" });
  } catch (err: any) {
    res.status(500).json({ message: err.message });
  }
});

export default router;
