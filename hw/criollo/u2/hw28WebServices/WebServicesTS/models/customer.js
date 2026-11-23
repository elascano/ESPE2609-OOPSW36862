import mongoose, { Schema } from "mongoose";
const customerSchema = new Schema({
    id: { type: Number, required: true, unique: true },
    name: { type: String, required: true },
    age: { type: Number, required: true },
    moneySpent: { type: Number, required: true },
}, { collection: "Customer" });
export const Customer = mongoose.model("Customer", customerSchema);
