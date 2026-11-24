import mongoose from "mongoose";

const customerSchema = new mongoose.Schema({
    id: Number,
    name: String,
    age: Number,
    moneySpent: Number
}, { collection: "Customer" });

export const Customer = mongoose.model("Customer", customerSchema);

